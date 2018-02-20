package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * ShapeChooser class that will handle user interaction
 * of which shape the user will draw. Handles view interaction
 * with the user choosing a color for the shape. Takes a View
 * parameter so that we can talk to our parent of view or
 * the other components of view.
 * @author csc207student
 *
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private JColorChooser jcc;
	private JButton cache;
	private JSlider tSlider;
	private BackgroundColorChanger backgroundChanger;
	/**
	 * Constructs a ShapeChooserPanel that consists of 6 JButtons.
	 * These JButtons represent each of the shape we will allow the
	 * user to choose from. Creates a JColorChooser object that will
	 * allow the user to pick the color they wish to use when creating
	 * shapes. Has a fill JButton that allows the user whether or not
	 * they want their shape to be filled or not. Uses the GridBag layout
	 * to show all these components together.
	 * 
	 * @param view
	 * 		Our parent View component that allows
	 * 		us to interact with the other components
	 * 		or the parent view itself.
	 */
	public ShapeChooserPanel(View view) {	
		this.view = view;
		String[] buttonLabels = {"Square.png", "Rectangle.png", "Polyline.png", "Squiggle.png",  "Circle.png", "Oval.png"
		, "Triangle.png", "Eraser.png" };

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		this.jcc = new JColorChooser();
		jcc.setColor(0, 0, 0);
		AbstractColorChooserPanel panels[] = jcc.getChooserPanels();

		//Removes all the unnecessary panels in jcc
		jcc.removeChooserPanel(panels[0]);
		jcc.removeChooserPanel(panels[1]);
		//jcc.removeChooserPanel(panels[2]);
		jcc.removeChooserPanel(panels[3]);
		jcc.removeChooserPanel(panels[4]);
		jcc.setPreviewPanel(new JPanel());
		
		jcc.setFocusable(false);

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 5;
		this.add(jcc, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;

		for (String label : buttonLabels) {

			BufferedImage buttonIcon = null;

			try {
				buttonIcon = ImageIO.read(new File("resources/" + label));
			}

			catch (IOException e) {
				System.out.println("Couldn't find image");
			}

			ImageIcon ii = new ImageIcon(buttonIcon);

			JButton button = new JButton(ii);
			button.setFocusable(false);
			button.setActionCommand(label.substring(0, label.length() - 4));

			if (c.gridx == 3) {
				c.gridy ++;
				c.gridx = 1;
			}

			this.add(button, c);
			button.addActionListener(this);
			c.gridx++;
		}
		
		c.fill = GridBagConstraints.HORIZONTAL;
		


		JButton fill = new JButton("Fill");
		fill.setFocusable(false);
		fill.addActionListener(this);

		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		//this.add(fill, c);

		c.gridy = 4;
		c.gridwidth = 1;
		JLabel stroke = new JLabel("Thickness");
		this.add(stroke, c);


		c.gridx++;
		tSlider = new JSlider();
		tSlider.setPreferredSize(new Dimension(0, 30));
		this.add(tSlider, c);
		
		tSlider.setValue(0);
		tSlider.setFocusable(false);
		
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 3;
		
		JPanel options = new JPanel(new GridLayout(1, 5));
		
		this.backgroundChanger = new BackgroundColorChanger(this.jcc);
		this.backgroundChanger.setActionCommand("Background");
		this.backgroundChanger.addActionListener(this);		
		options.add(backgroundChanger);
		
		ClearCanvasButton ccb = new ClearCanvasButton(this.view.getPaintModel());
		options.add(ccb);
		ccb.addActionListener(this);
		ccb.setFocusable(false);
		
		CopyColorButton cc = new CopyColorButton();
		options.add(cc);
		cc.addActionListener(this);
		cc.setFocusable(false);
		
		JButton ms = new JButton("Move Last Shape");
		ms.addActionListener(this);
		ms.setFocusable(false);
		options.add(ms);
		options.add(fill);
		
		this.add(options, c);
		
		
	}

	/**
	 * Returns the JColorChooser of this.
	 * @return
	 * 		The JColorChooser object of this.
	 */
	public JColorChooser getJCC() {
		return this.jcc;
	}

	/**
	 * Returns the JSlider object that represents
	 * the thickness.
	 * @return
	 * 		The JSlider object of this.
	 */
	public JSlider getJS() {
		return this.tSlider;
	}

	public BackgroundColorChanger returnBGC() {
		return this.backgroundChanger;
	}
	
	/** Flips the color of JButton b between it's original color and gray
	 * Used to indicate being held down.
	 * 
	 * @param b 
	 *      the button to flip
	 */
	public void toggleGray(JButton b) {
		if (b.getBackground() == Color.gray) {
			b.setBackground(null);
		} else {
			b.setBackground(Color.gray);
		}
	}

	/**
	 * The controller aspect of this.
	 * Whenever a button is clicked, it will
	 * gray out the JButton, and disable the button.
	 * If the fill JButton was clicked, will communicate
	 * with the PaintPanel to set the shape to be drawn to
	 * be filled. Will also set the mode of the PaintPanel
	 * if that specific shape button was clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton)e.getSource();
		if (e.getActionCommand() == "CLEAR") {
			this.view.getPaintPanel().clearScreen();
		}
		else if (e.getActionCommand() == "CopyCol") {			
			this.view.getPaintPanel().copyColor();
			toggleGray(pressed);
		}
		else if (e.getActionCommand() == "Fill") {
			this.view.getPaintPanel().setFilled();
			toggleGray(pressed);

		} else if (e.getActionCommand() == "Background") {
				
			this.view.getPaintPanel().setBackgroundColor(this.jcc.getColor());
					
		} else if (e.getActionCommand().equals("Move Last Shape")) {
			this.view.getPaintPanel().toggleMovement();
			toggleGray(pressed);			
		}
		else {

			if (cache == null) {
				pressed.setEnabled(false);
			}

			else {
				cache.setEnabled(true);
				pressed.setEnabled(false);
			}
			cache = pressed;

			ShapeFactory factory = new ShapeFactory();
			ShapeManipulatorStrategy strategy = factory.createShapeManipulatorStrategy(e.getActionCommand());
			this.view.getPaintPanel().setStrategy(strategy);		
		}
		
		this.view.setFocusable(true);
		this.view.getPaintPanel().setFocusable(true);
	}	
}
