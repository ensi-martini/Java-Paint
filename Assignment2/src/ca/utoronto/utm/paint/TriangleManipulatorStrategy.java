package ca.utoronto.utm.paint;

import java.awt.Color;
/**
 * A ShapeManipulatorStrategy for Triangle. Implements
 * the ShapeManipulatorStrategy interface.
 * Purpose of class is to manipulate the Triangle of this.
 * 
 * @author csc207student
 *
 */
public class TriangleManipulatorStrategy implements ShapeManipulatorStrategy{

	private Triangle tri;
	private TriangleDrawingCommand command;
	private int startOffsetX, startOffsetY, finishOffsetX, finishOffsetY;
	
	/**
	 * Creates a new strategy that initializes a new 
	 * TriangleDrawingCommand.
	 */
	public TriangleManipulatorStrategy() {
		this.command = new TriangleDrawingCommand();
	}
	
	/**
	 * Draws a Triangle by getting the top triangle of the undo stack
	 * and uses the Graphics2D class to create a graphical
	 * representation of the triangle.
	 * 
	 * @param g2d
	 * 		The Graphics2D object that will draw the triangle.
	 */
	@Override
	public void createShape(Point p, int thickness, Color c, boolean filled) {
		this.tri = new Triangle(p, thickness, c, filled);
	}

	/**
	 * Method that will set the centre point of triangle to p,
	 * and creates a new command that takes the triangle of this.
	 * @param p
	 * 		The centre point of the triangle.
	 */
	@Override
	public void pressed(Point p) {
		Point start = new Point(p.getX(), p.getY());
		this.tri.setStart(start);
		this.tri.setFinish(start);
		this.command = new TriangleDrawingCommand();
		this.command.addShape(this.tri);
	}

	/**
	 * Method will set the end of the hypotenuse of the triangle of this given
	 * a Point p.
	 * @param p
	 * 		Point that is used to calculate the hypotenuse of the triangle.
	 */
	@Override
	public void dragged(Point p) {
		if (this.tri != null) {
			Point finish = new Point(p.getX(), p.getY());
			this.tri.setFinish(finish);
		}
	}

	
	@Override
	public void released(Point p) {}

	@Override
	public void moved(Point p) {}

	/**
	 * Creates a new Triangle object from the previous information of the old triangle
	 * then adds this new triangle to the command.
	 * 
	 * @param p
	 * 		The Point p that is used in calculating values for our offset fields.
	 */
	@Override
	public void startMove(Point p) {
		Point prevStart = tri.getStart();
		Point prevFinish = tri.getFinish();
		this.startOffsetX = p.getX() - prevStart.getX();
		this.startOffsetY = p.getY() - prevStart.getY();
		this.finishOffsetX = p.getX() - prevFinish.getX();
		this.finishOffsetY = p.getY() - prevFinish.getY();
		this.tri = new Triangle(prevStart, tri.getThickness(), tri.getColor(), tri.getIsFilled());
		this.tri.setFinish(prevFinish);
		this.command.addShape(this.tri);
		
	}

	/**
	 * Sets the new start and finish of the hypotenuse for the triangle.
	 * 
	 * @param p
	 * 		The new starting point of the triangle.
	 */
	@Override
	public void move(Point p) {
		Point start = new Point(p.getX() - startOffsetX,  p.getY() - startOffsetY);
		Point finish = new Point(p.getX() - finishOffsetX, p.getY() - finishOffsetY);
		this.tri.setStart(start);
		this.tri.setFinish(finish);
	}

	@Override
	public void setFilled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setThickness(int t) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return
	 * 		The command of this.
	 */
	@Override
	public DrawingCommand getCommand() {
		return this.command;
	}

}
