package com.darwinruiz.springboot.errors.springbooterrors.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darwinruiz.springboot.errors.springbooterrors.configurations.exceptions.UserNotFoundException;
import com.darwinruiz.springboot.errors.springbooterrors.models.User;
import com.darwinruiz.springboot.errors.springbooterrors.services.interfaces.IUserService;

@RestController
@RequestMapping("/api/app")
public class AppController {

    private IUserService userService;

    public AppController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        // int value = 10 / 0; // This will cause an ArithmeticException
        int parsedValue = Integer.parseInt("abc"); // This will cause a NumberFormatException
        return "Hello, Spring Boot!";
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<User> showUser(@PathVariable(name = "id") Long id) {
        if (id == null || id <= 0) {
            throw new UserNotFoundException("Invalid user ID");
        }

        User user = this.userService.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return ResponseEntity.ok(user);
    }
}
