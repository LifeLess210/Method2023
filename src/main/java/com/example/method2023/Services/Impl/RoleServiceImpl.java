package com.example.method2023.Services.Impl;

import com.example.method2023.Entity.Role;
import com.example.method2023.Repos.RoleRepo;
import com.example.method2023.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepo roleRepo;

    public Role findById(Integer l){
        return roleRepo.findById(l).orElse(null);
    }

    public Role findByName(String s) {
        return roleRepo.findByName(s);
    }
}
