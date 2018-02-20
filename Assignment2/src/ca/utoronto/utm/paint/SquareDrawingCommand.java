package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

/**
 * A DrawingCommand for a Square object. Implements
 * the DrawingCommand interface. Contains two stacks,
 * that is a stack of Square objects. One will be the main
 * stack where if an undo method is invoked, will pop onto
 * the redo stack.
 * @author csc207student
 *
 */
public class SquareDrawingCommand implements DrawingCommand{

	private Stack<Square> undoStack, redoStack;
	
	/**
	 * Create a SquareDrawingCommand that initializes
	 * two stacks, the undo stack and the redo stack.
	 */
	public SquareDrawingCommand()
	{
		this.undoStack = new Stack<Square>();
		this.redoStack = new Stack<Square>();
	}
	
	/**
	 * Adds a shape to the undo stack of this. Will
	 * first cast the shape into a Square object.
	 * 
	 * @param shape
	 * 		The shape to be added to the undo stack.
	 */
	@Override
	public void addShape(Shape shape) {
		undoStack.add((Square) shape);
	}

	/**
	 * Draws a Square by getting the top Square of the undo stack
	 * and uses the Graphics2D class to create a graphical
	 * representation of the Square.
	 * 
	 * @param g2d
	 * 		The Graphics2D object that will draw the Square.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (!undoStack.isEmpty())
		{
			Square square = undoStack.peek();
			
			g2d.setColor(square.getColor());
			g2d.setStroke(new BasicStroke(square.getThickness()));
			
			int x = square.getTopLeftX();
			int y = square.getTopLeftY();
			int side = square.getSideLength();
			
			if (square.getIsFilled()) {
				g2d.fillRect(x, y, side, side);
			} else {
				g2d.drawRect(x, y, side, side);
			}
			
		}		
	}

	/**
	 * Method will pop from the undo stack a Square object and then
	 * push that Square onto our redo stack.
	 */
	@Override
	public void undo() {
		if (!undoStack.isEmpty())
		{
			redoStack.push(undoStack.pop());
		}
	}

	/**
	 * Method will pop from the redo stack a Square object and then push
	 * that Square onto the undo stack.
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
	 * Shape. Checks if the Point p is selected within the Square.
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
