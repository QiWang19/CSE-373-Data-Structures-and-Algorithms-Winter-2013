import java.util.Queue;
import java.util.*;

// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

public class BaconNumberFinder {

	// TODO: comment header
	Graph<String, String> actors;
	public BaconNumberFinder(Graph<String, String> actors) {
		// TODO: implement this constructor
		this.actors = actors;
		
		
	}
	
	// TODO: comment header
	public void findBaconNumber(String actor) {
		
		Queue<String> queue = new LinkedList<String>();
		queue.offer("Kevin Bacon");
		int level = 0;
		while( !queue.isEmpty()) {
			String str = queue.poll();
			Set<String> neighbors = actors.neighbors(str);
			level++;
			for(String s: neighbors) {
				if (queue.contains(s)) {
					continue;
				}
				if (s.equals(actor)) {
					return;
				}
				queue.offer(s);
			}
		}
	}
}
