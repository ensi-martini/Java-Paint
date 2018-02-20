package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A Square class that inherits from the Shape superclass.
 * Has a start and finish that are represented by Point objects.
 * 
 * Contains int fields that will represent the x value of the topLeft
 * corner and the y value of the topLeft corner. The sideLength will
 * represent the side length of this, a Square.
 * @author csc207student
 *
 */
public class Square extends Shape{
	private Point start;
	private Point finish;
	private int topLeftX, topLeftY, sideLength;

	/**
	 * Creates a Square object that initializes the start point
	 * since the finish point will be dynamically changing. 
	 * @param start
	 * 		Point object representing the start of this.
	 */
	public Square(Point start) {
		super();
		this.start = start;
	}

	/**
	 * Creates a Square object that also has a color, a thickness
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
	public Square(Point start, int t, Color c, boolean f) {
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
	 * Sets the finish point of this. Also sets the side length and the 
	 * top left corner coordinates of this.
	 * @param finish
	 * 		A Point object that will represent the finish of this.
	 */
	public void setFinish(Point finish) {
		this.finish = finish;
		setSideLength();
		setTopLeft();
	}

	/**
	 * Sets the sideLength of this, to the bigger value
	 * of deltaX or deltaY, calculated through the absolute values
	 * of the substraction of start and finish.
	 */
	private void setSideLength()
	{
		int deltaX = Math.abs(start.getX() - finish.getX());
		int deltaY = Math.abs(start.getY() - finish.getY());
		this.sideLength = Math.max(deltaX, deltaY);
	}

	/**
	 * Sets the topLeft corner of this depending on cases and
	 * calculations through the start and finish of this.
	 */
	private void setTopLeft()
	{
		if (start.getX() < finish.getX()) {
			if (start.getY() > finish.getY()) {
				this.topLeftX = start.getX();
				this.topLeftY = start.getY() - sideLength;
			}
			else {
				this.topLeftX = start.getX();
				this.topLeftY = start.getY();
			}
		}
		else {
			if (start.getY() > finish.getY())
			{
				this.topLeftX = start.getX() - sideLength;
				this.topLeftY = start.getY() - sideLength;
			}
			else {
				this.topLeftX = start.getX() - sideLength;
				this.topLeftY = start.getY();
			}
		}
	}

	/**
	 * 
	 * @return
	 * 		An int representing the topLeft x value.
	 */
	public int getTopLeftX()
	{
		return this.topLeftX;
	}

	/**
	 * 
	 * @return
	 * 		An int representing the topLeft y value.
	 */
	public int getTopLeftY()
	{
		return this.topLeftY;
	}

	/**
	 * 
	 * @return
	 * 		An int representing the side length of this.
	 */
	public int getSideLength()
	{
		return this.sideLength;
	}

	/**
	 * Checks to see if a given Point p is located within this.
	 * @param p
	 * 		The Point to be checked.
	 * @return
	 * 		True if p is located within this.
	 */
	public boolean isSelected(Point p)
	{
		boolean withinXBound = topLeftX <= p.getX() && p.getX() <= topLeftX + sideLength;
		boolean withinYBound = topLeftY <= p.getY() && p.getY() <= topLeftY + sideLength;
		return withinXBound && withinYBound;
	}

}
