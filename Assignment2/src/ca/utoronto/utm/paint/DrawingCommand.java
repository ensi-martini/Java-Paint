package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * A basic DrawingCommand interface in which 
 * all other DrawingCommands will implement.
 * @author csc207student
 *
 */
public interface DrawingCommand {
	
	/**
	 * Change the color of the shape to the color c. This
	 * is only needed for changing the color of erasers.
	 * 
	 * @param c the color to be changed to
	 */
	public void changeColor(Color c);

	/**
	 * Will add a shape to an undo stack.
	 * @param shape
	 * 		Shape to be added.
	 */
	public void addShape(Shape shape);
	
	/**
	 * Graphically creates a given shape when this
	 * method called using the Graphics2D class.
	 * @param g2d
	 * 		The Graphics2D class in which the shape
	 * will be graphically represented on.
	 */
	public abstract void execute(Graphics2D g2d);
	
	/**
	 * Will pop from the undo stack and push onto
	 * a redo stack.
	 */
	public void undo();
	
	/**
	 * Will pop from the redo stack and push onto
	 * the undo stak.
	 */
	public void redo();
	
	/**
	 * Checks if the undo stack is empty or not.
	 * @return
	 * 		True if undo stack is empty.
	 */
	public boolean isUndoStackEmpty();
	
	/**
	 * Checks if redo stack is empty or not.
	 * @return
	 * 		True if redo stack is empty.
	 */
	public boolean isRedoStackEmpty();
	
	/**
	 * Given a Point p, checks whether this Point
	 * was selected in a given shape.
	 * @param p
	 * 		The Point to be compared.
	 * @return
	 * 		True if Point p is selected.
	 */
	public boolean isSelected(Point p);
	
}
