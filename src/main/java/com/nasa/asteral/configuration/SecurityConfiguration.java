package com.nasa.asteral.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    private static final String[] PUBLIC_ENDPOINTS = { "/", "/public", "/public/**", "/api/public/**", "/asteroid/**",
            "/register", "/v3/api-docs/**", "/swagger-ui/**" };

    private static final String[] USER_ENDPOINTS = { "/user", "/user/**", "/api/user/**" };

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
				.requestMatchers(USER_ENDPOINTS).hasAnyAuthority("USER")
				.anyRequest().authenticated())
				.csrf(csrf -> csrf.disable()).cors(withDefaults())
				.formLogin(login -> login
						.loginPage("/login").defaultSuccessUrl("/")
						.permitAll())
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/").deleteCookies("JSESSIONID")
						.invalidateHttpSession(true)
						.permitAll());
		return http.build();
    }

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

	@Bean
	PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
