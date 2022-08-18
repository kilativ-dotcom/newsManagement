package com.example.news.service;

import com.example.news.database.RoleRepository;
import com.example.news.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRoleIfNotExists(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        return role.orElseGet(() -> roleRepository.save(new Role(name)));
    }
}
