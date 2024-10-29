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

        // Create dialog for JOptionPane
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);  // Ensure JOptionPane appears on top

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
                                 "3. Quit";
            String optionString = JOptionPane.showInputDialog(dialog, menuMessage);

            if (optionString == null) { // Handle cancel option
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
        
        if (taskCountString == null) { // Handle cancel option
            return; // Exit the task addition if canceled
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

            String devFirstName = JOptionPane.showInputDialog(dialog, "Assigned Developer's First Name:");
            String devLastName = JOptionPane.showInputDialog(dialog, "Assigned Developer's Last Name:");

            String taskDurationString = JOptionPane.showInputDialog(dialog, "Estimated Task Duration (in hours):");
            int taskDuration = Integer.parseInt(taskDurationString);

            // Select Task Status
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

            // Create and store the task
            Task task = new Task(taskName, i, taskDescription, devFirstName + " " + devLastName, taskDuration, taskStatus);
            taskList.add(task);

            // Display task information using JOptionPane with dialog owner
            JOptionPane.showMessageDialog(dialog, task.printTaskDetails());
        }
    }

    private static void showReport(JDialog dialog) {
        // Example report to show all tasks
        StringBuilder report = new StringBuilder("Current Tasks:\n");
        for (Task task : taskList) {
            report.append(task.printTaskDetails()).append("\n\n");
        }
        JOptionPane.showMessageDialog(dialog, report.toString());
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

    // Constructor
    public Task(String taskName, int taskNumber, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        // Directly validate and set taskDescription
        setTaskDescription(taskDescription);
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskID = createTaskID();
        this.taskStatus = taskStatus;
    }

    // Check task description
    public boolean checkTaskDescription() {
        return taskDescription != null && taskDescription.length() <= 50;
    }

    // Create task ID
    public String createTaskID() {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" +
               developerDetails.substring(developerDetails.length() - 3).toUpperCase();
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
