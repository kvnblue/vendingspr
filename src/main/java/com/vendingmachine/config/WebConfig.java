package com.vendingmachine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.vendingmachine.constants.VmApiRestEndpoints;

/**
 * Title : WebConfig
 * 
 * Description : Class to define Global CORS Configuration to authorize cross
 * domain requests
 * 
 * @author Kalai
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private static final String LOCAL_HOST = "http://localhost:4200";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#
	 * addCorsMappings(org.springframework.web.servlet.config.annotation.
	 * CorsRegistry)
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping(VmApiRestEndpoints.VM_API + VmApiRestEndpoints.PRODUCT_CATEGORIES)
				.allowedOrigins(LOCAL_HOST).allowedMethods("GET");
		registry.addMapping(VmApiRestEndpoints.VM_API + VmApiRestEndpoints.PRODUCT_PURCHASE_REQUEST)
				.allowedOrigins(LOCAL_HOST);
	}
}
