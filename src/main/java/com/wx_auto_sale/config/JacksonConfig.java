package com.wx_auto_sale.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @Author wangyu
 * @Create: 2020/4/11 9:58 上午
 * @Description:
 */
@Component
public class JacksonConfig {
    @Bean
    public MappingJackson2HttpMessageConverter objectMapper(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new JsonObjectMapper());
        return converter;
    }

    class JsonObjectMapper extends ObjectMapper{
        public JsonObjectMapper(){
            super();
            super.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            super.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        }
    }
}
