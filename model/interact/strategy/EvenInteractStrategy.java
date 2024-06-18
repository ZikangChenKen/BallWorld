package hw06.model.interact.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * Even interact strategy
 */
public class EvenInteractStrategy implements IInteractStrategy {

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBallCmd interactWithThen(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
		// TODO Auto-generated method stub
		return new IBallCmd() {

			@Override
			public void apply(IBall other, IDispatcher<IBallCmd> disp) {
				// TODO Auto-generated method stub
				context.setRadius((context.getRadius() + target.getRadius()) / 2);

			}

		};
	}

}
