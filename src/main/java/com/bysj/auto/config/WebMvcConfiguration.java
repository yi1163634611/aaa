package com.bysj.auto.config;

import com.bysj.auto.interceptor.UserContextInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public UserContextInterceptor userContextInterceptor() {
        return new UserContextInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userContextInterceptor())
                //拦截的路径
                .addPathPatterns("/userInfo")
                //不拦截的路径
//                .excludePathPatterns("/auth")
//                .excludePathPatterns("/user")
//                .excludePathPatterns("/**")
        ;
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }


    /**
     * 配置资源访问路径
     **/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:E:/lixiaoyi/img/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
    }
}
