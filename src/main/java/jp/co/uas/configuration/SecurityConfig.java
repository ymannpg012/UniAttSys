package jp.co.uas.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	/**
	 * initial configuration for setting up the filter chain.
	 */
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	return http
        	.csrf(csrf -> csrf.disable())
        	.authorizeHttpRequests(auth -> auth
        			.requestMatchers("/login").permitAll() //"requestMatchers("/login")" indicates that this endpoint is accessible even if the user is not yet authenticated.
        												   // it makes sense to expose this endpoint because this holds the login form with which the authentication will take place.
        			
        			.anyRequest().authenticated()		   // this indicates that all other requests will only be accessible if the user is already authenticated.
        			
        	)
        	.formLogin(form -> form
        			.loginPage("/login")                   //this indicates which endpoint holds the login form.
        												   // without this, spring security will use its default login form.
                    .defaultSuccessUrl("/dashboard", true) // this indicates which endpoint to go to if the authentication is successful.
                    .permitAll()
            )
        	.authenticationProvider(authProvider())        // this sets the authentication provider
        	.build();
    }
	
	/*
	 * initialization of the hashing algorithm to be used.
	 * 
	 *  @param: password
	 * 
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	//authenticator setup
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder()); //indicates what hashing algorithm to use for validating the password
														//note: make sure that the algorithm used here is the same one as that used for encrypting the password during signup.
		return provider;
	}
}
