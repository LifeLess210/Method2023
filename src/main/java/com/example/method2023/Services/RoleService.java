package com.example.method2023.Services;

import com.example.method2023.Entity.Role;

public interface RoleService {
    Role findById(Integer l);
    Role findByName(String s);
}
