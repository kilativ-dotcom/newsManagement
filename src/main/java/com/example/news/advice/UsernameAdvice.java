package com.example.news.advice;

import com.example.news.constants.JspConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class UsernameAdvice {
    @ModelAttribute(JspConstants.LOGGED_IN_USERNAME_ATTRIBUTE)
    public String loggedInUsername(Principal principal) {
        return null == principal ? null : principal.getName();
    }
}
