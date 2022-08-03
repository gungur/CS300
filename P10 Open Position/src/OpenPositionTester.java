//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OpenPositionTester
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
 * This class implements unit test methods to check the correctness of Application,
 * ApplicationIterator, ApplicationQueue and OpenPosition classes in the assignment.
 */
public class OpenPositionTester {

  /**
   * This method tests and makes use of the Application constructor, getter methods, toString() and
   * compareTo() methods.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplication() {
    // create an Application with valid input
    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", 50);
    } catch (IllegalArgumentException e) {
      return false;
    } catch (Exception e) {
      return false;
    }

    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", 0);
    } catch (IllegalArgumentException e) {
      return false;
    } catch (Exception e) {
      return false;
    }

    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", 100);
    } catch (IllegalArgumentException e) {
      return false;
    } catch (Exception e) {
      return false;
    }

    // create an Application with invalid input:

    // blank name
    try {
      Application test = new Application("", "sponge@gmail.com", 50);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // null email
    try {
      Application test = new Application("Spongebob Squarepants", null, 50);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // no @ email
    try {
      Application test = new Application("Spongebob Squarepants", "spongegmail.com", 50);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // too many @ email
    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail@com", 50);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // invalid score
    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", -50);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", 150);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // verify getters
    try {
      String name = "Spongebob Squarepants";
      String email = "sponge@gmail.com";
      int score = 50;
      Application test = new Application(name, email, score);

      if (!test.getName().equals(name) || !test.getEmail().equals(email)
          || test.getScore() != score) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // verify compareTo

    // other application is null
    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application test2 = null;
      test.compareTo(test2);
      return false;
    } catch (NullPointerException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // valid applications
    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application test2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      if (test.compareTo(test2) <= 0 || test2.compareTo(test) >= 0 || test.compareTo(test) != 0) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // verify toString
    try {
      Application test = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      String expected = "Spongebob Squarepants:sponge@gmail.com:90";
      if (!test.toString().equals(expected)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method tests and makes use of the ApplicationIterator class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplicationIterator() {
    // create an ApplicationQueue with capacity at least 3 and at least 3 Applications with
    // different scores

    // add those Applications to the queue

    // verify that iterating through the queue gives you the applications in order of
    // INCREASING score
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      test.enqueue(app1);
      test.enqueue(app2);
      test.enqueue(app3);
      String expected =
          "Patrick Star:star@gmail.com:0\n" + "Squidward Tentacles:squid@gmail.com:30\n"
              + "Spongebob Squarepants:sponge@gmail.com:90";

      // the toString method of ApplicationQueue utilizes ApplicationIterator
      if (!test.toString().trim().equals(expected)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method tests and makes use of the enqueue() and dequeue() methods in the ApplicationQueue
   * class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testEnqueueDequeue() {
    // create an ApplicationQueue with capacity 3 and at least 4 Applications with different scores
    try {
      ApplicationQueue test = new ApplicationQueue(3);
    } catch (Exception e) {
      return false;
    }

    // enqueue an invalid value (null)
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application invalidApp = null;
      test.enqueue(invalidApp);
      return false;
    } catch (NullPointerException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // enqueue one valid application
    // enqueue two more valid applications
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      test.enqueue(app1);
      test.enqueue(app2);
      test.enqueue(app3);
      String expected =
          "Patrick Star:star@gmail.com:0\n" + "Squidward Tentacles:squid@gmail.com:30\n"
              + "Spongebob Squarepants:sponge@gmail.com:90";

      if (!test.toString().trim().equals(expected)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // enqueue one more application (exceeds capacity)
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      Application app4 = new Application("Mr. Krabs", "crab@gmail.com", 100);
      test.enqueue(app1);
      test.enqueue(app2);
      test.enqueue(app3);
      test.enqueue(app4);
      return false;
    } catch (IllegalStateException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // dequeue one application (should be lowest score)
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      test.enqueue(app1);
      test.enqueue(app2);
      test.enqueue(app3);

      if (!test.dequeue().equals(app3)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // dequeue all applications
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      test.enqueue(app1);
      test.enqueue(app2);
      test.enqueue(app3);
      test.dequeue();
      test.dequeue();
      test.dequeue();

      if (test.isEmpty() == false) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // dequeue from an empty queue
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      test.dequeue();
      return false;
    } catch (NoSuchElementException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method tests and makes use of the common methods (isEmpty(), size(), peek()) in the
   * ApplicationQueue class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testCommonMethods() {
    // create an ApplicationQueue with 0 capacity (should fail)
    try {
      ApplicationQueue test = new ApplicationQueue(0);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // create an ApplicationQueue with capacity 3 and at least 3 Applications with different scores

    // verify the methods' behaviors on an empty queue
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      if (test.isEmpty() != true || test.size() != 0) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      ApplicationQueue test = new ApplicationQueue(3);
      test.peek();
      return false;
    } catch (NoSuchElementException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // add one Application and verify the methods' behaviors
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      test.enqueue(app1);

      if (test.isEmpty() != false || test.size() != 1 || !test.peek().equals(app1)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // add the rest of the Applications and verify the methods' behaviors
    try {
      ApplicationQueue test = new ApplicationQueue(3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      test.enqueue(app1);
      test.enqueue(app2);
      test.enqueue(app3);

      if (test.isEmpty() != false || test.size() != 3 || !test.peek().equals(app3)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method tests and makes use of OpenPosition class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testOpenPosition() {
    // create an OpenPosition with 0 capacity (should fail)
    try {
      OpenPosition test = new OpenPosition("Fry Cook", 0);
      return false;
    } catch (IllegalArgumentException e) {
      // correct
    } catch (Exception e) {
      return false;
    }

    // create an OpenPosition with capacity 3 and at least 5 Applications with different scores
    try {
      OpenPosition test = new OpenPosition("Fry Cook", 3);
    } catch (Exception e) {
      return false;
    }

    // verify that the 3 MIDDLE-scoring Applications can be added don't use the highest and lowest
    // scoring applications YET

    // verify that getApplications returns the correct value for your input

    // verify that the result of getTotalScore is the sum of all 3 Application scores
    try {
      OpenPosition test = new OpenPosition("Fry Cook", 3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      Application app4 = new Application("Mr. Krabs", "crab@gmail.com", 100);
      Application app5 = new Application("Plankton", "plankton@gmail.com", 70);
      test.add(app1);
      test.add(app2);
      test.add(app5);
      String expected = "Squidward Tentacles:squid@gmail.com:30\n"
          + "Plankton:plankton@gmail.com:70\n" + "Spongebob Squarepants:sponge@gmail.com:90";
      int expectedTotalScore = 190;

      if ((!test.getApplications().trim().equals(expected))
          || test.getTotalScore() != expectedTotalScore) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // verify that the lowest-scoring application is NOT added to the OpenPosition
    try {
      OpenPosition test = new OpenPosition("Fry Cook", 3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      Application app4 = new Application("Mr. Krabs", "crab@gmail.com", 100);
      Application app5 = new Application("Plankton", "plankton@gmail.com", 70);
      test.add(app1);
      test.add(app2);
      test.add(app5);
      test.add(app3);
      String expected = "Squidward Tentacles:squid@gmail.com:30\n"
          + "Plankton:plankton@gmail.com:70\n" + "Spongebob Squarepants:sponge@gmail.com:90";

      if (!test.getApplications().trim().equals(expected)) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    // verify that the highest-scoring application IS added to the OpenPosition

    // verify that getApplications has changed correctly

    // verify that the result of getTotalScore has changed correctly
    try {
      OpenPosition test = new OpenPosition("Fry Cook", 3);
      Application app1 = new Application("Spongebob Squarepants", "sponge@gmail.com", 90);
      Application app2 = new Application("Squidward Tentacles", "squid@gmail.com", 30);
      Application app3 = new Application("Patrick Star", "star@gmail.com", 0);
      Application app4 = new Application("Mr. Krabs", "crab@gmail.com", 100);
      Application app5 = new Application("Plankton", "plankton@gmail.com", 70);
      test.add(app1);
      test.add(app2);
      test.add(app5);
      test.add(app4);
      String expected = "Plankton:plankton@gmail.com:70\n"
          + "Spongebob Squarepants:sponge@gmail.com:90\n" + "Mr. Krabs:crab@gmail.com:100";
      int expectedTotalScore = 260;

      if ((!test.getApplications().trim().equals(expected))
          || test.getTotalScore() != expectedTotalScore) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your OpenPositionTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    return testApplication() && testApplicationIterator() && testEnqueueDequeue()
        && testCommonMethods() && testOpenPosition();
  }

  /**
   * Driver method defined in this OpenPositionTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.print(runAllTests());
  }
}
