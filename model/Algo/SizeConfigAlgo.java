package hw06.model.Algo;

import hw06.model.IBallAlgo2ModelAdapter;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.AConfigBallAlgo;
import hw06.model.ball.IBall;
import hw06.model.ball.IBallAlgo;
import hw06.model.ball.IBallCmd;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILogger;
import provided.logger.ILoggerControl;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.view.ValuesPanel;

/**
 * The Config algo that turns the ball to become bigger and green
 */
public class SizeConfigAlgo extends AConfigBallAlgo{
	
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1119511296058349637L;

	/**
	 * Constructor for ChangeSizeConfigAlgo
	 * @param adpt A ballalgo to model adapter
	 */
	public SizeConfigAlgo(IBallAlgo2ModelAdapter adpt) {
		this(ILoggerControl.getSharedLogger(), adpt);
	}

	/**
	 * The current enabled status of all instances of the installed update strategy.
	 */
	private boolean isEnabled = true;
	
	/**
	 * The current size of all instanced of the installed update strategy
	 */
	private int size = 10;

	/**
	 * Constructs the algo with the given adapter
	 * @param logger The logger to use
	 * @param algo2ModelAdpt The adapter to the model
	 */
	public SizeConfigAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		super(logger, algo2ModelAdpt);

		// Must set the default command explicitly because an object that references 
		// the class's instance methods and fields cannot be passed through the super() call.
		this.setDefaultCmd(new ABallAlgoCmd<>() {
			/**
			 * Serial version id.
			 */
			private static final long serialVersionUID = 3792399440108701987L;
			/**
			 * Algorithm that turns host black depending on its type.
			 * Is tied to the global isEnabled boolean.
			 */
			IBallAlgo<Void, Void> updateAlgo = new SizeBallAlgo(() -> isEnabled, () -> size);

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// Inherited AConfigBallAlgo.installInteractStrategy() creates the composite with the existing strategy.           
				installUpdateStrategy(host, new IUpdateStrategy() {

					@Override
					public void init(IBall context) {
						// TODO Auto-generated method stub

					}

					@Override
					public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
						// TODO Auto-generated method stub
						context.execute(updateAlgo);
					}

				});

				return null;
			}

		});

		// Add the control panel to the view
		algo2ModelAdpt.addConfigComponent("ChangeSize Interaction", () -> {

			// Panel is instantiated INSIDE of the factory to ensure that is created on the GUI thread!
			ValuesPanel pnlValues = new ValuesPanel("Enable or disable the interaction.", logger);

			pnlValues.addBooleanInput("Status", "Enabled", isEnabled, (newVal) -> {
				isEnabled = newVal; // No validation being done here.
				return isEnabled; // Return the current value 		
			});

			pnlValues.addIntegerInput("size", 0, (newVal) -> {
				this.size = newVal;
				return newVal;
			});

			pnlValues.setVisible(true);
			return pnlValues; // Return the control panel to be displayed

		});
		System.out.println("got here");
	}

	public String toString() {
		return "Size";
	}

}
