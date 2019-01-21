package com.company.system.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.system.mapper.RoleMapper;
import com.company.system.service.mapstruct.RoleMapStruct;
import com.company.common.utils.PageUtil;
import com.company.system.entity.Role;
import com.company.system.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.util.List;

/**
 * @author jim
 * @date 2018-12-03
 */
@Service
@CacheConfig(cacheNames = "role")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleQueryService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMapStruct roleMapStruct;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RoleDTO role, Pageable pageable){
//        Page<Role> page = roleRepository.findAll(new Spec(role),pageable);
//        return PageUtil.toPage(page.map(roleMapStruct::toDto));

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Role> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<Role>(pageable.getPageNumber(), pageable.getPageSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(role.getName())) {
            queryWrapper.like("name", role.getName());
        }
        roleMapper.selectPage(page, queryWrapper);

        Page<Role> pageRet = new PageImpl<>(
                page.getRecords(),
                pageable,
                page.getTotal());
        return PageUtil.toPage(pageRet.map(roleMapStruct::toDto));
    }

    /**
     * 不分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(RoleDTO role){

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(role.getName())) {
            queryWrapper.like("name", role.getName());
        }
        List<Role> listRole = roleMapper.selectList(queryWrapper);
        return roleMapStruct.toDto(listRole);
    }
}
