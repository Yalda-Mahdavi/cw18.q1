package org.example.service.impl;

import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.example.repository.impl.TaskRepositoryImpl;
import org.example.service.TaskService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = emf.createEntityManager();
    TaskRepository taskRepository;

    public TaskServiceImpl() {
        this.taskRepository = new TaskRepositoryImpl(entityManager);
    }

    @Override
    public void saveTask(Task task) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            taskRepository.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void updateTask(Task task) {

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            taskRepository.update(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteTask(Task task) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            taskRepository.delete(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Task> loadAllTasks() {
        return taskRepository.loadAll();
    }

    @Override
    public List<Task> loadAllTasksById(long id) {
        return taskRepository.loadAllById(id);
    }

    @Override
    public List<Task> sortTask(int option, long userId) {
        return taskRepository.sortTask(option, userId);
    }

    @Override
    public Task findTaskById(long id) {
        return taskRepository.findById(id);
    }
}
