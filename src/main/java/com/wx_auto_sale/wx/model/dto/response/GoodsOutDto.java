package com.wx_auto_sale.wx.model.dto.response;

import com.wx_auto_sale.wx.model.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author wangyu
 * @Create: 2020/4/18 10:34 上午
 * @Description:
 */
@Data
public class GoodsOutDto extends BaseOutDto {

    private List<CategoryOutDto> categoryOutDtoList;

    private List<CommodityOutDto> commodityOutDtoList;

    private boolean isTest;

}

