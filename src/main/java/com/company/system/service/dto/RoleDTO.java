package com.company.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.Set;

/**
 * @author jim
 * @date 2018-11-23
 */
@Data
public class RoleDTO implements Serializable {

    private Long id;

    private String name;

    private String remark;

    private Set<PermissionDTO> permissions;

    private Timestamp createTime;
}
