package hw06.model.ball.ConcreteBall;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallAlgo;

/**
 * The interface to construct the ball.
 */
public interface IConcreteBallFactory {
	/**
	 * Instantiate the specific concrete ball for which this factory is defined.
	 * @param configAlgo The algo used to configure the ball
	 * @return An instance of IBall
	 */
	public IBall make(IBallAlgo<Void, Void> configAlgo);

	/**
	 * A no-op ball factory.
	 */
	public static final IConcreteBallFactory NULL_OBJECT = new IConcreteBallFactory() {

		@Override
		public IBall make(IBallAlgo<Void, Void> configAlgo) {
			// TODO Auto-generated method stub
			return null;
		}

	};

}
