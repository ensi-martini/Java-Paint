package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Stack;

public class OvalDrawingCommand implements DrawingCommand{

	private Stack<Oval> undoStack, redoStack;
	
	public OvalDrawingCommand()
	{
		this.undoStack = new Stack<Oval>();
		this.redoStack = new Stack<Oval>();
	}
	
	@Override
	public void addShape(Shape shape) {
		undoStack.add((Oval) shape);
	}

	@Override
	public void execute(Graphics2D g2d) {
		if (!undoStack.isEmpty())
		{
			Oval oval = undoStack.peek();
			g2d.setColor(oval.getColor());
			g2d.setStroke(new BasicStroke(oval.getThickness()));
			
			int x = oval.getCentre().getX() - oval.getWidth();
			int y = oval.getCentre().getY() - oval.getHeight();
			int width = oval.getWidth() * 2;
			int height = oval.getHeight() * 2;
			
			if (oval.getIsFilled()){
				g2d.fillOval(x, y, width, height);
			} else {
				g2d.drawOval(x, y, width, height);
			}
		}
	}

	@Override
	public void undo() {
		if (!undoStack.isEmpty())
		{
			redoStack.push(undoStack.pop());
		}
	}

	@Override
	public void redo() {
		if (!redoStack.isEmpty())
		{
			undoStack.push(redoStack.pop());
		}
	}

	@Override
	public boolean isUndoStackEmpty() {
		return undoStack.isEmpty();
	}

	@Override
	public boolean isRedoStackEmpty() {
		return redoStack.isEmpty();
	}

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
