package com.company.system.service.mapstruct;

import com.company.common.mapper.EntityMapper;
import com.company.system.entity.User;
import com.company.system.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jim
 * @date 2018-11-23
 */
@Mapper(componentModel = "spring", uses = {RoleMapStruct.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapStruct extends EntityMapper<UserDTO, User> {

}
