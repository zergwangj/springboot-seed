package com.company.tools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jim wang
 * @since 2019-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AlipayConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("appID")
    private String appID;

    private String charset;

    /**
     * 类型 固定格式json
     */
    private String format;

    @TableField("gatewayUrl")
    private String gatewayUrl;

    @TableField("notifyUrl")
    private String notifyUrl;

    @TableField("privateKey")
    private String privateKey;

    @TableField("publicKey")
    private String publicKey;

    @TableField("returnUrl")
    private String returnUrl;

    @TableField("signType")
    private String signType;

    @TableField("sysServiceProviderId")
    private String sysServiceProviderId;


}
