// TODO: Remove each 'todo' comment once I implement each part!

// TODO: comment header

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.*;

public class StarChart {
	// TODO: declare my private fields here
	private int width;
	private int height;
	private Multimap<String, Star> mmap; 
	private ListMultimap<String, Star> lmap;
	private List<Star> dead;
			
	
	// TODO: comment header
	public StarChart(int width, int height) {
		this.width = width;
		this.height = height;
		this.mmap = ArrayListMultimap.create();
		this.lmap = ArrayListMultimap.create();
		this.dead = new ArrayList<Star>();     
	}
	
	// TODO: comment header
	public void addStar(Star star, String name) {
		if (name != null) {
			this.mmap.put(name, star);
		} else {
			this.mmap.put("", star);
		}
		
	}
	
	// TODO: comment header
	public void addConstellation(String[] starNames, String constellationName) {
		Collection<Star> list = null;
		for (String str: starNames) {
			list = new ArrayList();
			list.addAll(this.mmap.get(str));
//			this.lmap.put(constellationName, list);
		}
		this.lmap.putAll(constellationName,list);
	}
	
	// TODO: comment header
	public String getName(Star star) {
		for(String name: this.mmap.keySet()) {
			if (this.mmap.get(name).size() == 1 && this.mmap.get(name).contains(star)) {
				return name;
			}
		}
		return null;
	}
	
	// TODO: comment header
	public Star getStar(double x, double y) {
		
		for(Star c: this.mmap.values()) {
			if (c.getX() == x && c.getY() == y) {
				return c;
			}
		}
		return null;
	}
	
	// TODO: comment header
	public Star getStar(String name) {
		if (this.mmap.get(name).size() == 1 ) {
			for(Star s: this.mmap.get(name)) {
				return s;
			}
		}
		return null;
	}
	
	
	// TODO: comment header
	public void draw(Graphics g, boolean showStarNames) {
		
		for (Star s: this.mmap.values()) {
			
			if (this.dead.contains(s)) {
				g.setColor(Color.RED);
				g.fillRect(s.pixelX(width), s.pixelY(height), s.pixelSize(), s.pixelSize() );
			} else {
				g.setColor(s.pixelColor());
				g.fillRect(s.pixelX(width), s.pixelY(height), s.pixelSize(), s.pixelSize() );
			}
			
			
		}
		
		for (String name: this.lmap.keySet()) {
			
			List<Star> l = this.lmap.get(name);
			for (int i= 0; i < l.size() - 1; i++) {
				Star start = l.get(i);
				Star end = l.get(i + 1);
				
				g.setColor(Color.YELLOW);
				g.drawLine(start.pixelX(width), start.pixelY(height), end.pixelX(width), end.pixelY(height));
				if (showStarNames) {
					g.setColor(start.pixelColor());
					g.drawString(getName(start), start.pixelX(width), start.pixelY(height));
					
					g.setColor(end.pixelColor());
					g.drawString(getName(end), end.pixelX(width), end.pixelY(height));
				}
			}
			g.setColor(Color.YELLOW);
			g.drawString(name, l.get(l.size() - 1).pixelX(width), l.get(l.size() - 1).pixelY(height));
		}
		
		

	}
	
	// TODO: comment header
	public int supernova(Star star) {
		
		if (this.dead.contains(star)) {
			return 0;
		} 
		
		int count = 1;
		dead.add(star);
		for(Star s: this.mmap.values()) {
			if (star.distance(s) > 0 && star.distance(s) <= 0.25 && !this.dead.contains(s)) {
				count++;
				dead.add(s);
			}
		}
		return count;
	}
}