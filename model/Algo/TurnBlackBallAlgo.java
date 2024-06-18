package hw06.model.Algo;

import java.awt.Color;
import java.util.function.Supplier;

import hw06.model.BallAlgo;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.IBall;
import hw06.model.ball.ConcreteBall.PreyBall;
import provided.ballworld.extVisitors.IBallHostID;

/**
 * BallAlgo command that turns a host black depending on its type and if enabled.
 * @author swong
 *
 */
public class TurnBlackBallAlgo extends BallAlgo<Void, Void> {

	// Need to add generated serialVersionUID
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4971468429313439198L;

	/**
	 * The accessor for the global enabled state.
	 */
	private Supplier<Boolean> isEnabledFn;
	
	/**
	 * Command to turn a given host black if enabled.
	 * Defined as a field so that it can be used in multiple cases.
	 */
	private ABallAlgoCmd<Void, Void> turnBlackAlgoCmd = new ABallAlgoCmd<>() {

		// Need to add generated serialVersionUID

		private static final long serialVersionUID = -7226987606358136495L;

		@Override
		public Void apply(IBallHostID index, IBall host, Void... params) {
			if(isEnabledFn.get()) { // Turn host black if enabled
				host.setColor(Color.BLACK);
			}	
			return null;
		}
		
	};
		
	
	/**
	 * Construct the algorithm with the given accessor of the global enabled state.
	 * Note that the isEnabled value is accessed via a _function_ to ensure that the latest
	 * value is obtained!
	 * @param isEnabledFn Accessor function for the global enabled state.  
	 */
	public TurnBlackBallAlgo(Supplier<Boolean> isEnabledFn) {
		super(ABallAlgoCmd.MakeNull());  // Can't use turnBlackAlgoCmd here b/c it is an instance field.
		
//		setDefaultCmd(turnBlackAlgoCmd);  // Use this to turn balls black by default.  
		
		this.isEnabledFn = isEnabledFn;		
		
		setCmd(PreyBall.ID, turnBlackAlgoCmd); // Turns a specific type of ball to black
		
//		setCmd(PredatorBall.ID, ABallAlgoCmd.MakeNull());  // Use this to exclude a ball type from turning black 
		
	}
	

}
