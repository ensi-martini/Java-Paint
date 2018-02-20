package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

/**
 * A DrawingCommand for a Polyline object. Implements
 * the DrawingCommand interface. Contains two stacks,
 * that is a stack of Polyline objects. One will be the main
 * stack where if an undo method is invoked, will pop onto
 * the redo stack.
 * 
 * @author csc207student
 *
 */
public class PolylineDrawingCommand implements DrawingCommand{

private Stack<Polyline> undoStack, redoStack;
	
	/**
	 * Create a PolylineDrawingCommand that initializes
	 * two stacks, the undo stack and the redo stack.
	 */
	public PolylineDrawingCommand()
	{
		this.undoStack = new Stack<Polyline>();
		this.redoStack = new Stack<Polyline>();
	}
	
	/**
	 * Adds a shape to the undo stack of this. Will
	 * first cast the shape into a Polyline object.
	 * 
	 * @param shape
	 * 		The shape to be added to the undo stack.
	 */
	@Override
	public void addShape(Shape shape) {
		undoStack.add((Polyline) shape);
	}

	/**
	 * Draws a Polyline by iterating through the array of points
	 * that the Polyline has, then connecting pairs of lines together
	 * to create one polyline.
	 * 
	 * @param g2d
	 * 		The Graphics2D object that will draw the Polyline.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (!undoStack.isEmpty())
		{
			Polyline polyline = undoStack.peek();
			g2d.setStroke(new BasicStroke(polyline.getThickness()));
			g2d.setColor(polyline.getColor());
			
			for (int i = 0; i < polyline.getPoints().size() - 1; i++) {
				Point point1 = polyline.getPoints().get(i);
				Point point2 = polyline.getPoints().get(i+1);
				g2d.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
			}
		}
	}

	/**
	 * Method will pop from the undo stack a Polyline object and then
	 * push that Polyline onto our redo stack.
	 */
	@Override
	public void undo() {
		if (!undoStack.isEmpty())
		{
			redoStack.push(undoStack.pop());
		}
	}

	/**
	 * Method will pop from the redo stack a Polyline object and then push
	 * that Polyline onto the undo stack.
	 */
	@Override
	public void redo() {
		if (!redoStack.isEmpty())
		{
			undoStack.push(redoStack.pop());
		}
	}
	
	/**
	 * Returns whether or not our undo stack is empty.
	 * 
	 * @return
	 * 		True if undo stack is empty.
	 */
	@Override
	public boolean isUndoStackEmpty() {
		return undoStack.isEmpty();
	}

	/**
	 * Return whether or not our redo stack is empty.
	 * 
	 * @return
	 * 		True if redo stack is empty.
	 */
	@Override
	public boolean isRedoStackEmpty() {
		return redoStack.isEmpty();
	}

	/**
	 * Given a Point object, calls the method isSelected from
	 * Shape. Checks if the Point p is selected within the Polyline.
	 * @param p
	 * 		The Point to be sent into isSelected.
	 * @return
	 * 		True if the Point p is selected.
	 */
	@Override
	public boolean isSelected(Point p) {
		if (!undoStack.isEmpty())
		{
			return undoStack.peek().isSelected(p);
		}
		
		return false;
	}

	@Override
	public void changeColor(Color c) {}

}
