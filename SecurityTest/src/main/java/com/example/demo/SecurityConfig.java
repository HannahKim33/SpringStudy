package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.authorizeHttpRequests().mvcMatchers("/","/all/**","/signUp").permitAll()
		.mvcMatchers("/admin/**").hasRole("admin")
		.anyRequest().authenticated();
		
		http.formLogin().loginPage("/login").permitAll()
		//모든 사용자에게 로그인 페이지에 접근 허용
		//로그인 페이지 주소는 "/login"
		.defaultSuccessUrl("/service1");
		//로그인 성공하면 service1로 보내기
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)	//세션 파괴
		.logoutSuccessUrl("/login"); //로그아웃 성공하면 보낼 페이지:login
		
		http.httpBasic(); //나머지는 http 기본 설정을 따름
	}

}
