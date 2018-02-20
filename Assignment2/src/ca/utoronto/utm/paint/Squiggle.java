package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A Squiggle class that inherits from the Shape superclass.
 * Uses an ArrayList of Point objects to create itself.
 * @author csc207student
 *
 */
public class Squiggle extends Shape{

	private ArrayList<Point> points;

	/**
	 * Creates a Squiggle object and initializes
	 * an ArrayList of Point objects.
	 */
	public Squiggle() {
		super();
		this.points = new ArrayList<Point>();
	}

	/**
	 * Creates a Squiggle object that initializes
	 * an ArrayList of Points, and also has
	 * a thickness, color and whether or not
	 * this is filled.
	 * @param t
	 * 		Integer value that represents the thickness
	 * 		of this.
	 * @param c
	 * 		Color object that represents the color of this.
	 * @param f
	 * 		Boolean value that represents if this is filled.
	 */
	public Squiggle(int t, Color c, boolean f) {
		super(t, c, f);
		this.points = new ArrayList<Point>();
	}

	/**
	 * Return the Point ArrayList of this.
	 * @return
	 * 		ArrayList<Point> of this.
	 */
	public ArrayList<Point> getPoints() {
		return this.points;
	}

	/**
	 * Adds a Point object to the ArrayList of this.
	 * @param p
	 * 		The Point object to be added to the
	 * 		ArrayList of this.
	 */
	public void addPoint(Point p) {
		this.points.add(p);
	}
	
	/**
	 * Checks if the given Point p is contained
	 * in the array of points of Squiggle.
	 * 
	 * @param p the point to be checked if it is contained in this squiggle
	 * @return a boolean indicating whether p is inside this squiggle
	 */
	public boolean isSelected(Point p)
	{
		return this.points.contains(p);
	}

}


