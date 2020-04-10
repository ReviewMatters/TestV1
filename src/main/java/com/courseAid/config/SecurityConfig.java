package com.courseAid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.Transactional;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//To redirect the user to user/index on successful login
        http.formLogin().defaultSuccessUrl("/student");
        
		//remove next 2 line for a production environment
		http.csrf().disable();  
		http.headers().frameOptions().disable();
		
		http.authorizeRequests()
		.antMatchers("/student/**").hasRole("USER")
		.antMatchers("/", "/js/**", "/css/**", "/images/**","/**").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers(HttpMethod.POST, "/register").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.loginPage("/login").permitAll()
		.and().logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
		
		// a tiny bit more to place below here in a few slides

		}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {  auth.inMemoryAuthentication()
	.passwordEncoder(NoOpPasswordEncoder.getInstance())
	.withUser("Frank").password("1234").roles("STUDENT")
	.and()
	.withUser("manager").password("pass").roles("MANAGER");
	}
	*/
	
}
