package ca.utoronto.utm.paint;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * A PaintPanel class that is the View+Controller module
 * of the PaintProgram. Where most of the interaction
 * between the ShapeChooser, PaintModel occurs.
 * 
 * Has different shape variables that represent
 * the most current shape that is being built.
 * 
 * Contains variables that contains information
 * to be sent to the model when creating a shape.
 * @author csc207student
 *
 */

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private boolean filled;
	private JColorChooser jcc;
	private JSlider thickness;
	
	private boolean moving;
	private boolean colCopy;

	private ShapeManipulatorStrategy strategy;
	private ShapeManipulatorStrategy prevStrategy;
	private DrawingCommandStack stack;

	private Color backgroundColor;
	
	private Robot someRobot;
	

	/**
	 * A PaintPanel constructor that constructs the basic paint panel 
	 * that the user will be interacting with. Establishes
	 * connection between the model and view. Sets the preferred size,
	 * and initializes its fields to base values.
	 * 
	 * @param model
	 * 			The PaintModel object.
	 * @param view
	 * 			The top-level View object.
	 * @param colorChooser
	 * 			The JColorcChooser object used to choose a color.
	 * @param js
	 * 			The JSlider object used to select a thickness.
	 */
	public PaintPanel(PaintModel model, View view, JColorChooser colorChooser, JSlider js){
		this.setBackground(Color.blue);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.filled = false;
		this.jcc = colorChooser;
		this.thickness = js;

		this.moving = false;
		this.colCopy = false;
		this.someRobot = null;
		
		try{someRobot = new Robot();}
	    catch(AWTException err){}
		
		this.model = model;
		this.model.addObserver(this);
		this.stack = this.model.getStack();
		this.view = view;
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));	
			
		this.backgroundColor = Color.white;
	}
	
	/**
	 * Return the strategy of this PaintPanel
	 * @return 
	 * 		A ShapeManipulatorStrategy currently being used
	 */
	public ShapeManipulatorStrategy getStrategy()
	{
		return this.strategy;
	}

	/**
	 * Set the strategy of this PaintPanel
	 * @param strategy
	 */
	public void setStrategy(ShapeManipulatorStrategy strategy) {
	if (this.prevStrategy == null) {
		this.prevStrategy = strategy;
	    }
	this.strategy = strategy;
	}
	
	/**
	 * Flips the state of whether or not the shape is moving
	 */
	public void toggleMovement() {
		this.moving = !this.moving;
	}
	
	/**
	 * The view aspect of this. Uses the Graphics2D class
	 * and the entire ShapeArray that redraws all its shapes (in-order)
	 * whenever a refresh to the JPanel is called.
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!

		super.paintComponent(g); //paint background
		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		setBackground(this.backgroundColor); 
		this.stack.updateErasers(this.backgroundColor);
		// Origin is at the top left of the window 50 over, 75 down

		stack.executeCommands(g2d); // for undo and redo
		this.view.requestFocus();
		g2d.dispose();
	}

	/**
	 * Show the updated version of all shape objects.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.	
		this.repaint(); // Schedule a call to paintComponent
	}

	/**
	 * Flips the state of whether or not the shape to be drawn is to
	 * be filled in.
	 */
	public void setFilled() {
		this.filled = !this.filled;
	}

	public void setBackgroundColor(Color c) {
		Color co = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255);
		this.backgroundColor = co;
		this.repaint();
	}
	
	public Color getBackgroundColor() {
		return this.backgroundColor;
	}

	/**
	 * Permanently deletes all shapes on this panel, and shows the empty canvas resulting.
	 */
	public void clearScreen() {
		this.model.emptyStack();
		this.repaint();
	}
	
	/**
	 * Flips the state of whether or not to copy the color chosen for the panel
	 */
	public void copyColor() {
		this.colCopy = !this.colCopy;
	}
	@Override
	/**
	 * Whenever the user's mouse is being dragged, will draw
	 * the shape depending on which mode the PaintPanel is set
	 * to and constantly sets the finish point for each shape, 
	 * and adds a new point for squiggle (depending on the mode).
	 * 
	 */
	public void mouseDragged(MouseEvent e) {
		
		Point clicked = new Point(e.getX(), e.getY());

		if (this.strategy != null) {
		if (this.moving) 
		{
			if (stack.isTopSelected(clicked))
			{
				prevStrategy.move(clicked);
			}
		}
		else {
			strategy.dragged(clicked);
		}
		this.repaint();
	}}

	@Override
	/**
	 * Depending on which mode the PaintPanel is on,
	 * whenever the mouse is clicked, will set the
	 * PaintPanel's shape to a new shape object
	 * and set whether or not they will be filled,
	 * and what color they will be.
	 */
	public void mousePressed(MouseEvent e) {
		
		Point clicked = new Point(e.getX(), e.getY());
		
		if (this.colCopy) {
		    java.awt.Point currMouse = MouseInfo.getPointerInfo().getLocation();		    		    
		    this.jcc.setColor(someRobot.getPixelColor(currMouse.x, currMouse.y));
		}
		
		if (strategy != null)
		{
			if (this.moving)
			{
				if (stack.isTopSelected(clicked))
				{
					System.out.println(prevStrategy);
					prevStrategy.startMove(clicked);
				}
			}
			else
			{
				this.prevStrategy = strategy;
				strategy.createShape(clicked, this.thickness.getValue(), jcc.getColor(), this.filled);
				strategy.pressed(clicked);
				this.stack.addCommand(strategy.getCommand());
			}
		}
		this.repaint();
	}

	@Override
	/**
	 * When the mouse is released, sets the finish points
	 * of the shape, depending on the mode of this,
	 * and then sets that shape to a null object, for
	 * a new shape to be created.
	 */
	public void mouseReleased(MouseEvent e) {
		Point clicked = new Point(e.getX(), e.getY());
		if (strategy != null)
		{
			strategy.released(clicked);
		}
		
		this.repaint();		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}