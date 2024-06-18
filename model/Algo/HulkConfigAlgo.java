package hw06.model.Algo;

import hw06.model.IBallAlgo2ModelAdapter;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.AConfigBallAlgo;
import hw06.model.ball.IBall;
import hw06.model.ball.IBallAlgo;
import hw06.model.ball.IBallCmd;
import hw06.model.interact.AInteractStrategy;
import provided.ballworld.extVisitors.IBallHostID;
import provided.logger.ILogger;
import provided.utils.dispatcher.IDispatcher;
import provided.utils.view.ValuesPanel;

/**
 * HulkConfigAlgo class extends AConfigBallAlgo and is responsible for configuring the HulkBallAlgo.
 * This class also provides a control panel to enable or disable the interaction.
 */
public class HulkConfigAlgo extends AConfigBallAlgo {
	
	/**
	 * serial ID 
	 */
	private static final long serialVersionUID = -8140082135026443978L;

	/**
	 * The current enabled status of all instances of the installed interact strategy.
	 */
	private boolean isEnabled = true;
	
	/**
	 * Constructor for HulkConfigAlgo.
	 * Initializes the algorithm with the given logger and adapter to the model.
	 * 
	 * @param logger The logger to use.
	 * @param algo2ModelAdpt The adapter to the model.
	 */
	public HulkConfigAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		super(logger, algo2ModelAdpt);

		// Set the default command
		this.setDefaultCmd(new ABallAlgoCmd<>() {
			
			// Auto-generated serialVersionUID for serialization
			private static final long serialVersionUID = 3877503369438837359L;

			/**
			 * Algorithm that turns host black depending on its type.
			 * Is tied to the global isEnabled boolean.
			 */
			IBallAlgo<Void, Void> interactAlgo = new HulkBallAlgo(() -> isEnabled);

			/**
			 * Applies the interaction strategy to the given ball host.
			 * 
			 * @param index The ID of the ball host.
			 * @param host The ball host itself.
			 * @param params Additional parameters (not used here).
			 * @return null
			 */
			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				installInteractStrategy(host, new AInteractStrategy() {
					@Override
					public void interact(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
						target.execute(interactAlgo);
					}
				});
				return null;
			}
		});

		// Add the control panel to the view
		algo2ModelAdpt.addConfigComponent("Hulk Interaction", () -> {
			ValuesPanel pnlValues = new ValuesPanel("Enable or disable the interaction.", logger);
			pnlValues.addBooleanInput("Status", "Enabled", isEnabled, (newVal) -> {
				isEnabled = newVal;
				return isEnabled;
			});
			return pnlValues;
		});
	}
	
	/**
	 * Returns the string representation of the algorithm.
	 * 
	 * @return The name of the algorithm.
	 */
	@Override
	public String toString() {
		return "Hulk";
	}
}

