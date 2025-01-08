package org.example.filter;

import org.example.config.AppConfig;
import org.example.model.RateLimiterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RateLimitingFilter implements Filter {
    @Autowired
    AppConfig appConfig;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String clientIpAddress = httpServletRequest.getRemoteAddr();

        String apiName = httpServletRequest.getHeader("appId");
        int allowedTPS = appConfig.getAllowedTpsPerApi().get(apiName);

        String rateLimiterName = httpServletRequest.getHeader("rateLimiterName");
        Long rateLimiterWindowTime = Long.valueOf(httpServletRequest.getHeader("rateLimiterWindowTime"));

        RateLimiterManager rateLimiterManager = RateLimiterManager.getInstance(rateLimiterName, rateLimiterWindowTime);
        if(!rateLimiterManager.isAllowRequest(rateLimiterName, allowedTPS, apiName)) {
            httpServletResponse.setStatus(429);
            httpServletResponse.getWriter().write("Too many requests. Please try again later.");
            return;
        }

        // Allow the request to proceed
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //do nothing for now
    }

    @Override
    public void destroy() {
        //do nothing for now
    }
}
