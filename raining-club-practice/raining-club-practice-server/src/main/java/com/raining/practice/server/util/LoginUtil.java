package com.raining.practice.server.util;

import com.raining.practice.server.config.context.LoginContextHolder;

/**
 * 用户登录util
 *
 * @author: raining
 * @date: 2023/11/26
 */
public class LoginUtil {

    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }


}
