package com.company.system.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.system.mapper.MenuMapper;
import com.company.system.service.mapstruct.MenuMapStruct;
import com.company.system.entity.Menu;
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
 * @date 2018-12-17
 */
@Service
@CacheConfig(cacheNames = "menu")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuQueryService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuMapStruct menuMapStruct;

    /**
     * 不分页
     */
    @Cacheable(key = "'queryAll:'+#p0")
    public List queryAll(String name){

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        List<Menu> listMenu = menuMapper.selectList(queryWrapper);
        return menuMapStruct.toDto(listMenu);
    }

}
