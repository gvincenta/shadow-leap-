import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author gilbert
 * Basic object definition by its image and its position.
 */
public class Tile {
	//each Tile defined by: 
	private Image image; 
	private Position position;
	
	/**renders image all the time.*/
	public void render() { 
		// draw center
		image.drawCentered(position.getX(),position.getY());
	}
	
	/** get centre x coordinate 
	 * @return x-coordinate */
	public float getX() {
		return this.position.getX(); 
	}
	
	/** get centre y coordinate
	 * @return y-coordinate */
	public float getY() {
		return this.position.getY(); 
	}
	
	/**gets the Tile's current position 
	 * @return position coordinates */
	public Position getPosition() {
		return this.position;
	}
	
	/**for rendering later. 
	 * @return image object of the tile. */
	public Image getImage() {
		return this.image;
	}
	
	
	/** instantiates  Tile objects  
	 * at (x,y) with specific direction.
	  * @param x x coordinate of  Tile on the screen
	  * @param y y coordinate of  Tile on the screen
	  * @param imgSrc specifies Tile's image directory */
	public Tile(String imageSrc, float x, float y) {
		setPosition(x, y);

		/* for safety's sake, handle the image init */
		
		try {
			this.image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}	
	}
	
	/** sets (x,y) of Tile. 
	 * @param x centre x-coordinate 
	 * @param y centre y-coordinate 
	 */
	public void setPosition (float x, float y) {
		this.position = new Position(x,y);
	}
}

