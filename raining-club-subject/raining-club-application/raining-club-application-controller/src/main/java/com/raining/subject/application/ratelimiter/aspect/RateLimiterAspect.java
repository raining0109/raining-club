package com.raining.subject.application.ratelimiter.aspect;

import cn.hutool.core.util.ArrayUtil;
import com.raining.subject.application.ratelimiter.annotation.LimitType;
import com.raining.subject.application.ratelimiter.annotation.RateLimiter;
import com.raining.subject.application.ratelimiter.exception.RateLimiterException;
import com.raining.subject.application.ratelimiter.util.MessageUtils;
import com.raining.subject.application.ratelimiter.util.RedissionUtils;
import com.raining.subject.application.ratelimiter.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RateType;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class RateLimiterAspect {

    /**
     * 定义spel表达式解析器
     */
    private final ExpressionParser parser = new SpelExpressionParser();
    /**
     * 定义spel解析模版
     */
    private final ParserContext parserContext = new TemplateParserContext();
    /**
     * 定义spel上下文对象进行解析
     */
    private final EvaluationContext context = new StandardEvaluationContext();
    /**
     * 方法参数解析器
     */
    private final ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();


    /**
     * GLOBAL_REDIS_KEY 和  RATE_LIMIT_KEY  最好还是定义在项目的一个统一的常量文件中，这里为了解剖出来的文件少一点
     *
     * */

    /**
     * 全局 redis key (业务无关的key)
     */
    private final String GLOBAL_REDIS_KEY = "global:";

    /**
     * 限流 redis key
     */
    private final String RATE_LIMIT_KEY = GLOBAL_REDIS_KEY + "rate_limit:";


    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
        // 获取注解传的 时间 次数
        int time = rateLimiter.time();
        int count = rateLimiter.count();
        // 处理 key
        String combineKey = getCombineKey(rateLimiter, point);
        try {
            RateType rateType = RateType.OVERALL;//全局
            if (rateLimiter.limitType() == LimitType.CLUSTER /*集群，也就是单个实例*/) {
                rateType = RateType.PER_CLIENT;
            }
            long number = RedissionUtils.rateLimiter(combineKey, rateType, count, time);
            if (number == -1) {
                String message = rateLimiter.message();
                if (StringUtils.startsWith(message, "{") && StringUtils.endsWith(message, "}")) {
                    message = MessageUtils.message(StringUtils.substring(message, 1, message.length() - 1));
                }
                throw new RateLimiterException(message);
            }
            log.info("限制令牌 => {}, 剩余令牌 => {}, 缓存key => '{}'", count, number, combineKey);
        } catch (Exception e) {
            if (e instanceof RateLimiterException) {
                throw e;
            } else {
                throw new RuntimeException("服务器限流异常，请稍候再试");
            }
        }
    }


    /**
     * 返回带有特定前缀的 key
     * @param rateLimiter 限流注解
     * @param point 切入点
     * @return key
     */
    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        String key = rateLimiter.key();
        // 获取方法(通过方法签名来获取)
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        // 判断是否是spel格式
        if (StringUtils.containsAny(key, "#")) {
            // 获取参数值
            Object[] args = point.getArgs();
            // 获取方法上参数的名称
            String[] parameterNames = pnd.getParameterNames(method);
            if (ArrayUtil.isEmpty(parameterNames)) {
                throw new RateLimiterException("限流key解析异常!请联系管理员!");
            }
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
            // 解析返回给key
            try {
                Expression expression;
                if (StringUtils.startsWith(key, parserContext.getExpressionPrefix())
                        && StringUtils.endsWith(key, parserContext.getExpressionSuffix())) {
                    expression = parser.parseExpression(key, parserContext);
                } else {
                    expression = parser.parseExpression(key);
                }
                key = expression.getValue(context, String.class) + ":";
            } catch (Exception e) {
                throw new RateLimiterException("限流key解析异常!请联系管理员!");
            }
        }
        // 限流前缀key
        StringBuilder stringBuffer = new StringBuilder(RATE_LIMIT_KEY);
        stringBuffer.append(ServletUtils.getRequest().getRequestURI()).append(":");
        // 判断限流类型
        if (rateLimiter.limitType() == LimitType.IP) {
            // 获取请求ip
            stringBuffer.append(ServletUtils.getClientIP()).append(":");
        } else if (rateLimiter.limitType() == LimitType.CLUSTER) {
            // 获取客户端实例id
            stringBuffer.append(RedissionUtils.getClient().getId()).append(":");
        }
        return stringBuffer.append(key).toString();
    }
}

