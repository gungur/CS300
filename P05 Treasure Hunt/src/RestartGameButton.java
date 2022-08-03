//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: RestartGameButton
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
 * Resets progress and restarts the game when clicked.
 */
public class RestartGameButton extends Button {

  /**
   * Creates a new RestartGameButton object labeled "Restart Game" at a specific position on the
   * screen.
   * 
   * @param x x-position to be assigned to this restart game button
   * @param y y-position to be assigned to this restart game button
   */
  public RestartGameButton(int x, int y) {

    super("Restart Game", x, y);
  }

  /**
   * Defines the behavior of this button when the mouse is pressed. This button initializes the game
   * if it is clicked (meaning if the mouse is over it).
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      InteractiveObject.processing.initGame();
    }
  }
}
