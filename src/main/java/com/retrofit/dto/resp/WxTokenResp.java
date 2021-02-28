package com.retrofit.dto.resp;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author wangyu
 */
@Data
@Accessors(chain = true)
public class WxTokenResp extends BaseHttpResp {

    private String access_token;

    private int expires_in;

}
