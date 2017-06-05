package com.chn.modules.sys.bean;

import com.chn.common.persistence.BaseEntity;
import lombok.Data;

/**
 * 角色Entity
 *
 * @version 2013-12-05
 */
@Data
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 角色名称
     */
    private String name;

    /**
     * 权限类型
     */
    private String roleType;

    /**
     * 数据范围
     */
    private String dataScope;

}
