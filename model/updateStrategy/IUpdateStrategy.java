package hw06.model.updateStrategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * Interface used to model the balls update behavior as a strategy
 * 
 */
public interface IUpdateStrategy {

	/**
	 * A method for updating the state of the ball according to the strategy
	 * @param context the ball that is being updated
	 * @param disp a dispatcher object corresponding to the ball
	 */
	public void updateState(IBall context, IDispatcher<IBallCmd> disp);

	/**
	 * Method for changing the ball's characteristics at initialization
	 * @param context the IBall object representing the ball's stats
	 */
	void init(IBall context);

	/**
	 * The NULL update strategy
	 */
	public static final IUpdateStrategy NULL = new IUpdateStrategy() {

		@Override
		public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
		}

		@Override
		public void init(IBall context) {
		}

	};
}
