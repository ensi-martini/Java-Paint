package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A Polyline object that inherits from the Shape superclass.
 * Uses an ArrayList of Point objects to create itself.
 * @author csc207student
 *
 */
public class Polyline extends Shape{

	private ArrayList<Point> points;

	/**
	 * Creates a polyline object that only initializes point array.
	 *
	 * @param start
	 * 			A Point object representing the starting point.
	 */
	public Polyline(Point start) {
		super();
		this.points = new ArrayList<Point>();
	}

	/**
	 * Creates a Polyline object that also has a color, a thickness
	 * and whether or not it is filled, since this inherits
	 * from the Shape class.
	 * @param start
	 * 			A Point object representing the start of this.
	 * @param t
	 * 			An integer representing the thickness of this.
	 * @param c
	 * 			A Color representing the color of this.
	 * @param f
	 * 			Boolean of whether this if filled.
	 */
	public Polyline(Point start, int t, Color c, boolean f) {
		super(t, c, f);
		this.points = new ArrayList<Point>();
	}

	/**
	 * Returns all the points comprising this.
	 * @return
	 * 		A Point object representing the start of this.
	 */
	public ArrayList<Point> getPoints() {
		ArrayList<Point> newPoints = new ArrayList<Point>();
		for (Point point : this.points)
		{
			newPoints.add(point);
		}
		
		return newPoints;
	}
	
	/**
	 * Sets all the points that comprise this.
	 * 
	 * @param points the array of points that will comprise this.
	 */
	public void setPoints(ArrayList<Point> points)
	{
		this.points = points;
	}
	
	/**
	 * Adds a new point to this.
	 * @param p
	 * 		The point to be added.
	 */
	public void addPoint(Point p) {
		this.points.add(p);
	}
	
	/**
	 * Checks if the given Point p is contained
	 * in the array of points of Polyline.
	 * 
	 * @param p the point to be checked if it is contained in this polyline
	 * @return a boolean indicating whether p is inside this polyline
	 */
	public boolean isSelected(Point p)
	{
		return this.points.contains(p);
	}

}
