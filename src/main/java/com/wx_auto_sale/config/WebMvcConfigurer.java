package com.wx_auto_sale.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径  file:/root/java/public/images/
        registry.addResourceHandler("/images/**").addResourceLocations("file:/root/java/public/images/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new BaseInterceptor())
                .excludePathPatterns("/userInfo/**")
                .excludePathPatterns("/merchant/info")
                .excludePathPatterns("/goods/allInfo")
                .excludePathPatterns("/static/*")
                .excludePathPatterns("/images/*")
                .excludePathPatterns("/order/pageList")
        ;
        super.addInterceptors(registry);
    }
}
