package com.company.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.system.entity.Permission;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * findByName
     * @param name
     * @return
     */
    @Select("SELECT * FROM permission WHERE name = #{name}")
    Permission findByName(@Param("name") String name);

    /**
     * findByRoles
     * @param roleSet
     * @return
     */
    @Select({
            "<script>",
                "SELECT p.* FROM roles_permissions rp LEFT JOIN permission p ON rp.permissionId = p.id WHERE rp.roleId IN",
                "<foreach collection='roleSet' item='role' open='(' separator=',' close=')'>",
                    "#{role.id}",
                "</foreach>",
            "</script>"
    })
    Set<Permission> findByRoles(@Param("roleSet") Set<Role> roleSet);

    /**
     * findByPid
     * @param pid
     * @return
     */
    @Select("SELECT * FROM permission WHERE pid = #{pid}")
    List<Permission> findByPid(@Param("pid") long pid);
}
