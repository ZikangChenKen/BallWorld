package hw06.model.interact.strategy;

import java.awt.Point;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.IInteractStrategy;
import provided.utils.dispatcher.IDispatcher;

/**
 * An interact strategy where larger ball changes velocity to match the smaller ball's.
 *
 */
public class AbsorbInteractStrategy implements IInteractStrategy {

	@Override
	public IBallCmd interactWithThen(IBall source, IBall target, IDispatcher<IBallCmd> dispatcher) {

		return new IBallCmd() {

			@Override
			public void apply(IBall source, IDispatcher<IBallCmd> disp) {
				//				if (source.getRadius() < target.getRadius()) {
				//					source.setVelocity(target.getVelocity());
				//				}
				double dx = target.getLocation().getX() - source.getLocation().getX();
				double dy = target.getLocation().getY() - source.getLocation().getY();

				// Normalize the direction vector to have a magnitude of 1
				double distance = Math.sqrt(dx * dx + dy * dy);
				double normalizedDx = dx / distance;
				double normalizedDy = dy / distance;

				// Set the velocity of source to follow target
				// You can multiply normalizedDx and normalizedDy with a desired speed
				double followSpeed = 1.0; // You can adjust this as needed
				Point v = new Point();
				v.setLocation(followSpeed * normalizedDx, followSpeed * normalizedDy);
				source.setVelocity(v);

			}

		};

	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
