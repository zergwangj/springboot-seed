package com.company.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.system.entity.Menu;
import com.company.system.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim wang
 * @since 2019-01-17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * findByName
     * @param name
     * @return
     */
    @Select("SELECT * FROM menu WHERE name = #{name}")
    Menu findByName(@Param("name") String name);

    /**
     * findByRoles
     * @param roleSet
     * @return
     */
    @Select({
            "<script>",
            "SELECT m.* FROM menus_roles mr LEFT JOIN menu m ON mr.menuId = m.id WHERE mr.roleId IN",
            "<foreach collection='roleSet' item='role' open='(' separator=',' close=')'>",
            "#{role.id}",
            "</foreach>",
            "ORDER BY m.sort",
            "</script>"
    })
    Set<Menu> findByRolesOrderBySort(@Param("roleSet") Set<Role> roleSet);

    /**
     * findByPid
     * @param pid
     * @return
     */
    @Select("SELECT * FROM menu WHERE pid = #{pid}")
    List<Menu> findByPid(@Param("pid") long pid);
}
