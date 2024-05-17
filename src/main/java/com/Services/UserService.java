package com.Services;



import com.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);

    void saveUser(User user);

    void deleteUser(Long id);

      User getUserByName(String name);

    // Other methods as needed
}

