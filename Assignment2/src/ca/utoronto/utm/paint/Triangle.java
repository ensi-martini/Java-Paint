package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A Triangle object that inherits from the Superclass Shape.
 * All three verticies are represented with points.
 * @author csc207student
 *
 */
public class Triangle extends Shape{

	private Point start;
	private Point finish;
	private Point mid;
	
	/**
	 * Constructor for Triangle class. Only creates one point upon instantiation.
	 * @param start
	 */
	public Triangle(Point start) {
		super();
		this.start = start;
	}
	
	/**
	 * Constructor for Triangle class. Takes all modifiable parameters.
	 * @param start
	 * @param t
	 * @param c
	 * @param f
	 */
	public Triangle(Point start, int t, Color c, boolean f) {
		super(t, c, f);
		this.start = start;
	}
	
	public Point getStart() {
		return this.start;
	}
	
	public Point getFinish() {
		return this.finish;
	}
	
	public Point getMid() {
		return this.mid;
	}
	
	public void setStart(Point start) {
		this.start = start;
	}
	
	public void setFinish(Point finish) {
		this.finish = finish;
		this.mid = new Point(this.start.getX(), this.finish.getY());
	}

	/**
	 * Checks within a hitbox whether this can be moved or not.
	 * @param p
	 * @return boolean of whether it can be moved or not.
	 */
	public boolean isSelected(Point p) {
		boolean withinXBound = Math.min(start.getX(), finish.getX()) <= p.getX() && p.getX() <=  Math.max(start.getX(), finish.getX());
		boolean withinYBound = Math.min(start.getY(), finish.getY()) <= p.getY() && p.getY() <=  Math.max(start.getY(), finish.getY());
		double m = (double) (finish.getY() - start.getY()) / (finish.getX() - start.getX());
		int b = -start.getY();
		if (withinXBound && withinYBound) {
			return true;
		}
	
		return false;
	}
	
	
	

	
}
