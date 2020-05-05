package com.wx_auto_sale.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author wangyu
 * @Create: 2020/4/10 3:29 下午
 * @Description:
 */
@Slf4j
public class RestTemplateConfig {

    /**
     * 启动连接
     * @return
     */
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder, boolean isProxy) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = null;
        HttpClientBuilder builder = HttpClientBuilder.create();
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();
            builder.setSSLContext(sslContext);
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,hostnameVerifier);
            //注册http和https请求
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https",sslConnectionSocketFactory).build();
            //设置连接池
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            //设置最大连接数
            poolingHttpClientConnectionManager.setMaxTotal(2000);
            //同路由并发数
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
            builder.setConnectionManager(poolingHttpClientConnectionManager);
            //重试次数
            builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0,false));
            //设置代理服务器
            if(isProxy){
//                HttpHost httpHost = new HttpHost("host",port);
//                builder.setProxy(httpHost);
            }

            HttpClient httpClient = builder.build();
            //httpclient连接配置
            factory = new HttpComponentsClientHttpRequestFactory(httpClient);
            //连接超时
            factory.setConnectTimeout(10000);
            //数据读取超时时间
            factory.setReadTimeout(10000);
            //连接不够用时等待时间
            factory.setConnectionRequestTimeout(3000);
            restTemplate.setRequestFactory(factory);
            restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        } catch (Exception e) {
            log.error("初始化连接异常",e);
        }

        return restTemplate;
    }

}
