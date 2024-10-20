/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10439619poepartone;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;



public class St10439619POEPARTONE {
    private static List<User> userList = new ArrayList<>();
    private static List<Task> taskList = new ArrayList<>();
    private static Login loginService = new Login();
    private static boolean isLoggedIn = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Registration System");
        
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
        loginUser(scanner);
        break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        if (isLoggedIn) {
                showEasyKanbanMenu(scanner);
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

    private static void loginUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (loginService.loginUser(username, password, userList)) {
            System.out.println(loginService.returnLoginStatus(true));
            isLoggedIn = true;
        } else {
            System.out.println(loginService.returnLoginStatus(false));
        }
    }
    private static void showEasyKanbanMenu(Scanner scanner) {
        System.out.println("Welcome to EasyKanban");
        
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Add tasks");
            System.out.println("2. Show report");
            System.out.println("3. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTasks(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon");
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0); // Exit the program
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add tasks
    private static void addTasks(Scanner scanner) {
        System.out.println("How many tasks would you like to enter?");
        int numberOfTasks = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("Enter task name:");
            String taskName = scanner.nextLine();

            System.out.println("Enter task description:");
            String taskDescription = scanner.nextLine();

            Task task = new Task(taskName, taskDescription);
            taskList.add(task);
            System.out.println("Task added successfully!");
        }
    }
}

// Task class to store task details
class Task {
    private String taskName;
    private String taskDescription;

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

        //Create a placeholder User
        User newUser = new User(username, password, "FirstName", "LastName");
        // Add a user to the userList
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
        if (isLoggedIn) {
            return "Welcome! It is great to see you .";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    private boolean containsUppercase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
                
            }
        }
        return false;
    }
// Define the special characters
    private boolean containsSpecialCharacter(String str) {
        String specialChars = "!@#$%^&+=()"; 
        for (char c : str.toCharArray()) {
            if (specialChars.indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
    }
}
