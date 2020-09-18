package org.example.SpringHibernate.config;

import org.example.SpringHibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //scanning for additional conditions - @PreAuthorize("hasAuthority('ADMIN')")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //enabling authorization
                .authorizeRequests()
                //grant full access to the following folders/resources
                    .antMatchers("/", "/registration", "/static/**").permitAll()
                //all other pages require authentication
                    .anyRequest().authenticated()
                .and()
                //enabling form login
                    .formLogin()
                    .loginPage("/login")
                //allowing this page to be accessed by everyone
                    .permitAll()
                //enabling logout and allowing this to be accessed by everyone
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //getting user data from DB
        auth.userDetailsService(userService)
                //there is no password encoding. all passwords are stored in the database as they are
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}
