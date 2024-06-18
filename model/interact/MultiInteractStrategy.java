package hw06.model.interact;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.ball.MultiBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * A class for multiple interact strategies.
 *
 */
public class MultiInteractStrategy implements IInteractStrategy {

	/**
	 * The first strategy.
	 */
	IInteractStrategy interactStrat1;
	/**
	 * The second strategy.
	 */
	IInteractStrategy interactStrat2;

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub
		interactStrat1.init(context);
		interactStrat2.init(context);
	}

	/**
	 * @param strat1 the first strategy
	 * @param strat2 the second strategy
	 */
	public MultiInteractStrategy(IInteractStrategy strat1, IInteractStrategy strat2) {
		interactStrat1 = strat1;
		interactStrat2 = strat2;
	}

	@Override
	public IBallCmd interactWithThen(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
		IBallCmd a = interactStrat1.interactWithThen(context, target, disp);
		IBallCmd b = interactStrat2.interactWithThen(context, target, disp);

		return new MultiBallCmd(interactStrat1, interactStrat2) {

			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				context.update(disp, a);
				context.update(disp, b);
			}
		};

	}

}
