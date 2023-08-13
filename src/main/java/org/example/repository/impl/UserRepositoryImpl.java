package org.example.repository.impl;

import org.example.model.User;
import org.example.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;
    public UserRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public User signIn(String username) {
        TypedQuery<User> query = entityManager.createQuery("FROM User WHERE username = username", User.class);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public void signUp(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}
