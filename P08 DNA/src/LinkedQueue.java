//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: LinkedQueue
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
 * A generic implementation of a linked queue.
 *
 * @param <T> - the type of data to be contained in the queue
 */
public class LinkedQueue<T> implements QueueADT<T> {

  // data fields
  private int n; // The number of elements in the queue.
  protected Node<T> front; // The node at the front of the queue, added LEAST recently.
  protected Node<T> back; // The node at the back of the queue, added MOST recently.

  /**
   * Adds the given data to this queue; every addition to a queue is made at the back.
   * 
   * @param element - the element to add
   */
  @Override
  public void enqueue(T element) {
    // adding to an empty queue
    if (this.isEmpty() == true) {
      Node<T> newNode = new Node<T>(element);
      this.front = newNode; // is both front and back because it is the only node in the queue
      this.back = newNode;
      this.n++; // incrementing size
    } else {
      // adding to a non-empty queue
      Node<T> newNode = new Node<T>(element);
      this.back.setNext(newNode); // back is the most recently added item
      this.back = newNode; // must be done last or higher runtime complexity
      this.n++;
    }
  }

  /**
   * Removes and returns the item from this queue that was least recently added.
   * 
   * @return the item from this queue that was least recently added
   * @throws NoSuchElementException - if this queue is empty
   */
  @Override
  public T dequeue() throws NoSuchElementException {
    if (this.isEmpty() == true) {
      throw new NoSuchElementException("Cannot remove from an empty queue!");
    }
    T toReturn = this.front.getData(); // front is the least recently added item
    if (this.size() == 1) {
      this.front = null; // last item so calling getNext() on null would result in an exception
    } else {
      this.front = this.front.getNext(); // setting front to the next least recently added item
    }
    this.n--; // decrementing size

    return toReturn;
  }

  /**
   * Returns the item least recently added to this queue without removing it.
   * 
   * @return the item least recently added to this queue
   * @throws NoSuchElementException - if this queue is empty
   */
  @Override
  public T peek() throws NoSuchElementException {
    if (this.isEmpty() == true) {
      throw new NoSuchElementException("Cannot peek in an empty queue!");
    }
    T toReturn = this.front.getData();

    return toReturn;
  }

  /**
   * Checks whether the queue contains any elements.
   * 
   * @return true if this queue is empty; false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (this.size() == 0) {
      return true;
    }

    return false;
  }

  /**
   * Returns the number of items in this queue.
   * 
   * @return the number of items in this queue
   */
  @Override
  public int size() {

    return this.n; // properly incremented/decremented in enqueue/dequeue
  }

  /**
   * Returns a string representation of this queue, beginning at the front (least recently added) of
   * the queue and ending at the back (most recently added). An empty queue is represented as an
   * empty string; otherwise items in the queue are represented using their data separated by
   * spaces.
   * 
   * @return the sequence of items in FIFO order, separated by spaces
   */
  @Override
  public String toString() {
    if (this.isEmpty() == true) {
      return "";
    }
    String toReturn = "";
    Node<T> pointer = new Node<T>(null); // don't want to alter queue so created a "pointer" node
                                         // data inside doesn't matter
    pointer.setNext(this.front);
    // pointer node traverses queue and concatenates a String along the way
    for (int i = 0; i < this.size(); i++) {
      toReturn = toReturn + pointer.getNext().getData() + " "; // a space between each item
      pointer.setNext(pointer.getNext().getNext()); // increments the pointer node
    }

    return toReturn;
  }

}
