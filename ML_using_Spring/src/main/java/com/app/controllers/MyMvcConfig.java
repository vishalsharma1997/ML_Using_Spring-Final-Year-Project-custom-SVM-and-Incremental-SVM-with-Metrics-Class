package com.app.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.app")
public class MyMvcConfig implements WebMvcConfigurer{

	@Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    
	       // Css resource.
	       registry.addResourceHandler("/styles/**") //
	                 .addResourceLocations("/WEB-INF/resources/").setCachePeriod(31556926);
	        
	   }
	 
	    
	   @Override
	   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	       configurer.enable();
	   }
	
}
