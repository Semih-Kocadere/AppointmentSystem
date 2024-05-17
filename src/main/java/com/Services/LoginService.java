package com.Services;

import com.Entity.User;
import com.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean authenticate(String name, String password) {
        User user = loginRepository.findByName(name);
        return user != null && user.getPassword().equals(password);
    }
}
