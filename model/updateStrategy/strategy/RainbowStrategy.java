package hw06.model.updateStrategy.strategy;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import hw06.model.BallAlgo;
import hw06.model.ball.ABallAlgoCmd;
import hw06.model.ball.IBall;
import hw06.model.ball.IBallCmd;
import hw06.model.ball.ConcreteBall.RainbowBall;
import hw06.model.updateStrategy.IUpdateStrategy;
import provided.ballworld.extVisitors.IBallHostID;
import provided.utils.dispatcher.IDispatcher;

/**
 * The class that represents the rain bow strategy.
 */
public class RainbowStrategy implements IUpdateStrategy {

	/**
	 * 
	 */
	public RainbowStrategy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * radius of routine
	 */
	private double rad_circle = 10;
	/**
	 * angle
	 */
	private int angle = 0;
	
	/**
	 * Update the ball.
	 */
	
	@Override
	public void updateState(IBall context, IDispatcher<IBallCmd> disp) {
		context.execute(new BallAlgo<Void, Void>(new ABallAlgoCmd<>() {
		// Add generated serialVersionUID
					
			//private static final long serialVersionUID = 7793759701688998320L;

			/**
			 * 
			 */
			private static final long serialVersionUID = -6462398001288185810L;

			@Override
			public Void apply(IBallHostID index, IBall host, Void... params) {
				// no-op by default, i.e. non-PreyBalls will not have any interaction due to this strategy.
				return null;
			}
			
		}) {

			// Add generated serialVersionUID
			
			private static final long serialVersionUID = 4170609528565177902L;

			// Add additional commands in the "initializer block" of the ball algo's anonymous inner class
			{
				// Add different behavior for PreyBalls
				setCmd(RainbowBall.ID, new ABallAlgoCmd<Void, Void>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -6872489503957309744L;

					// Add generated serialVersionUID

					//private static final long serialVersionUID = -4557639566229337977L;

					@Override
					public Void apply(IBallHostID index, IBall host, Void... params) {
						double x = context.getCanvas().getWidth() / 2 + (rad_circle * Math.cos(Math.toRadians(angle)));
					    double y = context.getCanvas().getHeight() / 2 + (rad_circle * Math.sin(Math.toRadians(angle)));
					    
					    //context.setPos(x, y);
					   
					    
					    Random rand = new Random();
					    
					    context.setLocation(new Point((int)x, (int)y));
					    
					    int red = rand.nextInt(256);    // Random value between 0-255
				        int green = rand.nextInt(256);  // Random value between 0-255
				        int blue = rand.nextInt(256);   // Random value between 0-255
				        
				       
					    context.setColor(new Color(red, green, blue));
					    
					    angle += 5;
					    
					    if (rad_circle + context.getRadius() <= Math.min(context.getCanvas().getWidth() / 2, context.getCanvas().getHeight() / 2)) {
					    	rad_circle++;
					    } else {
					    	rad_circle =  Math.min(context.getCanvas().getWidth() / 2, context.getCanvas().getHeight() / 2) - context.getRadius();
					    }
						return null;
					}
				});
			
			}
	});
			
			
	        
		//}
	}

	@Override
	public void init(IBall context) {
		// TODO Auto-generated method stub

	}

}
