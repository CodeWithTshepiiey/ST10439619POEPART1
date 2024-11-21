/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.st10439619poepartone;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class St10439619POEPARTONETest {

    private List<Task> tasks;

    @BeforeEach
    public void setUp() {
        // Setup initial task data
        tasks = new ArrayList<>();
        tasks.add(new Task("Create Login", 1, "Login feature description", "Mike Smith", 5, "To Do"));
        tasks.add(new Task("Create Add Features", 2, "Add feature description", "Edward Harrison", 8, "Doing"));
        tasks.add(new Task("Create Reports", 3, "Reports feature description", "Samantha Paulson", 2, "Done"));
        tasks.add(new Task("Add Arrays", 4, "Array feature description", "Glenda Oberholzer", 11, "To Do"));
    }

    // Test for Task Description Length
    @Test
    public void testTaskDescriptionLength_Success() {
        String validDescription = "Create Login to authenticate users";
        Task task = new Task("Login Feature", 1, validDescription, "Robyn Harrison", 8, "To Do");
        assertTrue(task.checkTaskDescription(), "Task successfully captured");
    }

    @Test
    public void testTaskDescriptionLength_Failure() {
        String longDescription = "This description is intentionally longer than 50 characters.";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Feature Test", 3, longDescription, "Test Dev", 5, "To Do");
        });
        assertEquals("Task description must be 50 characters or less.", exception.getMessage());
    }

    // Test for Adding and Removing Tasks
    @Test
public void testAddAndRemoveTasks() {
    // Initially, tasks list should have 4 tasks
    tasks.add(new Task("New Task", 5, "New Description", "New Dev", 4, "To Do"));
    assertEquals(5, tasks.size(), "After adding a task, the list size should be 5.");

    tasks.remove(0); // Remove the first task
    assertEquals(4, tasks.size(), "After removing a task, the list size should be 4.");
}
@Test
public void testInvalidTaskIDGeneration() {
    Task invalidTask = new Task("", -1, "Description", "", 5, "Doing");
    String taskID = invalidTask.createTaskID();
    assertNotNull(taskID, "Task ID should not be null even for invalid input.");
    assertEquals("Invalid ID", taskID, "Expected 'Invalid ID' for invalid task input.");
}

@Test
public void testTotalHoursAccumulation() {
    // Verify total hours for the initial tasks list
    assertEquals(26, Task.returnTotalHours(tasks), "Total hours for the default tasks list should be 26.");

    // Example for a large task list
    List<Task> largeTaskList = List.of(
        new Task("Task1", 1, "Description", "Dev1", 10, "To Do"),
        new Task("Task2", 2, "Description", "Dev2", 12, "Doing"),
        new Task("Task3", 3, "Description", "Dev3", 55, "Done"),
        new Task("Task4", 4, "Description", "Dev4", 11, "To Do"),
        new Task("Task5", 5, "Description", "Dev5", 1, "Doing")
    );

    assertEquals(89, Task.returnTotalHours(largeTaskList), "Total hours for the large task list should be 89.");
}

    // Test for Developer Array Correctly Populated
    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        List<String> expectedDevelopers = List.of("Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer");
        List<String> actualDevelopers = new ArrayList<>();
        for (Task task : tasks) {
            actualDevelopers.add(task.getDeveloperDetails());
        }
        assertEquals(expectedDevelopers, actualDevelopers, "The developer array is not correctly populated.");
    }

    // Test for Longest Task Duration
    @Test
    public void testLongestTaskDuration() {
        Task longestTask = tasks.stream()
                                .max((task1, task2) -> Integer.compare(task1.getTaskDuration(), task2.getTaskDuration()))
                                .orElse(null);
        assertNotNull(longestTask, "Longest task should not be null.");
        assertEquals("Glenda Oberholzer", longestTask.getDeveloperDetails(), "Expected developer for longest task is incorrect.");
        assertEquals(11, longestTask.getTaskDuration(), "Expected task duration for longest task is incorrect.");
    }

    // Test for Searching Task by Name
    @Test
    public void testSearchForTaskByName() {
        String taskName = "Create Login";
        Task foundTask = tasks.stream()
                              .filter(task -> task.getTaskName().equals(taskName))
                              .findFirst()
                              .orElse(null);
        assertNotNull(foundTask, "Task with the given name should be found.");
        assertEquals("Mike Smith", foundTask.getDeveloperDetails(), "Developer for the task is incorrect.");
        assertEquals("Create Login", foundTask.getTaskName(), "Task name is incorrect.");
    }

    // Test for Searching Tasks by Developer
    @Test
    public void testSearchTasksByDeveloper() {
        String developer = "Samantha Paulson";
        List<String> taskNames = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDeveloperDetails().equals(developer)) {
                taskNames.add(task.getTaskName());
            }
        }
        assertEquals(List.of("Create Reports"), taskNames, "Tasks for the developer are incorrect.");
    }

    // Test for Deleting Task
    @Test
    public void testDeleteTask() {
        String taskToDelete = "Create Reports";
        boolean isDeleted = tasks.removeIf(task -> task.getTaskName().equals(taskToDelete));
        assertTrue(isDeleted, "The task should be successfully deleted.");
        assertFalse(tasks.stream().anyMatch(task -> task.getTaskName().equals(taskToDelete)), "The task should no longer exist in the list.");
    }

    // Test for Displaying Report
    @Test
    public void testDisplayReport() {
        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            report.append(task.getDeveloperDetails())
                  .append(", ")
                  .append(task.getTaskName())
                  .append(", Duration: ")
                  .append(task.getTaskDuration())
                  .append("\n");
        }

        String expectedReport = """
            Mike Smith, Create Login, Duration: 5
            Edward Harrison, Create Add Features, Duration: 8
            Samantha Paulson, Create Reports, Duration: 2
            Glenda Oberholzer, Add Arrays, Duration: 11
            """;

        assertEquals(expectedReport.trim(), report.toString().trim(), "The report does not match the expected format.");
    }
}








