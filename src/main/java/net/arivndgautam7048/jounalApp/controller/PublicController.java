package net.arivndgautam7048.jounalApp.controller;

import net.arivndgautam7048.jounalApp.entity.User;
import net.arivndgautam7048.jounalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully: " + user.getUserName());
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }
}
