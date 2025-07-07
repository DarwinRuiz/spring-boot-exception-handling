package com.darwinruiz.springboot.errors.springbooterrors;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.darwinruiz.springboot.errors.springbooterrors.models.User;

@Configuration
public class AppConfig {

    @Bean
    List<User> userList() {
        return List.of(
                new User(1L, "Darwin", "Ruiz", null),
                new User(2L, "John", "Doe", null),
                new User(3L, "Jane", "Smith", null),
                new User(4L, "Alice", "Johnson", null),
                new User(5L, "Bob", "Brown", null)
        );
    }
}
