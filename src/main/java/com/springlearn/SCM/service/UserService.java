package com.springlearn.SCM.service;

import com.springlearn.SCM.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    User getUserByEmail(String email);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String id);
    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();
}
