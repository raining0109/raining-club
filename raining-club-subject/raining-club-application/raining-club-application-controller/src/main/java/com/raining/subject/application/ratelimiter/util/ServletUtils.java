package com.raining.subject.application.ratelimiter.util;


import cn.hutool.extra.servlet.ServletUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServletUtils extends ServletUtil {

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) getRequestAttributes();
            return requestAttributes.getRequest();
        } catch (Exception e) {
            return null;
        }
    }
    
     public static String getClientIP() {
        return ServletUtil.getClientIP(getRequest());
    }
}
