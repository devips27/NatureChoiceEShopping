package com.naturechoice.springmvceshopping.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import com.naturechoice.springmvceshopping.authentication.MyDBAuthenticationService;
 
@Configuration
//Enables spring security configuration which is defined in WebSecurityConfigurerAdapter
@EnableWebSecurity
//We have extended WebSecurityConfigurerAdapter, which allows us to override spring’s security default feature
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
   @Autowired
   MyDBAuthenticationService myDBAauthenticationService;
 
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
       // For User in database.
       auth.userDetailsService(myDBAauthenticationService);
	   
 
   }
   
   @Override
 //configure(HttpSecurity http) method configures the HttpSecurity class which authorizes each HTTP request 
 //which has been made.
   protected void configure(HttpSecurity http) throws Exception {
	   //Enables CSRF protection
       http.csrf().disable();
 
       // The pages requires login as USER or ADMIN.
       
       http.authorizeRequests().antMatchers("/accountInfo")
               .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
 
       // For ADMIN only.
       http.authorizeRequests().antMatchers("/product").access("hasRole('ROLE_ADMIN')");
 
       
       http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
       // Config for Login Form
       http.authorizeRequests().and().formLogin()//
               // Submit URL of login page.
               .loginProcessingUrl("/spring_security_check") // Submit URL
               .loginPage("/login")//
               .defaultSuccessUrl("/accountInfo")//
               .failureUrl("/login?error=true")//
               .usernameParameter("userName")//
               .passwordParameter("password")
               // Config for Logout Page
               // (Go to home page).
               .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
 
   }
}