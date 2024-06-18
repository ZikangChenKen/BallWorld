package hw06.model.interact.strategy;




import hw06.model.BallAlgo;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.ball.ConcreteBall.BullyBall;
import hw06.model.ball.ConcreteBall.FriendBall;
import hw06.model.interact.IInteractStrategy;
import hw06.model.paint.IPaintStrategy;
import provided.ballworld.extVisitors.IBallHostID;
import provided.utils.dispatcher.IDispatcher;

/**
 * StealPaintInteractStrategy class implements IInteractStrategy and defines
 * the interaction behavior between BullyBall and FriendBall where friend copies the paint strategy of BullyBall
 */

public class StealPaintInteractStrategy implements IInteractStrategy {

	/**
	 * Initializes the strategy for the given ball context.
	 * @param context The ball context.
	 */
	@Override
	public void init(IBall context) {
		// TODO: Initialization logic if needed
	}

	/**
	 * Defines the interaction behavior between the source ball and the target ball.
	 * @param source The source ball.
	 * @param target The target ball.
	 * @param disp The dispatcher.
	 * @return An IBallCmd object.
	 */
	@Override
	public IBallCmd interactWithThen(IBall source, IBall target, IDispatcher<IBallCmd> disp) {
		// Define the main interaction algorithm
		BallAlgo<Void, Void> algo = new BallAlgo<>(new ABallAlgoCmd<>() {
	

			private static final long serialVersionUID = 1L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				target.interactWithThen(source, disp);
				return null;
			}
		});

		// Define the interaction behavior for FriendBall
		algo.setCmd(FriendBall.ID, new ABallAlgoCmd<Void, Void>() {
	

			private static final long serialVersionUID = 1L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// Define the secondary interaction algorithm
				BallAlgo<Void, Void> algo2 = new BallAlgo<>(new ABallAlgoCmd<>() {
					

					private static final long serialVersionUID = 1L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						return null;
						
					}
				});

				// Define the interaction behavior for BullyBall when interacting with FriendBall
				algo2.setCmd(BullyBall.ID, new ABallAlgoCmd<Void, Void>() {


					private static final long serialVersionUID = 1L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						IPaintStrategy paintStrat = source.getPaintStrategy();
						target.setPaintStrategy(paintStrat);
						
						return null;
					}
				});

				target.execute(algo2);
				return null;
			}
		});

		// Define the interaction behavior for FriendBall
		algo.setCmd(FriendBall.ID, new ABallAlgoCmd<Void, Void>() {


			private static final long serialVersionUID = 1L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				return null;
			}
		});

		source.execute(algo);

		return new IBallCmd() {
			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				// TODO: Additional logic if needed
			}
		};
	}

}
