package com.Controllers;
import com.DTO.UserDTO;
import com.Entity.User;
import com.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserDTO userResponse = new UserDTO();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setGender(user.getGender());
        userResponse.setAge(user.getAge());
        userResponse.setDisease(user.getDisease());

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@ModelAttribute User user,
                                           @RequestParam Long id,
                                           @RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String phone,
                                           @RequestParam String gender,
                                           @RequestParam int age,
                                           @RequestParam(required = false) String disease,
                                           @RequestParam String password) {
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setAge(age);
        user.setDisease(disease);
        user.setPassword(password);
        user.setUsername(null);

        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) String email,
                                           @RequestParam(required = false) String phone,
                                           @RequestParam(required = false) String gender,
                                           @RequestParam(required = false) Integer age,
                                           @RequestParam(required = false) String disease,
                                           @RequestParam(required = false) String password) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (name != null) {
            user.setName(name);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (phone != null) {
            user.setPhone(phone);
        }
        if (gender != null) {
            user.setGender(gender);
        }
        if (age != null) {
            user.setAge(age);
        }
        if (disease != null) {
            user.setDisease(disease);
        }
        if (password != null) {
            user.setPassword(password); // Make sure to handle the password securely
        }

        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}

