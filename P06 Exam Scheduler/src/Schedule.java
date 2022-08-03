//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Schedule
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

import java.util.Arrays;

/**
 * An object to maintain the current draft schedule. Manages the matching between Course and Room
 * objects.
 */
public class Schedule {

  // data fields
  private Room[] rooms; // an array of the Room objects available for exams
  private Course[] courses; // an array of the Course objects which require exam rooms
  private int[] assignments; // an array where the integer at index N is the index of the room that
                             // course[N] has been assigned to

  /**
   * Initializes the rooms and courses arrays to the provided values, and creates an assignments
   * array of the correct length where all values are -1, indicating that the corresponding course
   * has not yet been assigned a room.
   * 
   * @param rooms   an array of the Room objects available for exams
   * @param courses an array of the Course objects which require exam rooms
   */
  public Schedule(Room[] rooms, Course[] courses) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = new int[courses.length];
    for (int i = 0; i < this.assignments.length; i++) {
      assignments[i] = -1; // -1 indicates that the corresponding course has not yet been assigned a
                           // room
    }
  }

  /**
   * Private constructor. Initializes the rooms and courses arrays to the provided values and
   * assignments to the provided assignments array. May assume the assignments array is the correct
   * length (equal to the length of the courses array).
   * 
   * @param rooms       an array of the Room objects available for exams
   * @param courses     an array of the Course objects which require exam rooms
   * @param assignments an array where the integer at index N is the index of the room that
   *                    course[N] has been assigned to
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }

  /**
   * Returns the number of rooms available in this schedule.
   * 
   * @return the number of rooms available in this schedule
   */
  public int getNumRooms() {
    int counter = 0;
    for (int i = 0; i < rooms.length; i++) {
      if (rooms[i] != null) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Returns the Room object at the given index in the rooms array. Throws an
   * IndexOutOfBoundsException with a descriptive error message if the given index is invalid.
   * 
   * @param index the index in the rooms array
   * @return the Room object at the given index in the rooms array
   * @throws IndexOutOfBoundsException with descriptive error message if the given index is invalid
   */
  public Room getRoom(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > rooms.length - 1) {
      throw new IndexOutOfBoundsException("The given index is invalid for the rooms array!");
    }
    return rooms[index];
  }

  /**
   * Returns the number of courses in this schedule.
   * 
   * @return the number of courses in this schedule
   */
  public int getNumCourses() {
    int counter = 0;
    for (int i = 0; i < courses.length; i++) {
      if (courses[i] != null) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * Returns the Course object at the given index in the courses array. Throws an
   * IndexOutOfBoundsException with a descriptive error message if the given index is invalid.
   * 
   * @param index the index in the courses array
   * @return the Course object at the given index in the courses array
   * @throws IndexOutOfBoundsException with descriptive error message if the given index is invalid
   */
  public Course getCourse(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > courses.length - 1) {
      throw new IndexOutOfBoundsException("The given index is invalid for the courses array!");
    }

    return courses[index];
  }

  /**
   * Returns true if and only if the course at the given index has been assigned a room; false
   * otherwise.
   * 
   * @param index the index of the course in the courses array
   * @return true if and only if the course at the given index has been assigned a room; false
   *         otherwise
   */
  public boolean isAssigned(int index) {
    // -1 in the assignments array means that the corresponding course has not yet been assigned a
    // room
    if (assignments[index] != -1) {
      return true;
    }

    return false;
  }

  /**
   * Returns the Room object assigned to the course at the given index. Throws an
   * IllegalArgumentException if the course has not been assigned a room, or an
   * IndexOutOfBoundsException with a descriptive error message if the given course index is
   * invalid.
   * 
   * @param index the index of the course in the courses array
   * @return the Room object assigned to the course at the given index
   * @throws IllegalArgumentException  with descriptive error message if the course has not been
   *                                   assigned a room
   * @throws IndexOutOfBoundsException with descriptive error message if the given course index is
   *                                   invalid
   */
  public Room getAssignment(int index) throws IllegalArgumentException, IndexOutOfBoundsException {
    if (index < 0 || index > courses.length - 1) {
      throw new IndexOutOfBoundsException("The given index is invalid for the courses array!");
    }
    if (assignments[index] == -1) {
      throw new IllegalArgumentException("The course has not yet been assigned a room!");
    }
    int roomIndex = assignments[index]; // assignments[N] is the index of the room in the rooms
                                        // array that courses[N] is assigned to

    return rooms[roomIndex];
  }

  /**
   * Returns true if and only if all courses have been assigned to rooms; false otherwise.
   * 
   * @return true if and only if all courses have been assigned to rooms; false otherwise
   */
  public boolean isComplete() {
    for (int i = 0; i < assignments.length; i++) {
      // -1 in the assignments array means that the corresponding course has not yet been assigned a
      // room
      if (assignments[i] == -1) {
        return false;
      }
    }

    return true; // all courses have been assigned to rooms
  }

  /**
   * Returns a NEW Schedule object with the course at the first argument index assigned to the room
   * at the second argument index. Throws an IndexOutOfBoundsException with a descriptive error
   * message if the given course or room index is invalid, or an IllegalArgumentException with a
   * descriptive error message if the given course has already been assigned a room, or if the room
   * does not have sufficient capacity.
   * 
   * @param courseIndex the index of the course
   * @param roomIndex   the index of the room
   * @return a new schedule object with the course at the first argument index assigned to the room
   *         at the second argument index
   * @throws IndexOutOfBoundsException with descriptive error message if the given course or room
   *                                   index is invalid
   * @throws IllegalArgumentException  with descriptive error message if the given course has
   *                                   already been assigned a room, or if the room does not have
   *                                   sufficient capacity
   */
  public Schedule assignCourse(int courseIndex, int roomIndex)
      throws IndexOutOfBoundsException, IllegalArgumentException {
    // exception handling
    if (roomIndex < 0 || roomIndex > rooms.length - 1) {
      throw new IndexOutOfBoundsException("The given index is invalid for the rooms array!");
    }
    if (courseIndex < 0 || courseIndex > courses.length - 1) {
      throw new IndexOutOfBoundsException("The given index is invalid for the courses array!");
    }
    if (assignments[courseIndex] != -1) {
      throw new IllegalArgumentException("The course has already been assigned a room!");
    }
    if (courses[courseIndex].getNumStudents() > rooms[roomIndex].getCapacity()) {
      throw new IllegalArgumentException(
          "The room does not have sufficient capacity for the numStudents in the course!");
    }

    // creation of new Schedule object with deep copies so current schedule object is not changed
    Room[] newRooms = Arrays.copyOf(this.rooms, this.rooms.length);
    Course[] newCourses = Arrays.copyOf(this.courses, this.courses.length);
    int[] newAssignments = Arrays.copyOf(this.assignments, this.assignments.length);

    Schedule schedule = new Schedule(newRooms, newCourses, newAssignments);
    schedule.assignments[courseIndex] = roomIndex; // assigning course to room based on arguments
    // reducing the capacity of the room by numStudents of the course that was just assigned to it
    schedule.rooms[roomIndex] =
        schedule.rooms[roomIndex].reduceCapacity(schedule.courses[courseIndex].getNumStudents());

    return schedule;
  }

  /**
   * Overridden toString() method that creates a String representation of this Schedule, formatted
   * as follows: {CS300: AG 125, CS200: HUM 3650, CS400: Unassigned}. Note that CS400 has not yet
   * been assigned a room.
   * 
   * @return a String representation of this Schedule
   */
  @Override
  public String toString() {
    String scheduleString = "{";
    int roomIndex;
    String roomLocation;

    for (int i = 0; i < courses.length; i++) {
      scheduleString = scheduleString + courses[i].getName() + ": ";
      // assignments[N] stores the index of the room in rooms[] that courses[N] is assigned to
      roomIndex = assignments[i];
      if (roomIndex == -1) {
        roomLocation = "Unassigned"; // -1 means course has not been assigned a room yet
      } else {
        roomLocation = rooms[roomIndex].getLocation();
      }
      scheduleString = scheduleString + roomLocation + ", ";
    }
    // removes the extra comma and space at the end
    scheduleString = scheduleString.substring(0, scheduleString.length() - 2);
    scheduleString = scheduleString + "}";

    return scheduleString;
  }

}
