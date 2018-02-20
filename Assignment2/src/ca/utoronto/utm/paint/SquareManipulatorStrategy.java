package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A ShapeManipulatorStrategy for Square. Implements
 * the ShapeManipulatorStrategy interface.
 * Purpose of class is to manipulate the Square of this.
 * 
 * @author csc207student
 *
 */
public class SquareManipulatorStrategy implements ShapeManipulatorStrategy{
	
	private Square square;
	private SquareDrawingCommand command;
	private int startOffsetX, startOffsetY, finishOffsetX, finishOffsetY;
	
	/**
	 * Creates a new strategy that initializes a new 
	 * SquareDrawingCommand.
	 */
	public SquareManipulatorStrategy()
	{
		this.command = new SquareDrawingCommand();
	}
	
	/**
	 * Sets the Square of this to be filled.
	 */
	@Override
	public void setFilled() {
		this.square.fill();
	}

	/**
	 * Sets the color of the Square of this to the Color c.
	 * @param c
	 * 		The Color to be set onto a Square.
	 */
	@Override
	public void setColor(Color c) {
		this.square.setColor(c);
	}

	/**
	 * Sets the stroke of the Square of this to a specified value.
	 * @param t
	 * 		int representing the stroke of the Square of this.
	 */

	@Override
	public void setThickness(int t) {
		this.square.setStroke(t);
	}

	/**
	 * @return
	 * 		The command of this.
	 */
	@Override
	public DrawingCommand getCommand() {
		return this.command;
	}

	/**
	 * Creates a Square object according to the given parameters.
	 * @param p
	 * 		The point at which this Square will start at.
	 * @param thickness
	 * 		The stroke value of the Square.
	 * @param c
	 * 		The color of the Square.
	 * @param filled
	 * 		Whether or not the Square is filled in.
	 */
	@Override
	public void createShape(Point p, int thickness, Color c, boolean filled) {
		this.square = new Square(p, thickness, c, filled);
	}

	/**
	 * Sets the start and finish of the Square to the given Point
	 * p. Creates a new SquareDrawingCommand and adds the Square
	 * of this to that command.
	 * 
	 * @param p
	 * 		The Point used to set the start and finish.
	 */
	@Override
	public void pressed(Point p) {
		Point start = new Point(p.getX(), p.getY());
		this.square.setStart(start);
		this.square.setFinish(start);
		this.command = new SquareDrawingCommand();
		this.command.addShape(this.square);
	}

	/**
	 * Sets the finish of the Square of this to the Point
	 * p.
	 * 
	 * @param p
	 * 		The Point used to set the finish.
	 */

	@Override
	public void dragged(Point p) {
		if(this.square!=null) {
			Point finish = new Point(p.getX(), p.getY());
			this.square.setFinish(finish);
		}
	}

	@Override
	public void released(Point p) {

	}

	/**
	 * Creates a new Square object from the previous information of the old Square
	 * then adds this new Square to the command.
	 * 
	 * @param p
	 * 		The Point p that is used in calculating values for our offset fields.
	 */
	@Override
	public void startMove(Point p) {
		Point prevStart = square.getStart();
		Point prevFinish = square.getFinish();
		this.startOffsetX = p.getX() - prevStart.getX();
		this.startOffsetY = p.getY() - prevStart.getY();
		this.finishOffsetX = p.getX() - prevFinish.getX();
		this.finishOffsetY = p.getY() - prevFinish.getY();
		this.square = new Square(prevStart, square.getThickness(), square.getColor(), square.getIsFilled());
		this.square.setFinish(prevFinish);
		this.command.addShape(this.square);
		
	}
	
	/**
	 * Set the start and finish point to calculated values from the Point p
	 * to new values.
	 * 
	 * @param p
	 * 		The Point used in setting the start and finish of the Square
	 * of this.
	 */
	@Override
	public void move(Point p) {
		Point start = new Point(p.getX() - startOffsetX,  p.getY() - startOffsetY);
		Point finish = new Point(p.getX() - finishOffsetX, p.getY() - finishOffsetY);
		this.square.setStart(start);
		this.square.setFinish(finish);
		
	}
	
	@Override
	public void moved(Point p) {}
}
