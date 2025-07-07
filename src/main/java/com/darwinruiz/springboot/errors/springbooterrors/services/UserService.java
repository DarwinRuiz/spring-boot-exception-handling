package com.darwinruiz.springboot.errors.springbooterrors.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darwinruiz.springboot.errors.springbooterrors.models.User;
import com.darwinruiz.springboot.errors.springbooterrors.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private List<User> users;

    @Override
    public Optional<User> findById(Long id) {
        return this.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }

}
