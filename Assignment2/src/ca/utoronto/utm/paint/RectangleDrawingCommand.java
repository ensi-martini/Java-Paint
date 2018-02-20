package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

/**
 * A DrawingCommand for a Rectangle object. Implements
 * the DrawingCommand interface. Contains two stacks,
 * that is a stack of Rectangle objects. One will be the main
 * stack where if an undo method is invoked, will pop onto
 * the redo stack.
 * @author csc207student
 *
 */
public class RectangleDrawingCommand implements DrawingCommand{

	private Stack<Rectangle> undoStack, redoStack;
	
	/**
	 * Create a RectangleDrawingCommand that initializes
	 * two stacks, the undo stack and the redo stack.
	 */
	public RectangleDrawingCommand()
	{
		this.undoStack = new Stack<Rectangle>();
		this.redoStack = new Stack<Rectangle>();
	}
	
	/**
	 * Adds a shape to the undo stack of this. Will
	 * first cast the shape into a Rectangle object.
	 * 
	 * @param shape
	 * 		The shape to be added to the undo stack.
	 */
	@Override
	public void addShape(Shape shape) {
		undoStack.add((Rectangle) shape);
	}


	/**
	 * Draws a Rectangle by getting the top Rectangle of the undo stack
	 * and uses the Graphics2D class to create a graphical
	 * representation of the Rectangle.
	 * 
	 * @param g2d
	 * 		The Graphics2D object that will draw the Rectangle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (!undoStack.isEmpty())
		{
			Rectangle rectangle = undoStack.peek();
			
			g2d.setColor(rectangle.getColor());
			g2d.setStroke(new BasicStroke(rectangle.getThickness()));
			
			Point topLeft = rectangle.getTopLeft();
			int width = rectangle.getWidth();
			int height = rectangle.getHeight();
			
			if (rectangle.getIsFilled()){
				g2d.fillRect(topLeft.getX(), topLeft.getY(), width, height);
			} else {
				g2d.drawRect(topLeft.getX(), topLeft.getY(), width, height);
			}
			
		}		
	}

	/**
	 * Method will pop from the undo stack a Rectangle object and then
	 * push that Rectangle onto our redo stack.
	 */
	@Override
	public void undo() {
		if (!undoStack.isEmpty())
		{
			redoStack.push(undoStack.pop());
		}
	}

	/**
	 * Method will pop from the redo stack a Rectangle object and then push
	 * that Rectangle onto the undo stack.
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
	 * Shape. Checks if the Point p is selected within the Rectangle.
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
