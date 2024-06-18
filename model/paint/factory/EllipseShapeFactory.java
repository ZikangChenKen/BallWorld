package hw06.model.paint.factory;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Factory class for Ellipses
 *
 */
public class EllipseShapeFactory implements IShapeFactory {

	/**
	 * Default constructor for the factory
	 */
	public EllipseShapeFactory() {
	}

	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		// TODO Auto-generated method stub
		return new Ellipse2D.Double(x - xScale / 2, y - yScale / 2, xScale, yScale);
	}

	/**
	 * A singleton field used to instantiate new ellipses
	 */
	public static EllipseShapeFactory Singleton = new EllipseShapeFactory();
}
