package com.example.news.security;

import com.example.news.constants.JspConstants;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.DELETE + "/**").authenticated()
                    .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.EDIT + "/**").authenticated()
                    .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.ADD + "/**").authenticated()
                    .antMatchers("/resources/**").permitAll()
                .and()
                    .formLogin()
                .and()
                    .logout().logoutSuccessUrl("/" + JspConstants.SITE_BASENAME)
        ;
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("1"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("starwars"))
                .roles("USER", "ADMIN")
                .build();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        if(userDetailsManager.userExists(user.getUsername())) {
            userDetailsManager.deleteUser(user.getUsername());
        }

        if(userDetailsManager.userExists(admin.getUsername())) {
            userDetailsManager.deleteUser(admin.getUsername());
        }

        userDetailsManager.createUser(user);
        userDetailsManager.createUser(admin);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}
