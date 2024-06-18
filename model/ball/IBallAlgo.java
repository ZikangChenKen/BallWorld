package hw06.model.ball;

import provided.ballworld.extVisitors.IBallHostAlgo;

/**
 * An algorithm to process a host ball
 * @param <R> return type 
 * @param <P> parameter 
 */
public interface IBallAlgo<R,P> extends IBallHostAlgo<R,P,IBall> {

	/**
	 * The default case process
	 * @param host The host ball to process.
	 */
	//public void caseDefault(IBall host);
}
