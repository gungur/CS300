//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ApplicationIterator
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an iterator for Applications, which returns the Applications in order from earliest to
 * latest based on their order in a priority queue.
 */
public class ApplicationIterator implements Iterator<Application> {

  private ApplicationQueue queue; // a copy of the priority queue of applications to iterate over

  /**
   * Creates a new ApplicationIterator which iterates over the elements of the given
   * ApplicationQueue in order from lowest-scored application to the highest-scored application.
   * 
   * @param queue the ApplicationQueue to iterate over
   */
  public ApplicationIterator(ApplicationQueue queue) {
    this.queue = queue.deepCopy(); // we are going to work on a deep copy of the provided queue
                                   // as input parameter.
  }

  /**
   * Returns true if the iteration has more elements.
   * 
   * @return true if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    if (!this.queue.isEmpty()) {
      return true;
    }

    return false;
  }

  /**
   * Returns the next element in the iteration.
   * 
   * @return the next element in the iteration.
   * @throws NoSuchElementException with a descriptive error message if the iteration has no more
   *                                elements
   */
  @Override
  public Application next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("No more elements in this iteration");
    }

    // essentially doing heap-sort
    return this.queue.dequeue();
  }
}
