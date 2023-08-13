package org.example;

import org.example.model.Task;
import org.example.model.User;
import org.example.model.enumeration.TaskStatus;
import org.example.service.TaskService;
import org.example.service.UserService;
import org.example.service.impl.TaskServiceImpl;
import org.example.service.impl.UserServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        TaskService taskService = new TaskServiceImpl();
        LocalDate localDate1 = LocalDate.of(2023, 11, 5);
        LocalDate localDate2 = LocalDate.of(2023, 11, 10);

        User user1 = new User();
        user1.setUsername("AAAA");
        user1.setPassword("1111");

        User user2 = new User();
        user2.setUsername("BBBB");
        user2.setPassword("2222");

        User user3 = new User();
        user3.setUsername("CCC");
        user3.setPassword("3333");

        Task task1 = new Task();
        task1.setTitle("Wash door");
        task1.setTaskStatus(TaskStatus.OPEN);
        task1.setDeadline(localDate1);
        task1.setUser(user1);

        Task task2= new Task();
        task2.setTitle("Clean car");
        task2.setTaskStatus(TaskStatus.IN_PROGRESS);
        task2.setDeadline(localDate2);
        task2.setUser(user1);

        // Test save of task
        taskService.saveTask(task1);
        taskService.saveTask(task2);
        user1.getTasks().add(task1);

        // Test update of task
        task1.setTitle("Wash door");
        task1.setTaskStatus(TaskStatus.COMPLETED);
        task1.setDeadline(localDate1);
        task1.setUser(user1);
        taskService.updateTask(task1);

        // Test find task by id
        System.out.println("Task with id=1: " + taskService.findTaskById(1L));

        // Test find all tasks
        System.out.println(taskService.loadAllTasks());

        // Test delete a task
        taskService.deleteTask(task1);

        // Test signIn for user
        System.out.println("Invalid user: " + userService.signIn("AAA", "1236"));
        System.out.println("Valid user: " + userService.signIn("AAAA", "1111"));

        // Test signUp for user
        userService.signUp(user2);

        // Test delete for user
        userService.delete(user3);

        System.out.println(taskService.loadAllTasksById(1L));
        System.out.println("Sorted task: " + taskService.sortTask(1, 1L));
        System.out.println("Sorted task: " + taskService.sortTask(2, 1L));
    }
}