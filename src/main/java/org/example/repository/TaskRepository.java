package org.example.repository;

import org.example.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository {
    void save(Task task);
    void update(Task task);
    void delete (Task task);
    List<Task> loadAll();
    List<Task> loadAllById(long id);
    List<Task> sortTask(int option, long userId);
    Task findById(long id);
}
