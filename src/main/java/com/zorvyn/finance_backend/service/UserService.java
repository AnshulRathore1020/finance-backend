package com.zorvyn.finance_backend.service;

import com.zorvyn.finance_backend.model.User;
import com.zorvyn.finance_backend.model.User.Role;
import com.zorvyn.finance_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Sab users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Role update karo
    public User updateRole(Long userId, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Role.valueOf(role.toUpperCase()));
        return userRepository.save(user);
    }

    // Status update karo (active/inactive)
    public User updateStatus(Long userId, boolean active) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(active);
        return userRepository.save(user);
    }
}