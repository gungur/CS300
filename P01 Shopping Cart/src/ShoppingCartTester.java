//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ShoppingCartTester
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

import java.util.Arrays;

/**
 * This class tests methods in the ShoppingCart class
 */
public class ShoppingCartTester {

  /**
   * Checks whether ShoppingCart.lookupProductByName() and ShoppingCart.lookupProductById() methods
   * work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupMethods() {
    // define test scenarios.

    // 1. The item to find is at index 0 of the marketItems array
    String expectedOutput = "4390 Apple $1.59";

    if (!ShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed Apple as input");

      return false;
    }

    if (!ShoppingCart.lookupProductById(4390).equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the id " + "of Apple as input");

      return false;
    }

    // 2. The item to find is at the last non-null position of the marketItems array
    expectedOutput = "4688 Tomato $1.79";

    if (!ShoppingCart.lookupProductByName("Tomato").equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed Tomato as input");

      return false;
    }

    if (!ShoppingCart.lookupProductById(4688).equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the id " + "of Tomato as input");

      return false;
    }

    // 3. The item to find is at an arbitrary position of the
    // middle of the marketItems array
    expectedOutput = "3560 Cheese $3.49";

    if (!ShoppingCart.lookupProductByName("Cheese").equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed Cheese as input");

      return false;
    }

    if (!ShoppingCart.lookupProductById(3560).equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the id " + "of Cheese as input");

      return false;
    }

    // 4. The item to find is not found in the market
    expectedOutput = "No match found";

    if (!ShoppingCart.lookupProductByName("NOT FOUND").equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed the name of "
          + "a product not found in the market.");

      return false;
    }

    if (!ShoppingCart.lookupProductById(1000).equals(expectedOutput)) {

      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the identifier"
          + "of a product not found in the market.");

      return false;
    }

    return true; // NO BUGS detected by this tester method
  }

  /**
   * Checks the correctness of ShoppingCart.getProductPrice() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetProductPrice() {
    // define test scenarios

    // 1. get the price of Apple (at index 0 of marketItems array)
    double expectedPrice = 1.59; // price of the product Apple in the market
    // Note that we do not use the == operator to check whether two
    // floating-point numbers (double or float) in java are equal.
    // Two variables a and b of type double are equal if the absolute
    // value of their difference is less or equal to a small threshold epsilon.
    // For instance, if Math.abs(a - b) <= 0.001, then a equals b

    if (Math.abs(ShoppingCart.getProductPrice("Apple") - expectedPrice) > 0.001) {

      System.out.println("Problem detected: Your getProductPrice() method "
          + "failed to return the expected price when passed Apple as input");

      return false;
    }

    // 2. get price of Candy (product not found in market)
    expectedPrice = -1.0; // what method should return if no match was found in the market catalog

    if (Math.abs(ShoppingCart.getProductPrice("Candy") - expectedPrice) > 0.001) {

      System.out.println("Problem detected: Your getProductPrice() method "
          + "failed to return the expected price when passed Candy as input");

      return false;
    }

    // 3. get the price of Tomato (at last non-null position of marketItems array)
    expectedPrice = 1.79;

    if (Math.abs(ShoppingCart.getProductPrice("Tomato") - expectedPrice) > 0.001) {

      System.out.println("Problem detected: Your getProductPrice() method "
          + "failed to return the expected price when passed Tomato as input");

      return false;
    }

    return true; // No bug detected. The ShoppingCart.getProductPrice() passed this tester.
  }

  /**
   * This tester method checks the correctness of addItemToCart, contains, and nbOccurrences methods
   * defined in the ShoppingCart class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToCartContainsNbOccurrences() {
    // define test scenarios

    // 1. adding an item to an empty cart
    String[] cart = new String[10];
    int size = 0;
    size = ShoppingCart.addItemToCart("Banana", cart, size);
    int expectedSize = 1;
    String[] expectedCart =
        new String[] {"Banana", null, null, null, null, null, null, null, null, null};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected cart when adding to an empty cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected size when adding to an empty cart");

      return false;
    }

    // 2. adding an item to a full cart
    cart = new String[] {"Milk", "Apple", "Banana", "Pizza"};
    size = 4; // full array (size == cart.length)
    size = ShoppingCart.addItemToCart("Eggs", cart, size);
    expectedSize = 4;
    expectedCart = new String[] {"Milk", "Apple", "Banana", "Pizza"};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected cart when adding to a full cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected size when adding to a full cart");

      return false;
    }

    // 3. adding successfully an item to a non-empty cart
    cart = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
    size = 4; // 4 items are stored in the cart
    size = ShoppingCart.addItemToCart("Eggs", cart, size);
    expectedSize = 5;
    expectedCart = new String[] {"Milk", "Apple", "Banana", "Pizza", "Eggs", null};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected cart when adding to a non-empty cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected size when adding to a non-empty cart");

      return false;
    }

    return true; // NO BUGS detected by this tester method
  }

  /**
   * This tester method checks the correctness of removeItem() method defined in the ShoppingCart
   * class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveItem() {
    // define test scenarios

    // 1. removing an item stored at index 0 of a non-empty cart
    String[] cart = new String[] {"Milk", "Apple", "Banana", "Pizza", "Eggs", null, null};
    int size = 5;
    size = ShoppingCart.removeItem(cart, "Milk", size);
    int expectedSize = 4;
    String[] expectedCart = new String[] {"Eggs", "Apple", "Banana", "Pizza", null, null, null};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected cart when removing the first item of a non-empty cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected size when removing the first item of a non-empty cart");

      return false;
    }

    // 2. removing an item whose first occurrence is stored at index size-1 of a non-empty cart
    cart = new String[] {"Milk", "Apple", "Banana", "Pizza", "Eggs", null, null};
    size = 5;
    size = ShoppingCart.removeItem(cart, "Eggs", size);
    expectedSize = 4;
    expectedCart = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null, null};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected cart when removing the last item from a non-empty cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected size when removing the last item from a non-empty cart");

      return false;
    }

    // 3. removing an item whose first occurrence is stored at an arbitrary position within a
    // non-empty array cart
    cart = new String[] {"Milk", "Apple", "Banana", "Pizza", "Eggs", null, null};
    size = 5;
    size = ShoppingCart.removeItem(cart, "Banana", size);
    expectedSize = 4;
    expectedCart = new String[] {"Milk", "Apple", "Eggs", "Pizza", null, null, null};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected cart when removing an item in a non-arbitrary postiion "
          + "from a non-empty cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected cart when removing an item in a non-arbitrary postiion "
          + "from a non-empty cart");

      return false;
    }

    // 4. trying to remove an item from an empty cart
    cart = new String[5]; // array containing 5 null references
    size = 0; // no item is stored in the cart
    size = ShoppingCart.removeItem(cart, "Apple", size);
    expectedSize = 0;
    expectedCart = new String[] {null, null, null, null, null};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected cart when removing from an empty cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected size when removing from an empty cart");

      return false;
    }

    // 5. trying to remove a non-existing item from the cart
    cart = new String[] {"Milk", "Apple", "Banana", "Pizza", "Milk", null, null};
    size = 5;
    size = ShoppingCart.removeItem(cart, "Eggs", size);
    expectedSize = 5;
    expectedCart = new String[] {"Milk", "Apple", "Banana", "Pizza", "Milk", null, null};

    if (!Arrays.equals(cart, expectedCart)) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected cart when removing a non-existing item from the cart");

      return false;
    }

    if (size != expectedSize) {

      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected size when removing a non-existing item from the cart");

      return false;
    }

    return true; // NO BUGS detected by this tester method
  }

  /**
   * This tester method checks the correctness of checkout and getCartSummary() methods defined in
   * the ShoppingCart class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCheckoutGetCartSummary() {
    // define test scenarios

    // 1. calling method on an empty cart
    String[] cart = new String[7]; // array containing 7 null references
    int size = 0;
    String expectedCartSummary = "";
    if (!ShoppingCart.getCartSummary(cart, size).equals(expectedCartSummary)) {

      System.out.println("Problem detected: Your getCartSummary() method "
          + "failed to return the expected cart summary for an empty cart");

      return false;
    }

    // 2. calling method on a cart which contains non-duplicate items
    cart = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
    size = 4; // non-empty cart of size 4
    expectedCartSummary = "(1) Milk\n(1) Apple\n(1) Banana\n(1) Pizza\n";

    if (!ShoppingCart.getCartSummary(cart, size).equals(expectedCartSummary)) {

      System.out.println("Problem detected: Your getCartSummary() method "
          + "failed to return the expected cart summary for a cart containing unique items");

      return false;
    }

    // 3. calling method on a cart which contains items with multiple occurrences at different
    // positions of the over-sized array
    cart = new String[] {"Tomato", "Milk", "Milk", "Eggs", "Tomato", "Onion", "Eggs", "Milk",
        "Banana", null, null};
    size = 9; // non-empty cart of size 4
    expectedCartSummary = "(2) Tomato\n(3) Milk\n(2) Eggs\n(1) Onion\n(1) Banana\n";

    if (!ShoppingCart.getCartSummary(cart, size).equals(expectedCartSummary)) {

      System.out.println("Problem detected: Your getCartSummary() method "
          + "failed to return the expected cart summary for a cart containing unique items");

      return false;
    }

    return true; // NO BUGS detected by this tester method
  }

  /**
   * This tester runs all the tester methods defined in this tester class. For instance, if this
   * tester class defines three tester methods named test1(), test2() and test3(), it will return
   * test1() && test2() && test3().
   * 
   * @return false if any of the tester methods fails, and true if all the tests are passed
   */
  public static boolean runAllTests() {

    return testLookupMethods() && testGetProductPrice() && testAddItemToCartContainsNbOccurrences()
        && testRemoveItem() && testCheckoutGetCartSummary();
  }

  /**
   * Prints out the deep copy of the marketItems 2D String array
   */
  public static void arrayToString() {

    String[][] testArray = ShoppingCart.getCopyOfMarketItems();

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

    arrayToString();
    System.out.println("runAllTests(): " + runAllTests());
  }
}
