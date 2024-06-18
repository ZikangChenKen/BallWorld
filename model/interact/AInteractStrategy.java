package hw06.model.interact;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * AInteractStrategy is an abstract class that provides a default implementation
 * for the IInteractStrategy interface. It serves as a base class for all interaction
 * strategies that return a no-op IBallCmd in interactWithThen(), meaning that balls
 * that have interacted will not change any traits.
 */
public abstract class AInteractStrategy implements IInteractStrategy {

	/**
	 * Default constructor.
	 */
	public AInteractStrategy() {
		// TODO: Initialization logic if needed
	}

	/**
	 * Initializes the strategy for the given ball context.
	 * @param context The ball context.
	 */
	@Override
	public void init(IBall context) {
		// TODO: Initialization logic if needed
	}

	/**
	 * Defines the interaction behavior between the context ball and the target ball.
	 * This method calculates the interaction and then returns a command that performs
	 * the actual change. The command is run by the dispatcher.
	 * 
	 * @param context The context ball.
	 * @param target The target ball.
	 * @param disp The dispatcher.
	 * @return An IBallCmd object, which is a no-op command by default.
	 */
	@Override
	public IBallCmd interactWithThen(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
		this.interact(context, target, disp);
		return IBallCmd.NULL;  // Return a no-op command
	}

	/**
	 * Abstract method to be implemented by subclasses to define the specific interaction
	 * behavior between the context ball and the target ball.
	 * 
	 * @param context The context ball.
	 * @param target The target ball.
	 * @param disp The dispatcher.
	 */
	public abstract void interact(IBall context, IBall target, IDispatcher<IBallCmd> disp);
}

