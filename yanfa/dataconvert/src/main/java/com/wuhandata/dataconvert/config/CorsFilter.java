package com.wuhandata.dataconvert.config;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@WebFilter
@Order(Integer.MIN_VALUE)
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader("origin");
        if (!StringUtils.isBlank(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "86400");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Cache-Control, Authorization");
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            if ("OPTIONS".equals(request.getMethod())) {//这里通过判断请求的方法，判断此次是否是预检请求，如果是，立即返回一个204状态吗，标示，允许跨域；预检后，正式请求，这个方法参数就是我们设置的post了
                response.setStatus(HttpStatus.SC_NO_CONTENT); //HttpStatus.SC_NO_CONTENT = 204\
                response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, DELETE");//当判定为预检请求后，设定允许请求的方法
                response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Authorization"); //当判定为预检请求后，设定允许请求的头部类型
                response.addHeader("Access-Control-Max-Age", "86400");  // 预检有效保持时间
                return;
            }
        }

        chain.doFilter(req, res);
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }


    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
