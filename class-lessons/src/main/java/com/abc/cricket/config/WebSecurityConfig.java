/**
 * 
 */
package com.abc.cricket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Soham.Chakravarti
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(
	        		"/**",
	        		"/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**");
	    }

	}