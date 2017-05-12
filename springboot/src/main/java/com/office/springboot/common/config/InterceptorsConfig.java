package com.office.springboot.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.office.springboot.common.interceptor.ExceptionInterfaceIntercept;
import com.office.springboot.common.interceptor.LoginInterfaceInterceptor;

@SuppressWarnings("deprecation")
@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter{

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterfaceInterceptor());
		registry.addInterceptor(new ExceptionInterfaceIntercept());
		super.addInterceptors(registry);
	}
}
