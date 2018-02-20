package ca.utoronto.utm.paint;

import java.awt.Color;

interface ShapeManipulatorStrategy {
	public void createShape(Point p, int thickness, Color c, boolean filled);
	public void pressed(Point p);
	public void dragged(Point p);
	public void released(Point p);
	public void moved(Point p);
	public void startMove(Point p);
	public void move(Point p);
	public void setFilled();
	public void setColor(Color c);
	public void setThickness(int t);
	public DrawingCommand getCommand();
}
