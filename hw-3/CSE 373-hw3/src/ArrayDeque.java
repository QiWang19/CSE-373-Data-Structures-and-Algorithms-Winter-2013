// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.*;

public class ArrayDeque<E> implements Deque<E> {
	// TODO: declare my private fields here
	
	
	 private E[] deque;
	 private int size = 0;	//number of elements in the array
	 private int front = 0;	//index of the first element
	
	// TODO: comment header
	public ArrayDeque() {
		// TODO: implement the constructor
		
		deque = (E[])new Object[10];
	}
	
	// TODO: comment header
	public void addFirst(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (this.size == this.deque.length) {
			this.deque = this.reSize();
		}
		if (this.size == 0) {
			this.deque[this.front] = element;
//			this.front = (this.front - 1 + this.deque.length) % this.deque.length;
			this.size = this.size + 1;
			return;
		}
		int index = (this.front - 1 + this.deque.length) % this.deque.length;
		this.deque[index] = element;
		this.size = this.size + 1;
		this.front = index;
		if (this.size == this.deque.length) {
			this.deque = this.reSize();
		}
		
	}
	
	// TODO: comment header
	public void addLast(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (this.size == this.deque.length) {
			this.deque = this.reSize();
		}
		if (this.size == 0) {
			this.deque[this.front] = element;
			this.size = this.size + 1;
			return;
		}
		int end = this.front;
		 for (int i = 1; i < this.size; i++) {
			 end = (end + 1) % ArrayDeque.this.deque.length;
		 }
		 end =( end + 1) % this.deque.length;
		 this.deque[end] = element;
		 this.size = this.size + 1;
		 if (this.size == this.deque.length) {
			 this.deque = this.reSize();
		 }
		
		
	}
	
	private E[] reSize() {
		E[] array = (E[])new Object[this.deque.length * 2];
		int temp = this.front;
		for (int i = 0; i < this.size; i++) {
			int index = temp % this.deque.length;
			temp++;
			array[i] = this.deque[index];
		}
		this.front = 0;
		return array;
	}
	
	// TODO: comment header
	public void clear() {
		for (int i = 0; i < this.deque.length; i++) {
			deque[i] = null;
		}
		this.size = 0;
		this.front = 0;
		
	}
	
	// TODO: comment header
	public boolean isEmpty() {
		return this.size == 0;
		
	}
	
	// TODO: comment header
	public Iterator<E> iterator() {
		return new ArrayDequeIterator();
	}
	
	
	// TODO: comment header
	public E peekFirst() {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		E first = this.deque[this.front];
		return first;
		
	}
	
	// TODO: comment header
	public E peekLast() {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		int end = this.front;
		 for (int i = 1; i < this.size; i++) {
			 end = (end + 1) % ArrayDeque.this.deque.length;
		 }
		E last = this.deque[end];
		
		return last;
	}
	
	// TODO: comment header
	public E removeFirst() {
		
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		E first  = this.deque[this.front];
		this.size = this.size - 1;
		if (this.size == 0) {
			this.clear();
		} else {
			this.deque[front] = null;
			this.front = (this.front + 1) % this.deque.length;
		}
		return first;
	}
	
	// TODO: comment header
	public E removeLast() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		int end = this.front;
		 for (int i = 1; i < this.size; i++) {
			 end = (end + 1) % ArrayDeque.this.deque.length;
		 }
		E last = this.deque[end];
		this.deque[end] = null;
		this.size = this.size - 1;
		if (this.size == 0) {
			this.clear();
		}
		return last;
	}
	
	// TODO: comment header
	public int size() {
		
		return this.size;
	}
	
	// TODO: comment header
	public String toString() {
		
		ArrayList<String> str = new ArrayList<String>();
		int temp = this.front;
		for (int i = 0; i < this.size; i++) {
			int index = temp % this.deque.length;
			temp++;
			str.add(this.deque[index].toString());
		}
		return str.toString();

	}
	
	// TODO: Implement your inner iterator class here.
	// TODO: comment header
	private class ArrayDequeIterator implements Iterator<E> {
		
		int current;
		int numOfElements;
		int endIndex;
		int count;
		// TODO: comment header
		public ArrayDequeIterator() {
			
			 this.current = ArrayDeque.this.front;
			 this.numOfElements = ArrayDeque.this.size;
			 this.count = 0;
			 
			 
			 if (this.numOfElements == 0 || this.numOfElements == 1) {
				 this.endIndex = ArrayDeque.this.front;
			 } else {
				 for (int i = 1; i < this.numOfElements; i++) {
					 this.endIndex = (this.endIndex + 1) % ArrayDeque.this.deque.length;
				 }
			 }
			 
			 
		}
		
		// TODO: comment header
		public boolean hasNext() {
			if (this.current == (this.endIndex ) %  ArrayDeque.this.deque.length && this.count == this.numOfElements) {
				return false;
			}
			 
			return !(ArrayDeque.this.deque[( this.current  ) % ArrayDeque.this.deque.length] == null);
			
			
		}
		
		// TODO: comment header
		public E next() {
			E next = null;
			if (this.hasNext()) {
				next = ArrayDeque.this.deque[( this.current  ) % ArrayDeque.this.deque.length];
			}
			this.current =( this.current + 1 ) % ArrayDeque.this.deque.length;
			this.count++;
			return next;
		}
		
		/**
		 * Removes the most recently returned element.
		 * Not supported. Throws an UnsupportedOperationException when called.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}