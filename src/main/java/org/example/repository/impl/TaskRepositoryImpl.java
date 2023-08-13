package org.example.repository.impl;

import org.example.model.Task;
import org.example.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private final EntityManager entityManager;

    public TaskRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Override
    public void update(Task task) {
        entityManager.merge(task);
    }

    @Override
    public void delete(Task task) {
        entityManager.remove(task);
    }

    @Override
    public List<Task> loadAll() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

    @Override
    public List<Task> loadAllById(long userId) {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.user.id= :userId", Task.class);
        return query.setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Task> sortTask(int option, long userId) {
        if (option == 1)
            return loadAllById(userId).stream().sorted(Comparator.comparing(Task::getDeadline)).toList();
        else
            return loadAllById(userId).stream().sorted(Comparator.comparing(Task::getTitle)).toList();
    }

    @Override
    public Task findById(long id) {
        return entityManager.find(Task.class, id);
    }
}
