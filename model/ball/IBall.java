package hw06.model.ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import hw06.model.IModel2ViewAdapter;
import hw06.model.interact.IInteractStrategy;
import hw06.model.paint.IPaintStrategy;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.ballworld.extVisitors.IBallHost;
import provided.ballworld.extVisitors.IBallHostID;
import provided.extvisitor.IExtVisitor;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.dispatcher.IObserver;
import provided.utils.displayModel.IDimension;

/**
 * An interface for balls that bounce 
 *
 */
public interface IBall extends IObserver<IBallCmd>, IBallHost<IBall>{
	
	/**
	 * Null object for IBall.
	 */
	public static IBall NULL_OBJECT = new IBall() {

		private static final long serialVersionUID = 7916276109984621812L;

		@Override
		public <R, P> R execute(IExtVisitor<R, IBallHostID, P, ? extends IBall> algo, @SuppressWarnings("unchecked") P... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setUpdateStrategy(IUpdateStrategy strat) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IUpdateStrategy getUpdateStrategy() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setPaintStrategy(IPaintStrategy strat) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IPaintStrategy getPaintStrategy() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IModel2ViewAdapter getViewAdapter() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IDimension getCanvas() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Point getLocation() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setLocation(Point location) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getRadius() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setRadius(int radius) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Point getVelocity() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setVelocity(Point velocity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Color getColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setColor(Color color) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public double getMass() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setMass(double mass) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean bouncedGround() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean bounced() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void update(IDispatcher<IBallCmd> disp, IBallCmd cmd) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void move() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void bounce() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IBallCmd interactWithThen(IBall other, IDispatcher<IBallCmd> disp) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setInteractStrategy(IInteractStrategy interactStrat) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IInteractStrategy getInteractStrategy() {
			// TODO Auto-generated method stub
			return null;
		}
		
	};

	/**s
	 * Sets the update strategy
	 * @param strat the new update strategy
	 */
	public void setUpdateStrategy(IUpdateStrategy strat);

	/**
	 * @return gets the update strategy
	 */
	public IUpdateStrategy getUpdateStrategy();

	/**
	 * Sets the paint strategy
	 * @param strat the new paint strategy
	 */
	public void setPaintStrategy(IPaintStrategy strat);

	/**
	 * @return gets the paint strategy
	 */
	public IPaintStrategy getPaintStrategy();

	/**
	 * @return gets the view adapter
	 */
	public IModel2ViewAdapter getViewAdapter();

	/**
	 * Execute the given algorithm
	 * @param algo The algorithm to execute
	 */
	//public void execute(IBallAlgo algo);

	/**
	 * @return the dimensions of the canvas
	 */
	public IDimension getCanvas();

	/**
	 * @return the center of the ball
	 */
	public Point getLocation();

	/**
	 * @param location	A point representing the center of the ball	
	 */
	public void setLocation(Point location);

	/**
	 * @return the radius of the ball
	 */
	public int getRadius();

	/**
	 * @param radius	An int representing the radius of the ball
	 */
	public void setRadius(int radius);

	/**
	 * @return the velocity of the ball
	 */
	public Point getVelocity();

	/**
	 * @param velocity	A Point representing the velocity of the ball
	 */
	public void setVelocity(Point velocity);

	/**
	 * @return the color of the ball
	 */
	public Color getColor();

	/**
	 * @param color	A Color representing the color of the ball
	 */
	public void setColor(Color color);

	/**
	 * @return return the mass of ball
	 */
	public double getMass();

	/**
	 * @param mass set the mass of the ball
	 */
	public void setMass(double mass);

	/**
	 * @return a boolean representing whether the ball just bounced off the ground
	 */
	public boolean bouncedGround();

	/**
	 * @return a boolean representing whether the ball just bounced 
	 */
	public boolean bounced();

	/**
	 * The update method called by the main ball Dispatcher to notify all the balls to perform the given command.
	 * The given command is executed.
	 * @param disp The Dispatcher that sent the update request.
	 * @param cmd The IBallCmd that will be run.
	 */
	public void update(IDispatcher<IBallCmd> disp, IBallCmd cmd);

	/**
	 * Moves the ball
	 */
	public void move();

	/**
	 * Bounces the ball off the wall
	 */
	public void bounce();

	/**
	 * Paints the ball
	 * @param g	The Graphics object to paint on
	 */
	public void paint(Graphics g);

	/**
	 * Updates the state
	 * @param context       The ball to be updated
	 * @param dispatcher	An IDispatcher object representing the dispatcher
	 */
	public void updateState(IBall context, IDispatcher<IBallCmd> dispatcher);

	/**
	 * Send the command to realize interaction.
	 * @param other the other ball instace to be interacted with
	 * @param disp an IDispathcer object representing the dispatcher
	 * @return the command to interact
	 */
	public IBallCmd interactWithThen(IBall other, IDispatcher<IBallCmd> disp);

	/**
	 * Set the interact strategy.
	 * @param interactStrat an interact strategy
	 */
	public void setInteractStrategy(IInteractStrategy interactStrat);

	/**
	 * Get the interact strategy.
	 * @return the interact strategy of the ball
	 */
	public IInteractStrategy getInteractStrategy();

}