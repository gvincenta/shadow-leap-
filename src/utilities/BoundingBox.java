/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */
package utilities;

import org.newdawn.slick.Image;

public class BoundingBox {
	
	// all basic attributes of BoundingBox.
	private static final float FUZZ = 0.95f;
	
	private float left;
	private float top;
	private float width;
	private float height;
	
	
	/**  instantiates BoundingBox with certain x and y coordinates, 
	 * width and height. 
	 * @param x the x-coordinate of the BoundingBox.
	 * @param y the y-coordinate of the BoundingBox.
	 * @param width the width of the BoundingBox.
	 * @param height the height of the BoundingBox.
	 */
	public BoundingBox(float x, float y, float width, float height) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
	}
	
	
	/**  instantiates BoundingBox with a pre-defined image, x and y coordinates. 
	 * @param img the image of the respective BoundingBox.
	 * @param x the x-coordinate of the BoundingBox.
	 * @param y the y-coordinate of the BoundingBox.
	 */
	public BoundingBox(Image img, float x, float y) {
		setWidth(img.getWidth());
		setHeight(img.getHeight());
		setX(x);
		setY(y);
	}
	
	/** instantiates BoundingBox with a pre-defined BoundingBox. 
	 * @param bb the intended shape of BoundingBox.
	 */
	public BoundingBox(BoundingBox bb) {
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
	}

	/*
	 * Sets the x and y position at the centre of the bounding box.
	 */
	
	
	/** sets x of BoundingBox.
	 * @param x the intended x coordinate for BoundingBox.
	 */
	public void setX(float x) {
		left = x - width / 2;
	}
	
	/** sets y of BoundingBox.
	 * @param y the intended y coordinate for BoundingBox.
	 */
	public void setY(float y) {
		top = y - height / 2;
	}
	
	/** sets width of BoundingBox.
	 * @param w the intended width for BoundingBox.
	 */
	public void setWidth(float w) {
		width = w * FUZZ;
	}
	
	/** sets height of BoundingBox.
	 * @param h the intended height for BoundingBox.
	 */
	public void setHeight(float h) {
		height = h * FUZZ;
	}
	
	
	/** gets BoundingBox's left.
	 * @return the left-most coordinate of the BoundingBox.
	 */
	public float getLeft() {
		return left;
	}
	
	/** gets BoundingBox's top.
	 * @return the top-most coordinate of the BoundingBox.
	 */
	public float getTop() {
		return top;
	}
	
	/** gets BoundingBox's right.
	 * @return the right-most coordinate of the BoundingBox.
	 */
	public float getRight() {
		return left + width;
	}
	
	/** gets BoundingBox's bottom.
	 * @return the bottom-most coordinate of the BoundingBox.
	 */
	public float getBottom() {
		return top + height;
	}
	
	/** gets BoundingBox's width.
	 * @return the width of the BoundingBox.
	 */
	public float getWidth() {
		return width;
	}
	
	/** gets BoundingBox's height.
	 * @return the height of the BoundingBox.
	 */
	public float getHeight() {
		return height;
	}
	
	
	/** checks if this intersects 'other' BoundingBox.
	 * @param other the other BoundingBox to be checked against.
	 * @return true if intersects, false if not intersecting. 
	 */
	public boolean intersects(BoundingBox other) {
		return !(other.left > getRight()
			  || other.getRight()  < left
			  || other.top > getBottom()
			  || other.getBottom() < top);
	}
}
