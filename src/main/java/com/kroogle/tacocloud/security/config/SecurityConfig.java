package com.kroogle.tacocloud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("tacoUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                        .antMatchers("/design", "/orders")
                            .access("hasRole('ROLE_USER')")
                        .antMatchers("/")
                            .permitAll()
                        .antMatchers("/h2-console/**")
                            .permitAll()
                    .and()
                    .formLogin()
                        .loginPage("/login")
                            .defaultSuccessUrl("/taco", true)
                    .and()
                        .logout()
                            .logoutSuccessUrl("/login");
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}
