//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: OpenPosition
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

/**
 * A application handler of an open position using priority queue. Only saves a new Application when
 * the queue is not full, or when it can replace older, lower-scored ones with its higher scores.
 */
public class OpenPosition {

  private String positionName;
  private ApplicationQueue applications; // the priority queue of all applications
  private int capacity; // the number of vacancies

  /**
   * Creates a new open position with the given capacity
   * 
   * @param positionName name of the open position
   * @param capacity     the number of vacancies of this position
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public OpenPosition(String positionName, int capacity) throws IllegalArgumentException {
    // verify the value of capacity
    if (capacity <= 0) {
      throw new IllegalArgumentException("The capacity is not a positive integer!");
    }

    // initialize the data fields appropriately
    this.positionName = positionName;
    this.capacity = capacity;
    applications = new ApplicationQueue(this.capacity);
  }

  /**
   * Getter method for positionName data field.
   * 
   * @return the positionName data field for this instance
   */
  public String getPositionName() {
    return this.positionName;
  }

  /**
   * Tries to add the given Application to the priority queue of this position. Return False when
   * the new Application has a lower score than the lowest-scored Application in the queue.
   * 
   * @return Whether the given Application was added successfully
   */
  public boolean add(Application application) {
    // if the queue is full, determine whether this application has a higher score than
    // the current lowest-scoring application; if not, do not add it
    if (this.capacity == this.applications.size()) {
      if (application.compareTo(this.applications.peek()) < 0) {
        return false;
      }
    }

    // if there is room in the queue OR this application has a higher score than the current
    // lowest-scoring application, add it to the queue

    // only need to dequeue if the queue is full
    if (this.capacity == this.applications.size()) {
      this.applications.dequeue();
    }
    this.applications.enqueue(application); // need to enqueue no matter what

    return true; // the given Application was added successfully
  }

  /**
   * Returns the list of Applications in the priority queue.
   * 
   * @return The list of Applications in the priority queue, in increasing order of the scores.
   */
  public String getApplications() {
    return applications.toString();
  }

  /**
   * Returns the total score of Applications in the priority queue.
   * 
   * @return The total score of Applications in the priority queue.
   */
  public int getTotalScore() {
    // calculate the total score of all applications currently in the queue
    int totalScore = 0;
    ApplicationQueue deepCopy = this.applications.deepCopy(); // don't want to change original queue

    // keeps going until the queue is empty aka all scores have been added
    while (deepCopy.isEmpty() == false) {
      totalScore = totalScore + deepCopy.dequeue().getScore();
    }

    return totalScore;
  }
}
