package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Stack;

/**
 * A DrawingCommand for a Triangle object. Implements
 * the DrawingCommand interface. Contains two stacks,
 * that is a stack of Triangle objects. One will be the main
 * stack where if an undo method is invoked, will pop onto
 * the redo stack.
 * @author csc207student
 *
 */
public class TriangleDrawingCommand implements DrawingCommand{

	private Stack<Triangle> undoStack, redoStack;
	
	/**
	 * Create a TriangleDrawingCommand that initializes
	 * two stacks, the undo stack and the redo stack.
	 */
	public TriangleDrawingCommand() {
		undoStack = new Stack<Triangle>();
		redoStack = new Stack<Triangle>();
	}
	
	/**
	 * Adds a shape to the undo stack of this. Will
	 * first cast the shape into a Circle object.
	 * 
	 * @param shape
	 * 		The shape to be added to the undo stack.
	 */
	@Override
	public void addShape(Shape shape) {
		undoStack.add((Triangle) shape);
	}

	/**
	 * Draws a Triangle by getting the top triangle of the undo stack
	 * and uses the Graphics2D class to create a graphical
	 * representation of the triangle.
	 * 
	 * @param g2d
	 * 		The Graphics2D object that will draw the triangle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (!undoStack.isEmpty())
		{
			Triangle tri = undoStack.peek();
			
			g2d.setColor(tri.getColor());
			g2d.setStroke(new BasicStroke(tri.getThickness()));
			
			int[] xpoints = new int[] {tri.getStart().getX(), tri.getMid().getX(), tri.getFinish().getX()};
			int[] ypoints = new int[] {tri.getStart().getY(), tri.getMid().getY(), tri.getFinish().getY()};
			Polygon p = new Polygon(xpoints, ypoints, 3);
			
			if (tri.getIsFilled()) {
				g2d.fillPolygon(p);
			} else {
				g2d.drawPolygon(p);
			}
			
		}		
		
	}

	/**
	 * Method will pop from the undo stack a Triangle object and then
	 * push that triangle onto our redo stack.
	 */
	@Override
	public void undo() {
		if (!undoStack.isEmpty())
		{
			redoStack.push(undoStack.pop());
		}
	}

	/**
	 * Method will pop from the redo stack a Triangle object and then push
	 * that triangle onto the undo stack.
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
	 * Shape. Checks if the Point p is selected within the triangle.
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
