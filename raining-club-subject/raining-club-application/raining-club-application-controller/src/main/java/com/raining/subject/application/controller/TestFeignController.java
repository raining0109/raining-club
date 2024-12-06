package com.raining.subject.application.controller;

import com.raining.subject.infra.entity.UserInfo;
import com.raining.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 刷题分类controller
 *
 * @author: raining
 * @date: 2023/10/1
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class TestFeignController {

    @Resource
    private UserRpc userRpc;

    @GetMapping("testFeign")
    public void testFeign() {
        UserInfo userInfo = userRpc.getUserInfo("raining");
        log.info("testFeign.userInfo:{}", userInfo);
    }

}
