package com.example.news.service;

import com.example.news.database.UserRepository;
import com.example.news.exception.ValidationException;
import com.example.news.model.Role;
import com.example.news.model.User;
import com.example.news.presentation.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Cannot find user " + username)
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        );
    }

    public User registerUser(UserForm form) throws ValidationException {
        String username = form.getUsername();
        String password = form.getPassword();
        String passwordRepeat = form.getPasswordRepeat();
        if (null == password || !password.equals(passwordRepeat)) {
            throw new ValidationException("Passwords should be equal", "passwordRepeat");
        }
        if (findByUsername(username).isPresent()) {
            throw new ValidationException(String.format("Username '%s' is taken", username), "username");
        }
        User user = new User(username, passwordEncoder.encode(password));
        user.setRoles(new HashSet<>());
        user.addRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    public User updateRoles(User user, Set<String> roles) {
        user.getRoles().clear();
        for (String role : roles) {
            try {
                user.addRole(Role.valueOf(role));
            } catch (IllegalArgumentException e){
                // role from set is illegal
            }
        }
        return userRepository.save(user);
    }

    @Transactional
    public void deleteByUsername(List<String> usernames) {
        for (String username : usernames) {
            deleteByUsername(username);
        }
    }

    @Transactional
    public void deleteByUsername(String username) {
        if (userRepository.existsByUsername(username)){
            userRepository.deleteByUsername(username);
        }
    }
}
