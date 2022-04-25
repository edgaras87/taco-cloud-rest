package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 * (@EnableWebSecurity)SecurityConfig(with beans) vs (extend)
 * WebSecurityConfigurerAdapter (and @Override)
 * https://laurspilca.com/configuring-the-endpoint-authorization-in-spring-security-without-extending-the-websecurityconfigureradapter-class/
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//.authorizeRequests()
			.authorizeHttpRequests()
				.antMatchers("/data-api/**", "/api/**").hasRole("USER")
//				.antMatchers("/data-api/**", "/api/**").permitAll()
				.antMatchers("/**", "/h2-console/**").permitAll()
				//.and().csrf().disable()
			
			    .and().csrf().ignoringAntMatchers("/h2-console/**", "/api/**")
			    .and().headers().frameOptions().sameOrigin()
				.and().formLogin()
				//.disable()
				;

	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
		//return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public UserDetailsService users() {
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = User.builder()
			.username("user")
			.password(encoder().encode("pass") )
			.roles("USER")
			.build();
		System.out.println("============ Pass: " + user.getPassword());
		return new InMemoryUserDetailsManager(user);
	}
	
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("ben")
//			.password(encoder().encode("pass"))
//			.roles("USER")
//			;
//
//	}

}
