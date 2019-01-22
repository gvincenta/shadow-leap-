import org.newdawn.slick.Input;
import utilities.BoundingBox;
/**
 * @author gilbert
 * generalise all objects attributes. 
 */
public class Sprite extends Tile {
	
	/** 1 tile is generally 48 pixels */
	public static final int TILE_SIZE = 48; 
	/**half the tile size*/
	public static final int HALF_SIZE = TILE_SIZE / 2;
	
	/*general instance variables*/
	
	private BoundingBox box;
	
	/** instantiates Sprite object. 
	 * at (x,y) with specific direction. 
	 * with a BoundingBox sized at boxWidth x boxHeight.
	 * @param x x coordinate of Sprite object on the screen.
	 * @param y y coordinate of Sprite object on the screen.
	 * @param direction specifies Sprite object's direction.
	 * @param boxWidth the width of the BoundingBox.
	 * @param boxWidth the height of the BoundingBox.
	 * @param imgSrc specifies Sprite's image directory */
	public Sprite(String imageSrc, float x, float y, int boxWidth, 
			int boxHeight) {
		super(imageSrc,x,y);
		
		/* sets all the attributes required for basic existence */
		this.box = new BoundingBox (getPosition().getX(), getPosition().getY(), 
				boxWidth, boxHeight); 
	}
	
	/** to be over-riden in child classes. 
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update(Input input, int delta) {
	}
	
	
	
	/** detects collision against another sprite. 
	 * @param other the other sprite to be checked against. 
	 * @return true if contacted, false if there's no contact.
	 */
	public boolean  contactSprite(Sprite other) {
		// Should be called when one sprite makes contact with another. 
		if (this.box.intersects(other.box)) {
				return true;
			}
			
		return false;
	}

	/** gets the BoundingBox of this Sprite. 
	 * @return respective BoundingBox
	 */
	public BoundingBox getBox() {
		return this.box;
	}
	
	
	/** checks if Sprite still on screen.
	 * @return true if still on screen, else returns false.
	 */
	public boolean inBound() {
		if (this.box.getBottom() <= App.SCREEN_HEIGHT && 
				this.box.getTop() >= 0 && this.box.getLeft() >= 0 && 
				this.box.getRight() <= App.SCREEN_WIDTH) {
			return true;
		}
		return false;
	}
	
	
	
	/** sets the (centre) x-coordinate of a Sprite, as well as its BoundingBox.
	 * @param x the x-coordinate of the object. 
	 */
	public void setX(float x) {
		this.getPosition().setX(x);
		this.getBox().setX(this.getX());
	}
	
	/** sets the (centre) y-coordinate of a Sprite, as well as its BoundingBox.
	 * @param y the y-coordinate of the object. 
	 */
	public void setY(float y) {
		this.getPosition().setY(y);
		this.getBox().setY(this.getY());
	}	

	
}

