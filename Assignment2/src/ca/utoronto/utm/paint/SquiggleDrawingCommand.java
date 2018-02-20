package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

/**
 * A DrawingCommand for a Squiggle object. Implements
 * the DrawingCommand interface. Contains two stacks,
 * that is a stack of Squiggle objects. One will be the main
 * stack where if an undo method is invoked, will pop onto
 * the redo stack.
 * 
 * @author csc207student
 *
 */
public class SquiggleDrawingCommand implements DrawingCommand{

	private Stack<Squiggle> undoStack, redoStack;
	
	/**
	 * Create a SquiggleDrawingCommand that initializes
	 * two stacks, the undo stack and the redo stack.
	 */
	public SquiggleDrawingCommand()
	{
		this.undoStack = new Stack<Squiggle>();
		this.redoStack = new Stack<Squiggle>();
	}
	
	/**
	 * Adds a shape to the undo stack of this. Will
	 * first cast the shape into a Squiggle object.
	 * 
	 * @param shape
	 * 		The shape to be added to the undo stack.
	 */
	@Override
	public void addShape(Shape shape) {
		undoStack.add((Squiggle) shape);
	}

	/**
	 * Draws an Squiggle by iterating through the array of points
	 * that the Squiggle has, then connecting pairs of lines together
	 * to create one squiggle.
	 * 
	 * @param g2d
	 * 		The Graphics2D object that will draw the Squiggle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		if (!undoStack.isEmpty())
		{
			Squiggle squiggle = undoStack.peek();
			g2d.setColor(squiggle.getColor());
			g2d.setStroke(new BasicStroke(squiggle.getThickness()));
			for (int i = 0; i < squiggle.getPoints().size() - 1; i++) {
				Point point1 = squiggle.getPoints().get(i);
				Point point2 = squiggle.getPoints().get(i+1);

				g2d.drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
			}
		}
	}

	/**
	 * Method will pop from the undo stack a Squiggle object and then
	 * push that Squiggle onto our redo stack.
	 */
	@Override
	public void undo() {
		if (!undoStack.isEmpty())
		{
			redoStack.push(undoStack.pop());
		}
	}

	/**
	 * Method will pop from the redo stack a Squiggle object and then push
	 * that Squiggle onto the undo stack.
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
	 * Shape. Checks if the Point p is selected within the Squiggle.
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
