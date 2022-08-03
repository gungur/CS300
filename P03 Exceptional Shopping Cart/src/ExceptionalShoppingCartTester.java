//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ExceptionalShoppingCartTester
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Tests that the exception handling in the ExceptionalShoppingCart methods are correct.
 */
public class ExceptionalShoppingCartTester {

  /**
   * Tests the exception handling of lookupProductByName() and lookupProductById().
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupMethods() {

    System.out.println("testLookupMethods():");

    // test scenario 1. product name does not exist (expect NoSuchElementException)
    try {
      ExceptionalShoppingCart.lookupProductByName("Candy");
      System.out.println("No exception occurred!");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 2. product id does not exist (expect NoSuchElementException)
    try {
      ExceptionalShoppingCart.lookupProductById(1234);
      System.out.println("No exception occurred!");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 3. product id is too long (expect IllegalArgumentException)
    try {
      ExceptionalShoppingCart.lookupProductById(12345);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 4. correct input into lookupProductById() (expect no exception)
    try {
      ExceptionalShoppingCart.lookupProductById(4770);
      System.out.println("No exception occurred!");
    } catch (Exception e) {
      System.out.println("Exception occurred!");
      return false;
    }

    // test scenario 5. correct input into lookupProductByName() (expect no exception)
    try {
      ExceptionalShoppingCart.lookupProductByName("Grape");
      System.out.println("No exception occurred!");
    } catch (Exception e) {
      System.out.println("Exception occurred!");
      return false;
    }

    return true; // all test scenarios passed
  }

  /**
   * Tests the exception handling of testAddItemToMarketCatalog().
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToMarketCatalog() {

    String id = "";
    String name = "";
    String price = "";

    System.out.println("testAddItemToMarketCatalog():");

    // test scenario 1. id has a letter in it (expect IllegalArgumentException)
    try {
      id = "12E4";
      name = "Candy";
      price = "$1.00";
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 2. id is too long (expect IllegalArgumentException)
    try {
      id = "12345";
      name = "Candy";
      price = "$1.00";
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 3. name is null (expect IllegalArgumentException)
    try {
      id = "1234";
      name = null;
      price = "$1.00";
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 4. price is negative (expect IllegalArgumentException)
    try {
      id = "1234";
      name = "Candy";
      price = "$-1.00";
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 5. price does not start with dollar sign (expect IllegalArgumentException)
    try {
      id = "1234";
      name = "Candy";
      price = "1.00";
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 6. correct input (expect no exception)
    try {
      id = "1234";
      name = "Candy";
      price = "$1.00";
      ExceptionalShoppingCart.addItemToMarketCatalog(id, name, price);
      System.out.println("No exception occurred!");
    } catch (Exception e) {
      System.out.println("Exception occurred!");
      return false;
    }

    return true;
  }

  /**
   * Tests the exception handling of saveCartSummary().
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSaveCartSummary() {

    String[] cart;
    int size;
    File file;

    System.out.println("testSaveCartSummary(): ");

    // test scenario 1. size is negative (expect IllegalArgumentException)
    try {
      cart = new String[4];
      size = -4;
      file = new File("test.txt"); // file located in my project folder on my computer
      ExceptionalShoppingCart.saveCartSummary(cart, size, file);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 2. correct input (expect no exception)
    try {
      cart = new String[6];
      size = 0;
      file = new File("test.txt");

      size = ExceptionalShoppingCart.addItemToCart("Pepper", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Blueberry", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Broccoli", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Mushroom", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Eggs", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Pepper", cart, size);
      ExceptionalShoppingCart.saveCartSummary(cart, size, file);

      System.out.println("No exception occurred!");
    } catch (Exception e) {
      System.out.println("Exception occurred!");
      return false;
    }

    return true;
  }

  /**
   * Tests the exception handling loadCartSummary().
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCartSummary() {

    File file;
    String[] cart;
    String[] cart2;
    int size;
    int size2;

    System.out.println("testLoadCartSummary(): ");

    // test scenario 1. size is negative (expect IllegalArgumentException)
    try {
      cart = new String[4];
      size = -4;
      file = new File("test.txt"); // file located in my project folder on my computer
      ExceptionalShoppingCart.loadCartSummary(file, cart, size);
      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 2. cart reaches its capacity (expect IllegalStateException)
    try {
      cart = new String[6];
      size = 0;
      cart2 = new String[5]; // smaller capacity than "cart" intentionally
      size2 = 0;
      file = new File("test.txt"); // file located in my project folder on my computer

      // creating the cart summary file that will be used to test loadCartSummary()
      size = ExceptionalShoppingCart.addItemToCart("Pepper", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Blueberry", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Broccoli", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Mushroom", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Eggs", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Pepper", cart, size);
      ExceptionalShoppingCart.saveCartSummary(cart, size, file);
      // loading the test cart summary file in a different, empty cart with a smaller capacity
      size2 = ExceptionalShoppingCart.loadCartSummary(file, cart2, size2);

      System.out.println("No exception occurred!");
      return false;
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    // test scenario 3. correct input (expect no exception)
    try {
      cart = new String[6];
      size = 0;
      cart2 = new String[6];
      size2 = 0;
      file = new File("test.txt"); // file located in my project folder on my computer

      // creating the cart summary file that will be used to test loadCartSummary()
      size = ExceptionalShoppingCart.addItemToCart("Pepper", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Blueberry", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Broccoli", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Mushroom", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Eggs", cart, size);
      size = ExceptionalShoppingCart.addItemToCart("Pepper", cart, size);
      ExceptionalShoppingCart.saveCartSummary(cart, size, file);
      // loading the test cart summary file in a different, empty cart
      size2 = ExceptionalShoppingCart.loadCartSummary(file, cart2, size2);

      System.out.println("No exception occurred!");
    } catch (Exception e) {
      System.out.println("Exception occurred!");
      return false;
    }

    return true;
  }

  /**
   * Calls all tester methods in this class.
   * 
   * @return true if all tests pass, and false if any tests fail
   */
  public static boolean runAllTests() {

    return testLookupMethods() && testAddItemToMarketCatalog() && testSaveCartSummary()
        && testLoadCartSummary();
  }

  /**
   * Prints out the deep copy of the marketItems 2D String array
   */
  public static void arrayToString() {

    String[][] testArray = ExceptionalShoppingCart.getCopyOfMarketItems();

    for (int i = 0; i < testArray.length; i++) {

      if (testArray[i] != null) {

        for (int j = 0; j < testArray[i].length; j++) {

          System.out.println(testArray[i][j]);
        }
      }

      else {

        System.out.println("null");
      }
    }
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // ExceptionalShoppingCart.addItemToMarketCatalog("1234", "Gum", "$1.00");
    // arrayToString();
    System.out.println("runAllTests(): " + runAllTests());
  }
}
