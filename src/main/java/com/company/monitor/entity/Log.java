package com.company.monitor.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

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
@NoArgsConstructor
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Timestamp createTime;

    private String description;

    @TableField("exceptionDetail")
    private String exceptionDetail;

    @TableField("logType")
    private String logType;

    private String method;

    private String params;

    @TableField("requestIp")
    private String requestIp;

    private Long time;

    private String username;

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}
