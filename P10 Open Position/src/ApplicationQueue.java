//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: ApplicationQueue
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
 * Array-based heap implementation of a priority queue containing Applications. Guarantees the
 * min-heap invariant, so that the Application at the root should have the lowest score, and
 * children always have a higher or equal score as their parent. The root of a non-empty queue is
 * always at index 0 of this array-heap.
 */
public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {

  private Application[] queue; // array min-heap of applications representing this priority queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty ApplicationQueue with the given capacity
   * 
   * @param capacity Capacity of this ApplicationQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public ApplicationQueue(int capacity) throws IllegalArgumentException {
    // verify the capacity
    if (capacity <= 0) {
      throw new IllegalArgumentException("The capacity is not a positive integer!");
    }

    // initialize fields appropriately
    this.queue = new Application[capacity];
    this.size = 0; // do not confuse with capacity
  }

  /**
   * Checks whether this ApplicationQueue is empty
   * 
   * @return true if this ApplicationQueue is empty
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the size of this ApplicationQueue
   * 
   * @return the size of this ApplicationQueue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Adds the given Application to this ApplicationQueue and use the percolateUp() method to
   * maintain min-heap invariant of ApplicationQueue. Application should be compared using the
   * Application.compareTo() method.
   * 
   * 
   * @param o Application to add to this ApplicationQueue
   * @throws NullPointerException  if the given Application is null
   * @throws IllegalStateException with a descriptive error message if this ApplicationQueue is full
   */
  @Override
  public void enqueue(Application o) throws NullPointerException, IllegalStateException {
    // verify the application
    if (o == null) {
      throw new NullPointerException("The given Application is null!");
    }

    // verify that the queue is not full
    if (this.size == this.queue.length) {
      throw new IllegalStateException("This ApplicationQueue is full!");
    }

    // if allowed, add the application to the queue and percolate to restore the heap condition
    this.queue[this.size] = o; // add to the next leaf according to heap shape property
    this.size++; // important not to forget
    percolateUp(size - 1); // helper method below
  }

  /**
   * Removes and returns the Application at the root of this ApplicationQueue, i.e. the Application
   * with the lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException with a descriptive error message if this ApplicationQueue is
   *                                empty
   */
  @Override
  public Application dequeue() throws NoSuchElementException {
    // verify that the queue is not empty
    if (this.isEmpty() == true) {
      throw new NoSuchElementException("The queue is empty!");
    }

    // save the lowest-scoring application
    Application lowest = this.queue[0];

    // replace the root of the heap and percolate to restore the heap condition
    this.queue[0] = this.queue[size - 1]; // replace the root with the last leaf
    this.queue[size - 1] = null;
    this.size--; // important not to forget

    // no need to percolate down if queue is empty
    if (!this.isEmpty()) {
      percolateDown(0); // helper method below
    }

    // return the lowest-scoring application
    return lowest;
  }

  /**
   * An implementation of percolateDown() method. Restores the min-heap invariant of a given subtree
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap invariant (it is due before its children), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateDown(int i) throws IndexOutOfBoundsException {
    if (i > this.size - 1 || i < 0) {
      throw new IndexOutOfBoundsException("The index is out of bounds!");
    }

    // recall that the min-heap is implemented with an array
    int leftIndex = (i * 2) + 1;
    int rightIndex = (i * 2) + 2;

    // makes code more readable
    boolean leftExists = leftIndex < this.size();
    boolean rightExists = rightIndex < this.size();

    // base case: index is now a leaf node, cannot percolate down any further
    // if left child doesn't exist, then right child doesn't exist due to heap shape property
    if (!leftExists) {
      return;
    }

    // queue is a min-heap: children should be greater than parents
    boolean leftSwap = queue[i].compareTo(queue[leftIndex]) > 0;
    boolean rightSwap = rightExists && queue[i].compareTo(queue[rightIndex]) > 0;

    // if either one is smaller than it
    if (leftSwap || rightSwap) {
      // swap it with the smaller child
      if (!rightExists) {
        // base case: swapping index and left child
        // right child doesn't exist so left child must be leaf due to shape property
        Application temp = queue[i];
        queue[i] = queue[leftIndex];
        queue[leftIndex] = temp;
        return;
      }
      // recursive case: two children, need to swap with smaller one, don't know if they are leaves
      int smallerIndex;
      if (queue[leftIndex].compareTo(queue[rightIndex]) > 0) {
        smallerIndex = rightIndex;
      } else {
        smallerIndex = leftIndex;
      }
      // swapping index with smaller child
      Application temp = queue[i];
      queue[i] = queue[smallerIndex];
      queue[smallerIndex] = temp;
      percolateDown(smallerIndex); // because we don't know if we reached the leaf yet
    } else {
      // base case: neither child is smaller, no swap needed
    }
  }

  /**
   * An implementation of percolateUp() method. Restores the min-heap invariant of the tree by
   * percolating a leaf up the tree. If the element at the given index does not violate the min-heap
   * invariant (it occurs after its parent), then this method does not modify the heap. Otherwise,
   * if there is a heap violation, swap the element with its parent and continue percolating the
   * element up the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * Feel free to add private helper methods if you need them.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateUp(int i) throws IndexOutOfBoundsException {
    if (i > this.size - 1 || i < 0) {
      throw new IndexOutOfBoundsException("The index is out of bounds!");
    }

    // recall that the min-heap is implemented with an array
    int parentIndex = (i - 1) / 2;

    // keep swapping until root is reached or index is bigger than parent
    while (i > 0 && this.queue[i].compareTo(queue[parentIndex]) < 0) {
      // swap this index with its parent
      Application temp = this.queue[i];
      this.queue[i] = this.queue[parentIndex];
      this.queue[parentIndex] = temp;
      i = parentIndex;
      parentIndex = (i - 1) / 2;
    }

  }

  /**
   * Returns the Application at the root of this ApplicationQueue, i.e. the Application with the
   * lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException if this ApplicationQueue is empty
   */
  @Override
  public Application peek() throws NoSuchElementException {
    // verify that the queue is not empty
    if (this.isEmpty() == true) {
      throw new NoSuchElementException("The queue is empty!");
    }

    // return the lowest-scoring application aka the root of this min-heap
    return this.queue[0];
  }

  /**
   * Returns a deep copy of this ApplicationQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * applications. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this ApplicationQueue. The returned new application queue has the same
   *         length and size as this queue.
   */
  public ApplicationQueue deepCopy() {
    ApplicationQueue deepCopy = new ApplicationQueue(this.queue.length);
    deepCopy.size = this.size();

    // copying all elements from one queue to another queue in the same order
    for (int i = 0; i < this.size(); i++) {
      deepCopy.queue[i] = this.queue[i];
    }

    return deepCopy;
  }

  /**
   * Returns a String representing this ApplicationQueue, where each element (application) of the
   * queue is listed on a separate line, in order from the lowest score to the highest score.
   * 
   * This implementation is provided.
   * 
   * @see Application#toString()
   * @see ApplicationIterator
   * @return a String representing this ApplicationQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Application a : this) {
      val.append(a).append("\n");
    }

    return val.toString();
  }

  /**
   * Returns an Iterator for this ApplicationQueue which proceeds from the lowest-scored to the
   * highest-scored Application in the queue.
   * 
   * This implementation is provided.
   * 
   * @see ApplicationIterator
   * @return an Iterator for this ApplicationQueue
   */
  @Override
  public Iterator<Application> iterator() {
    return new ApplicationIterator(this);
  }
}
