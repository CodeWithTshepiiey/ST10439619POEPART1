/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10439619poepartone;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class St10439619POEPARTONE {
    private static List<User> userList = new ArrayList<>();
    private static List<Task> taskList = new ArrayList<>();
    private static Login loginService = new Login();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Registration System");

        
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true); 

        while (true) {
            System.out.println("Please select one of the following options:");
            System.out.println("1. Register");
            System.out.println("2. Login");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    if (loginUser(scanner)) {
                        showMainMenu(scanner, dialog);
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        String registrationMessage = loginService.registerUser(username, password, userList);
        System.out.println(registrationMessage);
    }

    private static boolean loginUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (loginService.loginUser(username, password, userList)) {
            // Display welcome message using JOptionPane
            JOptionPane.showMessageDialog(null, loginService.returnLoginStatus(true));
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
            return true;
        } else {
            System.out.println(loginService.returnLoginStatus(false));
            return false;
        }
    }

    private static void showMainMenu(Scanner scanner, JDialog dialog) {
        boolean quit = false;

        while (!quit) {
            // Show the main menu using JOptionPane
            String menuMessage = "Please select an option:\n" +
                                 "1. Add tasks\n" +
                                 "2. Show report\n" +
                                 "3. Display 'Done' tasks\n" +
                                 "4. Display task with the longest duration\n" +
                                 "5. Search for a task by name\n" +
                                 "6. Search tasks by developer\n" +
                                 "7. Delete a task by name\n" +
                                 "8. Quit";
            String optionString = JOptionPane.showInputDialog(dialog, menuMessage);

            if (optionString == null) { 
                System.out.println("Exiting EasyKanban. Goodbye!");
                System.exit(0);
            }

            int option = Integer.parseInt(optionString);

            switch (option) {
                case 1:
                    addTasks(scanner, dialog);
                    break;
                case 2:
                    showReport(dialog);
                    break;
                case 3:
                    StringBuilder doneTasks = new StringBuilder("Tasks with status 'Done':\n");
                    boolean found = false;
                    for (Task task : taskList) {
                        if (task.getTaskStatus().equalsIgnoreCase("Done")) {
                            doneTasks.append(task.printTaskDetails()).append("\n\n");
                            found = true;
                        }
                    }
                    if (!found) {
                        doneTasks.append("No tasks with status 'Done'.");
                    }
                    JOptionPane.showMessageDialog(dialog, doneTasks.toString());
                    break;
                case 4:
                    if (taskList.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "No tasks available.");
                    } else {
                        int maxDuration = 0;
                        Task longestTask = null;
                        for (Task task : taskList) {
                            if (task.getTaskDuration() > maxDuration) {
                                maxDuration = task.getTaskDuration();
                                longestTask = task;
                            }
                        }
                        JOptionPane.showMessageDialog(dialog, "Task with the longest duration:\n" + longestTask.printTaskDetails());
                    }
                    break;
                case 5:
                    String searchName = JOptionPane.showInputDialog(dialog, "Enter task name to search for:");
                    if (searchName != null) {
                        boolean taskFound = false;
                        for (Task task : taskList) {
                            if (task.getTaskName().equalsIgnoreCase(searchName)) {
                                JOptionPane.showMessageDialog(dialog, "Task found:\n" + task.printTaskDetails());
                                taskFound = true;
                                break;
                            }
                        }
                        if (!taskFound) {
                            JOptionPane.showMessageDialog(dialog, "Task with name '" + searchName + "' not found.");
                        }
                    }
                    break;
                case 6:
                    String developerName = JOptionPane.showInputDialog(dialog, "Enter developer name to search for tasks:");
                    if (developerName != null) {
                        StringBuilder developerTasks = new StringBuilder("Tasks assigned to " + developerName + ":\n");
                        boolean foundDev = false;
                        for (Task task : taskList) {
                            if (task.getDeveloperDetails().equalsIgnoreCase(developerName)) {
                                developerTasks.append(task.printTaskDetails()).append("\n\n");
                                foundDev = true;
                            }
                        }
                        if (!foundDev) {
                            developerTasks.append("No tasks assigned to " + developerName + ".");
                        }
                        JOptionPane.showMessageDialog(dialog, developerTasks.toString());
                    }
                    break;
                case 7:
                    String deleteTaskName = JOptionPane.showInputDialog(dialog, "Enter task name to delete:");
                    if (deleteTaskName != null) {
                        boolean taskDeleted = false;
                        for (int i = 0; i < taskList.size(); i++) {
                            if (taskList.get(i).getTaskName().equalsIgnoreCase(deleteTaskName)) {
                                taskList.remove(i);
                                JOptionPane.showMessageDialog(dialog, "Task '" + deleteTaskName + "' has been deleted.");
                                taskDeleted = true;
                                break;
                            }
                        }
                        if (!taskDeleted) {
                            JOptionPane.showMessageDialog(dialog, "Task with name '" + deleteTaskName + "' not found.");
                        }
                    }
                    break;
                    case 8:
                    quit = true;
                    System.out.println("Exiting EasyKanban. Goodbye!");
                    break;
                default:
                    JOptionPane.showMessageDialog(dialog, "Invalid option. Please try again.");
            }
        }
    }

    private static void addTasks(Scanner scanner, JDialog dialog) {
        String taskCountString = JOptionPane.showInputDialog(dialog, "How many tasks would you like to add?");
        
        if (taskCountString == null) { 
            return; 
        }

        int taskCount = Integer.parseInt(taskCountString);

        for (int i = 0; i < taskCount; i++) {
            // Input Task Details using JOptionPane
            String taskName = JOptionPane.showInputDialog(dialog, "Enter Task Name:");
            String taskDescription;

            while (true) {
                taskDescription = JOptionPane.showInputDialog(dialog, "Task Description (max 50 characters):");
                if (taskDescription != null && taskDescription.length() <= 50) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please enter a task description of less than 50 characters.");
                }
            }

            String devFirstName = JOptionPane.showInputDialog(dialog, "Developer's First Name:");
            String devLastName = JOptionPane.showInputDialog(dialog, "Developer's Last Name:");

            String taskDurationString = JOptionPane.showInputDialog(dialog, "Estimated Task Duration (in hours):");
            int taskDuration = Integer.parseInt(taskDurationString);

            String statusMessage = "Select Task Status:\n1. To Do\n2. Doing\n3. Done";
            String statusChoiceString = JOptionPane.showInputDialog(dialog, statusMessage);
            int statusChoice = Integer.parseInt(statusChoiceString);
            String taskStatus;
            switch (statusChoice) {
                case 1:
                    taskStatus = "To Do";
                    break;
                case 2:
                    taskStatus = "Doing";
                    break;
                case 3:
                    taskStatus = "Done";
                    break;
                default:
                    taskStatus = "Unknown";
            }
            
            Task task = new Task(taskName, i, taskDescription, devFirstName + " " + devLastName, taskDuration, taskStatus);
            taskList.add(task);

            
            JOptionPane.showMessageDialog(dialog, task.printTaskDetails());
        }
    }

    private static void showReport(JDialog dialog) {
        StringBuilder report = new StringBuilder("Report\n");
        for (Task task : taskList) {
            report.append(task.printTaskDetails()).append("\n\n");
        }
        JOptionPane.showMessageDialog(dialog, report.toString());
    }
    private static void searchTaskByTaskName(Scanner scanner) {
        System.out.println("Enter the task name to search for:");
        String searchName = scanner.nextLine();
        Task.searchTaskByTaskName(taskList, searchName);
    }

    private static void searchTasksByDeveloper(Scanner scanner) {
        System.out.println("Enter the developer name to search for tasks:");
        String developerName = scanner.nextLine();
        Task.searchTasksByDeveloper(taskList, developerName);
    }

    private static void deleteTaskByName(Scanner scanner) {
        System.out.println("Enter the task name to delete:");
        String deleteTaskName = scanner.nextLine();
        Task.deleteTaskByName(taskList, deleteTaskName);
    }
}

class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Login {
    public boolean checkUserName(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && 
               containsUppercase(password) && 
               containsDigit(password) && 
               containsSpecialCharacter(password);
    }

    public String registerUser(String username, String password, List<User> userList) {
        if (!checkUserName(username)) {
            return "Username is incorrectly formatted. It must contain an underscore and be no more than 5 characters.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password does not meet complexity requirements. Ensure it has at least 8 characters, a capital letter, a number, and a special character.";
        }

        User newUser = new User(username, password, "FirstName", "LastName");
        userList.add(newUser); 
        return "User registered successfully!";
    }

    public boolean loginUser(String username, String password, List<User> userList) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        return isLoggedIn ? "Welcome! It is great to see you." : "Username or password incorrect, please try again.";
    }

    private boolean containsUppercase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) return true;
        }
        return false;
    }

    private boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }

    private boolean containsSpecialCharacter(String str) {
        String specialChars = "!@#$%^&+=()";
        for (char c : str.toCharArray()) {
            if (specialChars.indexOf(c) >= 0) return true;
        }
        return false;
    }
}

class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;
    public static List<String> developers = new ArrayList<>();
    public static List<String> taskNames = new ArrayList<>();
    public static List<String> taskIDs = new ArrayList<>();
    public static List<Integer> taskDurations = new ArrayList<>();
    public static List<String> taskStatuses = new ArrayList<>();

    // Constructor
    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        setTaskDescription(taskDescription);
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskID = createTaskID();
        this.taskStatus = taskStatus;
    }
    public static void addTasksToArray(Task task) {
        developers.add(task.getDeveloperDetails());
        taskNames.add(task.getTaskName());
        taskIDs.add(task.createTaskID());
        taskDurations.add(task.getTaskDuration());
        taskStatuses.add(task.getTaskStatus());
    }
    public static void showDoneTasks(List<Task> taskList) {
    System.out.println("Tasks with status 'Done':");
    boolean found = false;
    for (Task task : taskList) {
        if (task.getTaskStatus().equalsIgnoreCase("Done")) {
            System.out.println(task.printTaskDetails());
            found = true;
        }
    }
    if (!found) {
        System.out.println("No tasks with status 'Done'.");
    }
}
    public static void showTaskWithLongestDuration() {
    if (taskDurations.isEmpty()) {
        System.out.println("No tasks available.");
        return;
    }

    int maxDuration = 0;
    int index = 0;

    for (int i = 0; i < taskDurations.size(); i++) {
        if (taskDurations.get(i) > maxDuration) {
            maxDuration = taskDurations.get(i);
            index = i;
        }
    }
    

    System.out.println("Task with the longest duration:");
    System.out.println("Developer: " + developers.get(index));
    System.out.println("Duration: " + maxDuration + " hours");
}
    public static void searchTaskByTaskName(List<Task> taskList, String searchName) {
    for (Task task : taskList) {
        if (task.getTaskName().equalsIgnoreCase(searchName)) {
            System.out.println("Task found:");
            System.out.println(task.printTaskDetails());
            return;
        }
    }
    System.out.println("Task with name '" + searchName + "' not found.");
}
    public static void searchTasksByDeveloper(List<Task> taskList, String developerName) {
    System.out.println("Tasks assigned to " + developerName + ":");
    boolean found = false;
    for (Task task : taskList) {
        if (task.getDeveloperDetails().equalsIgnoreCase(developerName)) {
            System.out.println(task.printTaskDetails());
            found = true;
        }
    }
    if (!found) {
        System.out.println("No tasks assigned to " + developerName + ".");
    }
}
    public static void deleteTaskByName(List<Task> taskList, String deleteTaskName) {
    for (int i = 0; i < taskList.size(); i++) {
        if (taskList.get(i).getTaskName().equalsIgnoreCase(deleteTaskName)) {
            taskList.remove(i);
            System.out.println("Task '" + deleteTaskName + "' has been deleted.");
            return;
        }
    }
    System.out.println("Task with name '" + deleteTaskName + "' not found.");
}
    

    // Check task description
    public boolean checkTaskDescription() {
        return taskDescription != null && taskDescription.length() <= 50;
    }

    // Create task ID
    public String createTaskID() {
       if (taskName == null || taskName.isEmpty() || developerDetails == null || developerDetails.isEmpty() || taskNumber < 0) {
        return "Invalid ID";
    } 
    String taskNamePart = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
    String developerPart = "Invalid ID"; 
    
    String[] developerNameParts = developerDetails.split(" ");
    if (developerNameParts.length > 1) {
        String lastName = developerNameParts[developerNameParts.length - 1];
        developerPart = lastName.length() >= 3 ? lastName.substring(lastName.length() - 3).toUpperCase() : lastName.toUpperCase();
    }
    
    return taskNamePart + ":" + taskNumber + ":" + developerPart;
}

    // Print task details
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer Details: " + developerDetails + "\n" +
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskID + "\n" +
               "Duration: " + taskDuration + " hours";
    }

    // Return total hours
    public static int returnTotalHours(List<Task> tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.taskDuration;
        }
        return totalHours;
    }
     public String getDeveloperDetails() {
        return developerDetails;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    // Setter for taskDescription with validation
    public void setTaskDescription(String taskDescription) {
        if (taskDescription == null) {
            throw new IllegalArgumentException("Task description cannot be null.");
        }
        if (taskDescription.length() <= 50) {
            this.taskDescription = taskDescription;
        } else {
            throw new IllegalArgumentException("Task description must be 50 characters or less.");
        }
    }
}