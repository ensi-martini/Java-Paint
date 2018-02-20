package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements KeyListener,ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private PaintModel model;
	private ArrayList<Integer> pressed = new ArrayList<Integer>();
	
	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	
	
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());		
		
		Container c=this.getContentPane();

		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.shapeChooserPanel,BorderLayout.NORTH);
	
		this.model=model;
		
		this.paintPanel = new PaintPanel(model, this, shapeChooserPanel.getJCC(), shapeChooserPanel.getJS());
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public PaintPanel getPaintPanel(){
		return this.paintPanel;
	}
	
	public PaintModel getPaintModel() {
		return this.model;
	}
	
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo       Ctrl+Z");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo       Ctrl+Y");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	/**
	 * Controller aspect of this that relates to the
	 * undo/redo feature whenever the JMenuItem
	 * is clicked or if a combination of Ctrl+Z
	 * and Ctrl+Y are pressed using the KeyListener
	 * interface.
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Undo"))
		{
			model.undo();
		}
		else if (command.equals("Redo"))
		{
			model.redo();
		}
	}

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
        if (pressed.size() > 1) {
        	if (pressed.contains(new Integer(17)) && pressed.contains(89)) {
        		model.redo();
        	}
        	else if (pressed.contains(new Integer(17)) && pressed.contains(90)) {
        		model.undo();
        	}
        }
        requestFocus();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	while(pressed.remove(new Integer(e.getKeyCode()))) { }
    }

	@Override
	public void keyTyped(KeyEvent e) {}

}
