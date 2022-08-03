//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ArtGallery
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
 * This class models the Artwork Gallery implemented as a binary search tree. The search criteria
 * include the year of creation of the artwork, the name of the artwork and its cost.
 * 
 * @author Sai Gungurthi
 *
 */
public class ArtGallery {

  private BSTNode<Artwork> root; // root node of the artwork catalog BST
  private int size; // size of the artwork catalog tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this ArtworkGallery is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.size == 0 && this.root == null) {
      return true;
    }

    return false;
  }

  /**
   * Returns the number of artwork pieces stored in this BST.
   * 
   * @return the size of this ArtworkGallery
   */
  public int size() {

    return this.size;
  }

  /**
   * Checks whether this ArtworkGallery contains a Artwork given its name, year, and cost.
   * 
   * @param name name of the Artwork to search
   * @param year year of creation of the Artwork to search
   * @param cost cost of the Artwork to search
   * @return true if there is a match with this Artwork in this BST, and false otherwise
   */
  public boolean lookup(String name, int year, double cost) {
    // Hint: create a new artwork with the provided name and year and default cost and use it in the
    // search operation

    Artwork target = new Artwork(name, year, cost);
    if (this.isEmpty() == true) {
      return false;
    }

    return lookupHelper(target, this.root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given Artwork in the subtree
   * rooted at current
   * 
   * @param target  a reference to a Artwork we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {
    // base case 1: reached end of branch
    if (current == null) {
      return false;
    }
    // base case 2: match found
    if (target.equals(current.getData())) {
      return true;
    }
    // recursive case:
    int compare = current.getData().compareTo(target);
    if (compare < 0) {
      // check right branch
      return lookupHelper(target, current.getRight());
    } else {
      // check left branch
      return lookupHelper(target, current.getLeft());
    }
  }

  /**
   * Adds a new artwork piece to this ArtworkGallery
   * 
   * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
   * @return true if the newArtwork was successfully added to this gallery, and returns false if
   *         there is a match with this Artwork already stored in gallery.
   * @throws NullPointerException if newArtwork is null
   */
  public boolean addArtwork(Artwork newArtwork) throws NullPointerException {
    if (newArtwork == null) {
      throw new NullPointerException("newArtwork is null!");
    }
    // duplicate artworks are not allowed
    if (lookup(newArtwork.getName(), newArtwork.getYear(), newArtwork.getCost()) == true) {
      return false;
    }
    // adding to an empty ArtGallery
    if (isEmpty() == true) {
      this.root = new BSTNode<Artwork>(newArtwork);
      this.size++;
      return true;
    } else {
      // adding to a non-empty ArtGallery
      if (addArtworkHelper(newArtwork, this.root) == true) {
        this.size++;
        return true;
      }
      return false;
    }
  }

  /**
   * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at current.
   * 
   * @param current    The "root" of the subtree we are inserting new Artwork into.
   * @param newArtwork The Artwork to be added to a BST rooted at current.
   * @return true if the newArtwork was successfully added to this ArtworkGallery, false if a match
   *         with newArtwork is already present in the subtree rooted at current.
   */
  protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {
    int compare = current.getData().compareTo(newArtwork);

    // current is smaller
    if (compare < 0) {

      if (current.getRight() == null) {
        // base case
        current.setRight(new BSTNode<Artwork>(newArtwork));
        return true;
      } else {
        // recursive case
        return addArtworkHelper(newArtwork, current.getRight());
      }
    }
    // current is larger
    else if (compare > 0) {

      if (current.getLeft() == null) {
        // base case
        current.setLeft(new BSTNode<Artwork>(newArtwork));
        return true;
      } else {
        // recursive case
        return addArtworkHelper(newArtwork, current.getLeft());
      }
    } else {
      // duplicate artworks are not allowed (compare = 0)
      return false;
    }
  }

  /**
   * Gets the recent best Artwork in this BST (meaning the largest artwork in this gallery)
   * 
   * @return the best (largest) Artwork (the most recent, highest cost artwork) in this
   *         ArtworkGallery, and null if this tree is empty.
   */
  public Artwork getBestArtwork() {
    if (this.isEmpty() == true) {
      return null;
    }
    // right of root node is larger according to binary search ordering
    if (this.root.getRight() == null) {
      return this.root.getData();
    }
    // finding the rightmost node aka largest artwork
    BSTNode<Artwork> pointer = root.getRight();
    // to avoid NullPointerException
    while (pointer.getRight() != null) {
      pointer = pointer.getRight();
    }

    return pointer.getData();
  }

  /**
   * Returns a String representation of all the artwork stored within this BST in the increasing
   * order of year, separated by a newline "\n". For instance
   * 
   * "[(Name: Stars, Artist1) (Year: 1988) (Cost: $300.0)]" + "\n" + "[(Name: Sky, Artist1) (Year:
   * 2003) (Cost: $550.0)]" + "\n"
   * 
   * @return a String representation of all the artwork stored within this BST sorted in an
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a ArtworkGallery is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current Artwork within this BST (root of a subtree)
   * @return a String representation of all the artworks stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Artwork> current) {
    // base case: nothing to traverse
    if (current == null) {
      return "";
    }
    String result = "";
    // recursive case: in-order traversal - left + self + right
    result = result + toStringHelper(current.getLeft());
    result = result + current.getData() + "\n"; // only need newline here
    result = result + toStringHelper(current.getRight());

    return result;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    if (this.isEmpty() == true) {
      return 0;
    } else {
      return heightHelper(this.root);
    }
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a ArtworkGallery (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Artwork> current) {
    // base case
    if (current == null) {
      return 0;
    }
    // recursive case
    int leftHeight = heightHelper(current.getLeft());
    int rightHeight = heightHelper(current.getRight());

    // leftHeight and rightHeights are counters, comparing to find greatest depth
    if (leftHeight > rightHeight) {
      return leftHeight + 1;
    } else {
      return rightHeight + 1;
    }
  }

  /**
   * Search for all artwork objects created on a given year and have a maximum cost value.
   * 
   * @param year creation year of artwork
   * @param cost the maximum cost we would like to search for a artwork
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost. If no artwork satisfies the lookup query, this method returns an empty arraylist
   */
  public ArrayList<Artwork> lookupAll(int year, double cost) {
    return lookupAllHelper(year, cost, this.root);
  }

  /**
   * Recursive helper method to lookup the list of artworks given their year of creation and a
   * maximum value of cost
   * 
   * @param year    the year we would like to search for a artwork
   * @param cost    the maximum cost we would like to search for a artwork
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost stored in the subtree rooted at current. If no artwork satisfies the lookup query,
   *         this method returns an empty arraylist
   */
  protected static ArrayList<Artwork> lookupAllHelper(int year, double cost,
      BSTNode<Artwork> current) {
    ArrayList<Artwork> artworkList = new ArrayList<Artwork>();
    // base case - reached end of branch
    if (current == null) {
      return artworkList;
    }
    // base case - found match
    if (current.getData().getYear() == year && current.getData().getCost() <= cost) {
      artworkList.add(current.getData());
    }
    // recursive case - new ArrayList each recursive call
    artworkList.addAll(lookupAllHelper(year, cost, current.getLeft()));
    artworkList.addAll(lookupAllHelper(year, cost, current.getRight()));

    return artworkList;
  }

  /**
   * Buy an artwork with the specified name, year and cost. In terms of BST operation, this is
   * equivalent to finding the specific node and deleting it from the tree
   * 
   * @param name name of the artwork, artist
   * @param year creation year of artwork
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria
   */
  public void buyArtwork(String name, int year, double cost) throws NoSuchElementException {
    Artwork artwork = new Artwork(name, year, cost);
    root = buyArtworkHelper(artwork, root);
    size--; // because removing
  }

  /**
   * Recursive helper method to buy artwork given the name, year and cost. In terms of BST
   * operation, this is equivalent to finding the specific node and deleting it from the tree
   * 
   * @param target  a reference to a Artwork we are searching to remove in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria in the BST rooted at current
   */
  protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current)
      throws NoSuchElementException {
    // TODO complete the implementation of this method. Problem decomposition and hints are provided
    // in the comments below

    // if current == null (empty subtree rooted at current), no match found, throw an exception
    if (current == null) {
      throw new NoSuchElementException("No Artwork found within the buying criteria!");
    }

    // Compare the target to the data at current and proceed accordingly
    // Recurse on the left or right subtree with respect to the comparison result
    // Make sure to use the output of the recursive call to appropriately set the left or the
    // right child of current accordingly
    // if match with target found, three cases should be considered. Feel free to organize the order
    // of these cases at your choice.
    int compare = target.compareTo(current.getData());
    BSTNode<Artwork> root;

    if (compare < 0) {
      root = buyArtworkHelper(target, current.getLeft());
    } else if (compare > 0) {
      root = buyArtworkHelper(target, current.getRight());
    } else {
      // base case
      root = current;
      // current may be a leaf (has no children), set current to null.
      if (current.getLeft() == null && current.getRight() == null) {
        current = null;
      }
      // current may have only one child, set current to that child (whether left or right child)
      if (current.getLeft() != null && current.getRight() == null) {
        current = current.getLeft();
      }
      if (current.getLeft() == null && current.getRight() != null) {
        current = current.getRight();
      }
      // current may have two children,
      // Replace current with a new BSTNode whose data field value is the successor of target in the
      // tree, and having the same left and right children as current. Notice carefully that you
      // cannot
      // set the data of a BSTNode.
      // The successor is the smallest element at the right subtree of current
      // Then, remove the successor from the right subtree. The successor must have up to one child.
      if (current.getLeft() != null && current.getRight() != null) {
        BSTNode<Artwork> temp = new BSTNode<Artwork>(getSuccessor(current));
        temp.setLeft(current.getLeft());
        temp.setRight(current.getRight());
        current = temp;
      }
    }
    return root;

    // Make sure to return current (the new root to this subtree) at the end of each case or at
    // the end of the method.

  }

  /**
   * Helper method to find the successor of a node in a BST (to be used by the delete operation).
   * The successor is defined as the smallest key in the right subtree. We assume by default that
   * node is not null. If node does not have a right child, return node.getData().
   * 
   * @param node node whose successor is to be found in the tree
   * @return returns without removing the key of the successor node
   */

  protected static Artwork getSuccessor(BSTNode<Artwork> node) {
    // right of root node is greater according to binary search ordering
    if (node.getRight() == null) {
      return node.getData();
    }
    // finding leftmost node of right child of parameter node = successor
    BSTNode<Artwork> pointer = node.getRight();
    while (pointer.getLeft() != null) {
      pointer = pointer.getLeft();
    }

    return pointer.getData();
  }
}
