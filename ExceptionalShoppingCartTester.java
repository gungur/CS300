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
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
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
 * 
 */
public class ExceptionalShoppingCartTester {

  /**
   * 
   */
  public static boolean testLookupMethods() {

    System.out.println("testLookupMethods():");

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

    try {
      ExceptionalShoppingCart.lookupProductById(12345);
      System.out.println("No exception occurred!");
      return false;
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("Wrong exception was thrown!");
      return false;
    }

    return true;
  }

  /**
   * 
   * @return
   */
  public static boolean testAddItemToMarketCatalog() {

    String id = "";
    String name = "";
    String price = "";

    System.out.println("testAddItemToMarketCatalog():");

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

    return true;
  }

  /**
   * Calls all tester methods in this class.
   * 
   * @return true if all tests pass, and false if any tests fail
   */
  public static boolean runAllTests() {

    return true;
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
    arrayToString();
    System.out.println(testLookupMethods());
    System.out.println(testAddItemToMarketCatalog());
  }
}
