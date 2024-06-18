package hw06.model.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import hw06.model.IModel2ViewAdapter;
import hw06.model.interact.IInteractStrategy;
import hw06.model.paint.IPaintStrategy;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.ABallHost;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.displayModel.IDimension;

/**
* Ball concrete class that represents a ball
*/
public abstract class ABall extends ABallHost<IBall> implements IBall {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3628727102411134028L;

	/**
	 * The center location of the ball
	 */
	private Point location;

	/**
	 * The radius of the ball
	 */
	private int radius;

	/**
	 * The velocity of the ball
	 */
	private Point velocity;

	/**
	 * The color of the ball
	 */
	private Color color;

	/**
	 * mass 
	 */
	private double Mass;

	/**
	 * The canvas dimension
	 */
	private IDimension canvasDim;

	/**
	 * The update strategy for ball, initially set to the null strategy
	 */
	private IUpdateStrategy updateStrategy = IUpdateStrategy.NULL;

	/**
	 * The paint strategy for the ball, initially set to the null strategy
	 */
	private IPaintStrategy paintStrategy = IPaintStrategy.NULL;

	/**
	 * The model to view adapter used for painting images.
	 */
	private IModel2ViewAdapter viewAdpt;

	/**
	 * Flag for representing whether a ball just bounced off the ground
	 */
	private boolean bouncedGround = false;

	/**
	 * Flag representing whether the ball just bounced
	 */
	private boolean ballBounced = false;

	/**
	 * The interact strategy for the ball, initially set to the null strategy
	 */
	IInteractStrategy _interactStrategy = IInteractStrategy.NULL;

	/**
	 * Constructor for Ball
	 * @param id id of the ball 
	 * @param location	A Point representing the center of the ball
	 * @param radius	An int representing the radius of the ball
	 * @param velocity	A Point representing the velocity of the ball
	 * @param color		A Color representing the color of the ball
	 * @param canvasDim	An IDimension representing the dimension of the canvas
	 * @param viewAdpt	A IModel2ViewAdapter to be passed to the ball
	 * @param installAlgo The installation Algo that the ball uses at the start
	 */
	protected ABall(IBallHostID id, Point location, int radius, Point velocity, Color color, IDimension canvasDim,
			IModel2ViewAdapter viewAdpt, IBallAlgo<Void, Void> installAlgo) {
		super (id);
		this.location = location;
		this.radius = radius;
		this.velocity = velocity;
		this.color = color;
		this.canvasDim = canvasDim;
		this.viewAdpt = viewAdpt;
		this.Mass = radius * radius;
		this.execute(installAlgo);
	}

	/**
	 * Runs the default case of the algorithm.
	 * 
	 * @param algo an IBallAlgo to execute
	 
	@Override
	public void execute(IBallAlgo algo) {
		algo.caseDefault(this);
	}
*/
	/**
	 * @return the dimensions of the canvas
	 */
	@Override
	public IDimension getCanvas() {
		return this.canvasDim;
	}

	/**
	 * @return the center of the ball
	 */
	@Override
	public Point getLocation() {
		return this.location;
	}

	/**
	 * @param location	A point representing the center of the ball	
	 */
	@Override
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return the radius of the ball
	 */
	@Override
	public int getRadius() {
		return this.radius;
	}

	/**
	 * @param radius An int representing the radius of the ball
	 */
	@Override
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * @return the velocity of the ball
	 */
	@Override
	public Point getVelocity() {
		return this.velocity;
	}

	/**
	 * @param velocity	A Point representing the velocity of the ball
	 */
	@Override
	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the color of the ball
	 */
	@Override
	public Color getColor() {
		return this.color;
	}

	/**
	 * @param color	A Color representing the color of the ball
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return a boolean representing whether the ball just bounced off the ground
	 */
	@Override
	public boolean bouncedGround() {
		return this.bouncedGround;
	}

	/**
	 * @return a boolean representing whether the ball just bounced 
	 */
	@Override
	public boolean bounced() {
		return this.ballBounced;
	}

	/**
	 * The update method called by the main ball Dispatcher to notify all the balls to perform the given command.
	 * The given command is executed.
	 * @param disp The Dispatcher that sent the update request.
	 * @param cmd The IBallCmd that will be run.
	 */
	@Override
	public void update(IDispatcher<IBallCmd> disp, IBallCmd cmd) {
		cmd.apply(this, disp);
	}

	/**
	 * Moves the ball
	 */
	@Override
	public void move() {
		location.translate(velocity.x, velocity.y);
	}

	/**
	 * Bounces the ball off the wall
	 */
	@Override
	public void bounce() {
		int canvasWidth = this.canvasDim.getWidth();
		int canvasHeight = this.canvasDim.getHeight();

		int ballX = this.location.x;
		int ballY = this.location.y;

		// Set bounce flags
		this.ballBounced = false;
		this.bouncedGround = false;

		// Check right bound
		if (canvasWidth - this.radius < ballX) {
			// Flip the balls x direction
			this.velocity.x = -this.velocity.x;
			// Adjust the balls place
			this.location.x = ballX - 2 * (ballX - canvasWidth + this.radius);

			this.ballBounced = true;
		}

		// Check left bound
		if (ballX - this.radius < 0) {
			// Flip the balls x direction
			this.velocity.x = -this.velocity.x;
			// Adjust the balls place
			this.location.x = ballX + 2 * (0 - ballX + this.radius);

			this.ballBounced = true;
		}

		// Check bottom bound
		if (canvasHeight - this.radius < ballY) {
			this.bouncedGround = true;
			// Flip the balls y direction
			this.velocity.y = -this.velocity.y;
			// Adjust the balls place
			this.location.y = ballY - 2 * (ballY - canvasHeight + this.radius);

			this.ballBounced = true;
		}

		// Check top bound
		if (ballY - this.radius < 0) {
			// Flip the balls y direction
			this.velocity.y = -this.velocity.y;
			// Adjust the balls place
			this.location.y = ballY + 2 * (0 - ballY + this.radius);

			this.ballBounced = true;
		}
	}

	/**
	 * Paints the ball
	 * @param g	The Graphics object to paint on
	 */
	@Override
	public void paint(Graphics g) {
		this.paintStrategy.init(this);
		this.paintStrategy.paint(g, this);
	}

	/**
	 * Updates the state
	 * @param dispatcher	An IDispatcher object representing the dispatcher
	 */
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher) {
		this.updateStrategy.updateState(this, dispatcher);

	}

	/**
	 * Invoke this ball's interaction strategy from the perspective of this ball.
	 * @param target  The "other" ball to interact with
	 * @param disp  A Dispatcher to use if desired.
	 * @return A command to be run by this ball after both balls' interaction behaviors are complete.
	 */
	public IBallCmd interactWithThen(IBall target, IDispatcher<IBallCmd> disp) {
		return this._interactStrategy.interactWithThen(this, target, disp);
	}

	public void setInteractStrategy(IInteractStrategy interactStrat) {
		this._interactStrategy = interactStrat;
	}

	public IInteractStrategy getInteractStrategy() {
		return this._interactStrategy;
	}

	/**
	 * Sets the update strategy
	 * 
	 * @param strat	the new IUpdateStrategy
	 */
	@Override
	public void setUpdateStrategy(IUpdateStrategy strat) {
		// TODO Auto-generated method stub
		this.updateStrategy = strat;
		this.updateStrategy.init(this);
	}

	/**
	 * @return the current IUpdateStrategy
	 */
	@Override
	public IUpdateStrategy getUpdateStrategy() {
		// TODO Auto-generated method stub
		return this.updateStrategy;
	}

	/**
	 * Sets the paint strategy
	 * 
	 * @param strat	the new IPaintStrategy
	 */
	@Override
	public void setPaintStrategy(IPaintStrategy strat) {
		// TODO Auto-generated method stub
		this.paintStrategy = strat;
	}

	/**
	 * @return the current IPaintStrategy
	 */
	@Override
	public IPaintStrategy getPaintStrategy() {
		// TODO Auto-generated method stub
		return this.paintStrategy;
	}

	/**
	 * @return the IModel2ViewAdapter
	 */
	public IModel2ViewAdapter getViewAdapter() {
		return this.viewAdpt;
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return this.Mass;
	}

	@Override
	public void setMass(double mass) {
		// TODO Auto-generated method stub
		this.Mass = mass;
	}

}