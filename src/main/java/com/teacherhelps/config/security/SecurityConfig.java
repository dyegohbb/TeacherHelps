package com.teacherhelps.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.teacherhelps.model.dao.PessoaDAO;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	PessoaDAO pessoaDAO;
	
	@Autowired
	private AuthService authService;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/professor").permitAll()
		.antMatchers(HttpMethod.POST, "/professor").permitAll()
		.antMatchers(HttpMethod.POST, "/aluno").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/disciplina").permitAll()
		.anyRequest().authenticated().and().cors()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new TokenAuth(tokenService, pessoaDAO), UsernamePasswordAuthenticationFilter.class);
	}
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/image/**").permitAll().antMatchers("/").permitAll().antMatchers("/css/**").permitAll()
//				.antMatchers("/login/**").permitAll()
//				.antMatchers("/cadastro/**").permitAll()
////				.antMatchers("/professor").permitAll()
//				.anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login").and()
//				.logout(logout -> {
//					logout.disable();
//				}).csrf().disable();
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(authService).passwordEncoder(encoder);
	}
	
}
