//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ExceptionalShoppingCart
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

/**
 * Expands upon ShoppingCart by adding additional functionality as well as exception handling.
 */
public class ExceptionalShoppingCart {

  // Define final parameters (constants)
  private static final double TAX_RATE = 0.05; // sales tax

  // a perfect-size two-dimensional array that stores the list of available items in a given market
  // MarketItems[i][0] refers to a String representation of the item key (unique identifier)
  // MarketItems[i][1] refers the item name
  // MarketItems[i][2] a String representation of the unit price of the item in dollars
  private static String[][] marketItems =
      new String[][] {{"4390", "Apple", "$1.59"}, {"4046", "Avocado", "$0.59"},
          {"4011", "Banana", "$0.49"}, {"4500", "Beef", "$3.79"}, {"4033", "Blueberry", "$6.89"},
          {"4129", "Broccoli", "$1.79"}, {"4131", "Butter", "$4.59"}, {"4017", "Carrot", "$1.19"},
          {"3240", "Cereal", "$3.69"}, {"3560", "Cheese", "$3.49"}, {"3294", "Chicken", "$5.09"},
          {"4071", "Chocolate", "$3.19"}, {"4363", "Cookie", "$9.5"}, {"4232", "Cucumber", "$0.79"},
          {"3033", "Eggs", "$3.09"}, {"4770", "Grape", "$2.29"}, {"3553", "Ice Cream", "$5.39"},
          {"3117", "Milk", "$2.09"}, {"3437", "Mushroom", "$1.79"}, {"4663", "Onion", "$0.79"},
          {"4030", "Pepper", "$1.99"}, {"3890", "Pizza", "$11.5"}, {"4139", "Potato", "$0.69"},
          {"3044", "Spinach", "$3.09"}, {"4688", "Tomato", "$1.79"}, null, null, null, null};

  /**
   * Creates a deep copy of the market catalog
   * 
   * @return Returns a deep copy of the market catalog 2D array of strings
   */
  public static String[][] getCopyOfMarketItems() {
    String[][] copy = new String[marketItems.length][];
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        copy[i] = new String[marketItems[i].length];
        for (int j = 0; j < marketItems[i].length; j++)
          copy[i][j] = marketItems[i][j]; // deep copy of marketItems
      }
    }
    return copy;
  }

  /**
   * Returns a string representation of the item whose name is provided as input
   * 
   * @param name name of the item to find
   * @return "itemId name itemPrice" if an item with the provided name was found
   * @throws NoSuchElementException with descriptive error message if no match found
   */
  public static String lookupProductByName(String name) throws NoSuchElementException {

    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null && name.equals(marketItems[i][1])) {
        return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
      }
    }

    throw new NoSuchElementException("No match found!");
  }

  /**
   * Returns a string representation of the item whose id is provided as input
   *
   * @param key id of the item to find
   * @return "itemId name itemPrice" if an item with the provided name was found
   * @throws IllegalArgumentException with descriptive error message if key is not a 4-digits int
   * @throws NoSuchElementException   with descriptive error message if no match found
   */
  public static String lookupProductById(int key)
      throws IllegalArgumentException, NoSuchElementException {

    // int key is turned into a String to be able to use .length() on its value
    // because int is primitive and does not have such methods
    int keyLength = String.valueOf(key).length();
    if (keyLength != 4) {

      throw new IllegalArgumentException("key is not a 4-digits int!");
    }

    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        if (marketItems[i][0].equals(String.valueOf(key)))
          return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
      }
    }

    throw new NoSuchElementException("No match found!");
  }

  /**
   * Returns the index of the first null position that can be used to add new market items returns
   * the length of MarketItems if no available null position is found
   * 
   * @return returns an available position to add new market items or the length of market items if
   *         no available positions are found
   */
  private static int indexOfInsertionPos() {
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] == null)
        return i;
    }
    return marketItems.length; // returns length of marketItems if no null positions found
  }

  /**
   * add a new item to market items array, expand the capacity of marketitems if it is full when
   * trying to add new item, use indexofInsertionPos() to find the position to add
   * 
   * @param id    id of the item to add
   * @param name  name of the item to add
   * @param price price of the item to add
   * @throws IllegalArgumentException with descriptive error message if id not parsable to 4-digits
   *                                  int, name is null or empty string, and price not parsable to
   *                                  positive double or does not start with '$'.
   */
  public static void addItemToMarketCatalog(String id, String name, String price)
      throws IllegalArgumentException {

    try {
      int idInt = Integer.parseInt(id);
      if (idInt < 1000 || idInt >= 10000) {
        throw new IllegalArgumentException("id is not parsable to 4-digits int!");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("id is not parsable to 4-digits int!");
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("id is not parsable to 4-digits int!");
    }

    if (name == null || name.equals("")) {

      throw new IllegalArgumentException("name is null or empty string!");
    }

    try {
      if (price.charAt(0) != '$') {

        throw new IllegalArgumentException("price does not start with '$'!");
      }
      String priceNoDollarSign = price.substring(1);
      double priceDouble = Double.parseDouble(priceNoDollarSign);
      if (priceDouble < 0.0) {
        throw new IllegalArgumentException("price is not parsable to positive double!");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("price is not parsable to positive double!");
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("price is not parsable to positive double!");
    }

    // necessary because you cannot increase the capacity of an array once initialized
    String[][] tempMarketItems;
    int next = indexOfInsertionPos();
    if (next == marketItems.length) {
      tempMarketItems = new String[marketItems.length * 2][]; // new 2D array with expanded capacity
      tempMarketItems = getCopyOfMarketItems(); // deep copy of marketItems
      marketItems = tempMarketItems; // now marketItems has increased capacity
      next = indexOfInsertionPos(); // need to get the insertion index for the expanded array
      marketItems[next] = new String[] {id, name, price};
    } else {
      marketItems[next] = new String[] {id, name, price};
    }
  }

  /**
   * Returns the price in dollars (a double value) of a market item given its name.
   * 
   * @param name name of the item to get the price
   * @return the price of the item
   * @throws NoSuchElementException with descriptive error message if price not found
   */
  public static double getProductPrice(String name) throws NoSuchElementException {

    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null && name.equals(marketItems[i][1])) {
        return Double.valueOf(marketItems[i][2].substring(1)); // substring to skip '$'
      }
    }

    throw new NoSuchElementException("Price not found!");
  }

  /**
   * Appends an item to a given cart (appends means adding to the end). If the cart is already full
   * (meaning its size equals its length), IllegalStateException will be thrown.
   * 
   * @param item the name of the product to be added to the cart
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the size of the oversize array cart after trying to add item to the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws IllegalStateException    with descriptive error message if this cart is full
   */
  public static int addItemToCart(String item, String[] cart, int size)
      throws IllegalArgumentException, IllegalStateException {

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    if (size == cart.length) {
      throw new IllegalStateException("Cart is full!");
    }

    cart[size] = item;
    size++; // incrementing size because item has been added to the oversize cart array
    return size;
  }

  /**
   * Returns the number of occurrences of a given item within a cart. This method must not make any
   * changes to the contents of the cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the number of occurrences of item (exact match) within the oversize array cart. Zero or
   *         more occurrences of item can be present in the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static int nbOccurrences(String item, String[] cart, int size)
      throws IllegalArgumentException {

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    int count = 0;
    for (int i = 0; i < size; i++) {
      if (cart[i].equals(item)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Checks whether a cart contains at least one occurrence of a given item. This method must not
   * make any changes to the contents of the cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns true if there is a match (exact match) of item within the provided cart, and
   *         false otherwise.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static boolean contains(String item, String[] cart, int size)
      throws IllegalArgumentException {

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    for (int i = 0; i < size; i++) {
      if (cart[i].equals(item)) {
        return true;
      }
    }
    return false; // item was not found in cart
  }

  /**
   * Removes one occurrence of item from a given cart.
   * 
   * @param item the name of the item to remove
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the oversize array cart after trying to remove item from the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws NoSuchElementException   with descriptive error message if item not found in the cart
   */
  public static int removeItem(String[] cart, String item, int size)
      throws IllegalArgumentException, NoSuchElementException {

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    for (int i = 0; i < size; i++) {
      if (cart[i].equals(item)) {
        cart[i] = cart[size - 1]; // specification states that order of cart does not matter
        cart[size - 1] = null;
        return size - 1;
      }
    }

    throw new NoSuchElementException("Item not found in the cart!");
  }

  /**
   * Removes all items from a given cart. The array cart must be empty (contains only null
   * references) after this method returns.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after removing all its items.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws NullPointerException     with descriptive error message if cart is null
   */
  public static int emptyCart(String[] cart, int size)
      throws IllegalArgumentException, NullPointerException {

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    if (cart == null) {
      throw new NullPointerException("cart is null!");
    }

    for (int i = 0; i < cart.length; i++) {
      cart[i] = null;
    }
    return 0;
  }

  /**
   * This method returns the total value in dollars of the cart. All products in the market are
   * taxable (subject to TAX_RATE).
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the total value in dollars of the cart accounting taxes.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static double checkout(String[] cart, int size) throws IllegalArgumentException {

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    double total = 0.0;
    for (int i = 0; i < size; i++) {
      total += getProductPrice(cart[i]) * (1 + TAX_RATE);
    }
    return total;
  }

  /**
   * Returns a string representation of the summary of the contents of a given cart. The format of
   * the returned string contains a set of lines where each line contains the number of occurrences
   * of a given item, between spaces and parentheses, followed by one space followed by the name of
   * a unique item in the cart. ( #occurrences ) name1 ( #occurrences ) name2 etc.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns a string representation of the summary of the contents of the cart
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static String getCartSummary(String[] cart, int size) throws IllegalArgumentException {

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    String s = "";
    for (int i = 0; i < size; i++) {
      // checks the cart up to the current item so that there are no repeats in the cart summary
      if (!contains(cart[i], cart, i)) {
        s = s + "( " + nbOccurrences(cart[i], cart, size) + " ) " + cart[i] + "\n";
      }
    }
    return s.trim();
  }

  /**
   * Save the cart summary to a file.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @param file the file to save the cart summary
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static void saveCartSummary(String[] cart, int size, File file)
      throws IllegalArgumentException {

    // NO other exception should be thrown by this method
    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }
    // must be created outside of try block or it would be local to try
    PrintWriter fileWriter = null;
    String cartSummary = getCartSummary(cart, size); // to be saved to the file
    try {
      fileWriter = new PrintWriter(file);
      fileWriter.println(cartSummary);
    } catch (FileNotFoundException e) {
      System.out.println("Cannot find " + file);
    }
    // Use finally block to close any resource used to write the cart summary into file
    // especially important when an exception occurs and the file is still open
    finally {
      if (fileWriter != null) {
        fileWriter.close();
        System.out.println("Closed " + file);
      }
    }
  }

  /**
   * Parse one line of cart summary and add nbOccurrences of item to cart correct formatting for
   * line:"( " + nbOccurrences + " ) " + itemName delimiter: one space (multiple spaces: wrong
   * formatting)
   * 
   * @param line a line of the cart summary to be parsed into one item to be added
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after adding items to the cart
   * @throws DataFormatException      with descriptive error message if wrong formatting (including
   *                                  nbOccurrences not parsable to a positive integer less or equal
   *                                  to 10)
   * @throws IllegalArgumentException with descriptive error message if itemName not found in
   *                                  marketItems
   * @throws IllegalStateException    with descriptive error message if cart reaches its capacity
   */
  protected static int parseCartSummaryLine(String line, String[] cart, int size)
      throws DataFormatException, IllegalArgumentException, IllegalStateException {

    int nbOccurrences = 0;
    String itemName = null;

    line = line.trim(); // getting rid of any whitespace before and after line
    // putting each part of line separated by a single space in it's own array element
    String[] splitLine = line.split(" ", 4); // limit of 4 in case itemName is more than one word

    if (!splitLine[0].equals("(") || !splitLine[2].equals(")") || splitLine.length != 4) {

      throw new DataFormatException("line has wrong formatting!");
    }

    try {
      nbOccurrences = Integer.parseInt(splitLine[1]);
    } catch (NumberFormatException e) {
      throw new DataFormatException(
          "nbOccurrences not parsable to a positive integer less or equal to 10!");
    }

    if (nbOccurrences <= 0 || nbOccurrences > 10) {

      throw new DataFormatException(
          "nbOccurrences not parsable to a positive integer less or equal to 10!");
    }

    itemName = splitLine[3];

    try {
      lookupProductByName(itemName);
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException("itemName not found in marketItems!");
    }

    // adds item to cart "nbOccurrences" times
    for (int i = 0; i < nbOccurrences; i++) {

      if (size >= cart.length) {
        throw new IllegalStateException("cart has reached its capacity!");
      }

      size = addItemToCart(itemName, cart, size);
    }

    return size;
  }

  /**
   * Load the cart summary from the file. For each line of summary, add nbOccurrences of item to
   * cart. Must call parseCartSummaryLine to operate
   * 
   * @param file file to load the cart summary from
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after adding items to the cart
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws IllegalStateException    with descriptive error message if cart reaches its capacity
   */
  public static int loadCartSummary(File file, String[] cart, int size)
      throws IllegalArgumentException, IllegalStateException {
    // NO other exception should be thrown by this method

    if (size < 0) {
      throw new IllegalArgumentException("size is less than zero!");
    }

    String line = null;
    Scanner fileScanner = null; // must be created outside of try block or would be local to try

    try {
      fileScanner = new Scanner(file);
      if (fileScanner != null) {
        // continues until the end of the file is reached
        while (fileScanner.hasNext()) {
          line = fileScanner.nextLine();
          // another try block so that if a given line causes an exception, it is simply skipped
          try {
            // This method MUST call parseCartSummaryLine to operate (to parse each line in file)
            size = parseCartSummaryLine(line, cart, size);
          } catch (DataFormatException e) {
            System.out.println(e.getMessage());
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Cannot find " + file);
    }
    // Use finally block to close any resource used to read from file
    // especially important when an exception occurs and the file is still open
    finally {
      if (fileScanner != null) {
        fileScanner.close();
        System.out.println("Closed " + file);
      }
    }
    return size;
  }
}
