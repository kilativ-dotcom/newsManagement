package com.example.news.presentation.action;

import com.example.news.constants.JspConstants;
import com.example.news.exception.ValidationException;
import com.example.news.presentation.form.UserForm;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class AuthenticationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(JspConstants.REGISTRATION)
    public String startRegistration(Map<String, Object> model) {
        model.put(JspConstants.USER_FORM_ATTRIBUTE, new UserForm());
        return JspConstants.JSP_REGISTRATION;
    }

    @PostMapping(JspConstants.REGISTRATION)
    public String endRegistration(
            @Valid UserForm userForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return JspConstants.JSP_REGISTRATION;
        }
        try {
            userService.registerUser(userForm);
        } catch (ValidationException e) {
            bindingResult.addError(new FieldError("userForm", e.getField(), e.getMessage()));
            return JspConstants.JSP_REGISTRATION;
        }
        UserDetails userDetails = userService.loadUserByUsername(userForm.getUsername());
        Authentication token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        return ControllerUtils.redirect(JspConstants.SITE_BASENAME);
    }
}
