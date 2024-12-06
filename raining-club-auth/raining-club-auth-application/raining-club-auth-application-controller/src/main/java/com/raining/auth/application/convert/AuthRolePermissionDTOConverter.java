package com.raining.auth.application.convert;

import com.raining.auth.application.dto.AuthRolePermissionDTO;
import com.raining.auth.domain.entity.AuthRolePermissionBO;

import java.util.ArrayList;

public class AuthRolePermissionDTOConverter {

    public static AuthRolePermissionBO convertDTOToBO(AuthRolePermissionDTO authRolePermissionDTO) {
        AuthRolePermissionBO authRolePermissionBO = new AuthRolePermissionBO();
        authRolePermissionBO.setId(authRolePermissionDTO.getId());
        authRolePermissionBO.setPermissionId(authRolePermissionDTO.getPermissionId());
        authRolePermissionBO.setRoleId(authRolePermissionDTO.getRoleId());
        if (authRolePermissionDTO.getPermissionIdList() != null && authRolePermissionDTO.getPermissionIdList().size() > 0) {
            ArrayList<Long> list = new ArrayList<>(authRolePermissionDTO.getPermissionIdList());
            authRolePermissionBO.setPermissionIdList(list);
        }
        return authRolePermissionBO;
    }

}
