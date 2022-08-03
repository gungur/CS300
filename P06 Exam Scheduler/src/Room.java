//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Room
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
 * A container for room-related information.
 */
public class Room {

  // data fields
  private String location; // the building and room number, e.g. "Noland 168"
  private int capacity; // the maximum number of people who can be in the room at a time

  /**
   * Initializes the data fields to the values of the arguments. If the provided integer is negative
   * (<0), throws an IllegalArgumentException with a descriptive error message.
   * 
   * @param location the building and room number
   * @param capacity the maximum number of people who can be in the room at a time
   * @throws IllegalArgumentException with descriptive error message if capacity is negative
   */
  public Room(String location, int capacity) throws IllegalArgumentException {
    if (capacity < 0) {
      throw new IllegalArgumentException("The provided capacity argument is negative!");
    }
    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Returns the location of this room.
   * 
   * @return the location of this room
   */
  public String getLocation() {
    return this.location;
  }

  /**
   * Returns the capacity of this room.
   * 
   * @return the capacity of this room
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Returns a NEW Room object with the same location as this one, but with a capacity less than
   * this one's by the argument's amount. If the argument is greater than the given Room's capacity,
   * this method should throw an IllegalArgumentException with a descriptive error message.
   * 
   * For example, if Room r has a capacity of 10, calling r.reduceCapacity(3) will return a new Room
   * object with the same location as r but a capacity of 7.
   * 
   * @param reduceAmount amount to reduce the capacity of the new Room by
   * @return a new Room object with the same location as this one, but with a capacity less than
   *         this one's by the argument's amount
   * @throws IllegalArgumentException with descriptive error message if reduceAmount is greater than
   *                                  this Room's capacity
   */
  public Room reduceCapacity(int reduceAmount) throws IllegalArgumentException {
    if (reduceAmount > this.capacity) {
      throw new IllegalArgumentException(
          "The reduceAmount argument is greater than this Room's capacity!");
    }
    Room newRoom = new Room(this.location, this.capacity - reduceAmount);
    return newRoom;
  }

}
