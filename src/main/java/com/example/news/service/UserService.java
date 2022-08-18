package com.example.news.service;

import com.example.news.database.UserRepository;
import com.example.news.exception.ValidationException;
import com.example.news.model.Role;
import com.example.news.model.User;
import com.example.news.presentation.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User registerUser(UserForm form) throws ValidationException {
        String username = form.getUsername();
        String password = form.getPassword();
        String passwordRepeat = form.getPasswordRepeat();
        if (null == password || !password.equals(passwordRepeat)) {
            throw new ValidationException("password","Passwords should be equal");
        }
        if (findByUsername(username).isPresent()) {
            throw new ValidationException("username", String.format("Username '%s' is taken", username));
        }
        User user = new User(username, passwordEncoder.encode(password));
        user.setRoles(new ArrayList<>());
        user.addRole(roleService.saveRoleIfNotExists("ROLE_ADMIN"));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Cannot find user " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                rolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
