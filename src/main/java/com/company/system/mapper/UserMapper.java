package com.company.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.system.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim wang
 * @since 2019-01-17
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * findByUsername
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE user.username = #{username}")
    @Results({
            @Result(property = "roles", column = "id",
                    many = @Many(select = "com.company.system.mapper.RoleMapper.findByUserId"))
    })
    User findByUsername(@Param("username") String username);

    /**
     * findByEmail
     * @param email
     * @return
     */
    @Select("SELECT * FROM user WHERE user.email = #{email}")
    @Results({
            @Result(property = "roles", column = "id",
                    many = @Many(select = "com.company.system.mapper.RoleMapper.findByUserId"))
    })
    User findByEmail(@Param("email") String email);

    /**
     * 修改密码
     * @param id
     * @param pass
     */
    @Update("UPDATE user SET password = #{pass} WHERE id = #{id}")
    void updatePass(@Param("id") Long id, @Param("pass") String pass);

    /**
     * 修改头像
     * @param id
     * @param url
     */
    @Update("UPDATE user SET avatar = #{url} WHERE id = #[id}")
    void updateAvatar(@Param("id") Long id, @Param("url") String url);

    /**
     * 修改邮箱
     * @param id
     * @param email
     */
    @Update("UPDATE user SET email = #{email} WHERE id = #{id}")
    void updateEmail(@Param("id") Long id, @Param("email") String email);
}
