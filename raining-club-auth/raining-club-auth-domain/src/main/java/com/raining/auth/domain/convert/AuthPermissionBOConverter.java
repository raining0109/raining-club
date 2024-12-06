package com.raining.auth.domain.convert;

import com.raining.auth.domain.entity.AuthPermissionBO;
import com.raining.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限bo转换器
 * 
 * @author: raining
 * @date: 2023/10/8
 */
@Mapper
public interface AuthPermissionBOConverter {

    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);

    AuthPermission convertBOToEntity(AuthPermissionBO authPermissionBO);

}
