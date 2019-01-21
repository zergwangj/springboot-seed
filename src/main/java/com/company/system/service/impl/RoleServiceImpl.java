package com.company.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.system.mapper.RoleMapper;
import com.company.system.service.dto.RoleDTO;
import com.company.system.service.mapstruct.RoleMapStruct;
import com.company.common.exception.BadRequestException;
import com.company.common.exception.EntityExistException;
import com.company.common.utils.ValidationUtil;
import com.company.system.entity.Role;
import com.company.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author jim
 * @date 2018-12-03
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMapStruct roleMapStruct;

    @Override
    public RoleDTO findById(long id) {
        Optional<Role> role = Optional.ofNullable(roleMapper.selectById(id));
        ValidationUtil.isNull(role,"Role","id", id);
        return roleMapStruct.toDto(role.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleDTO create(Role resources) {
        if(roleMapper.findByName(resources.getName()) != null){
            throw new EntityExistException(Role.class,"username",resources.getName());
        }
        roleMapper.insert(resources);
        return roleMapStruct.toDto(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role resources) {
        Optional<Role> optionalRole = Optional.ofNullable(roleMapper.selectById(resources.getId()));
        ValidationUtil.isNull(optionalRole,"Role","id",resources.getId());

        Role role = optionalRole.get();

        /**
         * 根据实际需求修改
         */
        if(role.getId().equals(1L)){
            throw new BadRequestException("该角色不能被修改");
        }

        Role role1 = roleMapper.findByName(resources.getName());

        if(role1 != null && !role1.getId().equals(role.getId())){
            throw new EntityExistException(Role.class,"username",resources.getName());
        }

        role.setName(resources.getName());
        role.setRemark(resources.getRemark());
        role.setPermissions(resources.getPermissions());
        roleMapper.updateById(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {

        /**
         * 根据实际需求修改
         */
        if(id.equals(1L)){
            throw new BadRequestException("该角色不能被删除");
        }
        roleMapper.deleteById(id);
    }

    @Override
    public Object getRoleTree() {

        List<Role> roleList = roleMapper.selectList(new QueryWrapper<>());

        List<Map<String, Object>> list = new ArrayList<>();
        for (Role role : roleList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",role.getId());
            map.put("label",role.getName());
            list.add(map);
        }
        return list;
    }
}
