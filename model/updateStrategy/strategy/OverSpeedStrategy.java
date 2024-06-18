package hw06.model.updateStrategy.strategy;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * Over speed strategy
 */
public class OverSpeedStrategy implements IUpdateStrategy {

	@Override
	public void updateState(IBall source, IDispatcher<IBallCmd> disp) {

		// Calculate average speed of all balls
		final double[] totalSpeed = { 0.0 };
		final int[] ballCount = { 0 };

		disp.updateAll(new IBallCmd() {
			@Override
			public void apply(IBall ball, IDispatcher<IBallCmd> disp) {
				double speed = Math
						.sqrt(Math.pow(ball.getVelocity().getX(), 2) + Math.pow(ball.getVelocity().getY(), 2));
				totalSpeed[0] += speed;
				ballCount[0]++;
			}
		});

		double averageSpeed = totalSpeed[0] / ballCount[0];

		// If source's speed is three times the average speed, do the update
		double sourceSpeed = Math
				.sqrt(Math.pow(source.getVelocity().getX(), 2) + Math.pow(source.getVelocity().getY(), 2));

		if (sourceSpeed >= 2 * averageSpeed) {
			disp.updateAll(new IBallCmd() {
				@Override
				public void apply(IBall other, IDispatcher<IBallCmd> disp) {
					IBallCmd contextPostInteractCmd = source.interactWithThen(other, disp);
					IBallCmd otherPostInteractCmd = other.interactWithThen(source, disp);

					source.update(disp, contextPostInteractCmd);
					other.update(disp, otherPostInteractCmd);
				}
			});
		}
	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
