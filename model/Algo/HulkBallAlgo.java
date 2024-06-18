package hw06.model.Algo;

import java.awt.Color;
import java.util.function.Supplier;

import hw06.model.BallAlgo;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.IBall;
import hw06.model.ball.ConcreteBall.DefaultBall;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * HulkBallAlgo class extends BallAlgo and is responsible for changing the color and size of a ball.
 * This class is particularly focused on turning the ball green and increasing its size.
 */
public class HulkBallAlgo extends BallAlgo<Void, Void> {
	
	// Auto-generated serialVersionUID for serialization
	/**
	 * 
	 */
	private static final long serialVersionUID = 1969852417557868293L;

	/**
	 * A Supplier function that returns a Boolean indicating whether the algorithm is enabled or not.
	 */
	private Supplier<Boolean> isEnabledFn;
	
	/**
	 * Command to turn a given host green and change its size if enabled.
	 * Defined as a field so that it can be used in multiple cases.
	 */
	private ABallAlgoCmd<Void, Void> hulkAlgoCmd = new ABallAlgoCmd<>() {

		// Auto-generated serialVersionUID for serialization
		private static final long serialVersionUID = 1L;

		/**
		 * Applies the algorithm to the given ball host.
		 * Turns the host green and changes its size if enabled.
		 * 
		 * @param index The ID of the ball host.
		 * @param host The ball host itself.
		 * @param params Additional parameters (not used here).
		 * @return null
		 */
		@Override
		public Void apply(IBallHostID index, IBall host, Void... params) {
			if(isEnabledFn.get()) {
				host.setColor(Color.green);
				if (host.getRadius() <= 30) {
					host.setRadius(host.getRadius() + 50);
				} else {
					host.setRadius(80);
				}
			}	
			return null;
		}
	};
	
	/**
	 * Constructor for HulkBallAlgo.
	 * Initializes the algorithm with the given accessor for the global enabled state.
	 * 
	 * @param isEnabledFn A Supplier function that returns a Boolean indicating the global enabled state.
	 */
	public HulkBallAlgo(Supplier<Boolean> isEnabledFn) {
		super(ABallAlgoCmd.MakeNull());  // Initialize with a null command
		
		this.isEnabledFn = isEnabledFn;  // Set the isEnabled function
		
		// Set the command for PreyBall type to hulkAlgoCmd
		setCmd(DefaultBall.ID, hulkAlgoCmd);
	}
}

