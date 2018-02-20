package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class OvalManipulatorStrategy implements ShapeManipulatorStrategy{
	private Oval oval;
	private OvalDrawingCommand command;
	private int centreOffsetX, centreOffsetY, finishOffsetX, finishOffsetY;
	
	public OvalManipulatorStrategy()
	{
		this.command = new OvalDrawingCommand();
	}
	
	@Override
	public void setFilled() {
		this.oval.fill();
	}

	@Override
	public void setColor(Color c) {
		this.oval.setColor(c);
	}

	@Override
	public void setThickness(int t) {
		this.oval.setStroke(t);
	}
	
	@Override
	public DrawingCommand getCommand() {
		return this.command;
	}

	@Override
	public void createShape(Point p, int thickness, Color c, boolean filled) {
		this.oval = new Oval(p, thickness, c, filled);
	}

	@Override
	public void pressed(Point p) {
		Point start = new Point(p.getX(), p.getY());
		this.oval.setCentre(start);
		this.oval.setFinish(start);
		this.command = new OvalDrawingCommand();
		this.command.addShape(this.oval);
	}

	@Override
	public void dragged(Point p) {
		if(this.oval !=null){
			Point finish = new Point(p.getX(), p.getY());
			this.oval.setFinish(finish);
		}	
	}

	@Override
	public void released(Point p) {

	}

	@Override
	public void startMove(Point p) {
		Point prevCentre = oval.getCentre();
		Point prevFinish = oval.getFinish();
		this.centreOffsetX = p.getX() - prevCentre.getX();
		this.centreOffsetY = p.getY() - prevCentre.getY();
		this.finishOffsetX = p.getX() - prevFinish.getX();
		this.finishOffsetY = p.getY() - prevFinish.getY();
		this.oval = new Oval(prevCentre, oval.getThickness(), oval.getColor(), oval.getIsFilled());
		this.oval.setFinish(prevFinish);
		this.command.addShape(this.oval);
	}
	
	@Override
	public void move(Point p) {
		Point centre = new Point(p.getX() - centreOffsetX, p.getY() - centreOffsetY);
		Point finish = new Point(p.getX() - finishOffsetX, p.getY() - finishOffsetY);
		this.oval.setCentre(centre);
		this.oval.setFinish(finish);
	}

	@Override
	public void moved(Point p) {}
}
