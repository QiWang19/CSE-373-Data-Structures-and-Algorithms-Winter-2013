// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapPriorityQueue<E> implements PriorityQueue<E> {
	// TODO: declare any private fields here
	private E[] queue;
	private int size;
	Comparator<E> myComparator;
	
	
	private int parent(int index) {
		return index / 2;
	}
	private int leftChild(int index) {
		return index * 2;
	}
	private int rightChild (int index) {
		return index * 2 + 1;
	}
	
	private boolean hasParent(int index) {
		return index > 1;
	}
	
	private boolean hasLeftChild (int index) {
		return leftChild(index) <= size;
	}
	
	private boolean hasRightChild (int index) {
		return rightChild(index) <= size;
	}
	
	private void swap(E[] e, int a, int b) {
		E temp = e[a];
		e[a] = e[b];
		e[b] = temp;
	}
	
	// TODO: comment header
	public HeapPriorityQueue() {
		
		 queue = (E[])new Object[10];
		 this.size = 0;
		
	}
	
	// TODO: comment header
	public HeapPriorityQueue(int capacity, Comparator<E> comparator) {
		if (capacity < 2) {
			throw new IllegalArgumentException();
		}
		this.size = 0;
		this.queue = (E[]) new Object[capacity];
		this.myComparator = comparator;
		
		
	}
	
	// TODO: comment header
	public void add(E value) {
		
		if (this.size == this.queue.length - 1) {
			this.queue = Arrays.copyOf(this.queue, 2 * this.queue.length);
		}
		if (value == null) {
			throw new NullPointerException();
		}
		
		int index = this.size + 1;
		this.queue[index] = value;
		boolean found = false;
		while(!found && hasParent(index)) {
			int parent = parent(index);
			if (this.myComparator == null) {
				if (((Comparable<E>)this.queue[index]).compareTo( this.queue[parent]) < 0) {
					this.swap(queue, index, parent);
					index = parent;
				} else {
					found = true;
				}
			} else {
				if (this.myComparator.compare(this.queue[index], this.queue[parent]) < 0) {
					this.swap(queue, index, parent);
					index = parent;
				}
				else {
					found = true;
				}
			}
			
		}
		this.size++;
	}
	
	// TODO: comment header
	public void clear() {
		// TODO: implement this method
		for (int i = 0; i < this.queue.length; i++) {
			this.queue[i] = null;
		}
		this.size = 0;
	}

	// TODO: comment header
	public boolean contains(E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		for (int i = 1; i <= this.size; i++) {
			if (this.queue[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

	// TODO: comment header
	public boolean isEmpty() {
		return this.size == 0;
	}

	// TODO: comment header
	public Iterator<E> iterator() {
		// TODO: implement this method
		return new myIterator();
		
	}
	
	private class myIterator<E> implements Iterator {
		
		private int curt = 1;
		@Override
		public boolean hasNext() {
			
			return !(HeapPriorityQueue.this.queue[curt] == null);
		}

		@Override
		public E next() {
			E res = (E) HeapPriorityQueue.this.queue[curt];
			this.curt++;
			
			return res;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	// TODO: comment header
	public E peek() {
		
		return this.queue[1];
	}

	// TODO: comment header
	public E remove() {
		if (this.isEmpty()) {
			this.clear();
			throw new NoSuchElementException();
		}
		E res = this.queue[1];
		this.queue[1] = this.queue[this.size];
		this.size--;
		int index = 1;
		boolean found = false;
		while (!found && hasLeftChild(index)) {
			int left = leftChild(index);
			int right = rightChild(index);
			int child = left;
			if (this.myComparator != null) {
				if (hasRightChild(index) && this.myComparator.compare(this.queue[right], this.queue[left]) < 0 ) {
					child = right;
				}
				if (this.myComparator.compare(this.queue[index], this.queue[child]) > 0) {
					swap(this.queue, index, child);
					index = child;
				} else {
					found = true;
				}
			} else {
				if (hasRightChild(index) && ((Comparable<E>)this.queue[right]).compareTo( this.queue[left]) < 0) {
					child = right;
				}
				if (((Comparable<E>)this.queue[index]).compareTo(this.queue[child]) > 0) {
					swap(this.queue, index, child);
					index = child;
				} else {
					found = true;
				}
			}
			
		}
		if (this.size == 0) {
			this.clear();
		}
		return res;
	}

	// TODO: comment header
	public void remove(E value) {
		if (value == null ) {
			throw new NullPointerException();
		}
		boolean found = false;
		int index = 1;
		for (int i = 1; i <= this.size; i++) {
			if (this.queue[i].equals(value)) {
				found = true;
				index = i;
			}
		}
		while(hasParent(index)) {
			int parent = parent(index);
			swap(this.queue, index, parent);
			index = parent;
		}
		if (found) {
			this.remove();
			if (this.size == 0) {
				this.clear();
			}
		}
		
		
	}
	
	// TODO: comment header
	public int size() {
		
		return this.size;
	}
	
	// TODO: comment header
	public String toString() {
		// TODO: implement this method
		if (this.size == 0) {
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 1; i <= this.size - 1; i++) {
			sb.append(this.queue[i].toString());
			sb.append(", ");
		}
		sb.append(this.queue[this.size].toString());
		sb.append("]");
		return sb.toString();
	}
	
}