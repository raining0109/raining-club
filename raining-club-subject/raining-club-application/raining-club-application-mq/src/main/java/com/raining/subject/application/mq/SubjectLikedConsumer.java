package com.raining.subject.application.mq;

import com.alibaba.fastjson.JSON;
import com.raining.subject.domain.entity.SubjectLikedBO;
import com.raining.subject.domain.service.SubjectLikedDomainService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SubjectLikedConsumer {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    @RabbitListener(queues = "subject-liked")
    public void listenSimpleQueueMessage(String s) {
        System.out.println("接受点赞mq,消息为" + s);
        SubjectLikedBO subjectLikedBO = JSON.parseObject(s, SubjectLikedBO.class);
        subjectLikedDomainService.syncLikedByMsg(subjectLikedBO);
    }
}