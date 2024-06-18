package hw06.model.paint;

import java.awt.Graphics;

import hw06.model.ball.IBall;

/**
 * The interface used for paint strategy
 *
 */
public interface IPaintStrategy {

	/**
	 * Method for running steps to initialize aspescts of the ball
	 * @param context the ball being modified
	 */
	public void init(IBall context);

	/**
	 * A method for painting the ball
	 * @param g the graphics object used to paint the ball
	 * @param host the ball object being painted
	 */
	public void paint(Graphics g, IBall host);

	/**
	 * A NULL IPaintStrategy, the default strategy for the objects
	 */
	public static final IPaintStrategy NULL = new IPaintStrategy() {
		public void init(IBall context) {
		}

		public void paint(Graphics g, IBall host) {
		}
	};
}
