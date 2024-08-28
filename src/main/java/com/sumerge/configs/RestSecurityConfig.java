package com.sumerge.configs;

import com.sumerge.filters.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true , securedEnabled = true)
public class RestSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests((auth) -> auth
                        .antMatchers(HttpMethod.DELETE, "/api/courses/delete/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/courses/update/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/courses/add").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/courses/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().permitAll())
                .addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and()
                .formLogin();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.builder().username("admin").password("admin").roles("ADMIN").build();
        UserDetails user = User.builder().username("user").password("user").roles("USER").build();
        return new InMemoryUserDetailsManager(user , admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // Use plain text passwords (for testing only)
    }
}
