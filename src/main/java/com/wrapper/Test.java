package com.wrapper;


import com.wrapper.constants.SqlWrapperConfig;
import com.wrapper.wrapper.SqlWrapper;

/**
 * Created by wangyu
 * Date: 2019/9/5
 * Time: 10:39 AM
 * Description:
 */
public class Test {
//    public static void main(String[] args){
//
//        SqlWrapper<EntityModel> sqlWrapper = new SqlWrapper<>(EntityModel.class);
//        sqlWrapper.eq(EntityModel::getId,12)
//                .le(EntityModel::getName,"zhangsan")
//                .and(wrapper->wrapper.ge(EntityModel::getName,"169219").eq(EntityModel::getId,12))
//                .or(wrapper->wrapper.le(EntityModel::getName,"name").or().eq(EntityModel::getId,88))
//                .orderBy(sqlWrapper.newOrderByModel(EntityModel::getId),
//                        sqlWrapper.newOrderByModel(EntityModel::getName, SqlWrapperConfig.Order.DESC));
//        ;
//        System.out.println(sqlWrapper.getHql());
//    }
}
