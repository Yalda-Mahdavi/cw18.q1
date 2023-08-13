package org.example.service.impl;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserServiceImpl implements UserService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = emf.createEntityManager();
    UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl(entityManager);
    }

    @Override
    public User signIn(String username, String password) {
        entityManager.getTransaction().begin();
        User user = userRepository.signIn(username);
        entityManager.getTransaction().commit();
        if (user != null && user.getPassword().equals(password))
            return user;
        return null;
    }

    @Override
    public void signUp(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            userRepository.signUp(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            userRepository.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
