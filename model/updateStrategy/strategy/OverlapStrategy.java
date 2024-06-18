package hw06.model.updateStrategy.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * When two balls collide with each other.
 *
 */
public class OverlapStrategy implements IUpdateStrategy {

	@Override
	public void updateState(final IBall source, IDispatcher<IBallCmd> dispatcher) {
		dispatcher.updateAll(new IBallCmd() {
			@Override
			public void apply(IBall other, IDispatcher<IBallCmd> disp) {
				if (source != other) {
					if (source.getRadius() + other.getRadius() >= source.getLocation().distance(other.getLocation())) { // whatever the interaction criteria is
						// Save the commands that were generated.   Note that each ball is doing its own interaction behavior/calculations.
						IBallCmd contextPostInteractCmd = source.interactWithThen(other, disp);
						IBallCmd otherPostInteractCmd = other.interactWithThen(source, disp);

						// Run the saved commands now that both balls' interaction behaviors are complete.
						source.update(disp, contextPostInteractCmd);
						other.update(disp, otherPostInteractCmd);
					}
				}
			}

		});

	}

	@Override
	public void init(IBall source) {

	}

}
