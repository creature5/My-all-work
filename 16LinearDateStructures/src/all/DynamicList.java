package all;

public class DynamicList<T extends Comparable<T>> {
	
	public static void main(String[] args){
		DynamicList<String> shoppingList = new DynamicList<String>();
		shoppingList.add("Milk");
		shoppingList.add("Honey");
		shoppingList.add("Olives");
		shoppingList.add("Beer");
//		shoppingList.remove("Olives");
		System.out.println("We need to buy:");
		shoppingList.sortByBubbles();
		for (int i = 0; i < shoppingList.getLength(); i++) {
			System.out.println(shoppingList.elementAt(i));
		}
		System.out.println("Do we have to buy Bread? " + shoppingList.contains("Bread"));
		
		DynamicList<Integer> numbers = new DynamicList<Integer>();
		numbers.add(10);
		numbers.add(7);
		numbers.add(3);
		numbers.add(1);
//		numbers.remove(new Integer(1));
		System.out.println("We need to buy:");
		numbers.sortByBubbles();
		for (int i = 0; i < numbers.getLength(); i++) {
			System.out.println(numbers.elementAt(i));
		}
		System.out.println("Do we have number 3 ? " + numbers.contains(3));
		
		
	}
	
	
	@SuppressWarnings("hiding")
	private class Node<T>{
		T element;
		Node<T> next;
		
		Node(T element, Node<T> prevNode) {
			this.element = element;
			prevNode.next = this;
		}
		Node(T element) {
			this.element = element;
			next = null;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int count;
	
	public DynamicList() {
		this.head = null;
		this.tail = null;
		this.count = 0;
	}
	
	/**
	* Add element at the end of the list
	* @param item - the element you want to add
	*/
	
	public void add(T item) {
		if (head == null) {
			// We have empty list
			head = new Node<T>(item);
			tail = head;
		} else {
			// We have non-empty list
			Node<T> newNode = new Node<T>(item, tail);
			tail = newNode;
		}
		count++;
	}
	
	/**
	* Removes and returns element on the specific index
	* @param index - the index of the element you want to remove
	* @return the removed element
	* @exception IndexOutOfBoundsException - when index is invalid
	*/
	public Object remove(int index) {
		if (index>=count || index<0) {
			throw new IndexOutOfBoundsException(
					"Invalid index: " + index);
		}
	
		// Find the element at the specified index
		int currentIndex = 0;
		Node<T> currentNode = head;
		Node<T> prevNode = null;
		while (currentIndex < index) {
			prevNode = currentNode;
			currentNode = currentNode.next;
			currentIndex++;
		}
	
		// Remove the element
		count--;
		if (count==0) {
			head = null;
			tail = null;
		} else if (prevNode == null) {
			head = currentNode.next;
		} else {
			prevNode.next = currentNode.next;
		}
		return currentNode.element;
	}
	
	/**
	* Removes the specified item and return its index
	* @param item – the item for removal
	* @return the index of the element or -1 if does not exist
	*/
	public int remove(T item){
		// Find the element containing searched item
		int currentIndex = 0;
		Node<T> currentNode = head;
		Node<T> prevNode = null;
		while (currentNode != null) {
			if ((currentNode.element!=null &&
					currentNode.element.equals(item)) ||
					(currentNode.element==null) && (item==null)){
				break;
			}
			prevNode = currentNode;
			currentNode = currentNode.next;
			currentIndex++;
		}
		if (currentNode != null) {
			// Element is found in the list. Remove it
			count--;
			if (count==0) {
				head = null;
				tail = null;
			} else if (prevNode == null) {
				head = currentNode.next;
			} else {
				prevNode.next = currentNode.next;
			}
			return currentIndex;
		} else {
			// Element is not found in the list
			return -1;
		}
	}
	
	/**
	* Searches for given element in the list
	* @param item - the item you are searching for
	* @return the index of the first occurrence of
	* the element in the list or -1 when not found
	*/
	public int indexOf(T item) {
		int index = 0;
		Node<T> current = head;
		while (current != null) {
			if ((current.element!=null && current.element.equals(item))
					|| (current.element==null) && (item==null)) {
					return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}
	
	/**
	* Check if the specified element exist in the list
	* @param item - the item you are searching for
	* @return true if the element exist or false otherwise
	*/
	public boolean contains(T item) {
		int index = indexOf(item);
		boolean found = (index != -1);
		return found;
	}
	
	/**
	* @param index – the position of the element [0 … count-1]
	* @return the object at the specified index
	* @exception IndexOutOfBoundsException - when index is invalid
	*/
	public T elementAt(int index) {
		if (index>=count || index<0) {
			throw new IndexOutOfBoundsException(
					"Invalid index: " + index);
		}
		Node<T> currentNode = this.head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode.element;
	}
	
	/**
	* @return the actual list length
	*/
	public int getLength() {
		return count;
	}

	@SuppressWarnings("null")
	public void sortByBubbles() {
		Node<T> currentNode = this.head;
		Node<T> prevousNode = null;
		int end = count;	
		for (int i = 1; i <= end + 1; i++) {
			for (int j = 1; j < end; j++) {
				if (currentNode.element.compareTo(currentNode.next.element) > 0) {
					Node<T> movedNode = currentNode;
					currentNode = currentNode.next;
					movedNode.next = currentNode.next;
					currentNode.next = movedNode;
					if (j != 1) {
						prevousNode.next = currentNode;
					}
				}
				if (j == 1) {
					this.head = currentNode;
				}
				prevousNode = currentNode;
				currentNode = currentNode.next;
			}
			prevousNode = null;
			currentNode = this.head;
			end--;
		}
	}	
}
