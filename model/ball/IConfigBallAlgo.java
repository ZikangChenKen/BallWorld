package hw06.model.ball;

import hw06.model.IBallAlgo2ModelAdapter;
import provided.logger.ILoggerControl;

/**
 * The interface represent the configure ball algo.
 */
public interface IConfigBallAlgo extends IBallAlgo<Void, Void> {
	/**
	 * Instantiate a configuration algorithm that defaults to a no-op and where additional commands can be added later.
	 * @return A configuration algo defaulting to no-op
	 */
	public static IConfigBallAlgo MakeDefaultNULL() {
		return new AConfigBallAlgo(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL) {
		
			// Add generated serialVersionUID
	
			private static final long serialVersionUID = 968557217514433075L;

			@Override
			public String toString() {
				return "IConfigBallAlgo.NULL";
			}
		
		};
		
	}	/**
	 * @return error 
	 */
	public static IConfigBallAlgo MakeDefaultERROR() { 
		return new AConfigBallAlgo(ILoggerControl.getSharedLogger(), IBallAlgo2ModelAdapter.NULL) {

			// Add generated serialVersionUID
	
			private static final long serialVersionUID = -8542248532151599982L;

			{
				setDefaultCmd(ABallAlgoCmd.MakeError("Error!"));
			}
			
			@Override
			public String toString() {
				return "IConfigBallAlgo.ERROR";
			}
		}; 
	}
}
