package hw06.model.paint.factory;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Factory class for Rectangles
 *
 */
public class RectangleShapeFactory implements IShapeFactory {

	/**
	 * Default constructor for the factory
	 */
	public RectangleShapeFactory() {

	}

	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		// TODO Auto-generated method stub
		return new Rectangle2D.Double(x - xScale / 2, y - yScale / 2, xScale, yScale);
	}

	/**
	 * A singleton field used to instantiate new rectangles
	 */
	public static RectangleShapeFactory Singleton = new RectangleShapeFactory();

}
