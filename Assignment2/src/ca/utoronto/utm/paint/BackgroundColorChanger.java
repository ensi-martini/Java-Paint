package ca.utoronto.utm.paint;
import java.awt.*;
import javax.swing.*;
/**
 * BackgroundColorChanger is a JButton that when clicked,
 * gets the color of the attached JColorChooserObject.
 * @author csc207student
 *
 */
public class BackgroundColorChanger extends JButton{
	
	private JColorChooser jcc;
	
	/**
	 * Creates a BackgroundColorChanger object with
	 * the JColorChooser jcc.
	 * @param jcc
	 * 		A JColorChoose object.
	 */
	public BackgroundColorChanger(JColorChooser jcc) {
		this.setPreferredSize(new Dimension(20, 30));
		this.setText("Set Background");
		this.jcc = jcc;
		this.setFocusable(false);
	}
	
	/**
	 * Gets the color of the attached JColorChooser object.
	 * @return
	 * 		The current color of the JColorChooser object.
	 */
	public Color getColor() {
		return this.jcc.getColor();
	}


}
