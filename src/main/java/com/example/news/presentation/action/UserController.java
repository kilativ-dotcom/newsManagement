package com.example.news.presentation.action;

import com.example.news.constants.JspConstants;
import com.example.news.exception.UserNotFoundException;
import com.example.news.exception.ValidationException;
import com.example.news.model.Role;
import com.example.news.model.User;
import com.example.news.presentation.form.UserForm;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(JspConstants.USER)
    public String getUsers(Map<String, Object> model){
        List<Role> roles = Arrays.asList(Role.values());
        model.put(JspConstants.ROLES_ATTRIBUTE, roles);

        List<User> users = userService.findAll();
        model.put(JspConstants.USERS_ATTRIBUTE, users);

        return JspConstants.JSP_USER_LIST;
    }

    @PostMapping(JspConstants.USER)
    public String deleteUsers(
            Principal principal,
            @RequestParam(
                    value = "deleteUsername",
                    defaultValue = ""
            ) List<String> usernames) {
        if (null != principal) {
            usernames.remove(principal.getName()); // to disable ability to delete yourself
        }
        userService.deleteByUsername(usernames);
        return ControllerUtils.redirect(JspConstants.USER);
    }

    @GetMapping(JspConstants.USER + "/{username}")
    public String editUser(
            @PathVariable String username,
            Map<String, Object> model
    ) throws UserNotFoundException {
        List<Role> roles = Arrays.asList(Role.values());
        model.put(JspConstants.ROLES_ATTRIBUTE, roles);

        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        model.put(JspConstants.USER, user);

        return JspConstants.JSP_USER_EDIT;
    }

    @PostMapping(JspConstants.USER + "/{username}")
    public String saveEditedUser(
            Principal principal,
            @PathVariable String username,
            @RequestParam(value = "role", defaultValue = "") Set<String> roles
    ) throws UserNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        if (principal != null && principal.getName().equals(username) && user.getRoles().contains(Role.MANAGE_USERS)) {
            roles.add(Role.MANAGE_USERS.getAuthority()); // to disable ability to take MANAGE_USERS authority from yourself
        }
        user = userService.updateRoles(user, roles);

        return ControllerUtils.redirect(JspConstants.USER + "/" + user.getUsername());
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
            bindingResult.addError(new FieldError(JspConstants.USER_FORM_ATTRIBUTE, e.getField(), e.getMessage()));
            return JspConstants.JSP_REGISTRATION;
        }

        UserDetails userDetails = userService.loadUserByUsername(userForm.getUsername());
        Authentication token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        return ControllerUtils.redirect(JspConstants.SITE_BASENAME);
    }

    @GetMapping(JspConstants.LOGIN)
    public String startLogin() {
        return JspConstants.JSP_LOGIN;
    }
}
