package com.dx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	protected void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		// TODO: HARD-CODED user & password, should be accessed from the database...
		// {noop} - NoOpPasswordEncoder (passwords stored in plain text)
	    auth.inMemoryAuthentication()
	        .withUser("admin").password("{noop}admin").roles("ADMIN")
	        .and()
	        .withUser("user").password("{noop}user").roles("USER");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/guest/**").permitAll().anyRequest().authenticated();
		http.httpBasic().authenticationEntryPoint(getBasicAuthEntryPoint());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
	}
}
