//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ArtGalleryTester
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
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 * 
 * @author Sai Gungurthi
 *
 */

public class ArtGalleryTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {
    // same year, cost, and name
    try {
      Artwork test1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      Artwork test2 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      if (test1.compareTo(test2) != 0 || test1.equals(test2) != true) {
        return false;
      }
      // different year, cost, and name
      test1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      test2 = new Artwork("Guernica, Picasso", 1937, 3000.0);
      if (test1.compareTo(test2) >= 0 || test1.equals(test2) != false) {
        return false;
      }
      // same year, different cost and name
      test1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      test2 = new Artwork("Guernica, Picasso", 1889, 3000.0);
      if (test1.compareTo(test2) >= 0 || test1.equals(test2) != false) {
        return false;
      }
      // same year and cost, different name
      test1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      test2 = new Artwork("Guernica, Picasso", 1889, 2000.0);
      if (test1.compareTo(test2) <= 0 || test1.equals(test2) != false) {
        return false;
      }
      // equals() with a non-Artwork Object as argument
      test1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      Object test3 = new Object();
      if (test1.equals(test3) != false) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {
    // (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty, and that
    // its string representation is an empty string ""
    try {
      ArtGallery test = new ArtGallery();
      if (test.size() != 0 || test.isEmpty() == false || !test.toString().equals("")) {
        return false;
      }
      // (2) try adding one artwork and then check that the addArtwork() method call returns true,
      // the
      // tree is not empty, its size is 1, and the .toString() called on the tree returns the
      // expected
      // output.
      Artwork art1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      if (test.addArtwork(art1) != true || test.isEmpty() != false || test.size() != 1) {
        return false;
      }
      // System.out.println(test.toString().trim());
      String art1String = "[(Name: Starry Night, Van Gogh) (Year: 1889) (Cost: $2000.0)]";
      if (!test.toString().trim().equals(art1String)) {
        return false;
      }
      // (3) Try adding another artwork which is smaller that the artwork at the root
      Artwork art2 = new Artwork("Mona Lisa, DaVinci", 1503, 1000.0);
      if (test.addArtwork(art2) != true || test.isEmpty() != false || test.size() != 2) {
        return false;
      }
      // System.out.println(test.toString().trim());
      String art2String = "[(Name: Mona Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]";
      if (!test.toString().trim().equals(art2String + "\n" + art1String)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    // (1) Create a new ArtworkGallery. Then, check that calling the lookup() method on an empty
    // ArtworkGallery returns false.
    try {
      ArtGallery test = new ArtGallery();
      if (test.lookup("test", 1500, 1000.0) != false) {
        return false;
      }
      // (2) Consider a ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to
      // call
      // lookup() method to search for the artwork having a match at the root of the tree.
      Artwork art1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      Artwork art2 = new Artwork("Mona Lisa, DaVinci", 1503, 1000.0);
      Artwork art3 = new Artwork("NightHawks, Hopper", 1942, 4000.0);
      Artwork art4 = new Artwork("Whistler, Abbott", 1871, 5000.0);
      Artwork art5 = new Artwork("Guernica, Picasso", 1937, 3000.0);

      test.addArtwork(art1);
      test.addArtwork(art2);
      test.addArtwork(art3);
      test.addArtwork(art4);
      test.addArtwork(art5);

      if (test.lookup("Starry Night, Van Gogh", 1889, 2000.0) != true) {
        return false;
      }

      // (3) Then, search for a artwork at the right and left subtrees at different levels
      // considering
      // successful and unsuccessful search operations.
      if (test.lookup("Mona Lisa, DaVinci", 1503, 1000.0) != true) {
        return false;
      }
      if (test.lookup("Whistler, Abbott", 1871, 5000.0) != true) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*)
   * (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // (1) ensures that the height of an empty artwork tree is zero.
    try {
      ArtGallery test = new ArtGallery();
      if (test.height() != 0) {
        return false;
      }

      // (2) ensures that the height of a tree which consists of only one node is 1.
      Artwork art1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      test.addArtwork(art1);
      if (test.height() != 1) {
        return false;
      }

      // (3) ensures that the height of a ArtworkGallery with several levels is correct.
      Artwork art2 = new Artwork("Mona Lisa, DaVinci", 1503, 1000.0);
      Artwork art3 = new Artwork("NightHawks, Hopper", 1942, 4000.0);
      Artwork art4 = new Artwork("Whistler, Abbott", 1871, 5000.0);
      Artwork art5 = new Artwork("Guernica, Picasso", 1937, 3000.0);

      test.addArtwork(art2);
      test.addArtwork(art3);
      test.addArtwork(art4);
      test.addArtwork(art5);

      if (test.height() != 3) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    // (1) ensures that the best Artwork of an empty ArtGallery is null
    try {
      ArtGallery test = new ArtGallery();
      if (test.getBestArtwork() != null) {
        return false;
      }

      // (2) add one artwork, ensures that the best Artwork is the root
      Artwork art1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      test.addArtwork(art1);
      if (!test.getBestArtwork().equals(art1)) {
        return false;
      }
      // (3) add more artworks, ensures that the best Artwork is correct
      Artwork art2 = new Artwork("Mona Lisa, DaVinci", 1503, 1000.0);
      Artwork art3 = new Artwork("NightHawks, Hopper", 1942, 4000.0);
      Artwork art4 = new Artwork("Whistler, Abbott", 1871, 5000.0);
      Artwork art5 = new Artwork("Guernica, Picasso", 1937, 3000.0);

      test.addArtwork(art2);
      test.addArtwork(art3);
      test.addArtwork(art4);
      test.addArtwork(art5);

      if (!test.getBestArtwork().equals(art3)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }


  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty
   * arraylist when called on an empty tree. (2) Ensures that the ArtworkGallery.lookupAll() method
   * returns an array list which contains all the artwork satisfying the search criteria of year and
   * cost, when called on a non empty artwork tree with one match, and two matches and more. Vary
   * your search criteria such that the lookupAll() method must check in left and right subtrees.
   * (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
   * on a non-empty artwork tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {
    // (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
    // on an empty tree.
    try {
      ArtGallery test = new ArtGallery();
      ArrayList<Artwork> testList = new ArrayList<Artwork>();
      if (!test.lookupAll(1500, 1000.0).equals(testList)) {
        return false;
      }

      // (2) Ensures that the ArtworkGallery.lookupAll() method returns an array list which contains
      // all the artwork satisfying the search criteria of year and cost, when called on a non empty
      // artwork tree with one match, and two matches and more. Vary your search criteria such that
      // the lookupAll() method must check in left and right subtrees.
      Artwork art1 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
      Artwork art2 = new Artwork("Mona Lisa, DaVinci", 1503, 1000.0);
      Artwork art3 = new Artwork("NightHawks, Hopper", 1942, 4000.0);
      Artwork art4 = new Artwork("Whistler, Abbott", 1871, 5000.0);
      Artwork art5 = new Artwork("Guernica, Picasso", 1889, 3000.0);

      test.addArtwork(art1);
      test.addArtwork(art2);
      test.addArtwork(art3);
      test.addArtwork(art4);
      test.addArtwork(art5);

      testList.add(art1);
      testList.add(art5);

      if (!test.lookupAll(1889, 3000.0).equals(testList)) {
        return false;
      }

      // (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when
      // called
      // on a non-empty artwork tree with no search results found.
      testList = new ArrayList<Artwork>();
      if (!test.lookupAll(1942, 1000.0).equals(testList)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    // TODO complete the implementation of this method
    // (1) Buying artwork that is at leaf node


    // (2) Buying artwork at non-leaf node


    // (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException when
    // called on an artwork that is not present in the BST


    return true;
  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   * 
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {

    return testArtworkCompareToEquals() && testAddArtworkToStringSize() && testLookup()
        && testHeight() && testGetBestArtwork() && testLookupAll() && testBuyArtwork();
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testArworkCompareToEquals(): " + testArtworkCompareToEquals());
    System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
    System.out.println("testLookupAll(): " + testLookupAll());
    System.out.println("testBuyArtwork(): " + testBuyArtwork());
    System.out.println("runAllTests(): " + runAllTests());
  }

}
