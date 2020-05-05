package com.wrapper.wrapper;

import com.wrapper.model.GroupByModel;
import com.wrapper.model.OrderByModel;

import java.util.function.Function;

/**
 * Created by wangyu
 * Date: 2019/9/5
 * Time: 10:41 AM
 * Description:
 */
public interface SqlCompare<Children, R> {

    Children groupBy(GroupByModel... groupByModels);

    Children orderBy(OrderByModel... orderByModels);

    Children and();
    Children and(Function<Children, Children> function);

    Children or();
    Children or(Function<Children, Children> function);

    default Children in(R column, Object value){
        return in(column,value,false);
    }

    default Children notIn(R column, Object value){
        return notIn(column,value,false);
    }

    Children notIn(R column, Object value, boolean b);

    default Children like(R column, Object value){
        return like(column,value,false);
    }

    Children like(R column, Object value, boolean b);

    default Children eq(R column, Object value){
        return eq(column,value,false);
    }

    Children eq(R column, Object value, boolean b);

    default Children ne(R column, Object value){
        return ne(column,value,false);
    }

    Children ne(R column, Object value, boolean b);

    default Children gt(R column, Object value){
        return gt(column,value,false);
    }

    Children gt(R column, Object value, boolean b);

    default Children ge(R column, Object value){
        return ge(column,value,false);
    }

    Children ge(R column, Object value, boolean b);

    default Children lt(R column, Object value){
        return lt(column,value,false);
    }

    Children lt(R column, Object value, boolean b);

    default Children le(R column, Object value){
        return le(column,value,false);
    }

    Children le(R column, Object value, boolean b);

    Children isNull(R column);

    Children isNotNull(R column);

    Children in(R column, Object value, boolean b);

    default String getHql(){return "";}

    default String getSql(){return "";}
}
