package hw06.model.ball;

import java.awt.Graphics;

import hw06.model.BallAlgo;
import hw06.model.IBallAlgo2ModelAdapter;
import hw06.model.interact.IInteractStrategy;
import hw06.model.paint.IPaintStrategy;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.logger.ILogger;
import provided.logger.ILoggerControl;
import provided.utils.dispatcher.IDispatcher;

/**
 * Abstract implementation of a BallAlgo<Void, Void> that holds an adapter to the model.
 *
 */
public abstract class AConfigBallAlgo extends BallAlgo<Void, Void> implements IConfigBallAlgo {

	// Add generated serialVersionUID
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 3739402137978996899L;

	/**
	 * The adapter to the model in use.
	 */
	protected IBallAlgo2ModelAdapter algo2ModelAdpt = IBallAlgo2ModelAdapter.NULL;
	
	/**
	 * The logger in use
	 */
	protected ILogger logger = ILoggerControl.getSharedLogger();

	/**
	 * Constructs a configuration algorithm with a no-op default case and the given adapter to the model.
	 * Use this constructor when the default case command needs to reference instance fields and methods of 
	 * the configuration algorithm.   In that scenario, setDefaultCmd() must be explicitly called to 
	 * override the default no-op behavior of the default case.
	 * @param logger The logger to use
	 * @param algo2ModelAdpt The adapter to the model.
	 */
	public AConfigBallAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt ) {
		this(logger, algo2ModelAdpt , ABallAlgoCmd.MakeNull());
	}
	
	/**
	 * Constructs a configuration algorithm with a no-op default case and the given adapter to the model.
	 * Use this constructor when the default case command needs to reference instance fields and methods of 
	 * the configuration algorithm.   In that scenario, setDefaultCmd() must be explicitly called to 
	 * override the default no-op behavior of the default case.
	 * @param logger The logger to use
	 * @param name A friendly name for the toString() method to return.
	 * @param algo2ModelAdpt The adapter to the model.
	 */
	public AConfigBallAlgo(ILogger logger, String name, IBallAlgo2ModelAdapter algo2ModelAdpt ) {
		this(logger, name, algo2ModelAdpt , ABallAlgoCmd.MakeNull());
	}
	
	/**
	 * Constructs a configuration algorithm with the given default case and the given adapter to the model.
	 * @param logger The logger to use
	 * @param algo2ModelAdpt adapter to the model.
	 * @param defaultCmd The default case command
	 */
	public AConfigBallAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt , ABallAlgoCmd<Void, Void> defaultCmd) {
		super(defaultCmd);
		this.algo2ModelAdpt = algo2ModelAdpt ;
		this.logger = logger;
	}

	/**
	 * Constructs a configuration algorithm with the given default case and the given adapter to the model.
	 * @param logger The logger to use
	 * @param name A friendly name for the toString() method to return.
	 * @param algo2ModelAdpt adapter to the model.
	 * @param defaultCmd The default case command
	 */
	public AConfigBallAlgo(ILogger logger, String name, IBallAlgo2ModelAdapter algo2ModelAdpt , ABallAlgoCmd<Void, Void> defaultCmd) {
		super(name, defaultCmd);
		this.algo2ModelAdpt = algo2ModelAdpt ;
		this.logger = logger;
	}
	
	/**
	 * Accessor for the adapter to the model
	 * @return The adapter to the model
	 */
	protected IBallAlgo2ModelAdapter getAlgo2ModelAdpt() {
		return algo2ModelAdpt ;
	}
	

	/**
	 * Convenience method to install an update strategy into the given host ball.
	 * @param host  The host ball to install the given update strategy into
	 * @param newStrat The update strategy to install into the host ball
	 */
	protected void installUpdateStrategy(IBall host, IUpdateStrategy newStrat) {
		// Make composite with existing strategy
		host.setUpdateStrategy(new IUpdateStrategy() {
			IUpdateStrategy strat1 = host.getUpdateStrategy();
			IUpdateStrategy strat2 = newStrat;
			
			@Override
			public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
				strat1.updateState(context, disp);
				strat2.updateState(context, disp);
			}
	
			@Override
			public void init(IBall host) {
				strat1.init(host);
				strat2.init(host);
			}	
		});
	}

	/**
	 * Convenience method to install a paint strategy into the given host ball.
	 * @param host  The host ball to install the given paint strategy into
	 * @param newStrat The paint strategy to install into the host ball
	 */
	protected void installPaintStrategy(IBall host, IPaintStrategy newStrat) {
		// Make composite with existing strategy
		host.setPaintStrategy(new IPaintStrategy() {
			IPaintStrategy strat1 = host.getPaintStrategy();
			IPaintStrategy strat2 = newStrat;
			
			@Override
			public void paint(Graphics g, IBall host) {
				strat1.paint(g, host);
				strat2.paint(g, host);
			}
	
			@Override
			public void init(IBall host) {
				strat1.init(host);
				strat2.init(host);
			}
		});
	}	
	
	/**
	 * Convenience method to install an interact strategy into the given host ball.
	 * @param host  The host ball to install the given interact strategy into
	 * @param newStrat The interact strategy to install into the host ball
	 */
	protected void installInteractStrategy(IBall host, IInteractStrategy newStrat) {
		// Make composite with existing strategy
		host.setInteractStrategy(new IInteractStrategy() {
			IInteractStrategy strat1 = host.getInteractStrategy();
			IInteractStrategy strat2 = newStrat;
			
			@Override
			public IBallCmd interactWithThen(IBall host, IBall other, IDispatcher<IBallCmd> disp) {
				IBallCmd cmd1 = strat1.interactWithThen(host, other, disp);
				IBallCmd cmd2 = strat2.interactWithThen(host, other, disp);
				return new IBallCmd() {
					@Override
					public void apply(IBall host, IDispatcher<IBallCmd> disp) {
						cmd1.apply(host, disp);
						cmd2.apply(host, disp);
					}
				};		
			}
	
			@Override
			public void init(IBall host) {
				strat1.init(host);
				strat2.init(host);
			}		
		});
	}

}
