import java.util.*;


class Node<E> {
  E element;
  Node<E> next;

  public Node(E element) {
    this.element = element;
  }
}


public class MyQueue<E> {
	private Node<E> head, tail;
	private int size = 0;
	
	
	// private java.util.LinkedList<E> list 
	// = new java.util.LinkedList<E>();
		
		
	public E peek() {
		if (size == 0) {
		  return null;
		}
		else {
		  return head.element;
		}
	}
	
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
	
	// public void enqueue(E e) {
		// list.addLast(e);
	// }



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
  
	// public E dequeue() {
		// return list.removeFirst();
	// }
	public void clear() {
		head = tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
    }

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


class TestGenericQueue{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		Random r = new Random();
		
		MyQueue<Integer> queue = new MyQueue<>();
		int choice;
		do{
			System.out.println("Queue: " + queue);
			System.out.println("1 - add a random integer (0-100) into queue");
			System.out.println("2 - poll from queue");
			System.out.println("3 - peek queue");
			System.out.println("4 - Clear queue");
			System.out.println("5 - get queue size");
			System.out.println("6 - is queue empty?");
			System.out.println("0 - Exit");
			System.out.print("Command > ");
			choice = input.nextInt();
			
			switch(choice){
					case 1: queue.add(r.nextInt(100));
							break;
					case 2: System.out.println(queue.poll());
							break;
					case 3: System.out.println(queue.peek());
							break;
					case 4: queue.clear();
							break;
					case 5: System.out.println(queue.size());
							break;	
					case 6: System.out.println(queue.isEmpty());
							break;
			}
		}while(choice != 0);
	
	}
}