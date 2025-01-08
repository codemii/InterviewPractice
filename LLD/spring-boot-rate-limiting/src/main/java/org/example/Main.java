package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public FilterRegistrationBean<RateLimitingFilter> rateLimitingFilter() {
//        FilterRegistrationBean<RateLimitingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new RateLimitingFilter());
//        registrationBean.addUrlPatterns("/api/*"); // Register filter for API endpoints
//        return registrationBean;
//    }
}