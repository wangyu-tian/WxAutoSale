package com.wx_auto_sale.config;

import com.wrapper.util.StringUtils;
import com.wx_auto_sale.utils.PermissionUtil;
import com.wx_auto_sale.utils.StreamUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@WebFilter(filterName = "dataFilter", urlPatterns = {"/*"})
@Slf4j
public class DataFilter implements Filter {

    private final int maxLength = 2048;

    private final String SESSION_TOKEN_KEY = "threadId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // 记录线程号
        String token = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        MDC. put(SESSION_TOKEN_KEY, token);
        try {
            ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);
            //请求参数打印
            RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
            String body = requestWrapper.getBody();
            body = body.length() > maxLength ? body.substring(0,maxLength):body;
            String url = httpServletRequest.getRequestURI();
            String ip = httpServletRequest.getRemoteAddr();
            String method = httpServletRequest.getMethod();
            log.info("请求ip：{},请求地址：{},请求方式：{},请求参数：{}",ip,url,method,body);
            if("POST".equals(method.toUpperCase())) {
                filterChain.doFilter(requestWrapper, responseWrapper);
                //响应参数打印
                log.info("响应参数:{}", responseWrapper.getBody());
                responseWrapper.flushResponse();
                MDC.remove(SESSION_TOKEN_KEY);
            }else{
                filterChain.doFilter(requestWrapper,servletResponse);
            }
        } catch (Exception e) {
            log.error("doFilter is error:", e);
        }
    }
}

/**
 * httpRequest处理
 */
class RequestWrapper extends HttpServletRequestWrapper {
    private  String body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            body = StreamUtil.InputStreamToString(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {
            }
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
}

class ResponseWrapper extends HttpServletResponseWrapper{
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private HttpServletResponse response;
    private  String body;
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    public String getBody() {
        if(StringUtils.isEmpty(body)){
            this.body = byteArrayOutputStream.toString();
        }
        return Optional.ofNullable(this.body).orElse("");
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return outputStream(byteArrayOutputStream);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8));
    }

    public ServletOutputStream outputStream(ByteArrayOutputStream byteArrayOutputStream){
        return new LogServletOutputStream(byteArrayOutputStream);
    }

    public void flushResponse() throws IOException {
        response.getOutputStream().write(getBody().getBytes());
    }

    private static class LogServletOutputStream extends ServletOutputStream{
        private ByteArrayOutputStream outputStream;

        public LogServletOutputStream(ByteArrayOutputStream outputStream){
            this.outputStream = outputStream;
        }
        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public void write(int i) throws IOException {
            outputStream.write(i);
        }
    }
}