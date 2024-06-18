package hw06.model.updateStrategy.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * Strategy used for when there is an error loading a strategy 
 *
 */
public class ErrorStrategy implements IUpdateStrategy {
	/**
	 * Update counter used to beep at intervals
	 */
	private int count;// update counter

	@Override
	/**
	 * Beep the speaker every 25 updates
	 */
	public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
		if (25 < count++) {
			java.awt.Toolkit.getDefaultToolkit().beep();
			this.count = 0;
		}
	}

	@Override
	/**
	 * Initialize the count to 0
	 * 
	 */
	public void init(IBall context) {
		count = 0;
	}
}
