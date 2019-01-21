package com.company.quartz.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class QuartzJob implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("beanName")
    private String beanName;

    @TableField("cronExpression")
    private String cronExpression;

    private Boolean pause;

    @TableField("jobName")
    private String jobName;

    @TableField("methodName")
    private String methodName;

    private String params;

    private String remark;

    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;


}
