/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.st10439619poepartone;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Makgotso Mokgoko
 */
public class St10439619POEPARTONETest {

    private List<Task> tasks;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        tasks = new ArrayList<>();
        tasks.add(new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do"));
        tasks.add(new Task("Add Task Feature", 2, "Create Add Task feature to add task users", "Mike Smith", 10, "Doing"));
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

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

@Test
public void testAddAndRemoveTasks() {
    tasks.add(new Task("New Task", 3, "New Description", "New Dev", 4, "To Do"));
    assertEquals(3, tasks.size(), "After adding a task, the list size should be 3.");

    tasks.remove(0);
    assertEquals(2, tasks.size(), "After removing a task, the list size should be 2.");
}
@Test
public void testInvalidTaskIDGeneration() {
    Task invalidTask = new Task("", -1, "Description", "", 5, "Doing");
    String taskID = invalidTask.createTaskID();
    assertNotNull(taskID, "Task ID should not be null even for invalid input.");
    assertEquals("Invalid ID", taskID, "Expected 'Invalid ID' for invalid task input.");
}

    @Test
public void testTaskIDGeneration() {
    Task task1 = tasks.get(0);
    assertEquals("LO:1:SON", task1.createTaskID(), "Expected Task ID LO:1:SON but got a different value for the first task.");

    String[][] testData = {
        {"Add Task", "Developer Smith", "AD:0:ITH"},
        {"Update Feature", "Devon Richards", "UP:1:RDS"},
        {"Review Code", "Samuel Leth", "RE:2:ETH"},
        {"Deploy Build", "Chris Hend", "DE:3:END"}
    };

    for (int i = 0; i < testData.length; i++) {
        String taskName = testData[i][0];
        String developer = testData[i][1];
        String expectedID = testData[i][2];

        Task task = new Task(taskName, i, "Description", developer, 5, "Doing");
        assertEquals(expectedID, task.createTaskID(), "Task ID for Task " + i + " does not match expected format.");
    }
    Task invalidTask1 = new Task(null, 1, "Description", "Developer Smith", 5, "To Do");
    assertEquals("Invalid ID", invalidTask1.createTaskID(), "Expected 'Invalid ID' for null task name.");

    Task invalidTask2 = new Task("Task", -1, "Description", "Developer Smith", 5, "To Do");
    assertEquals("Invalid ID", invalidTask2.createTaskID(), "Expected 'Invalid ID' for negative task number.");

    Task invalidTask3 = new Task("Task", 1, "Description", null, 5, "To Do");
    assertEquals("Invalid ID", invalidTask3.createTaskID(), "Expected 'Invalid ID' for null developer details.");
}

@Test
public void testTotalHoursAccumulation() {
    assertEquals(18, Task.returnTotalHours(tasks), 
        "Total hours for the default tasks list should be 18.");

    List<Task> largeTaskList = List.of(
        new Task("Task1", 1, "Description", "Dev1", 10, "To Do"),
        new Task("Task2", 2, "Description", "Dev2", 12, "Doing"),
        new Task("Task3", 3, "Description", "Dev3", 55, "Done"),
        new Task("Task4", 4, "Description", "Dev4", 11, "To Do"),
        new Task("Task5", 5, "Description", "Dev5", 1, "Doing")
    );

    assertEquals(89, Task.returnTotalHours(largeTaskList), "Total hours for the large task list should be 89.");
}
}