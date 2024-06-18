package hw06.model.updateStrategy.strategy;

import java.awt.Color;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * 
 */
public class ColorDistanceStrategy implements IUpdateStrategy {

	/**
	 * 
	 */
	private final double COLOR_DISTANCE_THRESHOLD = 100.0;

	@Override
	public void updateState(final IBall source, IDispatcher<IBallCmd> dispatcher) {
		dispatcher.updateAll(new IBallCmd() {
			@Override
			public void apply(IBall other, IDispatcher<IBallCmd> disp) {
				if (source != other) {
					Color c1 = source.getColor();
					Color c2 = other.getColor();

					double colorDistance = Math.sqrt(Math.pow(c2.getRed() - c1.getRed(), 2)
							+ Math.pow(c2.getGreen() - c1.getGreen(), 2) + Math.pow(c2.getBlue() - c1.getBlue(), 2));

					if (colorDistance <= COLOR_DISTANCE_THRESHOLD) {
						IBallCmd contextPostInteractCmd = source.interactWithThen(other, disp);
						IBallCmd otherPostInteractCmd = other.interactWithThen(source, disp);

						source.update(disp, contextPostInteractCmd);
						other.update(disp, otherPostInteractCmd);
					}
				}
			}
		});
	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
