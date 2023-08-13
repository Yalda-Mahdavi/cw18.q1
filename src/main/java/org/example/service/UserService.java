package org.example.service;

import org.example.model.User;

public interface UserService {
    User signIn(String username, String password);
    void signUp(User user);
    void delete(User user);
}
