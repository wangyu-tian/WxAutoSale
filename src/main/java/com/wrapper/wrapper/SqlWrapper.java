package com.wrapper.wrapper;

/**
 * Created by wangyu
 * Date: 2019/9/5
 * Time: 10:41 AM
 * Description:
 */
public class SqlWrapper<T> extends AbstractSqlWrapper<T,SqlWrapper<T>>{

    public SqlWrapper(Class<T> entityClass){
        super(entityClass);
    }
}
