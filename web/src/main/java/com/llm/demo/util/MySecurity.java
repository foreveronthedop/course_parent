package com.llm.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
@Configuration
public class MySecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsImpl userDetailsImpl;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//把安全框架的页面替换为自己的页面
		http.formLogin().loginPage("/login.html")
		//提交的后台方法
		.loginProcessingUrl("/login")
		//设置验证的账号密码字段
		.usernameParameter("name").passwordParameter("password")
		//验证成功
		.successForwardUrl("/login")
		//失败去的方法
		.failureForwardUrl("/errorlogin")
		.permitAll();
		//放行的地址 默认从static下
		http.authorizeRequests().antMatchers("/*","/login","/buy").permitAll();
		//拦截所有
		http.authorizeRequests().antMatchers("/**").fullyAuthenticated();
		http.csrf().disable();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsImpl).passwordEncoder(new PasswordEncoder() {
			//密码加密
			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}
			//密码解密
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return encodedPassword.equals(rawPassword.toString());
			}
			
			
		});
	}
	
}
