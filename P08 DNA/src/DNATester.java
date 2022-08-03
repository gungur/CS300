//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: DNATester
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
 * Test methods to verify your implementation of the methods for P08.
 */
public class DNATester {

  // TODO: verify your LinkedQueue implementation directly

  /**
   * Tests the transcribeDNA() method
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testTranscribeDNA() {
    try {
      DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
      String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
      System.out.println(testDNA.transcribeDNA().toString());
      // replaceAll() is necessary because toString method puts spaces between each character
      if (!testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
        return false;
      }
    } catch (Exception e) {
      return false; // no exception should occur
    }

    return true;
  }

  /**
   * Tests the translateDNA() method
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testTranslateDNA() {
    try {
      DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
      System.out.println(testDNA.translateDNA().toString());
      if (!testDNA.translateDNA().toString().replaceAll(" ", "").equals("PQSIRWPCMTEPLEARSFRD")) {
        return false;
      }
    } catch (Exception e) {
      return false; // no exception should occur
    }

    return true;
  }

  // TODO: verify the mRNATranslate() method
  /**
   * Tests the mRNATranslate method
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testMRNATranslate() {
    try {
      DNA testDNA = new DNA("CCGGCCCTCCGGTGGATCCAA");
      // used translateDNA() for ease, but still tests mRNATranslate()
      System.out.println(testDNA.translateDNA().toString());
      if (!testDNA.translateDNA().toString().replaceAll(" ", "").equals("GREAT")) {
        return false;
      }
    } catch (Exception e) {
      return false; // no exception should occur
    }

    return true;
  }

  /**
   * Tests adding and removing things from your queue
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testEnqueueDequeue() {
    // testing adding things to queue
    try {
      LinkedQueue<String> testQueue = new LinkedQueue<String>();
      testQueue.enqueue("hi");
      testQueue.enqueue("hey");
      testQueue.enqueue("hello");
      System.out.println(testQueue.toString());
      if (!testQueue.toString().trim().equals("hi hey hello")) {
        return false;
      }
      // testing removing things from queue
      testQueue.dequeue();
      System.out.println(testQueue.toString());
      if (!testQueue.toString().trim().equals("hey hello")) {
        return false;
      }
    } catch (NoSuchElementException e) {
      return false;
    } catch (Exception e) {
      return false; // no exception should occur
    }
    // testing removing from empty queue - expect NoSuchElementException
    try {
      LinkedQueue<String> testQueue = new LinkedQueue<String>();
      testQueue.dequeue();
      return false; // exception should have occurred
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false; // wrong exception was thrown
    }

    return true;
  }

  /**
   * Tests the queue's size and isEmpty methods
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testQueueSize() {
    // testing on an empty queue
    try {
      LinkedQueue<String> testQueue = new LinkedQueue<String>();
      if (testQueue.size() != 0) {
        return false;
      }
      if (!testQueue.isEmpty()) {
        return false;
      }
      // testing on a non-empty queue
      testQueue.enqueue("hi");
      testQueue.enqueue("hey");
      testQueue.enqueue("hello");
      testQueue.dequeue(); // to check if dequeue() correctly decrements size
      if (testQueue.size() != 2) {
        return false;
      }
      if (testQueue.isEmpty()) {
        return false;
      }
    } catch (Exception e) {
      return false; // no exception should occur
    }

    return true;
  }

  /**
   * Main method - use this to run your test methods and output the results (ungraded)
   * 
   * @param args - unused
   */
  public static void main(String[] args) {
    System.out.println("transcribeDNA: " + testTranscribeDNA());
    System.out.println("translateDNA: " + testTranslateDNA());
    System.out.println("mRNATranslate: " + testMRNATranslate());
    System.out.println("EnqueueDequeue: " + testEnqueueDequeue());
    System.out.println("QueueSize: " + testQueueSize());
  }

}
