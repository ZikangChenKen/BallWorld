package hw06.model;

import java.util.function.Supplier;

import javax.swing.JComponent;

/**
 * The adapter for the ballAlgo to model.
 */
public interface IBallAlgo2ModelAdapter {

	/**
	 * The null adapter.
	 */
	public static final IBallAlgo2ModelAdapter NULL = new IBallAlgo2ModelAdapter() {
		@Override
		public void addConfigComponent(String label, Supplier<JComponent> compFac) {}
		
	};
	
	/**
	 * Add the config components.
	 * @param label the label of the component
	 * @param compFac the factory to produce component
	 */
	public void addConfigComponent(String label, Supplier<JComponent> compFac);

}
