//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: AccessControlTester
// Course: CS 300 Spring 2022
//
// Author: Sai Gungurthi
// Email: sgungurthi@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * Tests the methods of the User and AccessControl classes.
 */
public class AccessControlTester {

  /**
   * Checks for the correctness of the constructor and all the accessor and mutator methods defined
   * in the User class.
   * 
   * @return true if all test scenarios pass, and false otherwise
   */
  public static boolean testUserConstructorAndMethods() {

    // declaring and initializing test arguments
    String username = "TestUsername";
    String password = "TestPassword";
    boolean isAdmin = false;

    // creating new User by calling constructor
    User test = new User(username, password, false);

    // testing constructor and getUsername()
    if (!test.getUsername().equals(username)) {
      System.out.println("username is incorrect!");
      return false;
    }

    // testing constructor and isValidLogin()
    if (!test.isValidLogin(password)) {
      System.out.println("password is incorrect!");
      return false;
    }

    // testing constructor and getIsAdmin()
    if (test.getIsAdmin() != isAdmin) {
      System.out.println("isAdmin is incorrect!");
      return false;
    }

    // testing setIsAdmin()
    test.setIsAdmin(true);

    if (test.getIsAdmin() != true) {
      System.out.println("isAdmin is incorrect!");
      return false;
    }

    // testing setPassword()
    String password2 = "TestPassword2";
    test.setPassword(password2);

    if (!test.isValidLogin(password2)) {
      System.out.println("password is incorrect!");
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of AccessControl.isValidLogin() method when called with incorrect
   * username or not matching (username, password) pair.
   * 
   * @return true if all test scenarios pass, and false otherwise
   */
  public static boolean testAccessControlIsValidLoginNotValidUser() {

    // testing not matching username and password pair
    AccessControl test = new AccessControl();
    String username = "TestUsername";
    String password = "TestPassword";
    if (AccessControl.isValidLogin(username, password)) {
      System.out.println("isValidLogin should return false!");
      return false;
    }

    // testing incorrect username
    if (AccessControl.isValidLogin("Username", password)) {
      System.out.println("isValidLogin should return false!");
      return false;
    }

    // testing correct username and password pair
    test.setCurrentUser("admin"); // necessary to use addUser()
    test.addUser(username);
    if (!AccessControl.isValidLogin(username, "changeme")) {
      System.out.println("isValidLogin should return true!");
      return false;
    }
    test.removeUser(username); // otherwise test User would remain in users list after test is over

    return true;
  }

  /**
   * Creates a new AccessControl object and does not log in an admin. This test must fail if
   * addUser(String username) does not return false or if a user was added to the list of users
   * after the method returns.
   * 
   * @return true if all test scenarios pass, and false otherwise
   */
  public static boolean testAddUserWithNoAdminPowers() {

    // testing addUser() without an admin logged in
    try {
      AccessControl test = new AccessControl();
      String username = "TestUsername";
      if (test.addUser(username)) {
        System.out.println("addUser() should return false!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Exception was thrown!");
      return false; // an exception should not be thrown in this test
    }

    return true;
  }

  /**
   * Checks the correctness of addUser and removeUser methods when the current user has admin powers
   * 
   * @return true if all test scenarios pass, and false otherwise
   */
  public static boolean testAddRemoveUserWithAdminPowers() {

    System.out.println("testAddRemoveUserWithAdminPowers():");

    // testing addUser() with username with length less than 5
    try {
      AccessControl test = new AccessControl();
      String username = "test";
      test.setCurrentUser("admin");
      if (test.addUser(username)) {
        System.out.println("IllegalArgumentException should be thrown!");
        return false;
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // testing addUser() with username that is null
    try {
      AccessControl test = new AccessControl();
      String username = null;
      test.setCurrentUser("admin");
      if (test.addUser(username, true)) {
        System.out.println("IllegalArgumentException should be thrown!");
        return false;
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // testing addUser() with already existing username
    try {
      AccessControl test = new AccessControl();
      String username = "admin";
      test.setCurrentUser("admin");
      if (test.addUser(username)) {
        System.out.println("IllegalArgumentException should be thrown!");
        return false;
      }
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // testing addUser() with valid username
    try {
      AccessControl test = new AccessControl();
      String username = "TestUsername";
      test.setCurrentUser("admin");
      if (!test.addUser(username, false)) {
        System.out.println("addUser() should return true!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Exception was thrown!");
      return false;
    }

    // testing removeUser() with non-existing username
    try {
      AccessControl test = new AccessControl();
      String username = "FakeUsername";
      test.setCurrentUser("admin");
      if (!test.removeUser(username)) {
        System.out.println("removeUser() should return true!");
        return false;
      }
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Exception was thrown!");
      return false;
    }

    // testing removeUser() with valid username
    try {
      AccessControl test = new AccessControl();
      String username = "TestUsername";
      test.setCurrentUser("admin");
      if (!test.removeUser(username)) {
        System.out.println("removeUser() should return true!");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Exception was thrown!");
      return false;
    }

    return true;
  }

  /**
   * Calls all the test methods in this class and returns true if all of them return true.
   * 
   * @return true if all test methods return true, and false otherwise
   */
  public static boolean runAllTests() {

    return testUserConstructorAndMethods() && testAccessControlIsValidLoginNotValidUser()
        && testAddUserWithNoAdminPowers() && testAddRemoveUserWithAdminPowers();
  }

  /**
   * Main method
   * 
   * @param args
   */
  public static void main(String[] args) {

    System.out.println("runAllTests(): " + runAllTests()); // one line to call all test methods
  }
}
