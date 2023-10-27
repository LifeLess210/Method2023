package com.example.method2023.Initializer;

import com.example.method2023.Entity.Role;
import com.example.method2023.Repos.RoleRepo;
import com.example.method2023.Services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Order(1)
@Component
public class RolesInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final RoleRepo roleRepository;

    public RolesInitializer(RoleService roleService, RoleRepo roleRepository) {

        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (Objects.isNull(roleService.findById(1))) {
            roleRepository.save(new Role(1, "ROLE_ADMIN"));
            roleRepository.save(new Role(2, "ROLE_ACCOUNTANT"));
            roleRepository.save(new Role(3, "ROLE_USER"));
        }
    }
}
