package ca.utoronto.utm.paint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A Rectangle class that inherits from the Shape superclass.
 * Has a start and finish that are represented by Point objects.
 * Also has four points that will represent the 4 corners of this.
 * 
 * @author csc207student
 *
 */
public class Rectangle extends Shape{
	private Point start;
	private Point finish;
	private Point topLeft, topRight, bottomLeft, bottomRight;

	/**
	 * Creates a Rectangle object that initializes the start point
	 * since the finish point will be dynamically changing. 
	 * @param start
	 * 		Point object representing the start of this.
	 */
	public Rectangle(Point start) {
		super();
		this.start = start;
	}

	/**
	 * Creates a Rectangle object that also has a color, a thickness
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
	public Rectangle(Point start, int t, Color c, boolean f) {
		super(t, c, f);
		this.start = start;
	}

	/**
	 * Returns the start point of this.
	 * @return
	 * 		A Point object representing the start of this.
	 */
	public Point getStart() {
		return this.start;
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
	 * Sets the start point of this.
	 * @param start
	 * 		A Point object that will represent the start of this.
	 */
	public void setStart(Point start) {
		this.start = start;
	}

	/**
	 * Sets the finish point of this. Also sets the corners of this.
	 * @param finish
	 * 		A Point object that will represent the finish of this.
	 */
	public void setFinish(Point finish) {
		this.finish = finish;
		this.setCorners();
	}
	
	/**
	 * Creates the 4 corners of this to four Point objects. Sets
	 * topLeft, bottomRight, topRight, bottomLeft to 4 distinct
	 * points.
	 */
	private void setCorners()
	{
		int startX = start.getX();
		int startY = start.getY();
		int finishX = finish.getX();
		int finishY = finish.getY();
		
		this.topLeft = new Point(Math.min(startX, finishX), Math.min(startY, finishY));
		this.bottomRight = new Point(Math.max(startX, finishX), Math.max(startY, finishY));
		this.topRight = new Point(bottomRight.getX(), topLeft.getY());
		this.bottomLeft = new Point(topLeft.getX(), bottomRight.getY());
	}
	
	/**
	 * 
	 * @return
	 * 		The Point representing topLeft of this.
	 */
	public Point getTopLeft()
	{
		return this.topLeft;
	}
	
	/**
	 * 
	 * @return
	 * 		An int representing the width of this.
	 */
	public int getWidth()
	{
		return topRight.getX() - topLeft.getX();
	}
	
	/**
	 * @return
	 * 		An int representing the height of this.
	 */
	public int getHeight()
	{
		return bottomRight.getY() - topRight.getY();
	}
	
	/**
	 * Checks if a given Point p is contained within this.
	 * @param p
	 * 		The Point that could be contained in this.
	 * @return
	 * 		True if Point p is contained in this.
	 */
	public boolean isSelected(Point p)
	{
		boolean withinXBound = topLeft.getX() <= p.getX() && p.getX() <= topRight.getX();
		boolean withinYBound = topLeft.getY() <= p.getY() && p.getY() <= bottomLeft.getY();
		return withinXBound && withinYBound;
	}
	
}
