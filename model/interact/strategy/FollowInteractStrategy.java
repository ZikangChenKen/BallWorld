package hw06.model.interact.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interact strategy where larger ball changes velocity to match the smaller ball's.
 *
 */
public class FollowInteractStrategy implements IInteractStrategy {

	@Override
	public IBallCmd interactWithThen(IBall source, IBall target, IDispatcher<IBallCmd> dispatcher) {

		return new IBallCmd() {

			@Override
			public void apply(IBall source, IDispatcher<IBallCmd> disp) {
				if (source.getRadius() < target.getRadius()) {
					source.setVelocity(target.getVelocity());
				}

			}

		};

	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
