package com.filadeatras.fila_de_atras.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Jakub on 30/03/2017.
 */

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider userRepositoryAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.antMatcher("/api/**");

        configureUrlAuthorization(http);

        http.csrf().disable();

        // Disable CSRF protection (it is difficult to implement with ng2)
        http.csrf().disable();

        // Use Http Basic Authentication
        http.httpBasic();

        // Do not redirect when logout
        http.logout().logoutSuccessHandler((rq, rs, a) -> {	});

    }

    private void configureUrlAuthorization(HttpSecurity http) throws Exception {

        /*
        URLs that need authentication to access to it
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/books/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/books/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN");
        TODO Protected URLs to be implemented as examples above.
        */
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/post/").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/post/upvotePost/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/post/downvotePost/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/post/reportPost/**").hasRole("USER");

        //Other URLs can be accessed without authentication
        http.authorizeRequests().anyRequest().permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Database authentication provider
        auth.authenticationProvider(userRepositoryAuthenticationProvider);
    }
}
