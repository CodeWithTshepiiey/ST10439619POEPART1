/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10439619poepartone;
import java.util.Scanner;
/**
 *
 * @author Makgotso Mokgoko
 */
public class St10439619POEPARTONE {

    public static void main(String[] args) {
        System.out.println("Welcome to the Registration System");
        registerUser();
    }

    private static void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        String usernameMessage = validateUsername(username);
        System.out.println(usernameMessage);

        System.out.println("Enter password:");
        String password = scanner.nextLine();
        String passwordMessage = validatePassword(password);
        System.out.println(passwordMessage);

        if (usernameMessage.startsWith("Username successfully captured") &&
            passwordMessage.startsWith("Password successfully captured")) {
            System.out.println("Please enter your first name:");
            String firstName = scanner.nextLine();

            System.out.println("Please enter your last name:");
            String lastName = scanner.nextLine();

            User user = new User(username, password, firstName, lastName);
            // Here you can save the user object to a database or a collection
            System.out.println("User registered successfully!");
        }
    }

    private static String validateUsername(String username) {
        // Check if the username is no more than 5 characters and contains an underscore
        if (username.length() <= 5 && username.contains("_")) {
            return "Username successfully captured.";
        }
        return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
    }

    private static String validatePassword(String password) {
        // Check the password length, presence of uppercase letter, digit, and special character
        if (password.length() >= 8 && containsUppercase(password) && containsDigit(password) && containsSpecialCharacter(password)) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
    }

    private static boolean containsUppercase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
// Define special characters
    private static boolean containsSpecialCharacter(String str) {
        String specialChars = "!@#$%^&+=()"; 
        for (char c : str.toCharArray()) {
            if (specialChars.indexOf(c) >= 0) {
                return true;
            }
        }
        return false;
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
}