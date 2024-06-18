package hw06.model.Algo;
import hw06.model.IBallAlgo2ModelAdapter;

//import hw06.model.Algo.TurnBlackBallAlgo;
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
 * Configuration algorithm to install a dynamically controllable interact strategy that turns the target ball black.
 * 
 *
 *
 * algorithm to model adapter 
 */
public class TurnBlackConfigAlgo extends AConfigBallAlgo { 
// AConfigBallAlgo is just a subclass of BallAlgo that provides services for installing various strategy types
// and has protected fields to hold the ILogger and IBallAlgo2ModelAdapter 
	
	
	/**
	 * serial ID 
	 */
	private static final long serialVersionUID = 3750987105613912817L;
	/**
	 * The current enabled status of all instances of the installed interact strategy.
	 */
	private boolean isEnabled = true;
	
	/**
	 * Constructs the algorithm with the given adapter
	 * @param logger The logger to use
	 * @param algo2ModelAdpt The adapter to the model
	 */
	public TurnBlackConfigAlgo(ILogger logger, IBallAlgo2ModelAdapter algo2ModelAdpt) {
		super(logger, algo2ModelAdpt);  
        
		// Must set the default command explicitly because an object that references 
		// the class's instance methods and fields cannot be passed through the super() call.
		this.setDefaultCmd(new ABallAlgoCmd<>() {
			
			// Need to add generated serialVersionUID
			
			private static final long serialVersionUID = -9086611782614790207L;
			/**
			 * Algorithm that turns host black depending on its type.
			 * Is tied to the global isEnabled boolean.
			 */
			IBallAlgo<Void, Void> interactAlgo = new TurnBlackBallAlgo(()->isEnabled);

			@Override
			/**
			 * installs the interact strategy to apply to the ball
			 * @param index the ballhost ID
			 * @param host an IBall
			 * @param params parameters 
			 * @return Void type
			 */
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// Inherited AConfigBallAlgo.installInteractStrategy() creates the composite with the existing strategy.           
				installInteractStrategy(host, new AInteractStrategy() {
					// AInteractStrategy.interactWithThen() delegates to interact() below and returns a no-op IBallCmd.                
					@Override
					public void interact(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
						// TODO Auto-generated method stub
						target.execute(interactAlgo); 
					}
				});
				return null;
			}});
		
		// Add the control panel to the view
		algo2ModelAdpt.addConfigComponent("TurnBlack Interaction", ()->{
			// Panel is instantiated INSIDE of the factory to ensure that is created on the GUI thread!
			ValuesPanel pnlValues =  new ValuesPanel("Enable or disable the interaction.", logger);
			pnlValues.addBooleanInput("Status","Enabled", isEnabled, (newVal)->{
				isEnabled = newVal; // No validation being done here.
				return isEnabled;  // Return the current value 		
			});			
			
			return pnlValues;  // Return the control panel to be displayed
		});
	}
	
	@Override
	public String toString() {
		return "TurnBlack";
	}

}
