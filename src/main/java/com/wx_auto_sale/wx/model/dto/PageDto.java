package com.wx_auto_sale.wx.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author wangyu
 * @Create: 2020/5/5 1:08 上午
 * @Description:
 */
@AllArgsConstructor
@Data
public class PageDto<T> {

    private long totalElements;

    private long totalPages;

    private T data;

}
