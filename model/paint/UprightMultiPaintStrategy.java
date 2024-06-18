package hw06.model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import hw06.model.ball.IBall;

/**
 * An extension of the multi paint strategy that always paints the objects upright
 *
 */
public class UprightMultiPaintStrategy extends MultiPaintStrategy {

	/**
	 * Default constructor, instantiates a new AffineTransform function
	 * @param strat1 first paint strategy
	 * @param strat2 second paint strategy
	 */
	public UprightMultiPaintStrategy(APaintStrategy strat1, APaintStrategy strat2) {
		this(new AffineTransform(), strat1, strat2);
	}

	/**
	 * Constructor used to adjust affine transform to paint upright
	 * @param at the affine transform
	 * @param strat1 first paint strategy
	 * @param strat2 second paint strategy
	 */
	public UprightMultiPaintStrategy(AffineTransform at, APaintStrategy strat1, APaintStrategy strat2) {
		super(at, strat1, strat2);
	}

	@Override
	protected void paintCfg(Graphics g, IBall host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x)) > Math.PI / 2.0) {
			at.scale(1.0, -1.0);
		}
	}

}
