package hw06.model.updateStrategy.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.IRandomizer;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * Strategy that randomizes a balls color
 */
public class ColorChangingStrategy implements IUpdateStrategy {

	/**
	 * variable to randomize
	 */
	private IRandomizer rand = Randomizer.Singleton;

	/**
	 * Updates the state of the ball
	 * @param context ball object to be updated
	 * @param disp the dispatcher to update the state
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
		//randomly set color
		context.setColor(rand.randomColor());
	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
