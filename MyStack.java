/**This class represents a reference based stack of generic type E
* made up of Node objects
*
* @author Suebin Kim
* @version 11/18/15
*/

public class MyStack <E> {
	
	private Node<E> head;
	//number of elements in stack
	private int num;
	
	
	/**
	 * MyStack constructor creates an empty stack
	 */
	public MyStack () {
		this.head = null;
		this.num = 0;
	}
	
	/**
	 * Checks if stack is empty
	 * @return boolean
	 * 	True if stack is empty
	 * 	False if not
	 */
	public boolean empty() {
		if (this.head == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Peeks at the stop of the stack without removing that element
	 * @return E
	 * 	The element at the top of the stack
	 * @throws NullPointerException
	 * 
	 */
	public E peek() throws NullPointerException{
		if(empty()) {
			 throw new NullPointerException("Stack is empty");
		}
		return head.getData();
	}
	
	/**
	 * Returns and removes (Pops) the element at the top of the stack
	 * @return E
	 * 	The element at the top of the stack
	 * @throws NullPointerException
	 * 
	 */
	public E pop() throws NullPointerException{
		if(empty()) {
			throw new NullPointerException("Stack is empty");
		}
		//remove the element that was just popped
		//and set head to the next element
		E data = head.getData();
		head = head.getNext();
		this.num--;
		return data;
		
	}
	
	/**
	 * Adds (Push) element to the top of the stack
	 * @return E
	 * 	The element that was just added 
	 */
	public E push(E item) {
		
		//add node only if item is not null
		if (item != null ) {
			//create new node
			Node<E> newNode = new Node<E>(item);
			newNode.setNext(null);

			//special case for an empty list
			if (this.head == null) {
				this.head = newNode;
			}
			else {
				//create the current reference and advance it to the last node
				newNode.setNext(this.head);
				head = newNode;
			}
			//increase count of elements
			this.num++;
		}
		return item;
	}
		
	/**
	 * Finds the position of the first occurrence of the object
	 * @param o
	 * 	The object to search for
	 * @return int
	 * 	The position of the object in the stack
	 * 
	 */
	public int search(Object o) {
		
		//store the element at top of stack and set position
		Node <E> cur = this.head;
		int pos = 1;
		if(empty()) {
			throw new NullPointerException("Stack is empty");
		}
		
		while(cur.getData() != o) {
			pos++;
			cur = cur.getNext();
			//if o is not in the stack
			if (cur == null) {
				return -1;
			}
		}
		
		return pos;
	}
	
	/**
	 * String representation of the stack
	 * @return String
	 * 	The string representation of the stack
	 */
	@Override
	public String toString() {
		if(empty()) {
			return "empty stack";
		}
		String s = "";
		Node <E> cur = this.head;
		while(cur != null) {
			s = s+" "+ cur.getData();
			cur = cur.getNext();
		}
		return s;
	}
	
	
}


