package com.chn.common.persistence;

import com.chn.modules.sys.bean.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.UUID;

/**
 * 数据Entity类
 */
@Data
public abstract class BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标记（0：正常；1：删除；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";

    /**
     * 表ID
     */
    protected String id;
    /**
     * 创建者
     */
    protected Long createBy;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createDate;

    /**
     * 更新者
     */
    protected Long updateBy;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateDate;

    /**
     * 删除标记（0：正常；1：删除；）
     */
    @Length(max = 1, min = 1)
    protected String delFlag = DEL_FLAG_NORMAL;

    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        this.setId(UUID.randomUUID().toString());
        User user = new User();
        if (StringUtils.isNotBlank(user.getId())) {
            this.updateBy = 1L;
            this.createBy = 1L;
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {
        User user = new User();
        if (StringUtils.isNotBlank(user.getId())) {
            this.updateBy = 1L;
        }
        this.updateDate = new Date();
    }

}
