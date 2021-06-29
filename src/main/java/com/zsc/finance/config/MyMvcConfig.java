package com.zsc.finance.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 扩展SpringMVC的功能
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override //这里配置静态资源访问路径
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //springboot默认静态资源访问路径就是以下的路径 所以不必再设置 但是若配置有拦截器 则需要在拦截器里面放行静态资源
        //若静态资源路径不是在springboot规定的静态资源文件夹下面，则需要在下面添加映射路径
        //springboot规定的静态资源文件夹
        //1. "classpath:/META-INF/resources/",
        //2. "classpath:/resources/",
        //3. "classpath:/static/",
        //4. "classpath:/public/"
        //5. "/"：当前项目的根路径
        //registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        //registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

    //无业务逻辑跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //项目运行后直接访问或访问index.html（不存在index.html页面）界面跳转到login登录界面
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
    }
}
