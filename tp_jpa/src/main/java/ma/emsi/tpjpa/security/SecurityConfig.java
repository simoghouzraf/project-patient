package ma.emsi.tpjpa.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	PasswordEncoder passwordEncoder = passwordEncoder();
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  System.out.println("**************");
	  System.out.println(passwordEncoder.encode("1234"));
	  System.out.println("**************");
		auth.inMemoryAuthentication()
		 .passwordEncoder(new BCryptPasswordEncoder())
		.withUser("GHOUZRAF").password(passwordEncoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication()
		 .passwordEncoder(new BCryptPasswordEncoder())
		.withUser("simo").password(passwordEncoder.encode("1234")).roles("ADMIN","USER");
	
	}

@Override
protected void configure(HttpSecurity http) throws Exception {
http.formLogin();
http.authorizeRequests().antMatchers("savepatient**/**","/add**/**","/delete**/**").hasRole("ADMIN");
http.authorizeRequests().anyRequest().authenticated();
http.exceptionHandling().accessDeniedPage("/notAutorized");
http.csrf();

}
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
		
		
	}

}
