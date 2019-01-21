package com.company.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.system.entity.VerificationCode;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jim wang
 * @since 2019-01-17
 */
public interface VerificationCodeMapper extends BaseMapper<VerificationCode> {

    /**
     * 获取有效的验证码
     * @param scenes 业务场景，如重置密码，重置邮箱等等
     * @param type
     * @param value
     * @return
     */
    @Select("SELECT * FROM verification_code WHERE scenes = #{scenes} AND " +
            "type = #{type} AND value = #{value} AND status = 1")
    VerificationCode findByScenesAndTypeAndValueAndStatusIsTrue(String scenes, String type, String value);
}
