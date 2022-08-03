//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ExamScheduler
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
 * A collection of static recursive utility methods to help create the Schedule. Includes recursive
 * methods that schedule Courses into Rooms.
 */
public class ExamScheduler {

  /**
   * Returns a valid Schedule for the given set of rooms and courses, or throws an
   * IllegalStateException if no such schedule exists. This method should contain only a call to the
   * helper method, providing an empty starting Schedule.
   * 
   * @param rooms   set of rooms to find a schedule for
   * @param courses set of courses to find a schedule for
   * @return a valid Schedule for the given set of rooms and courses
   * @throws IllegalStateException with descriptive error message if no such schedule exists
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) throws IllegalStateException {
    Schedule schedule = new Schedule(rooms, courses);

    return findScheduleHelper(schedule, 0); // first recursive method call so index of 0
  }

  /**
   * A private, recursive method that assigns all unassigned courses in a Schedule beginning from
   * the index provided as an argument.
   * 
   * @param schedule the schedule that will have all unassigned courses assigned beginning from
   *                 startingIndex
   * @param index    the index where to start assigning all unassigned courses in a Schedule
   * @return the resulting schedule with all courses assigned beginning from startingIndex
   * @throws IllegalStateException with descriptive error message if this Schedule is invalid
   */
  private static Schedule findScheduleHelper(Schedule schedule, int index)
      throws IllegalStateException {

    // base case
    // checks if all courses have had a recursive call
    if (index == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        return schedule;
      } else {
        throw new IllegalStateException("This Schedule is invalid!");
      }
    }

    // course is already assigned to a room
    if (schedule.isAssigned(index)) {
      return findScheduleHelper(schedule, index + 1); // recursive call for the next course
    }

    // course is not yet assigned to a room

    // If an exception gets thrown, it means the schedule is invalid.
    // Exception is caught and the for loop tries to assign another room to the current course.
    // Repeats until a valid schedule is found.
    for (int i = 0; i < schedule.getNumRooms(); i++) {
      try {
        // new schedule reference is necessary to check all room assignment possibilities
        Schedule newSchedule = schedule.assignCourse(index, i);
        return findScheduleHelper(newSchedule, index + 1);
      } catch (Exception e) {
        // do nothing
      }
    }

    // in case every room in the above for loop results in an exception
    return findScheduleHelper(schedule, index + 1);
  }

  /**
   * Returns an ArrayList containing all possible Schedules for the given set of rooms and courses.
   * (If none can be created, this ArrayList is empty.) This method should contain only a call to
   * the helper method, providing an empty starting Schedule.
   * 
   * @return an ArrayList containing all possible Schedules for the given set of rooms and courses
   */
  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    Schedule schedule = new Schedule(rooms, courses);

    return findAllSchedulesHelper(schedule, 0); // first recursive method call so index of 0
  }

  /**
   * A private, recursive method that assigns all unassigned courses in a Schedule in ALL POSSIBLE
   * ways, beginning from the index provided as an argument.
   * 
   * @param schedule the schedule to find all possible Schedules for
   * @param index    the index to start assigning all unassigned courses in a Schedule
   * @return an ArrayList containing all possible Schedules beginning from startingIndex
   */
  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int index) {
    // holds all valid created schedules
    ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();

    // base case
    // checks if all courses have had a recursive call
    if (index == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        scheduleList.add(schedule);
        return scheduleList;
      }
    }

    // course is already assigned to a room
    if (schedule.isAssigned(index)) {
      // recursive call for the next course; adds all created valid schedules to ArrayList
      scheduleList.addAll(findAllSchedulesHelper(schedule, index + 1));
      return scheduleList;
    }

    // course is not yet assigned to a room

    // If an exception gets thrown, it means the schedule is invalid.
    // Exception is caught and the for loop tries to assign another room to the current course.
    // Repeats until a valid schedule is found which is then added to the ArrayList
    for (int i = 0; i < schedule.getNumRooms(); i++) {
      try {
        // new schedule reference is necessary to check all room assignment possibilities
        Schedule newSchedule = schedule.assignCourse(index, i);
        scheduleList.addAll(findAllSchedulesHelper(newSchedule, index + 1));
      } catch (Exception e) {
        // do nothing
      }
    }
    return scheduleList;
  }

}
