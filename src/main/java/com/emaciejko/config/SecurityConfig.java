package com.emaciejko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * Setup in-memory users and their roles
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
	auth
	.inMemoryAuthentication()
		.withUser("eva").password("evAdmin79").roles("ADMIN");
    }

    // Configure our application's security policy
    @Override    
    protected void configure(HttpSecurity http) throws Exception {
	http
	   .authorizeRequests()
	   	.antMatchers("/admin/**").hasRole("ADMIN")
	   	.antMatchers("/**").permitAll()	   		   
	   	.and()	   	
	   .formLogin();
    }
    
    
}
