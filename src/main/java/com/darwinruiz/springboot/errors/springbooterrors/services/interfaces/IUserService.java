package com.darwinruiz.springboot.errors.springbooterrors.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.darwinruiz.springboot.errors.springbooterrors.models.User;

public interface IUserService {

    Optional<User> findById(Long id);

    List<User> findAll();
}
