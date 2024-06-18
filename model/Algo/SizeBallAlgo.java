package hw06.model.Algo;

import java.util.function.Supplier;

import hw06.model.BallAlgo;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.IBall;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * The ball algo that turns the normal ball to become bigger and green.
 */
public class SizeBallAlgo extends BallAlgo<Void, Void>{
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = -5662141857579973157L;

	/**
	 * The global enabled state.
	 */
	private Supplier<Boolean> isEnabledFn;

	/**
	 * The global enabled state.
	 */
	private Supplier<Integer> sizeBall;

	/**
	 * Command to turn a given host black if enabled.
	 * Defined as a field so that it can be used in multiple cases.
	 */
	private ABallAlgoCmd<Void, Void> makeLargeAlgoCmd = new ABallAlgoCmd<Void, Void>() {

		private static final long serialVersionUID = 7048720870749247670L;

		@Override
		public Void apply(IBallHostID index, IBall host, Void... params) {
			// TODO Auto-generated method stub
			if (isEnabledFn.get()) { // Turn host black if enabled
				int size = sizeBall.get();
				host.setRadius(size);
			}
			return null;
		}

	};

	/**
	 * Construct the algorithm with the given accessor of the global enabled state.
	 * Note that the isEnabled value is accessed via a _function_ to ensure that the latest
	 * value is obtained!
	 * @param isEnabledFn Accessor function for the global enabled state.  
	 * @param sizeBall Accessor function for the ball's size
	 */
	public SizeBallAlgo(Supplier<Boolean> isEnabledFn, Supplier<Integer> sizeBall) {
		super(ABallAlgoCmd.MakeNull()); // Can't use turnBlackAlgoCmd here b/c it is an instance field.

		setDefaultCmd(makeLargeAlgoCmd); // Use this to turn balls black by default.  

		this.isEnabledFn = isEnabledFn;
		this.sizeBall = sizeBall;

	}

}
