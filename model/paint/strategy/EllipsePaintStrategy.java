package hw06.model.paint.strategy;

import java.awt.geom.AffineTransform;

import hw06.model.paint.ShapePaintStrategy;
import hw06.model.paint.factory.*;

/**
 * Paint strategy to paint an ellipse shape
 */
public class EllipsePaintStrategy extends ShapePaintStrategy {

	/**
	 * No parameter constructor that creates a prototype ellipse.
	 * An AffineTranform for internal use is instantiated.
	 */
	public EllipsePaintStrategy() {
		this(new AffineTransform(), 0, 0, 8.0 / 3.0, 4.0 / 3.0);
	}

	/**
	 * Constructor that allows the specification of the location, x-radius and y-radius
	 * of the prototype ellipse.   The AffineTransform to use is given.
	 * @param at The AffineTransform to use for internal calculations
	 * @param x floating point x-coordinate of center of circle
	 * @param y floating point y-coordinate of center of circle
	 * @param width floating point x-radius of the circle (ellipse)
	 * @param height floating point y-radius of the circle (ellipse)
	 */
	public EllipsePaintStrategy(AffineTransform at, double x, double y, double width, double height) {
		super(at, EllipseShapeFactory.Singleton.makeShape(x, y, width, height));
	}
}
