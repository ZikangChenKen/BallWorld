package hw06.model.paint.strategy;

import java.awt.Graphics;

import hw06.model.ball.IBall;
import hw06.model.paint.IPaintStrategy;

/**
 * The paint strategy that paints a ball on the canvas
 *
 */
public class BallPaintStrategy implements IPaintStrategy {

	/**
	 * The default constructor for the strategy, used to instantiate balls
	 */
	public BallPaintStrategy() {
	}

	@Override
	public void init(IBall context) {
	}

	/**
	 * Paints a ball 
	 * @param g The Graphics context that will be paint on
	 * @param host The host Ball that the required information will be pulled from.
	 */
	@Override
	public void paint(Graphics g, IBall host) {
		int radius = host.getRadius();
		g.setColor(host.getColor());
		g.fillOval(host.getLocation().x - radius, host.getLocation().y - radius, radius * 2, radius * 2);
	}

}
