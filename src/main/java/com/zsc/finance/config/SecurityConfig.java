package com.zsc.finance.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zsc.finance.common.UserRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

@EnableWebSecurity  // 开启MVC security安全支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    //重写configure(AuthenticationManagerBuilder auth)方法，进行自定义用户认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    }

    //重写configure(HttpSecurity http)方法，进行用户授权管理
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1、自定义用户访问控制
        http.authorizeRequests()
                .antMatchers("/bootstrap/**", "images/**", "/js/**", "/lyear/**").permitAll()
                .antMatchers("/asserts/**", "/error/404", "/error/500").permitAll()
                .antMatchers("/register.html").permitAll()
//                .anyRequest().authenticated()
                .and().csrf().disable();//CSRF跨站请求伪造直接关闭
        // 2、自定义用户登录控制
        http.formLogin()
                .loginPage("/login").permitAll();
        // 4、自定义用户退出控制
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
