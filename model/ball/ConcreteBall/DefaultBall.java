package hw06.model.ball.ConcreteBall;

import java.awt.Color;
import java.awt.Point;

import hw06.model.IModel2ViewAdapter;
import hw06.model.ball.ABall;
import hw06.model.ball.IBallAlgo;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.BallHostIDFactory;
import provided.utils.displayModel.IDimension;

/**
 * The concrete default ball class.
 */
public class DefaultBall extends ABall {

	// Add generated serialVersionUID
		/**
		 * serial ID 
		 */
		private static final long serialVersionUID = 8016427480391547765L;
		
		/**
		 * The identifying host ID for this class.
		 * For convenience, the "friendly name" of the ID is just the fully qualified name of this class.
		 */
		public static final IBallHostID ID = BallHostIDFactory.Singleton.makeID(DefaultBall.class.getName());

		/**
		 * Initializes this Ball to a given center, radius, color,
		 * adapter to access system information such as canvas dimensions 
		 * and image loading services and an algorithm to run that will perform 
		 * any desired configuration operations such as installing strategies.
		 * 
		 * @param radius the initial radius of this Ball.
		 * @param velocity the initial velocity of this Ball.
		 * @param color The initial color of the ball.
		 * @param location the initial center of this Ball.
		 * @param canvasDim canvas dimension
		 * @param viewAdpt  view adapter 
		 * @param modelAdpt the adapter to the system model
		 * @param installAlgo The algo to complete the installation of strategies and any other desired operations
		 */
	public DefaultBall(Point location, int radius, Point velocity, Color color, IDimension canvasDim,
			IModel2ViewAdapter viewAdpt, IBallAlgo<Void, Void> installAlgo) {
		super(ID, location, radius, velocity, color, canvasDim, viewAdpt, installAlgo);
		// TODO Auto-generated constructor stub
	}

}
