import java.util.*;
/**
 * The {@code MyQueue} class represents a first-in-first-out (FIFO) queue of
 * generic items. It supports the usual <em>enqueue (add)</em> and <em>dequeue(poll)</em>
 * operations, with methods for peeking at the top item, testing if the
 * queue is empty, getting the size of the queue, and toString method that returns the items in queue 
 * in proper FIFO order.
 * <p>
 * </strong>This implementation uses the singly-linked list idea, that is implemented using the linked nodes</strong> 
 *</p>
 */

//
/**
* @param <E> the generic type of the element in the nodes.
*/
class Node<E> {
  E element;
  Node<E> next;

  public Node(E element) {
    this.element = element;
  }
}
/**
* The {@code MyQueue} class represents a first-in-first-out (FIFO) queue of
* generic items. It supports the usual <em>enqueue (add)</em> and <em>dequeue(poll)</em>
* operations, with methods for peeking at the top item, testing if the
* queue is empty, getting the size of the queue, and toString method that returns the items in queue 
* in proper FIFO order.
* @param <E> the generic type of an item in this queue
* @author OMAR CHALLAF
* @author ABDULRAHMAN IBRAHIM 
*/
public class MyQueue<E> {
	private Node<E> head; // head of the queue
	private Node<E> tail; // end of the queue
	private int size = 0; // size of the queue (number of elements)
	
	/**
    * Returns the firt item has been added to this queue.
    *
    * @return the firt item has been added to this queue, if queue is empty, return null
    */
	public E peek() {
		if (size == 0) {
		  return null;
		}
		else {
		  return head.element;
		}
	}
	
	/**
     * Add item to the queue (will be added to the end of queue).
     *
     * @param e the item to be added
     */
	
	public void add(E e) { // add to the last
		Node<E> newNode = new Node<>(e); // Create a new node for element e

		if (tail == null) {
		  head = tail = newNode; // The new node is the only node in list
		}
		else {
		  tail.next = newNode; // Link the new with the last node
		  tail = newNode; // tail now points to the last node
		}

		size++; // Increase size
	}
	
	 /**
     * Removes and returns the first item on this queue .
     *
     * @return the first item on this queue, if queue is empty, return null
     */

	public E poll() {
		if (size == 0) {
		  return null;
		}
		else {
		  E temp = head.element;
		  head = head.next;
		  size--;
		  if (head == null) {
			tail = null;
		  }
		  return temp;
		}
	}
	
	 /**
     * Remove all the elements from a queue, The queue will be empty after this method returns.
     * 
     */
  
	public void clear() {
		head = tail = null;
		size = 0;
	}
	
	/**
    * Returns size of queue (number of items).
    *
    * @return size of queue
    */

	public int size() {
		return size;
	}
	
	/**
    * Returns true if this queue is empty.
    *
    * @return {@code true} if this queue is empty(size = 0); {@code false} otherwise (size > 0)
    */
	
	public boolean isEmpty() {
		return size == 0;
    }
	
	/**
    * Returns a string representation of this queue.
    *
    * @return the sequence of items in FIFO order, closed by square brackets separated by spaces and commas
    */

	@Override
	public String toString() {
		if (head == null)
		return "[]";
    
		StringBuilder result = new StringBuilder("[");
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
		  result.append(current.element);
		  current = current.next;
		  if (current != null) {
			result.append(", "); // Separate two elements with a comma
		  }
		  else {
			result.append("]"); // Insert the closing ] in the string
		  }
		}
		return result.toString();
	}
}
