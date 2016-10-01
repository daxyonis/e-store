package com.emaciejko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
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
		.withUser("user").password("user").roles("USER").and()
		.withUser("eva").password("evAdmin79").roles("ADMIN");
    }
   
        // Configure our application's security policy
        @Override    
        protected void configure(HttpSecurity http) throws Exception {
        	http
        	   .authorizeRequests()
        	   	.antMatchers("/resources/**").permitAll()
        	   	.antMatchers("/admin/**").hasRole("ADMIN")
        	   	.antMatchers("/**").permitAll()	   		   
        	   	.and()	   	
        	   .formLogin()
        	   	.loginPage("login")
        	   	.permitAll()
        	   	.and()
        	   .logout()
        	   	.logoutSuccessUrl("/login?logout")
        	   	.permitAll();
        }       
}
