package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

/**
 * This class handles all possible shapes that can be drawn to the GUI. Circles, rectangles, squares,
 * polylines, and squiggles all get added to an array to be drawn later.
 * 
 * @author User
 *
 */
public class ShapeArray extends Observable{
	
	private ArrayList<Shape> shapes;
	public int length = 0;
	
	/**
	 *  Constructs a new ShapeArray of Shape objects to be drawn to the GUI
	 */
	public ShapeArray() {
		this.shapes = new ArrayList<Shape>();
	}
	
	/**
	 * Add a shape to our array to be drawn to the GUI
	 * 
	 * @param s Shape to be added to our shape array
	 */
	public void addShape(Shape s) {
		this.shapes.add(s);
		length += 1;
	}
	
	/**
	 * 
	 * @return an array of all current Shapes to be drawn
	 */
	public ArrayList<Shape> getShapes() {
		return this.shapes;
	}

}
