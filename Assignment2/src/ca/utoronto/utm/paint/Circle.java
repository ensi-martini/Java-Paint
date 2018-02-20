package ca.utoronto.utm.paint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A Circle object that inherits from the Superclass Shape.
 * The centre of the circle is represented by a Point object.
 * The radius is a positive integer that is >= 0. 
 * @author csc207student
 *
 */
public class Circle extends Shape{
	
	private Point centre;
	private int radius;

	/**
	 * Circle constructor that takes 5 parameters to create a circle.
	 * @param centre 
	 * 				The centre of the circle.
	 * @param radius 
	 * 				The radius value of the circle.
	 * @param t 
	 * 				The thickness value of the circle.
	 * @param c
	 * 				The color of the circle.
	 * @param f
	 * 				Boolean representing whether this Circle is going to be filled or not.
	 */
	public Circle(Point centre, int radius, int t, Color c, boolean f) {
		super(t, c, f);
		this.centre = centre;
		this.radius = radius;
	}
	
	/**
	 * Circle constructor that takes 2 parameters to create a circle.
	 * @param centre
	 * 				The centre of the circle.
	 * @param radius
	 * 				The radius of the circle.
	 */
	public Circle(Point centre, int radius){
		super();
		this.centre = centre;
		this.radius = radius;
	}
	
	/**
	 * Return the centre point of the circle.
	 * @return
	 * 			A Point object that represents the centre of the circle.
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * Set the centre point of the circle.
	 * @param centre
	 * 			A Point object that will represent the centre of the circle.
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	/**
	 * Return the radius of the circle.
	 * @return
	 * 			An integer value that represents the radius of the circle.
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Set the radius value of the circle.
	 * @param radius
	 * 			An integer value that will represent the radius of the circle.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Returns true if the Point p given is
	 * a point within this.
	 * @param p
	 * 		A Point that will be located in this.
	 * @return
	 * 		True if p is in this.
	 */
	public boolean isSelected(Point p)
	{
		int deltaX = this.centre.getX() - p.getX();
		int deltaY = this.centre.getY() - p.getY();
		return deltaX * deltaX + deltaY * deltaY <= radius * radius;
	}

}
