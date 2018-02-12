// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TreeSet<E extends Comparable<E>> implements Set<E> {
	// TODO: declare my private fields here
	TreeMap map;
	public TreeSet() {
		map = new TreeMap<>();
	}

	// TODO: comment header
	public void add(E element) {
		map.put(element, true);
	}

	// TODO: comment header
	public boolean contains(E element) {
		return map.containsKey(element);
	}

	// TODO: comment header
	public boolean isEmpty() {
		return map.isEmpty();
	}

	// TODO: comment header
	public Iterator<E> iterator() {
		// TODO: implement this method
		return map.keySet().iterator();
		
	}

	// TODO: comment header
	public void remove(E element) {
		map.remove(element);
	}

	// TODO: comment header
	public int size() {
		return map.size;
	}
	
	// TODO: comment header
	public String toString() {
		List<E> list = new ArrayList<>();
		Iterator i = this.map.keySet().iterator();
		while(i.hasNext()) {
			
			list.add((E)i.next());
		}
		for (int j = 0; j < list.size(); j++) {
			if (!contains(list.get(j))) {
				list.remove(j);
			}
		}
		Collections.sort(list);
		if (list == null || list.size() == 0 ) {
			return "[]";
		}
		return list.toString();
		
		
	}

	@Override
	public void clear() {
		this.map.clear();
	}
}
