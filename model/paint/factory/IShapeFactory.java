package hw06.model.paint.factory;

import java.awt.*;

/**
 * Abstract factory that creates a Shape for use as prototype shapes in
 * IPaintStrategies.  The location of the center of the shape and the
 * x and y scales can be specified.
 */
public interface IShapeFactory {
	/**
	 * Returns a Shape object centered at (x, y) and with the specified
	 * x and y dimensions.
	 * @param x x-coordinate of the center of the shape
	 * @param y y-coordinate of the center of the shape
	 * @param xScale The x-dimension of the shape, usually the x-radius.
	 * @param yScale The y-dimension of the shape, usually the y-radius.
	 * @return A Shape instance.
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale);
}
