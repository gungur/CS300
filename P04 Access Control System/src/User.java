//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: User
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

/**
 * Simple instantiable class with accessor and mutator methods. Utilitized by AccessControl class by
 * keeping a list of valid users.
 */
public class User {

  // data fields

  // final instance fields must be initialized in the constructor of the class only
  // we set its value when we create the user, but we don't change it after that (hence final field)
  private final String USERNAME; // The name of the user
  private String password; // The password of the user
  private boolean isAdmin; // Whether or not the user has Admin powers

  /**
   * Constructor which creates a new user with the given username, password, and admin status
   * 
   * @param username the name of the user
   * @param password the password of the user
   * @param isAdmin  whether or not the user has Admin powers
   */
  public User(String username, String password, boolean isAdmin) {

    // "this." is used to specify the instance's field rather than the argument with same name
    this.USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  /**
   * Report whether the password is correct (whether the password provided as input matches the
   * password of the user).
   * 
   * @param password the password to be compared to the actual password field of a User instance
   * @return true if password argument matches password instance field, and false otherwise
   */
  public boolean isValidLogin(String password) {

    if (this.password.equals(password)) {
      return true;
    }

    return false;
  }

  /**
   * Return the name of the user
   * 
   * @return the name of the user
   */
  public String getUsername() {

    return this.USERNAME;
  }

  /**
   * Report whether the user is an admin
   * 
   * @return whether the user is an admin
   */
  public boolean getIsAdmin() {

    return this.isAdmin;
  }

  /**
   * Set the new password
   * 
   * @param password the new password
   */
  public void setPassword(String password) {

    this.password = password;
  }

  /**
   * Set the new admin status
   * 
   * @param isAdmin the new admin status
   */
  public void setIsAdmin(boolean isAdmin) {

    this.isAdmin = isAdmin;
  }
}
