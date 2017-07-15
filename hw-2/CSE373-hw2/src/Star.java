// TODO: Remove each 'todo' comment once I implement each part!

// TODO: comment header

import java.awt.Color;

/**
 * Each Star object represents an individual star that can be put into a star chart.
 * A star is represented by a 3-dimensional (x, y, z) position, where each coordinate
 * is in the range [-1.0 .. 1.0] from bottom/left to top/right.
 * A star also has a magnitude which is inversely related to its brightness and size.
 */
public class Star implements Comparable<Star> {
	private double x;
	private double y;
	private double z;
	private double magnitude;
	private Color color;         // cached to save memory
	
	/**
	 * Constructs a new star with the given position and magnitude.
	 * The x/y/z are in range [-1.0 .. 1.0] from bottom/left to top/right.
	 * The magnitude is a non-negative number representing the star's size. 
	 * Assumes all parameters are in valid ranges.
	 */
	public Star(double x, double y, double z, double magnitude) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.magnitude = magnitude;
		int brightness = (int) ((z + 1.0) / 2 * 128) + 128;
		this.color = new Color(brightness, brightness, brightness);
	}
	
	/**
	 * Returns this star's magnitude as was passed to the constructor.
	 */
	public double getMagnitude() {
		return magnitude;
	}
	
	/**
	 * Returns this star's x-coordinate as was passed to the constructor.
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Returns this star's y-coordinate as was passed to the constructor.
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns this star's y-coordinate as was passed to the constructor.
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Returns an integer code that can be used by hash-based collections
	 * to store Star objects properly.
	 */
	public int hashCode() {
		return Double.valueOf(x + 1).hashCode() +
				103 * Double.valueOf(y + 1).hashCode() +
				317 * Double.valueOf(z + 1).hashCode() +
				1337 * Double.valueOf(magnitude).hashCode();
	}
	
	/**
	 * Returns the x-coordinate on the screen at which this star should be drawn.
	 * This is a translation of coordinates from [-1.0 .. 1.0] to [0 .. width).
	 */
	public int pixelX(int width) {
		return (int) ((x + 1.0) / 2 * width);
	}
	
	/**
	 * Returns the x-coordinate on the screen at which this star should be drawn.
	 * This is a translation of coordinates from [-1.0 .. 1.0] to [0 .. width).
	 * The y-axis is flipped between star coordinates and screen coordinates.
	 */
	public int pixelY(int height) {
		return height - (int) ((y + 1.0) / 2 * height);
	}
	
	/**
	 * Returns the color with which this star should be drawn.
	 */
	public Color pixelColor() {
		return color;
	}
	
	/**
	 * Returns the width/height of the oval that should be drawn to display this star.
	 * Brighter stars have smaller magnitudes.
	 */
	public int pixelSize() {
		return Math.max(2, (int) Math.round(10.0 / (magnitude + 2)));
	}
	
	// --------- YOUR NEW METHODS GO BELOW ---------- //
	
	// TODO: comment header
	public int compareTo(Star other) {
		if (this.getZ() != other.getZ()) {
			if (this.getZ() > other.getZ()) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.getY() != other.getY()) {
			if (this.getY() > other.getY()) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.getX() != other.getX()) {
			if (this.getX() > other.getX()) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.getMagnitude() != other.getMagnitude()) {
			if (this.getMagnitude() > other.getMagnitude()) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}
	
	// TODO: comment header
	public double distance(Star other) {
		double dX = Math.pow(this.getX() - other.getX(), 2);
		double dY = Math.pow(this.getY() - other.getY(), 2);
		double dZ = Math.pow(this.getZ() - other.getZ(), 2);
		return (double)Math.sqrt(dX + dY + dZ); 
	}
	
	// TODO: comment header
	public boolean equals(Object o) {
		if (this.getClass() == o.getClass()) {
			Star copy = (Star)o;
			return this.getX() == copy.getX() && 
					this.getY() == copy.getY() &&
					this.getZ() == copy.getZ() &&
					this.getMagnitude() == copy.getMagnitude();
		}
//		return super.equals(o);
		return false;
	}
	
	// TODO: comment header
	public String toString() {
		return "(" + this.getX() + "," + this.getY() + "," + this.getZ() + "):" + this.getMagnitude();
	}
	
	
}