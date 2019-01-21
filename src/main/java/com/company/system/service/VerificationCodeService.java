package com.company.system.service;

import com.company.tools.entity.vo.EmailVo;
import com.company.system.entity.VerificationCode;

/**
 * @author jim
 * @date 2018-12-26
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(VerificationCode code);

    /**
     * 验证
     * @param code
     */
    void validated(VerificationCode code);
}
