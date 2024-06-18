package hw06.model;

import java.awt.Image;

import provided.utils.displayModel.IATImage;
import provided.utils.displayModel.IDimension;

/**
 * Interface for the adapter to the view
 */
public interface IModel2ViewAdapter {

	/**
	 * @return An IDimension representing the canvas dimension
	 */
	public IDimension getCanvasDim();

	/**
	 * Repaints the canvas
	 */
	public void repaint();

	/**
	 * Returns the IATImage used for painting
	 * @param image the image to be used
	 * @return the corresponding IATImage
	 */
	public IATImage getIATImage(Image image);

	/**
	 * Constructs a null adapter to the view
	 */
	public static final IModel2ViewAdapter NULL = new IModel2ViewAdapter() {
		public IDimension getCanvasDim() {
			return null;
		}

		public void repaint() {
		}

		public IATImage getIATImage(Image image) {
			return null;
		}
	};
}
