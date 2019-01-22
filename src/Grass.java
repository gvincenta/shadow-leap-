
/**
 * @author gilbert
 * Draws grass as background.
 */
public class Grass extends Tile {
	/**Grass's image directory*/
	public  static final String ADDRESS = "assets/grass.png";
	
	/** instantiates Grass object at (x,y)
	 * @param x x-coordinate of Grass on the screen
	 * @param y y-coordinate of Grass on the screen */
	public Grass(float x, float y) {
		super(ADDRESS, x, y);
	}
	
}
