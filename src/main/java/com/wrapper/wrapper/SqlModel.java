package com.wrapper.wrapper;

import com.wrapper.constants.SqlWrapperConfig;
import com.wrapper.model.GroupByModel;
import com.wrapper.model.OrderByModel;

/**
 * @Author wangyu
 * @Create: 2019/9/26 2:44 下午
 * @Description:
 */
public interface SqlModel<R> {

    default OrderByModel<R> newOrderByModel(R column, String value){
        return new OrderByModel<R>(column,value);
    }

    default OrderByModel<R> newOrderByModel(R column){
        return new OrderByModel<R>(column, SqlWrapperConfig.Order.ASC);
    }

    default GroupByModel<R> newGroupByModel(R column){
        return new GroupByModel<>(column);
    }
}
