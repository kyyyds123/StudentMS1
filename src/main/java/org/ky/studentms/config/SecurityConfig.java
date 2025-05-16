package org.ky.studentms.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                .logoutUrl("/logout")           // 退出请求路径
                .logoutSuccessUrl("/login?logout") // ✅ 关键修复：退出后跳转路径
                .and()
                .authorizeRequests()
                .antMatchers("/admin/​**​").authenticated() // 管理员路径需认证
                .anyRequest().permitAll()                 // 其他路径允许匿名访问
                .and()
                .formLogin()
                .loginPage("/login")                      // 自定义登录页路径
                .defaultSuccessUrl("/admin/dashboard")    // 登录成功后跳转
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")                     // 退出登录路径
                .logoutSuccessUrl("/login?logout")        // 退出后跳转路径
                .invalidateHttpSession(true)              // 销毁会话
                .deleteCookies("JSESSIONID");            // 删除Cookie
    }
}