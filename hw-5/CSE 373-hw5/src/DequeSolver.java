// TODO: Remove each 'todo' comment once I implement each part!
// TODO: class comment header

import java.awt.*;

// TODO: remove these two import statements once my ArrayDeque (Part B) is working,
// TODO: so that this class will use my ArrayDeque instead of Java's Deque
import java.util.*;
import java.util.Deque;


public class DequeSolver implements Solver{
	// TODO: comment header
	public boolean solve(Maze maze) {
		 
		Deque<Point> deque = new LinkedList<Point>();
		if (maze == null) {
			throw new NullPointerException();
		}
		
		int[] X = {0, 0, -1, 1};
		int[] Y = {1, -1, 0, 0};
		
		deque.addFirst(maze.start());
		Point end = maze.end();
		
		while(!deque.isEmpty()) {
			Point l = deque.getFirst();
			deque.removeFirst();
			if (l.x == end.x && l.y == end.y) {
				return true;
			}
			if (maze.isVisited(l.x, l.y)) {
				continue;
			}
			maze.setVisited(l.x, l.y);
//			ArrayList<Point> neighbors = getNeighbors(l);
			for (int i = 0; i < 4; i++) {
				int px = l.x + X[i];
				int py = l.y + Y[i]; 
				if (!maze.isInBounds(px, py) || maze.isWall(px, py)) {
					continue;
				}
				
				if (getDistance(px, py, end.x, end.y) < getDistance(l.x, l.y, end.x, end.y)) {
					deque.addFirst(new Point(px, py));
				} else {
					deque.addLast(new Point(px, py));
				}
			}
			
		}
		return false;
	}
	
	
	
	private double getDistance(int x1, int y1, int x2, int y2) {
		return (double)Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}