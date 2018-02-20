package ca.utoronto.utm.paint;
/**
 * A Point class used for creating points.
 * Has an x and y value that will take on values
 * that are negative or positive integers.
 * @author csc207student
 *
 */
public class Point {
	
	int x, y;
	
	/**
	 * Construct a Point object.
	 * @param x
	 * 			The x-coordinate of the point.
	 * @param y
	 * 			The y-coordinate of the point.
	 */
	Point(int x, int y){
		this.x=x; this.y=y;
	}
	
	/**
	 * Returns the x-coordinate of this.
	 * @return
	 * 			An integer value representing the
	 * 			x-coordinate of this.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x-coordinate of this.
	 * @param x
	 * 			An integer value that will
	 * 			represent the x-coordinate of this.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the y-coordinate of this.
	 * @return
	 * 			An integer value representing the
	 * 			y-coordinate of this.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y-coordinate of this.
	 * @param x
	 * 			An integer value that will
	 * 			represent the y-coordinate of this.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
