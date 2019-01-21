package com.company.system.service.mapstruct;

import com.company.common.mapper.EntityMapper;
import com.company.system.entity.Role;
import com.company.system.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jim
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", uses = {PermissionMapStruct.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapStruct extends EntityMapper<RoleDTO, Role> {

}
