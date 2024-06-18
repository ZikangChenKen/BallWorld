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
 * rainbow ball 
 */
public class RainbowBall extends ABall {

	/**
	 * default serial ID 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * generate ID 
	 */
	public static final IBallHostID ID = BallHostIDFactory.Singleton.makeID(BullyBall.class.getName());

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
	 * @param dim dimension 
	 * @param modelAdpt the adapter to the system model
	 * @param configAlgo The algo to complete the installation of strategies and any other desired operations
	 */
	public RainbowBall(Point location, int radius, Point velocity, Color color, IDimension dim,
			IModel2ViewAdapter modelAdpt, IBallAlgo<Void, Void> configAlgo) {
		super(ID, location, radius, velocity, Color.black, dim, modelAdpt, configAlgo);
	}
}
