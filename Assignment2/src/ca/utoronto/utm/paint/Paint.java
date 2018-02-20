package ca.utoronto.utm.paint;
/**
 * 
 * Paint class that connect the Model and View+Controller,
 * where its main function runs a new Paint Program.
 * @author csc207student
 *
 */
public class Paint {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Paint();
			}
		});
	}

	PaintModel model; // Model
	View view; // View+Controller

	/**
	 * Constructs the Paint object by hooking up the
	 * Model, View+Controller.
	 */
	public Paint() {
		// Create MVC components and hook them together

		// Model
		this.model = new PaintModel();

		// View+Controller
		this.view = new View(model);
		
	}
}
