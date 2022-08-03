//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fountain
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

import java.util.Random;
import java.io.File;
import processing.core.PImage;

/**
 * Generates a graphical user interface (GUI) of a water fountain particle system as well as
 * capabilities for interaction by a user.
 */
public class Fountain {

  // static fields necessary for program to function, can be accessed within the different methods
  private static Random randGen;
  private static PImage fountainImage;
  private static int positionX;
  private static int positionY;
  private static Droplet[] droplets;
  private static int startColor; // blue: Utility.color(23,141,235)
  private static int endColor; // lighter blue: Utility.color(23,200,255)

  /**
   * Called once by Utility.runApplication() when the program begins. Creates and initializes the
   * data fields defined in the program and configures the graphical settings of the application.
   */
  public static void setup() {

    // Test method calls are here because running many of the methods requires first calling
    // Utility.runApplication().
    System.out.println(testUpdateDroplet());
    System.out.println(testRemoveOldDroplets());

    randGen = new Random();
    // dividing both width and height by 2 gives you the position at the center of the display
    positionX = Utility.width() / 2;
    positionY = Utility.height() / 2;
    // load the image of the fountain
    fountainImage = Utility.loadImage("images" + File.separator + "fountain.png");
    // will contain any droplet instances created, initialized to 800 null references
    droplets = new Droplet[800];
    // defines the range of color that each droplet can be
    startColor = Utility.color(23, 141, 235);
    endColor = Utility.color(23, 200, 255);
  }

  /**
   * Continuously executes its code until the program is stopped. Continuously draws the application
   * display window and updates its content regarding any change/event which affects its appearance.
   */
  public static void draw() {

    // must be first because it clears the display window
    Utility.background(Utility.color(253, 245, 230));
    // draws the fountain image to the screen at position (positionX, positionY)
    Utility.image(fountainImage, positionX, positionY);
    createNewDroplets(10);

    for (int i = 0; i < droplets.length; i++) {

      // avoids NullPointerException
      if (droplets[i] != null) {

        updateDroplet(i);
        // increments age of the droplet every time updateDroplet() method is called on its index
        droplets[i].setAge(droplets[i].getAge() + 1);
      }
    }

    // old droplets are removed to make room for new droplets, allows fountain to go "forever"
    removeOldDroplets(80);
  }

  /**
   * Updates the properties of a droplet (ex. appearance, motion, acceleration) and draws it in the
   * application display window.
   * 
   * @param index - index of the desired droplet in the droplets Droplet array
   */
  private static void updateDroplet(int index) {

    // local variables that make the code easier to read and understand
    int color = droplets[index].getColor();
    int transparency = droplets[index].getTransparency();
    float positionX = droplets[index].getPositionX();
    float positionY = droplets[index].getPositionY();
    float size = droplets[index].getSize();
    float velocityX = droplets[index].getVelocityX();
    float velocityY = droplets[index].getVelocityY();

    Utility.fill(color, transparency); // changes the color and transparency of the circles drawn
    // position and size of the drawn circle matches the droplet
    Utility.circle(positionX, positionY, size);
    velocityY = velocityY + 0.3f; // adds downwards acceleration to mimic gravity
    droplets[index].setVelocityY(velocityY);

    positionX = positionX + velocityX;
    droplets[index].setPositionX(positionX);
    positionY = positionY + velocityY;
    droplets[index].setPositionY(positionY);
  }

  /**
   * Indexes through droplets[] until a null reference is found. Then it creates a new Droplet
   * instance and assigns it to this null reference. Continues to do this until either indexing is
   * complete or the specified number of new droplets has been reached.
   * 
   * @param amount - the number of new droplets to be created
   */
  private static void createNewDroplets(int amount) {

    int counter = 0; // keeps track of new droplets created

    for (int i = 0; i < droplets.length; i++) {

      if (counter == amount) {

        break; // breaks out of the for loop when no more new droplets are needed
      }

      if (droplets[i] == null && counter < amount) {

        droplets[i] = new Droplet();
        // sets the basic properties of this newly created droplet using a random number generator
        // uses .nextFloat() because arguments require a float
        droplets[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextFloat()));
        droplets[i].setPositionX((positionX - 3) + (randGen.nextFloat() * 6));
        droplets[i].setPositionY((positionY - 3) + (randGen.nextFloat() * 6));
        droplets[i].setSize(4 + (randGen.nextFloat() * 7));
        droplets[i].setVelocityX(-1 + (randGen.nextFloat() * 2));
        droplets[i].setVelocityY(-10 + (randGen.nextFloat() * 5));
        // uses .nextInt() because arguments require an int
        droplets[i].setAge(randGen.nextInt(41));
        droplets[i].setTransparency(32 + randGen.nextInt(96));

        counter++;
      }
    }
  }

  /**
   * Searches through the droplets array for references to droplets with an age greater than the
   * specified max age and removes them by setting their array element to null. Allows the water
   * fountain to continue running "forever".
   * 
   * @param maxAge - the maximum age for Droplet instances in the droplets array
   */
  private static void removeOldDroplets(int maxAge) {

    for (int i = 0; i < droplets.length; i++) {

      if (droplets[i] != null && droplets[i].getAge() > maxAge) {

        droplets[i] = null; // sets the array droplet element to null if age is greater than maxAge
      }
    }
  }

  /**
   * Moves positionX and positionY to match the position of the mouse whenever the mouse button is
   * pressed. Can either click or drag to move the entire fountain around which new droplets are
   * created.
   */
  public static void mousePressed() {

    positionX = Utility.mouseX();
    positionY = Utility.mouseY();
  }

  /**
   * Saves a screenshot of the application display window when either the 's' or 'S' key is pressed
   * on the keyboard.
   * 
   * @param keyPressed - the character of the key pressed on the keyboard
   */
  public static void keyPressed(char keyPressed) {

    if (keyPressed == 's' || keyPressed == 'S') {

      Utility.save("screeenshot.png");
    }
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets. Creates a single
   * droplet at position (3,3) with velocity (-1,-2). Then checks whether calling updateDroplet() on
   * this droplet's index correctly results in changing its position to (2.0, 1.3).
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateDroplet() {

    // setting up droplet for test
    droplets = new Droplet[3];
    droplets[0] = new Droplet();
    droplets[0].setPositionX(3.0f);
    droplets[0].setPositionY(3.0f);
    droplets[0].setVelocityX(-1.0f);
    droplets[0].setVelocityY(-2.0f);

    updateDroplet(0); // calling the method we want to test

    if (droplets[0].getPositionX() != 2.0f || droplets[0].getPositionY() != 1.3f) {

      return false;
    }

    return true; // method passed test scenarios
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets. Calls
   * removeOldDroplets(6) on an array with three droplets (two of which have ages over six and
   * another that does not). Then checks whether the old droplets were removed and the young droplet
   * was left alone.
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldDroplets() {

    // setting up droplets for test
    droplets = new Droplet[3];
    droplets[0] = new Droplet();
    droplets[0].setAge(12);
    droplets[1] = new Droplet();
    droplets[1].setAge(4);
    droplets[2] = new Droplet();
    droplets[2].setAge(25);

    removeOldDroplets(6); // calling the method we want to test

    if (droplets[0] != null || droplets[1] == null || droplets[2] != null) {

      return false;
    }

    return true; // method passed test scenarios
  }

  /**
   * Main method
   * 
   * @param args - input arguments if any
   */
  public static void main(String[] args) {

    Utility.runApplication(); // automatically calls callback methods, do not explicitly call them
  }
}
