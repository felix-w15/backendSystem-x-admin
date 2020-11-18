package com.myhome.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyWebMVCConfigurer extends WebMvcConfigurationSupport {

    @Bean
    public RoleInterceptor roInterceptor() {
        return new RoleInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/", "/dologin", "logout")
                .excludePathPatterns("/static/**");
        registry.addInterceptor(roInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/login", "/", "/dologin", "/logout",
                                     "/pwdChange", "/pwdChangePage","/toLoginPage",
                                     "/error");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
// classpath表示在resource目录下，/static/** 表示在URL路径中访问如
// http://localhost:8080/static/ 即可访问到resource下的static目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index.html").setViewName("login");
    }


}
