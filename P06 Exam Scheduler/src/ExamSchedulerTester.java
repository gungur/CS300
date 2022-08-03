//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ExamSchedulerTester
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

/**
 * Tests that the functionality of the ExamScheduler program is correct.
 */
public class ExamSchedulerTester {

  /**
   * Verifies that the constructor and methods of the Course class work properly and any relevant
   * exceptions are thrown.
   * 
   * @return true if correct functionality and false otherwise
   */
  public static boolean testCourse() {
    System.out.println("testCourse():");
    // test case: valid input - expect no exceptions or errors
    try {
      Course test = new Course("test", 50);
      if (!test.getName().equals("test")) {
        return false;
      }
      if (test.getNumStudents() != 50) {
        return false;
      }
    } catch (Exception e) {
      return false; // no exception should be thrown
    }

    // test case: invalid numStudents - expect IllegalArgumentException
    try {
      new Course("test", -5);
      return false; // if program reaches this line, it means no exception occurred
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }

    return true; // all test cases passed
  }

  /**
   * Verifies that the constructor and methods of the Room class work properly and any relevant
   * exceptions are thrown.
   * 
   * @return true if correct functionality and false otherwise
   */
  public static boolean testRoom() {
    System.out.println("testRoom():");
    // test case: valid input - expect no exceptions or errors
    try {
      Room test = new Room("Test 123", 100);
      if (!test.getLocation().equals("Test 123")) {
        return false; // getter method returns the wrong value
      }
      if (test.getCapacity() != 100) {
        return false; // getter method returns the wrong value
      }
      Room test2 = test.reduceCapacity(20);
      if (!test2.getLocation().equals("Test 123")) {
        return false; // reduceCapacity() did not correctly keep the same location
      }
      if (test2.getCapacity() != 80) {
        return false; // reduceCapacity() did not correctly reduce the capacity
      }
    } catch (Exception e) {
      return false; // no exceptions should have been thrown
    }

    // test case: invalid capacity - expect IllegalArgumentException
    try {
      new Room("Test 123", -50);
      return false; // if program reaches this line, it means no exception occurred
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }

    // test case: invalid reduceAmount - expect IllegalArgumentException
    try {
      Room test = new Room("Test 123", 100);
      test.reduceCapacity(120);
      return false; // if program reaches this line, it means no exception occurred
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }

    return true; // all test cases passed
  }

  /**
   * Verifies that the constructors and all methods of the Schedule class work properly and any
   * relevant exceptions are thrown.
   * 
   * @return true if correct functionality and false otherwise
   */
  public static boolean testScheduleAccessors() {
    System.out.println("testScheduleAccessors(): ");
    // test case: valid input - expect no exceptions or errors
    // creating test Schedule object
    Room[] testRooms = new Room[3];
    Room testRoom1 = new Room("AG 125", 200);
    testRooms[0] = testRoom1;
    Room testRoom2 = new Room("HUM 3650", 180);
    testRooms[1] = testRoom2;
    Room testRoom3 = new Room("CS 1240", 100);
    testRooms[2] = testRoom3;

    Course[] testCourses = new Course[3];
    Course testCourse1 = new Course("CS200", 160);
    testCourses[0] = testCourse1;
    Course testCourse2 = new Course("CS300", 80);
    testCourses[1] = testCourse2;
    Course testCourse3 = new Course("CS400", 120);
    testCourses[2] = testCourse3;

    Schedule testSchedule = new Schedule(testRooms, testCourses);
    String toString = "{CS200: Unassigned, CS300: Unassigned, CS400: Unassigned}";

    // start testing
    if (testSchedule.getNumRooms() != 3) {
      return false; // getter method returns the wrong value
    }
    try {
      Room testRoom = testSchedule.getRoom(1);
      if (testRoom.getCapacity() != 180) {
        return false; // getter method returns the wrong value
      }
    } catch (Exception e) {
      return false; // no exception should be thrown
    }
    if (testSchedule.getNumCourses() != 3) {
      return false; // getter method returns the wrong value
    }
    try {
      Course testCourse = testSchedule.getCourse(0);
      if (!testCourse.getName().equals("CS200")) {
        return false; // getter method returns the wrong value
      }
    } catch (Exception e) {
      return false; // no exception should be thrown
    }
    if (testSchedule.isAssigned(2)) {
      return false;
    }
    if (testSchedule.isComplete()) {
      return false;
    }
    if (!testSchedule.toString().equals(toString)) {
      return false;
    }

    // test case: invalid input - expect IndexOutOfBoundsExceptions
    try {
      testSchedule.getRoom(8); // index is too big
      return false;
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }
    try {
      testSchedule.getCourse(8); // index is too big
      return false;
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }

    return true; // all test cases passed
  }

  /**
   * Tests the assignCourse() method of the Schedule class.
   * 
   * @return true if correct functionality and false otherwise
   */
  public static boolean testAssignCourse() {
    System.out.println("testAssignCourse():");
    // test case: valid input - expect no exceptions or errors
    // creating test Schedule object
    Room[] testRooms = new Room[3];
    Room testRoom1 = new Room("AG 125", 200);
    testRooms[0] = testRoom1;
    Room testRoom2 = new Room("HUM 3650", 180);
    testRooms[1] = testRoom2;
    Room testRoom3 = new Room("CS 1240", 100);
    testRooms[2] = testRoom3;

    Course[] testCourses = new Course[3];
    Course testCourse1 = new Course("CS200", 160);
    testCourses[0] = testCourse1;
    Course testCourse2 = new Course("CS300", 80);
    testCourses[1] = testCourse2;
    Course testCourse3 = new Course("CS400", 120);
    testCourses[2] = testCourse3;

    Schedule testSchedule = new Schedule(testRooms, testCourses);
    String toString = "{CS200: HUM 3650, CS300: Unassigned, CS400: Unassigned}";
    Schedule testSchedule2;

    // start testing
    try {
      testSchedule2 = testSchedule.assignCourse(0, 1);
    } catch (Exception e) {
      return false; // no exception should occur
    }
    if (!testSchedule2.isAssigned(0)) {
      return false;
    }
    try {
      Room testRoom = testSchedule2.getAssignment(0);
      if (!testRoom.getLocation().equals("HUM 3650")) {
        return false;
      }
    } catch (Exception e) {
      return false; // no exception should should occur
    }
    if (!testSchedule2.toString().equals(toString)) {
      return false;
    }
    if (testSchedule2.getRoom(1).getCapacity() != 20) {
      return false; // because assignCourse() calls reduceCapacity() at the end
    }

    // test case: invalid input - expect exceptions
    try {
      testSchedule.assignCourse(5, 0); // first argument index is too big
      return false;
    } catch (IndexOutOfBoundsException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }
    try {
      testSchedule2.assignCourse(0, 1); // course is already assigned
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }
    try {
      testSchedule.assignCourse(0, 2); // room does not have enough capacity for course
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }


    return true; // all test cases passed
  }

  /**
   * Verifies that the testFindSchedule() method in the ExamScheduler class produces the correct
   * results.
   * 
   * @return true if correct functionality and false otherwise
   */
  public static boolean testFindSchedule() {
    System.out.println("testFindSchedule():");

    Room[] testRooms = new Room[3];
    Room testRoom1 = new Room("AG 125", 120);
    testRooms[0] = testRoom1;
    Room testRoom2 = new Room("HUM 3650", 160);
    testRooms[1] = testRoom2;
    Room testRoom3 = new Room("CS 1240", 80);
    testRooms[2] = testRoom3;

    Course[] testCourses = new Course[3];
    Course testCourse1 = new Course("CS200", 160);
    testCourses[0] = testCourse1;
    Course testCourse2 = new Course("CS300", 80);
    testCourses[1] = testCourse2;
    Course testCourse3 = new Course("CS400", 120);
    testCourses[2] = testCourse3;

    // test case: valid input - expect no exceptions or errors
    String toString = "{CS200: HUM 3650, CS300: CS 1240, CS400: AG 125}";
    Schedule testSchedule;
    try {
      testSchedule = ExamScheduler.findSchedule(testRooms, testCourses);
    } catch (Exception e) {
      return false; // no exception should occur;
    }
    if (!testSchedule.toString().equals(toString)) {
      return false; // only one possible valid schedule for my test data
    }

    // test case: invalid input - expect IllegalStateException
    testRooms = new Room[3];
    testRoom1 = new Room("AG 125", 10);
    testRooms[0] = testRoom1;
    testRoom2 = new Room("HUM 3650", 10);
    testRooms[1] = testRoom2;
    testRoom3 = new Room("CS 1240", 10);
    testRooms[2] = testRoom3;

    testCourses = new Course[3];
    testCourse1 = new Course("CS200", 160);
    testCourses[0] = testCourse1;
    testCourse2 = new Course("CS300", 80);
    testCourses[1] = testCourse2;
    testCourse3 = new Course("CS400", 120);
    testCourses[2] = testCourse3;

    try {
      ExamScheduler.findSchedule(testRooms, testCourses); // all rooms are too small for courses
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      return false; // wrong exception was thrown
    }

    return true;
  }

  /**
   * Verifies that the testFindAllSchedules() method in the ExamScheduler class produces the correct
   * results.
   * 
   * @return true if correct functionality and false otherwise
   */
  public static boolean testFindAllSchedules() {
    System.out.println("testFindAllSchedules():");

    // valid input - expect no exceptions or errors
    Room[] testRooms = new Room[3];
    Room testRoom1 = new Room("AG 125", 200);
    testRooms[0] = testRoom1;
    Room testRoom2 = new Room("HUM 3650", 180);
    testRooms[1] = testRoom2;
    Room testRoom3 = new Room("CS 1240", 100);
    testRooms[2] = testRoom3;

    Course[] testCourses = new Course[3];
    Course testCourse1 = new Course("CS200", 160);
    testCourses[0] = testCourse1;
    Course testCourse2 = new Course("CS300", 80);
    testCourses[1] = testCourse2;
    Course testCourse3 = new Course("CS400", 120);
    testCourses[2] = testCourse3;

    // three possible schedules for this data set
    String toString1 = "{CS200: AG 125, CS300: CS 1240, CS400: HUM 3650}";
    String toString2 = "{CS200: HUM 3650, CS300: AG 125, CS400: AG 125}";
    String toString3 = "{CS200: HUM 3650, CS300: CS 1240, CS400: AG 125}";
    // comparing to what method returns
    ArrayList<Schedule> testScheduleList = ExamScheduler.findAllSchedules(testRooms, testCourses);
    for (int i = 0; i < testScheduleList.size(); i++) {
      if (!testScheduleList.get(i).toString().equals(toString1)
          && !testScheduleList.get(i).toString().equals(toString2)
          && !testScheduleList.get(i).toString().equals(toString3)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Calls all test methods in this class.
   * 
   * @return true if all called test methods return true; false otherwise
   */
  public static boolean testAll() {
    return testCourse() && testRoom() && testScheduleAccessors() && testAssignCourse()
        && testFindSchedule() && testFindAllSchedules();
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAll():\n" + testAll()); // one method calls all test methods
  }

}
