package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Task task);
    void updateTask(Task task);
    void deleteTask (Task task);
    List<Task> loadAllTasks();
    List<Task> loadAllTasksById(long id);
    List<Task> sortTask(int option, long userId);
    Task findTaskById(long id);
}
