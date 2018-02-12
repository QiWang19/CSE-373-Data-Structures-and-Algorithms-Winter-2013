// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.awt.Point;
import java.util.Comparator;

// TODO: comment out these two imports to use your own priority queue
import java.util.PriorityQueue;
import java.util.Queue;


public class PriorityQueueSolver implements Solver {

	Comparator<Point> comp;
	private Queue<Point> queue;
	private Point end;
	// TODO: comment header
	public boolean solve(Maze maze) {
		
		if (maze == null) {
			throw new NullPointerException();
		}
		
		
		int[] X = {0, 0, 1, -1};
		int[] Y = {1, -1, 0, 0};
		
		this.end = maze.end();
		
		comp = new MyComparatorClass(this.end);
		queue = new PriorityQueue<Point>(comp);
		queue.add(maze.start());
		
		while(!queue.isEmpty()) {
			Point l = queue.poll();
			if (l.equals(end)) {
				return true;
			}
			if (maze.isVisited(l.x, l.y) || maze.isWall(l.x, l.y)) {
				continue;
			}
			maze.setVisited(l.x, l.y);
			for (int i = 0; i < 4; i++) {
				int x = l.x + X[i];
				int y = l.y + Y[i];
				
				if ( !maze.isInBounds(x, y) || maze.isWall(x, y)) {
					continue;
				} else {
					queue.add(new Point(x, y));
				}
				
			}
		}
		
		return false;
		
		
		
	}
	
	private class MyComparatorClass<Point> implements Comparator {
		
		java.awt.Point p;
//		= PriorityQueueSolver.this.end;
		
		public MyComparatorClass(java.awt.Point end) {
			super();
			this.p = end;
		}

		@Override
		public int compare(Object o1, Object o2) {
			java.awt.Point p1 = (java.awt.Point)o1;
			java.awt.Point p2 = (java.awt.Point)o2;
			
			double d1 = p.distance(p1);
			double d2 = p.distance(p2);
			
			if (d1 > d2) {
				return 1;
			} else if (d1 < d2) {
				return -1;
			} 
			return 0;
		}

		
	}
	
}