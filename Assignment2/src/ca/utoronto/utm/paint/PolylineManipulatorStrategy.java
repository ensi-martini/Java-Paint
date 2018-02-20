package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * A ShapeManipulatorStrategy for Polyline. Implements
 * the ShapeManipulatorStrategy interface.
 * Purpose of class is to manipulate the Polyline of this.
 * 
 * @author csc207student
 *
 */
public class PolylineManipulatorStrategy implements ShapeManipulatorStrategy{
	private Polyline polyLine;
	private PolylineDrawingCommand command;
	
	/**
	 * Creates a new strategy that initializes a new 
	 * PolylineDrawingCommand.
	 */
	public PolylineManipulatorStrategy() {
		
	}
	
	/**
	 * Sets the Polyline of this to be filled.
	 */
	@Override
	public void setFilled() {
		return;
	}

	/**
	 * Sets the color of the Polyline of this to the Color c.
	 * @param c
	 * 		The Color to be set onto a Polyline.
	 */
	@Override
	public void setColor(Color c) {
		this.polyLine.setColor(c);
	}

	/**
	 * Sets the stroke of the Polyline of this to a specified value.
	 * @param t
	 * 		int representing the stroke of the Polyline of this.
	 */
	@Override
	public void setThickness(int t) {
		this.polyLine.setStroke(t);
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
	 * Creates a Polyline object according to the given parameters.
	 * @param p
	 * 		A point to be added to this.
	 * @param thickness
	 * 		The stroke value of the Eraser.
	 * @param c
	 * 		The color of the Eraser.
	 * @param filled
	 * 		Whether or not the Eraser is filled in.
	 */
	@Override
	public void createShape(Point p, int thickness, Color c, boolean filled) {
		if (this.polyLine == null) {
			this.polyLine = new Polyline(p, thickness, c, filled);
			this.polyLine.addPoint(p);
		}
		else {
			Polyline newPolyline = new Polyline(p, thickness, c, filled);
			newPolyline.setPoints(this.polyLine.getPoints());
			this.polyLine = newPolyline;
		}
	}

	/**
	 * Creates a new PolylineDrawingCommand and adds the current
	 * polyline of this to that command.
	 * @param p
	 * 		Point to be added to this polyline
	 */
	@Override
	public void pressed(Point p) {
		this.polyLine.addPoint(p);
		this.command = new PolylineDrawingCommand();
		this.command.addShape(this.polyLine);
	}

	@Override
	public void dragged(Point p) {

	}

	@Override
	public void released(Point p) {
		
	}
	
	@Override
	public void moved(Point p) {

	}

	@Override
	public void startMove(Point p) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void move(Point p) {
		// TODO Auto-generated method stub
		
	}

}
