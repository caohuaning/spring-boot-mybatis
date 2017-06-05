/**
 *
 */
package com.chn.common.persistence;

import lombok.Data;

/**
 *
 */
@Data
public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 当前每页显示数量
     */
    private int pageSize = 10;
    /**
     * 排序
     */
    private String orderBy;

}
