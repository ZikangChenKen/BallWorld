package hw06.model.paint.strategy;

import java.awt.Color;
import java.awt.Graphics;

import hw06.model.ball.IBall;
import hw06.model.paint.IPaintStrategy;

/**
 * A Paint Strategy for painting error balls
 *
 */
public class ErrorPaintStrategy implements IPaintStrategy {

	/**
	 * Default constructor for the 
	 */
	public ErrorPaintStrategy() {
	}

	/**
	 * By default, do nothing for initialization.
	 */
	@Override
	public void init(IBall context) {
	}

	/**
	 * Paints an error ball 
	 * param g The Graphics context that will be paint on
	 * param host The host Ball that the required information will be pulled from.
	 */
	@Override
	public void paint(Graphics g, IBall host) {
		int radius = host.getRadius();
		g.setColor(Color.RED);
		g.fillOval(host.getLocation().x - radius, host.getLocation().y - radius, radius * 2, radius * 2);
		g.setColor(Color.BLACK);
		radius = radius / 2;
		g.fillOval(host.getLocation().x - radius, host.getLocation().y - radius, radius * 2, radius * 2);
	}

}
