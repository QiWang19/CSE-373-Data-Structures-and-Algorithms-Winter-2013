// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.ArrayList;
//import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashMap<K, V> implements Map<K, V> {
	
	private class Node {
		public K key;
		public V value;
		public Node next = null;
		
		public Node(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		
	}
	
	
	private Node[] elements;
	private int size;
	
	// TODO: comment header
	
	public HashMap() {
		
		elements = (Node[]) new HashMap.Node[10];
		 size = 0;
		
	}
	
	
	
	public void clear() {
		for (int i = 0; i < elements.length; i++) {
			Node node = elements[i];
			while(node != null) {
				Node p = node;
				node = node.next;
				p = null;
			}
			elements[i] = null;
		}
		this.size = 0;
		
	}
	
	public boolean containsKey(K key) {
		int h = hash(key);
		Node curt = this.elements[h];
		while (curt != null) {
			if (curt.key.equals(key)) {
				return true;
			} else {
				curt = curt.next;
			}
			
		}
		return false;
	}

	public V get(K key) {
		
		Node node = elements[this.hash(key)];
		while(node != null) {
			if (node.key == key) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}

	
	public boolean isEmpty() {
		return this.size == 0;
	}

	public Set<K> keySet() {
		Set<K> set = (Set<K>) new TreeSet<Object>();
		for (int i = 0; i < this.elements.length; i++) {
			Node n = this.elements[i];
			while (n != null) {
				set.add(n.key);
				n = n.next;
			}
		}
		return set;
	}
	
	public void put(K key, V value) {
		int h = hash(key);
		if (this.containsKey(key)) {
			Node curt = elements[h];
			while(curt != null) {
				if (curt.key == key) {
					curt.value = value;
					return;
				}
				curt = curt.next;
			}
		} else {
			Node n = new Node(key, value);
			n.next = elements[h];
			elements[h] = n;
			this.size++;
		}
		if (this.size / this.elements.length >= 0.75) {
			this.reHash();
		}
		
	}
	
	private void reHash() {
		Node[] old = elements;
		this.size = 0;
		elements =  (Node[])new HashMap.Node[old.length * 2];
		for (int i = 0; i < old.length; i++) {
			Node node = old[i];
			while(node != null) {
				this.put(node.key, node.value);
				node = node.next;
			}
			
		}
		
		
	}



	public void remove(K key) {
		
		int h = hash(key);
		if (elements[h] != null && elements[h].key == key) {
			elements[h] = elements[h].next;
			this.size--;
		} else {
			Node curt = elements[h];
			while(curt != null && curt.next != null) {
				if (curt.next.key == key) {
					curt.next = curt.next.next;
					this.size--;
					return;
				}
				curt = curt.next;
			}
		}
		
		
		
	}

	public int size() {
		return this.size;
	}
	
	public String toString() {
		if (this.size == 0) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < this.elements.length; i++) {
			Node node = elements[i];
			while(node != null) {
				String str = node.key.toString() + "=" + node.value.toString();
				strings.add(str);
				node = node.next;
			}
		}
		
		for (int i = 0; i < strings.size() - 1; i++) {
			sb.append(strings.get(i));
			sb.append(", ");
		}
		sb.append(strings.get(strings.size() - 1));
		sb.append("}");
		return sb.toString();
	}
	
	private int hash(K key) {
		return  Math.abs(key.hashCode()) % elements.length;
	}
}