package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A ShapeManipulatorStrategy for Squiggle. Implements
 * the ShapeManipulatorStrategy interface.
 * Purpose of class is to manipulate the Squiggle of this.
 * 
 * @author csc207student
 *
 */
public class SquiggleManipulatorStrategy implements ShapeManipulatorStrategy{
	
	private Squiggle squiggle;
	private SquiggleDrawingCommand command;
	private int offsetX, offsetY;
	
	/**
	 * Creates a new strategy that initializes a new 
	 * SquiggleDrawingCommand.
	 */

	public SquiggleManipulatorStrategy(){
		this.command = new SquiggleDrawingCommand();
	}
	
	/**
	 * Sets the Squiggle of this to be filled.
	 */
	@Override
	public void setFilled() {
		return;
	}


	/**
	 * Sets the color of the Squiggle of this to the Color c.
	 * @param c
	 * 		The Color to be set onto a Squiggle.
	 */
	@Override
	public void setColor(Color c) {
		this.squiggle.setColor(c);
	}

	/**
	 * Sets the stroke of the Squiggle of this to a specified value.
	 * @param t
	 * 		int representing the stroke of the Squiggle of this.
	 */
	@Override
	public void setThickness(int t) {
		this.squiggle.setStroke(t);
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
	 * Creates a Squiggle object according to the given parameters.
	 * @param p
	 * 		Not used in creation of Squiggle.
	 * @param thickness
	 * 		The stroke value of the Squiggle.
	 * @param c
	 * 		The color of the Squiggle.
	 * @param filled
	 * 		Whether or not the Squiggle is filled in.
	 */

	@Override
	public void createShape(Point p, int thickness, Color c, boolean filled) {
		this.squiggle = new Squiggle(thickness, c, filled);
	}

	/**
	 * Creaes a new SquiggleDrawingCommand and adds the current
	 * squiggle of this to that command.
	 * @param p
	 * 		Not used in creation of squiggle.
	 */
	@Override
	public void pressed(Point p) {
		this.command = new SquiggleDrawingCommand();
		this.command.addShape(this.squiggle);
	}

	/**
	 * Adds a new point to the squiggle of this.
	 * @param p
	 * 		The point to be added.
	 */
	@Override
	public void dragged(Point p) {
		this.squiggle.addPoint(p);
	}

	@Override
	public void released(Point p) {}
	
	@Override
	public void startMove(Point p) {}

	@Override
	public void move(Point p) {}

	@Override
	public void moved(Point p) {}
}
