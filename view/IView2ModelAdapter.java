package hw06.view;

import java.awt.Graphics;

/**
 * An interface for an adapter from the view to the model
 * @author Teresa Zhou;
 *
 * @param <TDropListItem> the type of objects in the drop list in the view
 * @param <TDropListItem2> the type of objects in the drop list in the model 
 */
public interface IView2ModelAdapter<TDropListItem, TDropListItem2> {

	/**
	 * Clears balls
	 */
	public void clearBalls();

	/**
	 * Loads a ball with the selected IBallAlgo
	 * @param selectedItem the selected IBallAlgo
	 * @param selectedItem2 the selected ball type 
	 */
	public void loadBall(TDropListItem selectedItem, TDropListItem2 selectedItem2);

	/**
	 * Paints the balls
	 * @param g	The Graphics object to paint on
	 */
	public void paintBalls(Graphics g);

	/**
	 * Returns the strategy corresponding to classname
	 * @param classname a string representing the strategy
	 * @return an IBallAlgo with the corresponding strategy
	 */
	public TDropListItem addUpdateStrategy(String classname);

	/**
	 * Combines the two given strategies
	 * @param selectedItem1 the first selected strategy
	 * @param selectedItem2 the second selected strategy
	 * @return the new combined strategy
	 */
	public TDropListItem combineStrategies(TDropListItem selectedItem1, TDropListItem selectedItem2);

	/**
	 * Returns the strategy corresponding to classname
	 * @param classname a string representing the strategy
	 * @return an IBallAlgo with the corresponding paint strategy
	 */
	public TDropListItem addPaintStrategy(String classname);

	/**
	 * Returns the interact strategy
	 * @param classname a string representing the strategy
	 * @return an IBallAlgo with the corresponding interact strategy
	 */
	public TDropListItem addInteractStrategy(String classname);
	
	/**
	 * Returns the ball type
	 * @param classname string representing the type
	 * @return the corresponding ball
	 */
	public TDropListItem2 addBallType(String classname);
	
	/**
	 * Returns the algorithm
	 * @param classname string representing the algorithm type 
	 * @return the corresponding ball algorithm 
	 */
	public TDropListItem addBallAlgo(String classname);

	/**
	 * Switches the switcher strategy to the selected strategy
	 * @param selectedItem the new strategy
	 * @param selectedItem2 ball type
	 */
	public void switchStrategy(TDropListItem selectedItem, TDropListItem2 selectedItem2);

	/**
	 * Loads a switcher ball onto the canvas
	 * @param selectedItem ball type 
	 */
	public void loadSwitcher(TDropListItem2 selectedItem);

	
	/**
	 * Constructs a null adapter to the model
	 */
	public static final IView2ModelAdapter<Object, Object> NULL = new IView2ModelAdapter<Object, Object>() {
		public void paintBalls(Graphics g) {
		}

		public void clearBalls() {
		}

		public void loadBall(Object selectedItem, Object selectedItem2) {
		}

		public Object addUpdateStrategy(String classname) {
			return null;
		}

		public Object addPaintStrategy(String classname) {
			return null;
		}

		public Object addInteractStrategy(String classname) {
			return null;
		}
		
		public Object addBallType(String classname) {
			return null;
		}

		public Object combineStrategies(Object selectedItem1, Object selectedItem2) {
			return null;
		}

		public void switchStrategy(Object selectedItem, Object selectedItem2) {
		}

		public void loadSwitcher(Object selectedItem2) {
		}
		public Object addBallAlgo(String classname) {
			return null;
		}
	};

	
}
