package hw06.model.ball;

import provided.ballworld.extVisitors.IBallHostID;
import provided.ballworld.extVisitors.impl.ABallHostAlgoCmd;
import provided.logger.ILoggerControl;
import provided.logger.LogLevel;

/**
 * The abstract ball algo command class.
 * @param <R> return type 
 * @param <P> parameter 
 */
public abstract class ABallAlgoCmd<R,P> extends ABallHostAlgoCmd<R, P, IBall> {
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -1521704222511794377L;
    
		/**
		 * Static factory to create a null cmd that matches the generic typing of the target variable.
		 * @param <TReturn> The return type of the null cmd
		 * @param <TParam> The input parameter type of the null cmd
		 * @return Always returns null
		 */
		public static final <TReturn, TParam> ABallAlgoCmd<TReturn, TParam> MakeNull(){
			return new ABallAlgoCmd<>() {
				/**
				 * generated serial ID 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public TReturn apply(IBallHostID index, IBall host, @SuppressWarnings("unchecked") TParam... params) {
					ILoggerControl.getSharedLogger().log(LogLevel.DEBUG, "ABallAlgoCmd.NULL invoked.  Returned null.");
					return null;
				}
				
			};
		}
		
		/**
		 * Static factory to create a error cmd that matches the generic typing of the target variable.
		 * @param <TReturn> The return type of the null cmd
		 * @param <TParam> The input parameter type of the null cmd
		 * @param errMsg An error message to display when the error algo is executed, e.g. the erroneous classname that lead to the error. 
		 * @return Always returns null
		 */
		public static final <TReturn, TParam> ABallAlgoCmd<TReturn, TParam> MakeError(String errMsg){
			return new ABallAlgoCmd<>() {

				// Add generated serialVersionUID

				private static final long serialVersionUID = -8784500628768485371L;

				@Override
				public TReturn apply(IBallHostID index, IBall host, @SuppressWarnings("unchecked") TParam... params) {
					ILoggerControl.getSharedLogger().log(LogLevel.ERROR, "ABallAlgoCmd.ERROR invoked.  Returned null. Error msg: "+errMsg);
					return null;
				}
				
			};
		}  
		
		/**
		 * The method that performs the command's processing of the host
		 * @param index The host's identifying index that was used to invoke this command
		 * @param host The host that invoked the visitor with this command installed.
		 * @param params The input parameters to the execution of the visitor
		 * @return The return value of this command's processing of the host 
		 */
		abstract public R apply(IBallHostID index, IBall host, @SuppressWarnings("unchecked") P... params);
}
