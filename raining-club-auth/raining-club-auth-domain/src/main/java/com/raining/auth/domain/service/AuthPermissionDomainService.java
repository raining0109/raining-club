package com.raining.auth.domain.service;

import com.raining.auth.domain.entity.AuthPermissionBO;

import java.util.List;

/**
 * 角色领域service
 * 
 * @author: raining
 * @date: 2023/11/1
 */
public interface AuthPermissionDomainService {

    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);

    List<String> getPermission(String userName);
}
