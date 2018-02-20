package ca.utoronto.utm.paint;

public class ShapeFactory {
	
	public ShapeManipulatorStrategy createShapeManipulatorStrategy(String type)
	{
		if (type.equals("Square"))
			return new SquareManipulatorStrategy();
		else if (type.equals("Rectangle"))
			return new RectangleManipulatorStrategy();
		else if (type.equals("Polyline"))
			return new PolylineManipulatorStrategy();
		else if (type.equals("Circle"))
			return new CircleManipulatorStrategy();
		else if (type.equals("Squiggle"))
			return new SquiggleManipulatorStrategy();
		else if (type.equals("Eraser"))
			return new EraserManipulatorStrategy();
		else if (type.equals("Oval"))
			return new OvalManipulatorStrategy();
		else if (type.equals("Triangle"))
			return new TriangleManipulatorStrategy();
		else
			return null;
	}

}
