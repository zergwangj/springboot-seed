package com.company.system.service.mapstruct;

import com.company.common.mapper.EntityMapper;
import com.company.system.entity.Menu;
import com.company.system.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author jim
 * @date 2018-12-17
 */
@Mapper(componentModel = "spring", uses = {RoleMapStruct.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapStruct extends EntityMapper<MenuDTO, Menu> {

}
