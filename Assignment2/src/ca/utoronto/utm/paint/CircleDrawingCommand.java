package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

/**
 * A DrawingCommand for a Circle object. Implements
 * the DrawingCommand interface. Contains two stacks,
 * that is a stack of Circle objects. One will be the main
 * stack where if an undo method is invoked, will pop onto
 * the redo stack.
 * @author csc207student
 *
 */
public class CircleDrawingCommand implements DrawingCommand{

	private Stack<Circle> undoStack, redoStack;
	
	/**
	 * Create a CircleDrawingCommand that initializes
	 * two stacks, the undo stack and the redo stack.
	 */
	public CircleDrawingCommand()
	{
		this.undoStack = new Stack<Circle>();
		this.redoStack = new Stack<Circle>();
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
		undoStack.add((Circle) shape);
	}

	/**
	 * Draws a Circle by getting the top circle of the undo stack
	 * and uses the Graphics2D class to create a graphical
	 * representation of the circle.
	 * 
	 * @param g2d
	 * 		The Graphics2D object that will draw the circle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (!undoStack.isEmpty())
		{
			Circle circle = undoStack.peek();
			g2d.setColor(circle.getColor());
			g2d.setStroke(new BasicStroke(circle.getThickness()));
			
			int x = circle.getCentre().getX() - circle.getRadius();
			int y = circle.getCentre().getY() - circle.getRadius();
			int width = 2*circle.getRadius();
			
			if (circle.getIsFilled()){
				g2d.fillOval(x, y, width, width);
			} else {
				g2d.drawOval(x, y, width, width);
			}
		}
	}

	/**
	 * Method will pop from the undo stack a Circle object and then
	 * push that circle onto our redo stack.
	 */
	@Override
	public void undo() {
		if (!undoStack.isEmpty())
		{
			redoStack.push(undoStack.pop());
		}
	}

	/**
	 * Method will pop from the redo stack a Circle object and then push
	 * that circle onto the undo stack.
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
	 * Shape. Checks if the Point p is selected within the circle.
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
