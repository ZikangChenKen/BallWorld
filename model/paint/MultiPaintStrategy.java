package hw06.model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import hw06.model.ball.IBall;

/**
 * A strategy for painting multiple paint strategies at once
 *
 */
public class MultiPaintStrategy extends APaintStrategy {

	/**
	 * The first paint strategy
	 */
	private APaintStrategy paintStrategy1;

	/**
	 * The second paint strategy
	 */
	private APaintStrategy paintStrategy2;

	/**
	 * The constructor which creates a new affine transform object
	 * @param strat1 the first paint strategy
	 * @param strat2 the second paint strategy
	 */
	public MultiPaintStrategy(APaintStrategy strat1, APaintStrategy strat2) {
		this(new AffineTransform(), strat1, strat2);
	}

	/**
	 * The constructor which passes the affine transform object to the super class
	 * @param at the affine transform object used for drawing
	 * @param strat1 the first paint strategy
	 * @param strat2 the second paint strategy
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy strat1, APaintStrategy strat2) {
		super(at);
		this.paintStrategy1 = strat1;
		this.paintStrategy2 = strat2;
	}

	@Override
	public void init(IBall host) {
		paintStrategy1.init(host);
		paintStrategy2.init(host);
	}

	@Override
	public void paintXfrm(Graphics g, IBall host, AffineTransform at) {
		paintStrategy1.paintXfrm(g, host, at);
		paintStrategy2.paintXfrm(g, host, at);
	}

}
