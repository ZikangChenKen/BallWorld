package hw06.controller;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.function.Supplier;

import javax.swing.JComponent;

import hw06.model.*;
import hw06.model.ball.IBallAlgo;
import hw06.model.ball.ConcreteBall.IConcreteBallFactory;
import hw06.view.*;
import provided.utils.displayModel.IATImage;
import provided.utils.displayModel.IDimension;
import provided.utils.view.TabbedFrame;

/**
 * BallWorldController class representing the controller
 *
 */
public class BallWorldController {
	/**
	 * Model of BallWorld
	 */
	private BallWorldModel model;

	/**
	 * Model of BallWorld
	 */
	private BallWorldView<IBallAlgo<Void, Void>, IConcreteBallFactory> view;

	/**
	 * The config algo control frame.
	 */
	private TabbedFrame algoControlFrame = new TabbedFrame("Algo Control Frame");

	/**
	 * Constructor for BallWorldController
	 */
	public BallWorldController() {
		model = new BallWorldModel(new IModel2ViewAdapter() {

			public IDimension getCanvasDim() {
				return new IDimension() { // Creates dimension from width and height of canvas
					public int getWidth() {
						return view.getCanvas().getWidth();
					}

					public int getHeight() {
						return view.getCanvas().getHeight();
					}
				};
			}

			@Override
			public void repaint() { // Repaints canvas
				view.getCanvas().repaint();
			}

			/**
			 * Return an IATImage that wraps the given Image object
			 * and the ball's canvas from the view.
			 * @param image The Image object to imbed in the IATImage instance
			 * @return An IATImage instance
			 */
			public IATImage getIATImage(Image image) {
				return IATImage.FACTORY.apply(image, view.getCanvas());
			}

		}, new IBallAlgo2ModelAdapter(){

			@Override
			public void addConfigComponent(String label, Supplier<JComponent> compFac) {
				// TODO Auto-generated method stub
				algoControlFrame.addComponentFac(label, compFac);
				
			}
			
		});

		view = new BallWorldView<IBallAlgo<Void, Void>, IConcreteBallFactory>(new IView2ModelAdapter<IBallAlgo<Void, Void>, IConcreteBallFactory>() {

			@Override
			/**
			 * Method for clearing the balls on the screen.
			 */
			public void clearBalls() { // Clears balls
				model.clearBalls();
			}

			@Override
			/**
			  * Add a ball to the system with a strategy asgiven by the given IStrategyFac
			  * @param selectedItem The fully qualified name of the desired strategy.
			  */
			public void loadBall(IBallAlgo<Void,Void> selectedItem, IConcreteBallFactory selectedItem2) { // Loads balls
				if (null != selectedItem)
					model.makeBall(selectedItem,selectedItem2);
			}

			@Override
			/**
			 * Paints the balls every tick
			 * @param g the graphics object used to paint the balls
			 */
			public void paintBalls(Graphics g) { // Paints balls
				model.update(g);
			}

			@Override
			/**
			  * Returns an IStrategyFac that can instantiate the strategy specified
			  * by classname. Returns null if classname is null. The toString() of
			  * the returned factory is the classname.
			  * @param classname  Shortened name of desired strategy
			  * @return A factory to make that strategy
			  */
			public IBallAlgo<Void, Void> addUpdateStrategy(String classname) {
				// TODO Auto-generated method stub
				return model.makeStrategyAlgo(classname);
			}

			public IBallAlgo<Void, Void> addPaintStrategy(String classname) {
				return model.makePaintStrategyAlgo(classname);
			}

			public IBallAlgo<Void, Void> addInteractStrategy(String classname) {
				return model.makeInteractStrategyAlgo(classname);
			}
			
			@Override
			public IBallAlgo<Void, Void> addBallAlgo(String classname) {
				return model.makeBallAlgo(classname); //add to list 
			
			}
			
			public IConcreteBallFactory addBallType(String classname) {
				return model.makeBallFac(classname);
			}
			
			

			@Override
			/**
			 * Returns an IStrategyFac that can instantiate a MultiStrategy with the
			 * two strategies made by the two given IStrategyFac objects. Returns
			 * null if either supplied factory is null. The toString() of the
			 * returned factory is the toString()'s of the two given factories,
			 * concatenated with "-".             
			 * 
			 * @param selectedItem1 An IStrategyFac for a strategy
			 * @param selectedItem2 An IStrategyFac for a strategy
			 * @return An IStrategyFac for the composition of the two strategies
			 */
			public IBallAlgo<Void, Void> combineStrategies(IBallAlgo<Void,Void> selectedItem1, IBallAlgo<Void,Void> selectedItem2) {
				// TODO Auto-generated method stub
				return model.combineStrategyAlgos(selectedItem1, selectedItem2);
			}
		
			/*
			@Override
			
			 * Method for switching the strategy of all switcher balls
			 * 
			 * @param selectedItem the selected algorithm
			*/
			public void switchStrategy(IBallAlgo<Void,Void> selectedItem, IConcreteBallFactory ballFactory) {
				model.switchSwitcherStrategy(selectedItem, ballFactory);
			}
			
			@Override
			/**
			 * Method for loading a switcher ball
			 */
			public void loadSwitcher(IConcreteBallFactory ballFactory) {
				model.makeSwitcherBall();
			}

			

		});
	}

	/**
	 * Starts the application
	 */
	public void start() {
		model.start();
		view.start();
		this.algoControlFrame.start();
	}

	/**
	 * Launch the application.
	 * @param args for main method
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BallWorldController controller = new BallWorldController();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
