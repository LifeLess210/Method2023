package com.example.method2023.Services.Impl;


import com.example.method2023.Dtos.UserDTO;
import com.example.method2023.Entity.Role;
import com.example.method2023.Entity.User;
import com.example.method2023.Repos.RoleRepo;
import com.example.method2023.Repos.UserRepo;
import com.example.method2023.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        //encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRole(role);
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ArrayList<UserDTO> findAllUsers() {
        ArrayList<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public User findById(int i) {
        return userRepository.findById(i).orElse(null);
    }

    private UserDTO convertEntityToDto(User user) {
        UserDTO userDto = new UserDTO();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().getName());
        userDto.setId(Long.valueOf(user.getId()));
        return userDto;
    }
}