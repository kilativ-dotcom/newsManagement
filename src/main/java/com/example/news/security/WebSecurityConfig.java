package com.example.news.security;

import com.example.news.constants.JspConstants;
import com.example.news.model.Role;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.DELETE + "/**")
                    .hasAnyAuthority(Role.ROLE_ADMIN.getAuthority(), Role.DELETE_NEWS.getAuthority())

                .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.EDIT + "/**")
                    .hasAnyAuthority(Role.ROLE_ADMIN.getAuthority(), Role.CHANGE_NEWS.getAuthority())

                .antMatchers("/" + JspConstants.SITE_BASENAME + "/" + JspConstants.ADD + "/**")
                    .hasAnyAuthority(Role.ROLE_ADMIN.getAuthority(), Role.CREATE_NEWS.getAuthority())

                .antMatchers("/" + JspConstants.USER + "/**")
                    .hasAnyAuthority(Role.ROLE_ADMIN.getAuthority(), Role.MANAGE_USERS.getAuthority())

                .antMatchers("/resources/**")
                    .permitAll()
            .and()
                .formLogin().loginPage("/" + JspConstants.LOGIN)
            .and()
                .logout()
                    .logoutSuccessUrl("/" + JspConstants.SITE_BASENAME)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
        ;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}
