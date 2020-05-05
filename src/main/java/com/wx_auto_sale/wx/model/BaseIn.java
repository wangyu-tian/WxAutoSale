package com.wx_auto_sale.wx.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author wangyu
 * @Create: 2020/4/10 12:51 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class BaseIn<T> {

    //用户ID
    private String uId;

    //商户ID
    private String mId;

    //微信用户ID
    private String wId;

    //当前页从0开始
    private Integer currentPage;
    //每页大小
    private Integer pageSize;

    private T data;

    //这里使用lombok生成的设置方法会出错，需要手动设置
    public void setuId(String uid) {
        this.uId = uid;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public void setwId(String wId) {
        this.wId = wId;
    }
}
