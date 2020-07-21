package com.wx_auto_sale.wx.model.api;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;

/**
 * @Author wangyu
 * @Create: 2020/4/10 8:50 下午
 * @Description: http://u.ifeige.cn/
 */
@Data
public class FeiGe {

    private String secret = "4bcec35b41582245602b6de9dfa1849f";

    private String app_key = "e05d96188a39752534ca7bf096f76c06";

    private String template_id = "UZvQwUiQeY-scmoMsi-IOeR_DNW6ZLs6xhnUUTWfjNw";

    private String url = "";

    private Map<String, JSONObject> data;//推送文字

}

