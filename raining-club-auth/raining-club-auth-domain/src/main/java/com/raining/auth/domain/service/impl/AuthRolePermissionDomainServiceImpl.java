package com.raining.auth.domain.service.impl;

import com.raining.auth.common.enums.IsDeletedFlagEnum;
import com.raining.auth.domain.entity.AuthRolePermissionBO;
import com.raining.auth.domain.service.AuthRolePermissionDomainService;
import com.raining.auth.infra.basic.entity.AuthRolePermission;
import com.raining.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            rolePermissionList.add(authRolePermission);
        });
        int count = authRolePermissionService.batchInsert(rolePermissionList);
        return count > 0;
    }


}
