package com.company.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.system.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim wang
 * @since 2019-01-17
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * findByName
     * @param name
     * @return
     */
    @Select("SELECT * FROM role WHERE name = #{name}")
    Role findByName(@Param("name") String name);

    /**
     * findByUserId
     * @param userId
     * @return
     */
    @Select("SELECT r.* FROM users_roles ur LEFT JOIN role r ON ur.roleId = r.id WHERE ur.userId = #{userId}")
    Set<Role> findByUserId(@Param("userId") long userId);
}
