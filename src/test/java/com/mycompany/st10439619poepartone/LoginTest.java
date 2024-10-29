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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;




public class LoginTest {
    private Login loginService;
    private List<User> userList;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() {
        loginService = new Login();
        userList = new ArrayList<>();
        // Add a user to userlist
        userList.add(new User("kyl_1", "Ch&&sec@ke99!", "Kyl", "Smith"));
    }

    @AfterEach
    public void tearDown() throws Exception {
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

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "";
        String password = "";
        List<User> userList = null;
        Login instance = new Login();
        String expResult = "";
        String result = instance.registerUser(username, password, userList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "";
        String password = "";
        List<User> userList = null;
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.loginUser(username, password, userList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean isLoggedIn = false;
        Login instance = new Login();
        String expResult = "";
        String result = instance.returnLoginStatus(isLoggedIn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}   
