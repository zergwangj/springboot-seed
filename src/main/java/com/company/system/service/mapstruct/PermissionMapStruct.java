package com.company.system.service.mapstruct;

import com.company.common.mapper.EntityMapper;
import com.company.system.entity.Permission;
import com.company.system.service.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jim
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapStruct extends EntityMapper<PermissionDTO, Permission> {

}
