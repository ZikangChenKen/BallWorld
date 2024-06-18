package hw06.model.interact.strategy;

//import java.awt.Point;
//
//import java.awt.geom.Point2D;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.valueGenerator.IRandomizer;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * Change color interact strategy
 */
public class ChangeColorInteractStrategy implements IInteractStrategy {

	/**
	 * rand 
	 */
	private IRandomizer rand = Randomizer.Singleton;

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
				context.setColor(rand.randomColor());
			}
		};
	}

}
