package com.example.news.security;

import com.example.news.constants.JspConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.DELETE + "/**").authenticated()
                .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.EDIT + "/**").authenticated()
                .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.ADD + "/**").authenticated()
                .and()
                .formLogin();
//        http.authorizeRequests().anyRequest().permitAll();
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("u")
////                .password("1")
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(user);
//    }
}
