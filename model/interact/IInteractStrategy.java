package hw06.model.interact;

import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import provided.utils.dispatcher.IDispatcher;

/**
 * The interface for an interact strategy.
 *
 */
public interface IInteractStrategy {

	/**
	 * Default no-op initialization of the interact strategy. 
	 * While the semantics of a concrete method here are indeed problematic,
	 * defining an overridable default no-op method is a very common practice 
	 * for practical coding reasons.
	 * @param context   The context ball.
	 */
	public void init(IBall context);

	/**
	 * Performs a directed interaction between the context ball and the target Ball from the 
	 * perspective of the context Ball.
	 * @param context  The Ball from whose perspective the interaction 
	 * processing takes place.
	 * @param target  The Ball that is the "other ball" in the perspective of this processing.
	 * @param disp  The Dispatcher that is to be used if desired.
	 * @return A command to be executed after both ball's interaction behaviors have completed.   
	 */
	public IBallCmd interactWithThen(IBall context, IBall target, IDispatcher<IBallCmd> disp);

	/**
	 * Null strategy with no-op behavior.
	 */
	public static final IInteractStrategy NULL = new IInteractStrategy() {

		@Override
		public void init(IBall context) {
		}

		@Override
		public IBallCmd interactWithThen(IBall context, IBall target, IDispatcher<IBallCmd> disp) {
			return IBallCmd.NULL;
		}

	};
}
