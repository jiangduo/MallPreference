package com.totoro.mall.manager.config;

import com.totoro.mall.manager.interceptor.LoginAuthInterceptor;
import com.totoro.mall.manager.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * @author Totoro
 * @create 17 22:53
 * @Description: lei
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 配置跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }

    /**
     * 拦截器注册
     */
    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;
    @Autowired
    private UserProperties userProperties;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                //问题：如果需要配置的路径太多,不适合在类里面配置，放到配置类中
                // .excludePathPatterns("/admin/system/index/login" , "/admin/system/index/generateValidateCode")
                .excludePathPatterns(userProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }
}
