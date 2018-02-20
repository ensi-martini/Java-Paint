package ca.utoronto.utm.paint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * An Oval class that inherits from the Shape superclass.
 * Has a start and finish that are represented by Point objects.
 * @author csc207student
 *
 */
public class Oval extends Shape{
	private Point centre;
	private Point finish;
	private int VRadius;
	private int HRadius;

	/**
	 * Creates an Oval object that initializes the centre point
	 * since the finish point will be dynamically changing. 
	 * @param start
	 * 		Point object representing the start of this.
	 */
	public Oval(Point centre) {
		super();
		this.centre = centre;
	}

	/**
	 * Creates an Oval object that also has a color, a thickness
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
	public Oval(Point centre, int t, Color c, boolean f) {
		super(t, c, f);
		this.centre = centre;
	}

	/**
	 * Returns the centre point of this.
	 * @return
	 * 		A Point object representing the centre of this.
	 */
	public Point getCentre() {
		return this.centre;
	}

	/**
	 * Returns the finish point of this.
	 * @return
	 * 		A Point object representing the finish of this.
	 */
	public Point getFinish() {
		return this.finish;
	}

	/**
	 * Sets the centre point of this.
	 * @param start
	 * 		A Point object that will represent the centre of this.
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	/**
	 * Sets the finish point of this. Also sets the radii of this.
	 * @param finish
	 * 		A Point object that will represent the finish of this.
	 */
	public void setFinish(Point finish) {
		this.finish = finish;
		this.setRadii();
	}
	
	/**
	 * Sets the horizontal and vertical radii of this.
	 */
	private void setRadii()
	{
		this.HRadius = Math.abs(centre.getX() - finish.getX());
		this.VRadius = Math.abs(centre.getY() - finish.getY());
	}
	
	/**
	 * 
	 * @return the width of this, or the horizontal radius.
	 */
	public int getWidth()
	{
		return this.HRadius;
	}
	
	/**
	 * 
	 * @return the height of this, or the vertical radius.
	 */
	public int getHeight()
	{
		return this.VRadius;
	}
	
	/**
	 * Returns true if the Point p given is
	 * a point within this.
	 * @param p
	 * 		A Point that will be located in this.
	 * @return
	 * 		True if p is in this, otherwise False.
	 */
	public boolean isSelected(Point p)
	{
		int deltaX = this.centre.getX() - p.getX();
		int deltaY = this.centre.getY() - p.getY();
		return (double) (deltaX * deltaX) / (HRadius * HRadius) + (double) (deltaY * deltaY) / (VRadius * VRadius) <= 1;
	}
	
}
