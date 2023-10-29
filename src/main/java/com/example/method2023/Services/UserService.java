package com.example.method2023.Services;

import com.example.method2023.Dtos.UserDTO;
import com.example.method2023.Entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface UserService {
    User findUserByEmail(String s);

    void saveUser(UserDTO u);
    void saveUser(User u);

    ArrayList<UserDTO> findAllUsers();

    Object findById(int i);
}
