package hw06.model;

import java.awt.*;

import java.util.function.Supplier;

import javax.swing.Timer;

import hw06.model.ball.*;
import hw06.model.ball.ConcreteBall.IConcreteBallFactory;
import hw06.model.interact.IInteractStrategy;
import hw06.model.interact.MultiInteractStrategy;
import hw06.model.interact.strategy.ErrorInteractStrategy;
import hw06.model.paint.*;
import hw06.model.paint.strategy.BallPaintStrategy;
import hw06.model.paint.strategy.ErrorPaintStrategy;
import hw06.model.updateStrategy.IUpdateStrategy;
import hw06.model.updateStrategy.MultiStrategy;
import hw06.model.updateStrategy.strategy.*;
//import provided.logger.ILogger;
import provided.ballworld.extVisitors.IBallHostID;
//import provided.logger.ILoggerControl;
import provided.logger.impl.Logger;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.dispatcher.impl.SequentialDispatcher;
import provided.utils.displayModel.IDimension;
import provided.utils.loader.IObjectLoader;
import provided.utils.loader.impl.ObjectLoader;
import provided.utils.valueGenerator.IRandomizer;
import provided.utils.valueGenerator.impl.Randomizer;

/**
 * BallWorldModel class representing the model 
 *
 */
public class BallWorldModel {

	/**
	 * Adapter that allows the model to talk to the view
	 */
	private IModel2ViewAdapter viewAdpt = IModel2ViewAdapter.NULL;

	// Adapter for the algo.
	/**
	 * algorithm adapter 
	 */
	private IBallAlgo2ModelAdapter algoAdpt = IBallAlgo2ModelAdapter.NULL;
	
	/**
	 * The dimension of the canvas
	 */
	private IDimension dim;

	/**
	 * Dynamic class loader for update strategies
	 */
	private IObjectLoader<IUpdateStrategy> loader = new ObjectLoader<IUpdateStrategy>(
			(String, args) -> new ErrorStrategy());

	/**
	 * Dynamic class loader of paint strategies
	 */
	private IObjectLoader<IPaintStrategy> paintLoader = new ObjectLoader<IPaintStrategy>(
			(String, args) -> new ErrorPaintStrategy());

	/**
	 * Dynamic class loader of interact strategies
	 */
	private IObjectLoader<IInteractStrategy> interactLoader = new ObjectLoader<IInteractStrategy>(
			(String, args) -> new ErrorInteractStrategy());
	
	/**
	 * Dynamic class loader of ball type.
	 */
	private IObjectLoader<IBall> ballLoader = new ObjectLoader<IBall>((String, args) -> IBall.NULL_OBJECT);

	
	/** 
	 * Ball Algo loader 
	 * */
	private IObjectLoader<IBallAlgo<Void, Void>> algoLoader = new ObjectLoader<IBallAlgo<Void, Void>>(
			(String, args) -> new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 633040558203533146L;

				@Override
				public Void apply(IBallHostID index, IBall host, Void... params) {
					// TODO Auto-generated method stub
					return null;
				}
				
			}));

	/**
	 * Dispatcher to the balls
	 */
	private IDispatcher<IBallCmd> dispatcher = new SequentialDispatcher<IBallCmd>();

	
	/** 
	 * Supplier that controls the panel 
	 */
	@SuppressWarnings("unused")
	private Supplier<Boolean> isEnabledFn = new Supplier<Boolean>() {public Boolean get() {return true;}};
	
	/**
	 * Painting time delay in ms
	 */
	private int timeSlice = 50;

	/**
	 * Timer for painting
	 */
	private Timer timer = new Timer(timeSlice, (e) -> viewAdpt.repaint());

	/**
	 * Randomizer to generate random values
	 */
	private IRandomizer rand = Randomizer.Singleton;

//	/**
//	 * Max initial radius of a ball
//	 */
//	private int maxRadius = 40;
//
//	/**
//	 * Min initial radius of a ball
//	 */
//	private int minRadius = 10;

//	/**
//	 * Max initial speed of a ball
//	 */
//	private int maxSpeed = 20;

//	/**
//	 * Bounds for a ball's initial velocity
//	 */
//	private Rectangle maxVel = new Rectangle(-maxSpeed, -maxSpeed, maxSpeed, maxSpeed);

	/**
	 * Constructor for the model
	 * @param viewAdpt	The adapter to the view
	 * @param algoAdpt the adapter to algo
	 */
	public BallWorldModel(IModel2ViewAdapter viewAdpt, IBallAlgo2ModelAdapter algoAdpt) {
		this.viewAdpt = viewAdpt;
		this.algoAdpt = algoAdpt;
		this.dim = viewAdpt.getCanvasDim();
	}

	
	
	
	
	/**
	 * Makes a ball
	 * @param ballAlgo the installation algo of the ball
	 * @param ballFactory ball factory 
	 */
	public void makeBall(IBallAlgo<Void, Void> ballAlgo,IConcreteBallFactory ballFactory) {
	
		
		IBall concreteBall = ballFactory.make(ballAlgo);
		
		dispatcher.addObserver(concreteBall);
		/*
		dispatcher.addObserver(new DefaultBall(
				rand.randomLoc(new Dimension(viewAdpt.getCanvasDim().getWidth(), viewAdpt.getCanvasDim().getHeight())),
				rand.randomInt(minRadius, maxRadius), rand.randomVel(maxVel), rand.randomColor(),
				viewAdpt.getCanvasDim(), viewAdpt, ballAlgo));*/
	}
	
	/**
	 * 
	 * @param classname classname 
	 * @return a ball factory 
	 */
	public IConcreteBallFactory makeBallFac(String classname) {
		return new IConcreteBallFactory() {

			/**
			 * Return the given class name string
			 */
			public String toString() {
				return classname;
			}

			@Override
			public IBall make(IBallAlgo<Void, Void> configAlgo) {
				// Randomly generated parameters
				System.out.println(classname + "ball made: in model's makeBallFac().make()");
				int radius = rand.randomInt(10, 30);
				Color color = rand.randomColor();
				Point velocity = new Point(rand.randomInt(-20, 20), rand.randomInt(-20, 20));
				Point location = new Point(rand.randomInt(radius, dim.getWidth() - radius),
						rand.randomInt(radius, dim.getHeight() - radius));
				return ballLoader.loadInstance("hw06.model.ball.ConcreteBall." + classname + "Ball", location,
						radius, velocity, color, dim, viewAdpt, configAlgo);
			}
		};
	}

	/**
	 * Makes an update strategy of the given classname 
	 * @param classname the name of the update strategy
	 * @return the corresponding IBallAlgo with the update strategy
	 */
	public IBallAlgo<Void, Void> makeStrategyAlgo(final String classname) {
		return new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>() {
			private static final long serialVersionUID = 913238296732442247L;
			IUpdateStrategy strat;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// TODO Auto-generated method stub
				try {
					Class.forName("hw06.model.updateStrategy.strategy." + classname + "Strategy");
					strat = loader.loadInstance("hw06.model.updateStrategy.strategy." + classname + "Strategy");
				} catch (ClassNotFoundException e) {
					System.out.println("Error! Update Strategy " + classname
							+ " not found. The error update strategy was used instead.");
					strat = new ErrorStrategy();
				}
				host.setUpdateStrategy(new MultiStrategy(host.getUpdateStrategy(), strat));
				return null;
			}
			
		}) {
			private static final long serialVersionUID = 7567611226096593685L;

			@Override
			/**
			 * Converts to string
			 */
			public String toString() {
				return classname;
			}
		};
	}

	/**
	 * Combines the two given IBallAlgos into one
	 * @param ballAlgo1 first IBallAlgo
	 * @param ballAlgo2 second IBallAlgo
	 * @return new IBallAlgo containing both algos
	 */
	public IBallAlgo<Void, Void> combineStrategyAlgos(IBallAlgo<Void, Void> ballAlgo1, IBallAlgo<Void, Void> ballAlgo2) {
		if (null == ballAlgo1 || null == ballAlgo2)
			return null;
		return new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>() {

			private static final long serialVersionUID = 31572527133326313L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// TODO Auto-generated method stub
				// Always delegate to the host to enable type-dependent processing of the algorithm
				host.execute(ballAlgo1);
				host.execute(ballAlgo2);
				return null;
			}
			
		}) {

			private static final long serialVersionUID = 5627190071434350487L;

			@Override
			public String toString() {
				return ballAlgo1.toString() + "-" + ballAlgo2.toString();
			}
		};
	}

	/**
	 * Checks if the class exists and returns an error strategy if it does not
	 * @param classname the name of the paint strategy
	 * @return the corresponding IPaintStrategy
	 */
	public IPaintStrategy loadPaintStrategy(final String classname) {
		try {
			Class.forName("hw06.model.paint.strategy." + classname + "PaintStrategy");
			return paintLoader.loadInstance("hw06.model.paint.strategy." + classname + "PaintStrategy");
		} catch (ClassNotFoundException cnfe) {
			System.out.println(
					"Error! Paint Strategy " + classname + " not found. The error paint strategy was used instead.");
			return new ErrorPaintStrategy();
		}
	}

	/**
	 * Makes an paint strategy of the given classname 
	 * @param classname the name of the paint strategy
	 * @return the corresponding IBallAlgo with the paint strategy
	 */
	public IBallAlgo<Void, Void> makePaintStrategyAlgo(final String classname) {

		return new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>() {

			private static final long serialVersionUID = -6535820677942500512L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// TODO Auto-generated method stub
				// Want generic composite paint strategy here, not MultiPaintStrategy which is specifically an Affine transform composite.
				host.setPaintStrategy(new IPaintStrategy() {
					IPaintStrategy paintStrat1 = host.getPaintStrategy(); // Save the host's current paint strategy
					IPaintStrategy paintStrat2 = loadPaintStrategy(classname);

					@Override
					public void paint(Graphics g, IBall host) {
						// Delegate to each composee
						paintStrat1.paint(g, host);
						paintStrat2.paint(g, host);
					}

					@Override
					public void init(IBall host) {
						// Delegate to each composee
						paintStrat1.init(host);
						paintStrat2.init(host);
					}

				});
				return null;
			}
			
		}) {

			private static final long serialVersionUID = -8327089465818822849L;

			/**
			 * Return the given class name string
			 */
			public String toString() {
				return classname;
			}
		};
	}

	/**
	 * Load the interact strategy.
	 * @param classname a string representing the interact strategy
	 * @return an interact strategy
	 */
	public IInteractStrategy loadInteractStrategy(final String classname) {
		try {
			Class.forName("hw06.model.interact.strategy." + classname + "InteractStrategy");
			return interactLoader.loadInstance("hw06.model.interact.strategy." + classname + "InteractStrategy");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error! Interact Strategy " + classname
					+ " not found. The error interact strategy was used instead.");
			return new ErrorInteractStrategy();
		}
	}

	/**
	 * Make an interact strategy algorithm for the given classname.
	 * @param classname a string representing the interact strategy
	 * @return an algorithm with the interact strategy
	 */
	public IBallAlgo<Void, Void> makeInteractStrategyAlgo(final String classname) {
		return new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>(){

			private static final long serialVersionUID = 5025512154037785981L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// TODO Auto-generated method stub
				host.setInteractStrategy(
						new MultiInteractStrategy(host.getInteractStrategy(), loadInteractStrategy(classname)));
				return null;
			}
			
		}) {

			private static final long serialVersionUID = 6751032091160236912L;

			/**
			 * Return the given class name string
			 */
			public String toString() {
				return classname;
			}
		};
	}
	
	
	
	
	/**
	 * @param classname classname 
	 * @return a ball algo
	 */
	public IBallAlgo<Void, Void> makeBallAlgo(final String classname) {
		
		//public static final Logger  = new Logger();
		return algoLoader.loadInstance("hw06.model.Algo." + classname + "ConfigAlgo", Logger.SHARED, algoAdpt );
	
	}

	/**
	 * Updates the ball
	 * @param g The Graphics object to paint on
	 */
	public void update(Graphics g) {
		dispatcher.updateAll(new IBallCmd() {

			@Override
			public void apply(IBall context, IDispatcher<IBallCmd> disp) {
				// TODO Auto-generated method stub
				context.updateState(context, disp);
				context.move();
				context.bounce();
				context.paint(g);
			}

		});
	}

	/**
	 * Clears all balls
	 */
	public void clearBalls() {
		dispatcher.removeAllObservers();
	}

	/**
	 * An IBallAlgo that clears the balls strategies.
	 */
	private IBallAlgo<Void, Void> clearStrategiesAlgo = new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>() {

				private static final long serialVersionUID = 633040558203533146L;

				@Override
				public Void apply(IBallHostID index, IBall host, Void... params) {
					// TODO Auto-generated method stub
					host.setUpdateStrategy(IUpdateStrategy.NULL);
					host.setPaintStrategy(IPaintStrategy.NULL);
					host.setInteractStrategy(IInteractStrategy.NULL);
					return null;
				}
		
	}) {

		private static final long serialVersionUID = -1269013682581088572L;


	};

	// SWITCHERS

	/**
	 * Dummy ball that holds the strategies for the switcher strategies. 
	 */
	private IBall switcherDummyBall;

	/**
	 * The one switcher update strategy instance in the system. Allows all balls made with this strategy to be controlled at once.
	 */
	private IUpdateStrategy switcherUpdateStrategy = new IUpdateStrategy() {

		@Override
		public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
			// delegate to the strategy in the dummy ball
			switcherDummyBall.getUpdateStrategy().updateState(context, disp);
		}

		@Override
		public void init(IBall context) {
			switcherDummyBall.getUpdateStrategy().init(context);
		}
	};

	/**
	 * The one switcher paint strategy instance in the system. Allows all balls made with this strategy to be controlled at once.
	 */
	private IPaintStrategy switcherPaintStrategy = new IPaintStrategy() {

		@Override
		public void paint(Graphics g, IBall host) {
			// Delegate to the strategy in the dummy ball
			switcherDummyBall.getPaintStrategy().paint(g, host);
		}

		@Override
		public void init(IBall context) {
			switcherDummyBall.getPaintStrategy().init(context);
		}
	};

	/**
	 * The one switcher interact strategy instance in the system. Allows all balls made with this strategy to be controlled at once.
	 */
	private IInteractStrategy switcherInteractStrategy = new IInteractStrategy() {

		@Override
		public IBallCmd interactWithThen(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
			return switcherDummyBall.getInteractStrategy().interactWithThen(context, target, disp);
		}

		@Override
		public void init(IBall context) {
			// TODO Auto-generated method stub

		}

	};

	/**
	 * The algo used to install switcher strategies into a host ball.
	 */
	private IBallAlgo<Void, Void> switcherInstallAlgo = new BallAlgo<Void, Void>(new ABallAlgoCmd<Void, Void>() {

		private static final long serialVersionUID = -4691755557614417857L;

		@Override
		public Void apply(IBallHostID index, IBall host, Void... params) {
			// TODO Auto-generated method stub
			// Want to replace the existing strategies, so don't compose the switcher strategies with the existing ones.
			// Could have used anonymous inner classes for the switcher strategies being installed here instead of using shared, pre-instantiated objects.
			host.setUpdateStrategy(switcherUpdateStrategy);
			host.setPaintStrategy(switcherPaintStrategy);
			host.setInteractStrategy(switcherInteractStrategy);
			return null;
		}
		
	}) {

		private static final long serialVersionUID = -940340142388376133L;

	};

	//private 
	/**
	 * Getter for the algorithm to install switcher strategies into a host ball
	 * @return the switcher installation algo
	 */
	public IBallAlgo<Void, Void> getSwitcherInstallAlgo() {
		return this.switcherInstallAlgo;
	}

	/*
	 * Makes a ball with using the switcher configuration algorithm.
	*/
	/**
	 * make switcher ball 
	 */
	public void makeSwitcherBall() {
	//	makeBall(getSwitcherInstallAlgo());
	}

	/**
	 * Change the decoree strategies in the dummy ball using the given algorithm
	 * @param decoreeInstallAlgo the algorithm to install new decoree strategies into a ball
	 * @param ballFactory ball factory 
	 */
	public void switchSwitcherStrategy(IBallAlgo<Void, Void> decoreeInstallAlgo, IConcreteBallFactory ballFactory) {
		switcherDummyBall.execute(clearStrategiesAlgo); // clear the installed strategies b/c the incoming algo will compose with the existing ones.
		switcherDummyBall.execute(decoreeInstallAlgo); // Install the new decoree strategies, which will be composed with the now null/no-op existing strategies in the dummy ball.
	}

	/**
	 * Starts the model and timer
	 */
	public void start() {
		this.switcherDummyBall = new DefaultBall(null, 0, null, null, null, this.viewAdpt, new BallAlgo<Void, Void>(
				new ABallAlgoCmd<Void, Void>(){

					private static final long serialVersionUID = 2083944615856946336L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						// TODO Auto-generated method stub
						host.execute(clearStrategiesAlgo); // reset all the strategies to their null objects.
						host.setPaintStrategy(new BallPaintStrategy()); // default the painting to Ball at the beginning
						return null;
					}
					
				}) {

					private static final long serialVersionUID = 7781887689205963889L;
		});

		timer.start();
	}
}
