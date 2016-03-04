
/**This class represents a node of generic type E
 * that stores a piece of data 
 * and a reference to another node
 *
 * @author Joanna Kuklowska
 * @author Suebin Kim
 * @version 11/18/15
 */

public class Node <E> {

	//reference to the next node
	private Node <E> next;

	//data item stored in the node
	private E data;


	/**
	 * Default constructor creates an empty node.
	 */
	public Node () {
		data = null;
		next = null;
	}

	/**
	 * Creates a node with specified data item.
	 * @param data
	 *    data item to store in the node
	 */
	public  Node ( E data ) {
		if (data != null )
			this.data = data;
	}


	/**
	 * Creates a node with specified data and reference to next.
	 * @param data
	 *    data item to store in the node
	 * @param next
	 */
	public Node ( E data, Node<E> next ) {
		if (data != null )
			this.data = data;
		if (next != null )
			this.next = next;
	}

	/**
	 * @return the next
	 */
	public Node<E> getNext() {
		return next;
	}

	/**
	 * @param next 
	 * 	the next to set
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}

	/**
	 * @return the data
	 */
	public E getData() {
		return data;
	}

	/**
	 * @param item the data to set
	 */
	public void setData(E item) {
		this.data = item;
	}


	/**
	 * @return the string representation of the node
	 * 
	 */
	public String toString() {
		return "Data = " + this.data + " Next ref = " + this.next;
	}





}
