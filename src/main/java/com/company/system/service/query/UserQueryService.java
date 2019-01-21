package com.company.system.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.system.mapper.UserMapper;
import com.company.system.service.mapstruct.UserMapStruct;
import com.company.common.utils.PageUtil;
import com.company.system.entity.User;
import com.company.system.service.dto.UserDTO;
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
 * @date 2018-11-22
 */
@Service
@CacheConfig(cacheNames = "user")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserQueryService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapStruct userMapStruct;

    /**
     * 分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UserDTO user, Pageable pageable){

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<User>(pageable.getPageNumber(), pageable.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(user.getId())) {
            queryWrapper.eq("id", user.getId());
        }
        if(!ObjectUtils.isEmpty(user.getEnabled())) {
            queryWrapper.eq("enabled", user.getEnabled() ? "1" : "0");
        }
        if(!ObjectUtils.isEmpty(user.getUsername())) {
            queryWrapper.like("username", user.getUsername());
        }
        if(!ObjectUtils.isEmpty(user.getEmail())) {
            queryWrapper.like("email", user.getEmail());
        }
        userMapper.selectPage(page, queryWrapper);

        Page<User> pageRet = new PageImpl<>(
                page.getRecords(),
                pageable,
                page.getTotal());
        return PageUtil.toPage(pageRet.map(userMapStruct::toDto));
    }

    /**
     * 不分页
     */
    @Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(UserDTO user){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(user.getId())) {
            queryWrapper.eq("id", user.getId());
        }
        if(!ObjectUtils.isEmpty(user.getEnabled())) {
            queryWrapper.eq("enabled", user.getEnabled() ? "1" : "0");
        }
        if(!ObjectUtils.isEmpty(user.getUsername())) {
            queryWrapper.like("username", user.getUsername());
        }
        if(!ObjectUtils.isEmpty(user.getEmail())) {
            queryWrapper.like("email", user.getEmail());
        }
        List<User> listUser = userMapper.selectList(queryWrapper);
        return userMapStruct.toDto(listUser);
    }

}
