/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10439619poepartone;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Otshepeng Mokgoko
 */
public class St10439619POEPARTONE {
    private static List<User> userList = new ArrayList<>();
    private static Login loginService = new Login();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Registration System");
        while (true) {
        System.out.println("Please choose an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
         registerUser(scanner);
         break;
            case 2:
        loginUser(scanner);
        break;
            case 3:
                System.out.println("Exiting the system. Goodbye!");
                // Exit the program
                return;
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

    private static void loginUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (loginService.loginUser(username, password, userList)) {
            System.out.println(loginService.returnLoginStatus(true));
        } else {
            System.out.println(loginService.returnLoginStatus(false));
        }
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
        // Add user to the userList
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
            return "Welcome back! It is great to see you again.";
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
