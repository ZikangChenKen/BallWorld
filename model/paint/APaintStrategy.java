package hw06.model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import hw06.model.ball.IBall;

/**
 * An abstract class used to paint using affine transform
 */
public abstract class APaintStrategy implements IPaintStrategy {

	/**
	 * An AffineTransform object used to transform the strategy
	 */
	protected AffineTransform at;

	/**
	 * Constructor 
	 * @param at an affine transform object
	 */
	public APaintStrategy(AffineTransform at) {
		this.at = at;
	}

	/**
	   * By default, do nothing for initialization.
	   */
	@Override
	public void init(IBall context) {
	}

	/**
	   * Paints the graphics object with the affine transform
	   * @param g The Graphics context that will be paint on
	   * @param host The host Ball that the required information will be pulled from.
	   */
	@Override
	public void paint(Graphics g, IBall host) {
		// TODO Auto-generated method stub
		double scale = host.getRadius();
		at.setToTranslation(host.getLocation().x, host.getLocation().y);
		at.scale(scale, scale);
		at.rotate(host.getVelocity().x, host.getVelocity().y);
		g.setColor(host.getColor());
		paintCfg(g, host);
		paintXfrm(g, host, at);
	}

	/**
	 * A method used to configure the paint object
	 * @param g The Graphics context that will be paint on
	 * @param host The host Ball that the required information will be pulled from
	 */
	protected void paintCfg(Graphics g, IBall host) {
	}

	/**
	 * A method that can be overrided to apply affinetransforms
	 * @param g The Graphics context that will be paint on
	 * @param host The host Ball that the required information will be pulled from
	 * @param at an affine transform object
	 */
	public abstract void paintXfrm(Graphics g, IBall host, AffineTransform at);
}
