//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ShoppingCart
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

/**
 * Contains a list of market items as well as several methods that perform useful actions with
 * either the list of market items or the shopping cart.
 */
public class ShoppingCart {

  private static final double TAX_RATE = 0.05; // sales tax

  // MarketItems: a perfect-size two-dimensional array that stores the list of
  // available items in a given market
  // MarketItems[i][0] refers to a String representation of the item identifiers
  // MarketItems[i][1] refers the item name. Item names are also unique
  // MarketItems[i][2] a String representation of the unit price
  // of the item in dollars
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
   * Returns details of a specific product in the market given its name or "No match found"
   *
   * @param name the name of the product to search
   * @return string representation of the product to search including the identifier of the product,
   *         its name, and its price in dollars if match found or "No match found"
   */
  public static String lookupProductByName(String name) {

    String productInfo = "";

    for (int i = 0; i < marketItems.length; i++) {

      if (marketItems[i] != null && marketItems[i][1].equals(name)) {

        productInfo = marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];

        return productInfo;
      }

      else {

        productInfo = "No match found";
      }
    }

    return productInfo;
  }

  /**
   * Returns details of a specific product in the market given its id or "No match found"
   * 
   * @param id the identifier of the product or item to search
   * @return A string representation of the product to search including the identifier of the
   *         product, its name, and its price in dollars if match found or "No match found"
   */
  public static String lookupProductById(int id) {

    String productInfo = "";

    for (int i = 0; i < marketItems.length; i++) {

      if (marketItems[i] != null && Integer.parseInt(marketItems[i][0]) == id) {

        productInfo = marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];

        return productInfo;
      }

      else {

        productInfo = "No match found";
      }
    }

    return productInfo;
  }

  /**
   * Returns the price in dollars (a double value) of a market item given its name. If no match was
   * found in the market catalog, this method returns -1.0
   * 
   * @param name the name of the product to check its price
   * @return price in double representation of the product specified or -1.0 if no match was found
   */
  public static double getProductPrice(String name) {

    double price = 0.0;
    String tempPrice = "";

    for (int i = 0; i < marketItems.length; i++) {

      if (marketItems[i] != null && marketItems[i][1].equals(name)) {

        tempPrice = marketItems[i][2].substring(1);
        price = Double.parseDouble(tempPrice);

        return price;
      }
    }

    return -1.0;
  }

  /**
   * Creates and returns a deep copy of the marketItems array
   * 
   * @return deep copy of the marketItems array
   */
  public static String[][] getCopyOfMarketItems() {

    String[][] copyOfMarketItems = new String[marketItems.length][];
    // implemented so that marketItems with different lengths can work

    for (int i = 0; i < marketItems.length; i++) {

      if (marketItems[i] != null) {

        copyOfMarketItems[i] = new String[marketItems[i].length];
        // implemented so that marketItems[] with different lengths can work

        for (int j = 0; j < marketItems[i].length; j++) {

          copyOfMarketItems[i][j] = marketItems[i][j]; // deep copy of each string element
        }
      }

      else {

        copyOfMarketItems[i] = null;
      }
    }

    return copyOfMarketItems;
  }

  /**
   * Appends an item to a given cart. If the cart is already full, the item will not be added to the
   * cart and this method returns the same size.
   * 
   * @param item the name of the product to be added to the cart
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return size of the oversize array cart after trying to add item to the cart
   */
  public static int addItemToCart(String item, String[] cart, int size) {

    if (size != cart.length) {

      cart[size] = item;
      size++;
    }

    return size;
  }

  /**
   * Returns the number of occurrences of a given item within a cart. Zero or more occurrences of
   * item can be present in the cart. This method does not make any changes to the contents of the
   * cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the number of occurrences of item (exact match) within the oversize array cart
   */
  public static int nbOccurrences(String item, String[] cart, int size) {

    int occurrences = 0;

    for (int i = 0; i < size; i++) {

      if (cart[i] != null && cart[i].equals(item)) {

        occurrences++;
      }
    }

    return occurrences;
  }

  /**
   * Checks whether a cart contains at least one occurrence of a given item. This method does not
   * make any changes to the contents of the cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return true if there is a match (exact match) of item within the provided cart, and false
   *         otherwise.
   */
  public static boolean contains(String item, String[] cart, int size) {

    for (int i = 0; i < size; i++) {

      if (cart[i] != null && cart[i].equals(item)) {

        return true;
      }
    }

    return false;
  }

  /**
   * This method returns the total value in dollars of the cart. All products in the market are
   * taxable (subject to TAX_RATE).
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the total value in dollars of the cart accounting taxes
   */
  public static double checkout(String[] cart, int size) {

    double currentItemPrice = 0.0;
    double cartPrice = 0.0;

    for (int i = 0; i < size; i++) {

      if (cart[i] != null) {

        currentItemPrice = getProductPrice(cart[i]);
        cartPrice += currentItemPrice;
      }
    }

    cartPrice = cartPrice + (cartPrice * TAX_RATE); // applying tax rate to total cost of the cart

    return cartPrice;
  }

  /**
   * Removes one occurrence of item from a given cart and moves the last item in the cart to the
   * removed item's index. If no match with item was found in the cart, the method returns the same
   * value of input size without making any change to the contents of the array.
   * 
   * @param item the name of the item to remove
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the size of the oversize array cart after trying to remove item from the cart
   */
  public static int removeItem(String[] cart, String item, int size) {

    if (contains(item, cart, size)) {

      for (int i = 0; i < size; i++) {

        if (cart[i] != null && cart[i].equals(item)) {

          cart[i] = cart[size - 1]; // replacing the item we want to remove with last item in cart
          cart[size - 1] = null; // need to do this or there would be a duplicate item in the cart
          size--;

          return size;
        }
      }
    }

    return size;
  }

  /**
   * Removes all items from a given cart. The array cart must be empty (contains only null
   * references) after this method returns.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the size of the cart after removing all its items
   */
  public static int emptyCart(String[] cart, int size) {

    for (int i = 0; i < size; i++) {

      if (cart[i] != null) {

        cart[i] = null;
        size--;
      }
    }

    return size;
  }

  /**
   * Returns a string representation of the summary of the contents of a given cart. The format of
   * the returned string contains a set of lines where each line contains the number of occurrences
   * of a given item, between parentheses, followed by one space followed by the name of a unique
   * item in the cart. (#occurrences) name1 (#occurrences) name2 etc.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return string representation of the summary of the contents of the cart
   */
  public static String getCartSummary(String[] cart, int size) {

    String[] uniqueItemsInCart = new String[size];
    // This array will keep track of unique items in the cart so there are no duplicates in
    // the final cart summary.
    int realSize = 0;
    String summary = "";
    String summaryLine = "";
    int occurrences = 0;

    for (int i = 0; i < size; i++) {

      if (cart[i] != null && !contains(cart[i], uniqueItemsInCart, realSize)) {
        // if the current item in the cart is in uniqueItemsInCart, it is skipped

        uniqueItemsInCart[realSize] = cart[i];
        realSize++;
        occurrences = nbOccurrences(cart[i], cart, size);
        summaryLine = "(" + occurrences + ") " + cart[i] + "\n";
        // occurrences is automatically converted to a string
        summary = summary.concat(summaryLine);
      }
    }

    return summary;
  }
}
