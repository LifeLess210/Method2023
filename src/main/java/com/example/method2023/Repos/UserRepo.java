package com.example.method2023.Repos;


import com.example.method2023.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByNameOrEmail(String username, String username1);

    User findByEmail(String email);

    ArrayList<User> findAll();
}
