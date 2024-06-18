package hw06.model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import hw06.model.ball.IBall;

/**
 * Paint strategy for painting 2D Shape
 */
public class ShapePaintStrategy extends APaintStrategy {

	/**
	 * The Shape object to be painted
	 */
	private Shape shape;

	/**
	 * A constructor used by the subclasses
	 * @param at Affine transform object to transform the position
	 * @param shape the shape to be painted
	 */
	public ShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at);
		this.shape = shape;
	}

	@Override
	public void paintXfrm(Graphics g, IBall host, AffineTransform at) {
		Shape displayedShape = at.createTransformedShape(shape);
		((Graphics2D) g).fill(displayedShape);
	}

}
