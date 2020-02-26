package de.lequangtinh.fm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired 
	private CustomAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/css/**", "/javascript/**", "/webjars/**", "/images/**").permitAll()
			.anyRequest().authenticated()
			.antMatchers("/**").authenticated()
			.antMatchers("/benutzer/**").hasRole("Administrator")
			.and()
			.formLogin()
				.loginPage("/login").permitAll().failureUrl("/login?error=true")
			.and()
			.logout()
				.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout=true").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true);
	}
}
