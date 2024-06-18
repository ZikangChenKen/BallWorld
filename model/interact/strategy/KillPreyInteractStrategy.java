package hw06.model.interact.strategy;

import hw06.model.BallAlgo;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.ball.ConcreteBall.PreyBall;
import hw06.model.interact.AInteractStrategy;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILoggerControl;
import provided.logger.LogLevel;
import provided.utils.dispatcher.IDispatcher;

/**
 * KillPreyInteractStrategy class extends AInteractStrategy and defines
 * the interaction behavior for killing PreyBalls.
 */
public class KillPreyInteractStrategy extends AInteractStrategy {

	/**
	 * Default constructor.
	 */
	public KillPreyInteractStrategy() {
		// TODO: Initialization logic if needed
	}

	/**
	 * Defines the interaction behavior between the context ball and the target ball.
	 * @param context The context ball.
	 * @param target The target ball.
	 * @param disp The dispatcher.
	 * @return An IBallCmd object.
	 
	*/
	@Override
	public IBallCmd interactWithThen(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
		this.interact(context, target, disp);
		return new IBallCmd() {
			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				// TODO: Additional logic if needed
				return;
			}
		};
	}

	/**
	 * Executes the interaction strategy on the target ball.
	 * @param context The context ball.
	 * @param target The target ball.
	 * @param disp The dispatcher.
	 */
	@Override
	public void interact(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
		target.execute(new BallAlgo<Void, Void>(new ABallAlgoCmd<>() {
			// TODO: Add generated serialVersionUID

			private static final long serialVersionUID = 6009697558365806956L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// No-op by default for non-PreyBalls
				return null;
			}
		}) {
			// TODO: Add generated serialVersionUID

			private static final long serialVersionUID = 2442424806411747134L;

			// Initializer block for the BallAlgo's anonymous inner class
			{
				// Define the interaction behavior for PreyBalls
				setCmd(PreyBall.ID, new ABallAlgoCmd<Void, Void>() {
					// TODO: Add generated serialVersionUID

					private static final long serialVersionUID = 8728590958004520464L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						disp.removeObserver(target); // "Kill" the PreyBall
						ILoggerControl.getSharedLogger().log(LogLevel.INFO, "Prey ball killed!");
						return null;
					}
				});

				// TODO: Add additional interaction behaviors for other ball types if needed
			}
		});
	}
}

