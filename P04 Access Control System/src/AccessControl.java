//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: AccessControl
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Maintains access to shared computers. An instance is a single terminal/computer that a valid user
 * can login to.
 */
public class AccessControl {

  /*
   * NOTE: An instance of AccessControl represents a single terminal/computer. The AccessControl
   * system as a whole allows many users to be logged in at once-just at different terminals.
   * Therefore, the currentUser is made an instance variable, while the ArrayList of all valid users
   * and the default password are made as static class variables.
   */

  // data fields
  private static ArrayList<User> users; // An ArrayList of valid users
  private User currentUser; // Reference to the currently logged in user, if anyone.

  // static final data fields can be initialized ONLY where it is declared outside of any method
  // including the constructor of the class

  // Default password given to new users or when we reset a password of a specific user.
  private static final String DEFAULT_PASSWORD = "changeme";

  /**
   * A no argument constructor. Creates a new AccessControl object. Initializes any non-static
   * fields. Also checks whether each class variable (static data field) has been initialized and if
   * not, initialize them.
   */
  public AccessControl() {

    if (users == null) {

      users = new ArrayList<User>();
      // specification requires at least this User in users list
      users.add(new User("admin", "root", true));
    }

    this.currentUser = null;
  }

  /**
   * Report whether a given username/password pair is a valid login. Static since it only relies on
   * the static users ArrayList. Should not have any side-effects.
   * 
   * @param username the username to be checked
   * @param password the password to be checked
   * @return true if username/password pair matches any user in your usersArrayList, and false
   *         otherwise
   */
  public static boolean isValidLogin(String username, String password) {

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username) && users.get(i).isValidLogin(password)) {

        return true;
      }
    }

    return false;
  }

  /**
   * Changes the current password of the current user to be the given newPassword. Operates on
   * currentUser, so does not take a username as a parameter.
   * 
   * @param newPassword the new password to replace current password of current user
   */
  public void changePassword(String newPassword) {

    // Recall that currentUser is an instance field because each new AccessControl instance is a
    // separate terminal/computer. Therefore, it is good practice to use "this."
    this.currentUser.setPassword(newPassword);
  }

  /**
   * Log out the current user. Set currentUser to null. Operates on currentUser, so does not take a
   * username as a parameter.
   */
  public void logout() {

    this.currentUser = null;
  }

  /**
   * A mutator you can use to write tests without simulating user input. It sets the current user to
   * the user from the users list whose username matches the string provided as input to the method
   * (exact match case sensitive). If NO match found with username within the ArrayList users, this
   * method does nothing. Should not be used by any other method in the AccessControl class, but may
   * be very useful for writing test methods.
   * 
   * @param username the username of the User to set currentUser to
   */
  public void setCurrentUser(String username) {

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username)) {

        this.currentUser = users.get(i);
        break; // unnecessary to continue the for loop since a match was found
      }
    }
  }

  /**
   * Creates a new user (non-admin) with the default password and isAdmin==false and adds it to the
   * users ArrayList.
   * 
   * @param username the username of the new User to be created and added
   * @return true if the current user has Admin power and the new user was successfully added.
   *         Return false if the the current user is null or does not have Admin power.
   * @throws IllegalArgumentException with a descriptive error message if username is null or if its
   *                                  length is less than 5 ( < 5), or if a user with the same
   *                                  username is already in the list of users (usernames must be
   *                                  unique)
   */
  public boolean addUser(String username) throws IllegalArgumentException {

    if (this.currentUser == null || this.currentUser.getIsAdmin() == false) {

      return false;
    }

    if (username == null || username.length() < 5) {

      throw new IllegalArgumentException("username is null or has a length less than 5!");
    }

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username)) {

        throw new IllegalArgumentException("A user with the same username already exists!");
      }
    }

    users.add(new User(username, DEFAULT_PASSWORD, false));
    return true; // only returns true if User is added to list successfully
  }

  /**
   * Creates a new user, specifies their admin status, and adds it to the list of users.
   * 
   * @param username the username of the new User to be created and added
   * @param isAdmin  the admin status of the new User to be created and added
   * @return true if the current user has Admin power and the new user was successfully added.
   *         Return false if the the current user is null or does not have Admin power.
   * @throws IllegalArgumentException with a descriptive error message if username is null or if its
   *                                  length is less than 5 ( < 5), or if a user with the same
   *                                  username is already in the list of users.
   */
  public boolean addUser(String username, boolean isAdmin) throws IllegalArgumentException {

    if (this.currentUser == null || this.currentUser.getIsAdmin() == false) {

      return false;
    }

    if (username == null || username.length() < 5) {

      throw new IllegalArgumentException("username is null or has a length less than 5!");
    }

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username)) {

        throw new IllegalArgumentException("A user with the same username already exists!");
      }
    }

    users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
    return true;
  }

  /**
   * Removes a user given their unique username.
   * 
   * @param username the username of the User to be removed
   * @return true if the current user has Admin powers and the user whose username is passed as
   *         input was successfully removed. Return false if the the current user is null or does
   *         not have Admin power.
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean removeUser(String username) throws NoSuchElementException {

    if (this.currentUser == null || this.currentUser.getIsAdmin() == false) {

      return false;
    }

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username)) {

        users.remove(i);
        return true;
      }
    }

    // exception is only thrown after indexing through entire list and not finding match
    throw new NoSuchElementException("No match with username is found in list of users!");
  }

  /**
   * Gives a user admin power.
   * 
   * @param username the username of the User to be given admin power
   * @return true if this operation terminates successfully. Return false if the current user is
   *         null or does not have admin powers.
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean giveAdmin(String username) throws NoSuchElementException {

    if (this.currentUser == null || this.currentUser.getIsAdmin() == false) {

      return false;
    }

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username)) {

        users.get(i).setIsAdmin(true);
        return true;
      }
    }

    throw new NoSuchElementException("No match with username is found in list of users!");
  }

  /**
   * Removes the admin power of a user given their username.
   * 
   * @param username the username of the User to lose their admin power
   * @return true if this operation terminates successfully. Return false if the current user is
   *         null or does not have admin powers.
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean takeAdmin(String username) throws NoSuchElementException {

    if (this.currentUser == null || this.currentUser.getIsAdmin() == false) {

      return false;
    }

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username)) {

        users.get(i).setIsAdmin(false);
        return true;
      }
    }

    throw new NoSuchElementException("No match with username is found in list of users!");
  }

  /**
   * Resets the password of a user given their username.
   * 
   * @param username the username of the User to have their password reset
   * @return true if this operation terminates successfully. Return false if the current user is
   *         null or does not have admin powers.
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean resetPassword(String username) throws NoSuchElementException {

    if (this.currentUser == null || this.currentUser.getIsAdmin() == false) {

      return false;
    }

    for (int i = 0; i < users.size(); i++) {

      if (users.get(i).getUsername().equals(username)) {

        users.get(i).setPassword(DEFAULT_PASSWORD);
        return true;
      }
    }

    throw new NoSuchElementException("No match with username is found in list of users!");
  }

  /**
   * Main method
   * 
   * @param args
   */
  public static void main(String[] args) {


  }
}
