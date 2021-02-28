package com.wx_auto_sale.wx.controller;

import com.alibaba.fastjson.JSONObject;
import com.retrofit.JyApi;
import com.retrofit.JyApiTest;
import com.wx_auto_sale.utils.DateUtil;
import com.wx_auto_sale.wx.model.BaseIn;
import com.wx_auto_sale.wx.model.BaseOut;
import com.wx_auto_sale.wx.model.dto.response.GoodsOutDto;
import com.wx_auto_sale.wx.service.GoodsService;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import javax.annotation.Resource;

/**
 * @Author wangyu
 * @Create: 2020/4/18 10:28 上午
 * @Description: 商品信息
 */
@RestController
@RequestMapping("goods")
public class GoodsController2 {

    @Autowired
    private GoodsService goodsService;

    @Resource
    private JyApiTest jyApiTest;

    /**
     * 查询用户下所有商品和分类信息
     *
     * @param baseIn
     * @return
     */
    @PostMapping(value = "/allInfo123")
    public BaseOut allInfo(@RequestBody BaseIn baseIn) {

        GoodsOutDto goodsOutDto = goodsService.allInfo(baseIn.getUId(), baseIn.getMId());
        String json = "{\n" +
                "    \"app_key\": \"3cfcfef961953a6a5cf23a738c4365ef\",\n" +
                "    \"data\": {\n" +
                "        \"remark\": {\n" +
                "            \"value\": \"张三:¥ 61.80\"\n" +
                "        },\n" +
                "        \"first\": {\n" +
                "            \"value\": \"张三:¥ 61.80\"\n" +
                "        },\n" +
                "        \"keyword1\": {\n" +
                "            \"value\": \"订单编号\"\n" +
                "        },\n" +
                "        \"keyword2\": {\n" +
                "            \"value\": \"订单详情\"\n" +
                "        },\n" +
                "        \"keyword3\": {\n" +
                "            \"value\": \"订单金额\"\n" +
                "        },\n" +
                "        \"keyword4\": {\n" +
                "            \"value\": \"订单时间\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"template_id\": \"UvUc681NLbnE_r5dX1NJ_GxWdnIFKzUhkeLGUHhICFE\",\n" +
                "    \"secret\": \"de16cfb1467aca64c2688b3ded806145\",\n" +
                "    \"url\": \"http://www.baidu.com\"\n" +
                "}";
        String json2 = "{\n" +
                "\t\"mId\":\"42c9a7b15e8af46500545d8a2492a02e\",\n" +
                "\t\"uId\":\"1212\",\n" +
                "\t\"data\":{\n" +
                "\t\t\"wxCode\":\"021QIxwy0FjILb1Kseyy0PNzwy0QIxwr\"\n" +
                "\t}\n" +
                "}";
        Response<ResponseBody> result = jyApiTest.groupSend(JSONObject.parseObject(json));
        System.out.println(result.body());

        return new BaseOut();
    }
}
