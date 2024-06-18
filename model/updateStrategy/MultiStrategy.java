package hw06.model.updateStrategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * Class that represents when two strategies are combined
 * 
 */
public class MultiStrategy implements IUpdateStrategy {

	/**
	 * First strategy to be combined
	 */
	private IUpdateStrategy strategy1;

	/**
	 * Second strategy to be combined
	 */
	private IUpdateStrategy strategy2;

	/**
	 * Sets the two strategies to combine
	 * @param strategy1 first strategy to combine
	 * @param strategy2 second strategy to combine
	 */
	public MultiStrategy(IUpdateStrategy strategy1, IUpdateStrategy strategy2) {
		this.strategy1 = strategy1;
		this.strategy2 = strategy2;
	}

	@Override
	/**
	 * Updates the state of the ball
	 * @param context ball object to be updated
	 * @param disp the dispatcher of the ball
	 */
	public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
		this.strategy1.updateState(context, disp);
		this.strategy2.updateState(context, disp);
	}

	@Override
	public void init(IBall context) {
		this.strategy1.init(context);
		this.strategy2.init(context);
	}

}
