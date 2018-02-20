package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A DrawingCommand stack that consists of
 * two stacks, the real stack, used for storing
 * all drawing commands. The cache stack is used
 * in parallel with undo/redo functions. Also
 * has a Graphics2D object used to execute commands
 * with.
 * @author csc207student
 *
 */
public class DrawingCommandStack {
	
	private Stack<DrawingCommand> stack;
	private Stack<DrawingCommand> cacheStack;
	private Graphics2D g2d;
	
	/**
	 * Creates a new DrawingCommand stack
	 * that intializes both the regular stack
	 * and the cache stack.
	 */
	public DrawingCommandStack() {
		this.stack =  new Stack<DrawingCommand>();
		this.cacheStack = new Stack<DrawingCommand>();
	}
	
	/**
	 * Checks if that with the given Point, check if
	 * this point is in the top of the given command
	 * stack.
	 * @param p
	 * 		The Point to be checked.
	 * @return
	 * 		Return true if Point is in selected top item
	 * of a stack.
	 */
	public boolean isTopSelected(Point p)
	{
		if (!stack.isEmpty())
		{
			return stack.peek().isSelected(p);
		}
		
		return false;
	}
	
	/**
	 * Returns the command that contains the Point p.
	 * @param p
	 * 		Point that will be used to select the top
	 * command.
	 * @return
	 * 		The top command that contains p.
	 */
	public DrawingCommand getSelectedCommand(Point p)
	{
		DrawingCommand topCommand = null;
		for (DrawingCommand command : stack)
		{
			if (command.isSelected(p))
			{
				topCommand = command;
			}
		}
		return topCommand;
	}
	
	/**
	 * 
	 * @return
	 * 		An int representing the size of the stack.
	 */
	public int getLength()
	{
		return stack.size();
	}
	
	/**
	 * Checks within the DrawingCommand stack for all
	 * EraserDrawingCommands then changes the color of
	 * the shape related to the command.
	 * @param c
	 * 		The Color that all Erasers must change to.
	 */
	public void updateErasers(Color c) {
		for (DrawingCommand command: this.stack) {
			command.changeColor(c);
		}
	}

	/**
	 * Iterates through all the commands in this stack
	 * and executes each command with the given
	 * Graphics2D object.
	 * @param g
	 * 		The Graphics2D object that all commands will
	 * execute onto.
	 */
	public void executeCommands(Graphics2D g) {
		this.g2d = g;
		for(DrawingCommand command: this.stack) {
			command.execute(this.g2d);
		}
	}

	/**
	 * Will pop the command ontop of the DrawingCommand stack
	 * and then invokes undo on that command and then pushes
	 * that command onto the cacheStack.
	 */
	public void undoCommand() {
		if (!this.stack.isEmpty())
		{
			DrawingCommand command = this.stack.peek();
			if (command.isRedoStackEmpty())
			{
				this.cacheStack.push(command);
			}
			
			command.undo();
			if (command.isUndoStackEmpty())
			{
				this.stack.pop();
			}
		}
	}
	
	/**
	 * Will pop the command ontop of the DrawingCommand cache stack
	 * and then invokes redo on that command and then pushes
	 * that command onto the stack.
	 */
	public void redoCommand() {
		if (!this.cacheStack.isEmpty())
		{
			DrawingCommand command = this.cacheStack.peek();
			if (command.isUndoStackEmpty())
			{
				this.stack.push(command);
			}
			
			command.redo();
			if (command.isRedoStackEmpty())
			{
				this.cacheStack.pop();
			}
		}
	}
	
	/**
	 * Adds a command to the regular stack, then
	 * clears the cache stack.
	 * @param drawingCommand
	 * 		The DrawingCommand to be added to the stack.
	 */
	public void addCommand(DrawingCommand drawingCommand) {
		this.stack.add(drawingCommand);
		this.cacheStack.clear();
	}
	
	/**
	 * Clears the stack.
	 */
	public void deleteAllCommands() {
		this.stack.clear();
	}
}
