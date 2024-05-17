package com.Controllers;

import com.Entity.User;
import com.Repository.LoginRepository;
import com.Services.LoginService;
import com.Util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    public final LoginRepository loginRepository;

    public LoginController(LoginService loginService, LoginRepository loginRepository) {
        this.loginService = loginService;
        this.loginRepository = loginRepository;
    }


    @PostMapping("/")
    public ResponseEntity<?> login(@RequestParam("name") String name,
                                   @RequestParam("password") String password) {
        boolean authenticated = loginService.authenticate(name, password);

        if (authenticated) {
            User user = loginRepository.findByName(name);
            String token = JwtUtil.generateToken(name);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("UserName", userName);
        return "profile";
    }
}
