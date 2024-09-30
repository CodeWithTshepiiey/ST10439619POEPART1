/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.st10439619poepartone;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;




public class LoginTest {
    private Login loginService;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
        loginService = new Login();
        userList = new ArrayList<>();
        // Add a user to userlist
        userList.add(new User("kyl_1", "Ch&&sec@ke99!", "Kyl", "Smith"));
    }

    @Test
    public void testCheckUserNameCorrectFormat() {
        assertTrue(loginService.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrectFormat() {
        assertFalse(loginService.checkUserName("kyle!!!!!!!!"));
    }

    @Test
    public void testPasswordComplexitySuccess() {
        assertTrue(loginService.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordComplexityFailure() {
        assertFalse(loginService.checkPasswordComplexity("password"));
    }

    @Test
    public void testLoginSuccessful() {
        assertTrue(loginService.loginUser("kyl_1", "Ch&&sec@ke99!", userList));
    }

    @Test
    public void testLoginFailed() {
        assertFalse(loginService.loginUser("nonexistent", "wrongpass", userList));
    }

    @Test
public void testRegisterUserSuccess() {
    String message = loginService.registerUser("new_u", "Ch&&sec@ke99!", userList);
    assertEquals("User registered successfully!", message);
}

@Test
public void testRegisterUserFailure() {
    String message = loginService.registerUser("new_u", "password", userList);
    assertEquals("Password does not meet complexity requirements. Ensure it has at least 8 characters, a capital letter, a number, and a special character.", message);
}
}   
