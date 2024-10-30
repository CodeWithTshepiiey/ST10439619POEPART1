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
    String username = ""; // Test with an empty username
    Login instance = new Login();
    boolean expResult = false; // Expecting it to be false for an empty username
    boolean result = instance.checkUserName(username);
    assertEquals(expResult, result);
}

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
public void testCheckPasswordComplexity() {
    System.out.println("checkPasswordComplexity");
    String password = "Ch&&sec@ke99!"; // Use a complex password for testing
    Login instance = new Login();
    boolean expResult = true; // Expect it to return true for a valid password
    boolean result = instance.checkPasswordComplexity(password);
    assertEquals(expResult, result);

    password = ""; // Test with an empty password
    expResult = false; // Expect it to return false for an empty password
    result = instance.checkPasswordComplexity(password);
    assertEquals(expResult, result);
}

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
public void testRegisterUser() {
    System.out.println("registerUser");
    
    String username = ""; // Test with an empty username
    String password = ""; // Test with an empty password
    List<User> userList = new ArrayList<>(); // Initialize the user list
    Login instance = new Login();
    
    // Expecting an error message for empty fields
    String expResult = "Username and password cannot be empty.";
    String result = instance.registerUser(username, password, userList);
    assertEquals(expResult, result);
    
    // Now test with a valid username but an empty password
    username = "valid_user";
    password = ""; // Test with an empty password
    expResult = "Username and password cannot be empty.";
    result = instance.registerUser(username, password, userList);
    assertEquals(expResult, result);
    
    // Now test with an empty username but a valid password
    username = ""; // Test with an empty username
    password = "Ch&&sec@ke99!";
    expResult = "Username and password cannot be empty.";
    result = instance.registerUser(username, password, userList);
    assertEquals(expResult, result);
    
    // Finally, test with a valid username and password
    username = "new_user"; // Use a valid username
    password = "Ch&&sec@ke99!"; // Use a valid password
    expResult = "User registered successfully!";
    result = instance.registerUser(username, password, userList);
    assertEquals(expResult, result);
}

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
public void testLoginUser() {
    System.out.println("loginUser");

    // Set up test inputs
    String username = ""; // Test with an empty username
    String password = ""; // Test with an empty password
    List<User> userList = new ArrayList<>(); // Initialize with an empty list

    // Create an instance of the Login class
    Login instance = new Login();

    // Expected result should be false, as login with empty credentials should fail
    boolean expResult = false;

    // Call the loginUser method
    boolean result = instance.loginUser(username, password, userList);

    // Assert the result is as expected
    assertEquals(expResult, result);

    // Optionally, add tests for valid credentials here if needed
}


    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
public void testReturnLoginStatus() {
    System.out.println("returnLoginStatus");

    // Test when the user is logged in
    boolean isLoggedIn = true;
    Login instance = new Login();
    String expectedLoggedInResult = "Welcome! It is great to see you."; // Adjusted expected result
    String resultLoggedIn = instance.returnLoginStatus(isLoggedIn);
    assertEquals(expectedLoggedInResult, resultLoggedIn);
    
    // Test when the user is logged out
    isLoggedIn = false;
    String expectedLoggedOutResult = "Logged Out"; // Assuming this is the expected result
    String resultLoggedOut = instance.returnLoginStatus(isLoggedIn);
    assertEquals(expectedLoggedOutResult, resultLoggedOut);
}
}
