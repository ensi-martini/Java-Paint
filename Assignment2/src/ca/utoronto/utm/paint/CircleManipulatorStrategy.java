package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A ShapeManipulatorStrategy for Circle. Implements
 * the ShapeManipulatorStrategy interface.
 * Purpose of class is to manipulate the Circle of this.
 * 
 * @author csc207student
 *
 */
public class CircleManipulatorStrategy implements ShapeManipulatorStrategy{
	
	private Circle circle;
	private CircleDrawingCommand command;
	private int offsetX, offsetY;
	
	/**
	 * Creates a new strategy that initializes a new 
	 * CircleDrawingCommand.
	 */
	public CircleManipulatorStrategy()
	{
		this.command = new CircleDrawingCommand();
	}
	
	/**
	 * Sets the circle of this to be filled.
	 */
	@Override
	public void setFilled() {
		this.circle.fill();
	}

	/**
	 * Sets the color of the circle of this to the Color c.
	 * @param c
	 * 		The Color to be set onto a circle.
	 */
	@Override
	public void setColor(Color c) {
		this.circle.setColor(c);		
	}
	
	/**
	 * Sets the stroke of the circle of this to a specified value.
	 * @param t
	 * 		int representing the stroke of the circle of this.
	 */
	@Override
	public void setThickness(int t) {
		this.circle.setStroke(t);	
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
	 * Creates a circle object according to the given parameters.
	 * @param p
	 * 		The point at which this circle will start at.
	 * @param thickness
	 * 		The stroke value of the circle.
	 * @param c
	 * 		The color of the circle.
	 * @param filled
	 * 		Whether or not the circle is filled in.
	 */
	@Override
	public void createShape(Point p, int thickness, Color c, boolean filled) {
		this.circle = new Circle(p, 0, thickness, c, filled);
	}

	/**
	 * Method that will set the centre point of circle to p,
	 * and creates a new command that takes the circle of this.
	 * @param p
	 * 		The centre point of the circle.
	 */
	@Override
	public void pressed(Point p) {
		Point centre = new Point(p.getX(), p.getY());
		this.circle.setCentre(centre);
		this.command = new CircleDrawingCommand();
		this.command.addShape(this.circle);
	}

	/**
	 * Method will set the radius of the circle of this given
	 * a Point p.
	 * @param p
	 * 		Point that is used to calculate the radius of the circle.
	 */
	@Override
	public void dragged(Point p) {
		if(this.circle!=null)
		{
			int x = this.circle.getCentre().getX();
			int y = this.circle.getCentre().getY();
			int deltaX = Math.abs(x-p.getX());
			int deltaY = Math.abs(y-p.getY());
			double radius = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		
			this.circle.setRadius((int)radius);
		}
	}

	@Override
	public void released(Point p) {}
	
	/**
	 * Creates a new Circle object from the previous information of the old circle
	 * then adds this new circle to the command.
	 * 
	 * @param p
	 * 		The Point p that is used in calculating values for our offset fields.
	 */
	@Override
	public void startMove(Point p) {
		this.offsetX = p.getX() - circle.getCentre().getX();
		this.offsetY = p.getY() - circle.getCentre().getY();
		this.circle = new Circle(circle.getCentre(), circle.getRadius(), circle.getThickness(), circle.getColor(), circle.getIsFilled());
		this.command.addShape(this.circle);
	}

	/**
	 * Sets the centre point of the circle to the Point p.
	 * 
	 * @param p
	 * 		The centre point of this.circle.
	 */
	@Override
	public void move(Point p) {
		Point centre = new Point(p.getX() - offsetX, p.getY() - offsetY);
		this.circle.setCentre(centre);
		
	}
	
	@Override
	public void moved(Point p) {}
}
