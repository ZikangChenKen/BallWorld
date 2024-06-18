package hw06.model.interact.strategy;

import java.awt.Color;


import hw06.model.BallAlgo;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.ball.ConcreteBall.BullyBall;
import hw06.model.ball.ConcreteBall.FriendBall;
import hw06.model.interact.AInteractStrategy;
//import hw06.model.interact.IInteractStrategy;
import provided.ballworld.extVisitors.IBallHostID;
import provided.utils.dispatcher.IDispatcher;

/**
 * BullyInteractStrategy class implements IInteractStrategy and defines
 * the interaction behavior between BullyBall and FriendBall.
 */
public class BullyInteractStrategy extends AInteractStrategy {

	/**
	 * Initializes the strategy for the given ball context.
	 * @param context The ball context.
	 
	@Override
	public void init(IBall context) {
		// TODO: Initialization logic if needed
	}*/

	/**
	 * Defines the interaction behavior between the source ball and the target ball.
	 * @param source The source ball.
	 * @param target The target ball.
	 * @param disp The dispatcher.
	 */
	@Override
	public void interact(IBall source, IBall target, IDispatcher<IBallCmd> disp) {
		// Define the main interaction algorithm
		BallAlgo<Void, Void> algo = new BallAlgo<>(new ABallAlgoCmd<>() {
			private static final long serialVersionUID = 4263051031889256940L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				target.interactWithThen(source, disp);
				return null;
			}
		});

		// Define the interaction behavior for BullyBall
		algo.setCmd(BullyBall.ID, new ABallAlgoCmd<Void, Void>() {
			private static final long serialVersionUID = 4392891781365346153L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// Define the secondary interaction algorithm
				BallAlgo<Void, Void> algo2 = new BallAlgo<>(new ABallAlgoCmd<>() {
					private static final long serialVersionUID = 6567388391007973462L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						return null;
					}
				});

				// Define the interaction behavior for FriendBall when interacting with BullyBall
				algo2.setCmd(FriendBall.ID, new ABallAlgoCmd<Void, Void>() {
					private static final long serialVersionUID = -6212372483924566193L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						target.setRadius(source.getRadius() - (int) Math.round(source.getRadius() * 0.2));
						target.setColor(Color.RED);
						return null;
					}
				});

				target.execute(algo2);
				return null;
			}
		});

		// Define the interaction behavior for FriendBall
		algo.setCmd(FriendBall.ID, new ABallAlgoCmd<Void, Void>() {
			private static final long serialVersionUID = 634875195949410822L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				return null;
			}
		});

		source.execute(algo);
	}
}

