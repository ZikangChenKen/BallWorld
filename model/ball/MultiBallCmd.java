package hw06.model.ball;

import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * A class for multiple commands.
 * @author Teresa Zhou;
 *
 */
public class MultiBallCmd implements IBallCmd {
	/**
	 * The first interact strategy.
	 */
	IInteractStrategy interactStrat1;
	/**
	 * The second interact strategy.
	 */
	IInteractStrategy interactStrat2;

	/**
	 * @param strat1 the first strategy
	 * @param strat2 the second strategy
	 */
	public MultiBallCmd(IInteractStrategy strat1, IInteractStrategy strat2) {
		interactStrat1 = strat1;
		interactStrat2 = strat2;
	}

	@Override
	public void apply(IBall context, IDispatcher<IBallCmd> disp) {
	}

}
