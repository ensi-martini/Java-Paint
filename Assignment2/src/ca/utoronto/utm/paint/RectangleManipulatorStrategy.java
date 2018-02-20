package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A ShapeManipulatorStrategy for Rectangle. Implements
 * the ShapeManipulatorStrategy interface.
 * Purpose of class is to manipulate the Rectangle of this.
 * 
 * @author csc207student
 *
 */
public class RectangleManipulatorStrategy implements ShapeManipulatorStrategy{
	private Rectangle rectangle;
	private RectangleDrawingCommand command;
	private int startOffsetX, startOffsetY, finishOffsetX, finishOffsetY;
	
	/**
	 * Creates a new strategy that initializes a new 
	 * RectangleDrawingCommand.
	 */
	public RectangleManipulatorStrategy()
	{
		this.command = new RectangleDrawingCommand();
	}
	
	/**
	 * Sets the Rectangle of this to be filled.
	 */
	@Override
	public void setFilled() {
		this.rectangle.fill();
	}

	/**
	 * Sets the color of the Rectangle of this to the Color c.
	 * @param c
	 * 		The Color to be set onto a Rectangle.
	 */
	@Override
	public void setColor(Color c) {
		this.rectangle.setColor(c);
	}

	/**
	 * Sets the stroke of the Rectangle of this to a specified value.
	 * @param t
	 * 		int representing the stroke of the Rectangle of this.
	 */
	@Override
	public void setThickness(int t) {
		this.rectangle.setStroke(t);
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
	 * Creates a Rectangle object according to the given parameters.
	 * @param p
	 * 		The point at which this Rectangle will start at.
	 * @param thickness
	 * 		The stroke value of the Rectangle.
	 * @param c
	 * 		The color of the Rectangle.
	 * @param filled
	 * 		Whether or not the Rectangle is filled in.
	 */
	@Override
	public void createShape(Point p, int thickness, Color c, boolean filled) {
		this.rectangle = new Rectangle(p, thickness, c, filled);
	}

	/**
	 * Sets the start and finish of the rectangle to the given Point
	 * p. Creates a new RectangleDrawingCommand and adds the rectangle
	 * of this to that command.
	 * 
	 * @param p
	 * 		The Point used to set the start and finish.
	 */
	@Override
	public void pressed(Point p) {
		Point start = new Point(p.getX(), p.getY());
		this.rectangle.setStart(start);
		this.rectangle.setFinish(start);
		this.command = new RectangleDrawingCommand();
		this.command.addShape(this.rectangle);
	}

	/**
	 * Sets the finish of the rectangle of this to the Point
	 * p.
	 * 
	 * @param p
	 * 		The Point used to set the finish.
	 */
	@Override
	public void dragged(Point p) {
		if(this.rectangle!=null){
			Point finish = new Point(p.getX(), p.getY());
			this.rectangle.setFinish(finish);
		}	
	}

	@Override
	public void released(Point p) {

	}

	/**
	 * Creates a new Rectangle object from the previous information of the old Rectangle
	 * then adds this new Rectangle to the command.
	 * 
	 * @param p
	 * 		The Point p that is used in calculating values for our offset fields.
	 */
	@Override
	public void startMove(Point p) {
		Point prevStart = rectangle.getStart();
		Point prevFinish = rectangle.getFinish();
		this.startOffsetX = p.getX() - prevStart.getX();
		this.startOffsetY = p.getY() - prevStart.getY();
		this.finishOffsetX = p.getX() - prevFinish.getX();
		this.finishOffsetY = p.getY() - prevFinish.getY();
		this.rectangle = new Rectangle(prevStart, rectangle.getThickness(), rectangle.getColor(), rectangle.getIsFilled());
		this.rectangle.setFinish(prevFinish);
		this.command.addShape(this.rectangle);
	}
	
	/**
	 * Set the start and finish point to calculated values from the Point p
	 * to new values.
	 * 
	 * @param p
	 * 		The Point used in setting the start and finish of the rectangle
	 * of this.
	 */
	@Override
	public void move(Point p) {
		Point start = new Point(p.getX() - startOffsetX,  p.getY() - startOffsetY);
		Point finish = new Point(p.getX() - finishOffsetX, p.getY() - finishOffsetY);
		this.rectangle.setStart(start);
		this.rectangle.setFinish(finish);
		
	}

	@Override
	public void moved(Point p) {}
}
