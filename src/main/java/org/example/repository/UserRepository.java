package org.example.repository;

import org.example.model.User;

public interface UserRepository {
User signIn(String username);
void signUp(User user);
void delete(User user);
}
