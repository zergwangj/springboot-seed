package com.company.system.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.system.mapper.PermissionMapper;
import com.company.system.service.mapstruct.PermissionMapStruct;
import com.company.system.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "permission")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PermissionQueryService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionMapStruct permissionMapStruct;

    /**
     * 不分页
     */
    @Cacheable(key = "'queryAll:'+#p0")
    public List queryAll(String name){

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        List<Permission> listPermission = permissionMapper.selectList(queryWrapper);
        return permissionMapStruct.toDto(listPermission);
    }

}
