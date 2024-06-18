package hw06.model.interact.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interact strategy for error balls.
 *
 */
public class ErrorInteractStrategy implements IInteractStrategy {

	@Override
	public IBallCmd interactWithThen(IBall source, IBall target, IDispatcher<IBallCmd> disp) {
		return new IBallCmd() {

			@Override
			public void apply(IBall source, IDispatcher<IBallCmd> disp) {
				// Beeps whenever interact criteria is met
				java.awt.Toolkit.getDefaultToolkit().beep();
			}

		};
	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
