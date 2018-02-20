package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * A JButton that will be used to clear the canvas
 * of the given PaintModel.
 * @author csc207student
 *
 */
public class ClearCanvasButton extends JButton{

	private PaintModel model;
	
	/**
	 * Create a new ClearCanvasButton with the given model.
	 * @param model
	 * 		The PaintModel that ClearCanvas will attach to.
	 */
	public ClearCanvasButton(PaintModel model) {
		this.model = model;
		this.setText("Clear Canvas");
		this.setActionCommand("CLEAR");
	}
	


}
