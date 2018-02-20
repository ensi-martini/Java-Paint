package ca.utoronto.utm.paint;
import java.util.Observable;

/**
 * PaintModel that maintains and manipulates a DrawingCommand
 * stack.
 */
public class PaintModel extends Observable{
	private DrawingCommandStack stack;

	/**
	 * Initializes the DrawingCommand stack of this.
	 */
	public PaintModel() {
		this.stack = new DrawingCommandStack();
	}

	/**
	 * Returns the command stack of this.
	 * @return
	 * 		The DrawingCommand stack.
	 */
	public DrawingCommandStack getStack() {
		return this.stack;
	}

	/**
	 * Undoes the top command of the DrawingCommand stack.
	 */
	public void undo()
	{
		this.stack.undoCommand();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Redos the top command of the DrawingCommand stack.
	 */
	public void redo()
	{
		this.stack.redoCommand();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Empties the DrawingCommand stack of this.
	 */
	public void emptyStack() {
		this.stack.deleteAllCommands();
	}
}
