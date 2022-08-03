//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: DroppableObject
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
 * Allows us to specify a target for this kind of interactive object to be dropped on along with an
 * action that is produced when this happens.
 */
public class DroppableObject extends DraggableObject {

  // data fields
  private InteractiveObject target; // target of this droppable object

  /**
   * Creates a new Droppable object with specific name, x and y positions, message, target, and sets
   * its next clue.
   * 
   * @param name     name to be assigned to this droppable object
   * @param x        x-position of this droppable object
   * @param y        y-position this droppable object
   * @param message  message to be assigned to this droppable object
   * @param target   target where this droppable object should be dropped
   * @param nextClue reference to an interactive object which will be activated when this droppable
   *                 object is dropped on its target.
   */
  public DroppableObject(String name, int x, int y, String message, InteractiveObject target,
      InteractiveObject nextClue) {

    super(name, x, y, message);
    this.target = target;
    this.setNextClue(nextClue);
  }

  /**
   * Checks whether this object is over another interactive object.
   * 
   * @param other reference to another interactive object. We assume that other is NOT null.
   * @return true if this droppable object and other overlap and false otherwise.
   */
  public boolean isOver​(InteractiveObject other) {

    // diagonal of "this" object's rectangle
    int x1 = this.getX();
    int y1 = this.getY() + this.image.height;

    int x2 = this.getX() + this.image.width;
    int y2 = this.getY();

    // diagonal of "other" object's rectangle
    int x3 = other.getX();
    int y3 = other.getY() + other.image.height;

    int x4 = other.getX() + other.image.width;
    int y4 = other.getY();

    // checks if the borders of the rectangles do not overlap
    if ((x3 > x2) || (y3 < y2) || (x1 > x4) || (y1 < y4)) {

      return false;
    }

    return true; // the borders of the rectangles do overlap
  }

  /**
   * Stops dragging this droppable object. Also, if this droppable object is over its target and the
   * target is activated, this method (1) deactivates both this object and its target, (2) activates
   * the next clue, and (3) prints the message of this draggable object to the console.
   */
  @Override
  public void mouseReleased() {

    this.stopDragging();

    // target must be active for actions to occur
    if (this.isOver​(this.target) && this.target.isActive()) {

      this.deactivate();
      this.target.deactivate();
      this.activateNextClue();
      System.out.println(this.message());
    }
  }

}
