package hw06.model.interact.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interact strategy for balls, when larger balls change color to match 
 * the small ones. 
 */
public class RenderColorInteractStrategy implements IInteractStrategy {

	@Override
	public IBallCmd interactWithThen(IBall source, IBall target, IDispatcher<IBallCmd> dispatcher) {

		return new IBallCmd() {

			@Override
			public void apply(IBall source, IDispatcher<IBallCmd> disp) {
				if (!source.getColor().equals(target.getColor())) {
					if (source.getRadius() <= target.getRadius()) {
						target.setColor(source.getColor());
					} else {
						source.setColor(target.getColor());
					}

				}

			}

		};

	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
