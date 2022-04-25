package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * (@EnableWebSecurity)SecurityConfig(with beans) vs (extend)
 * WebSecurityConfigurerAdapter (and @Override)
 * https://laurspilca.com/configuring-the-endpoint-authorization-in-spring-security-without-extending-the-websecurityconfigureradapter-class/
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		        .authorizeRequests()
				//.authorizeHttpRequests()
		        
				.antMatchers("/data-api/**", "/api/**").hasRole("USER")
//				.antMatchers("/data-api/**", "/api/**").permitAll()
				.antMatchers("/**", "/h2-console/**").permitAll()
				
				// https://stackoverflow.com/questions/31746466/spring-security-always-returns-http-403
				// https://stackoverflow.com/questions/61123520/difference-between-httpbasic-and-authorizerequest
				.and().httpBasic() // for postman and curl enforcing access controls to web resources because it does not require cookies, session identifiers, or login pages
				//.and().csrf().disable()
	            
				.and()
				.csrf().ignoringAntMatchers("/h2-console/**").and().headers().frameOptions().sameOrigin()
				
				.and()
				.formLogin()//.disable()
				
				.and()
				.logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
				;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
		// return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	public UserDetailsService users() {
//		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		UserDetails user = User.builder()
//			.username("user")
//			.password(encoder().encode("pass") )
//			.roles("USER")
//			.build();
//		System.out.println("============ Pass: " + user.getPassword());
//		return new InMemoryUserDetailsManager(user);
//	}

//	@Bean
//	UserDetailsService userDetailsService(UserRepository userRepo) {
//		return username -> userRepo.findByUsername(username);
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder())

			// This will not work anymore after UserDetailsService is implemented
			//.inMemoryAuthentication().withUser("ben").password(encoder().encode("pass")).roles("USER");
			;
	}

}
