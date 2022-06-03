package com.sami.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomLoginSuccessHandler customLoginSuccessHandler;
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(this.getUserDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}

	//Configure Method
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider());
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/Library/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/Transport/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/examination/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/Inventory/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/Admission/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/Accounting/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/**").permitAll().and().formLogin().loginPage("/login")
			.loginProcessingUrl("/login")
			.successHandler(customLoginSuccessHandler)
			.and().csrf().disable();
	}
	
	
	
	
	

}
