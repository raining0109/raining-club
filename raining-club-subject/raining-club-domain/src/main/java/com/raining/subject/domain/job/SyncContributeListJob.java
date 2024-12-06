package com.raining.subject.domain.job;

import com.raining.subject.domain.entity.SubjectContributeRankBO;
import com.raining.subject.domain.redis.RedisUtil;
import com.raining.subject.domain.service.SubjectInfoDomainService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 全量刷新排行榜
 */
@Component
@Slf4j
public class SyncContributeListJob {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @Resource
    private RedisUtil redisUtil;

    private static final String RANK_KEY = "subject_rank";

    @XxlJob("asyncFlushContributeList")
    public void asyncFlushContributeList() {
        //先删除
        redisUtil.del(RANK_KEY);
        //先从数据库中查询贡献榜
        List<SubjectContributeRankBO> subjectInfoList = subjectInfoDomainService.getContributeCount();
        if (CollectionUtils.isEmpty(subjectInfoList)) {
            return;
        }
        //全量更新
        for (SubjectContributeRankBO rankBO : subjectInfoList) {
            if (rankBO.getCreatedBy() != null && rankBO.getSubjectCount() != null) {
                redisUtil.addScore(RANK_KEY, rankBO.getCreatedBy(), rankBO.getSubjectCount());
            }
        }
        if (log.isInfoEnabled()) {
            log.info("SyncContributeListJob.asyncFlushContributeList:{}", subjectInfoList);
        }
    }
}
