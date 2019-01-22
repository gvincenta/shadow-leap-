

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
/**
 * Controls on-screen for the game.
 */
public class World {
	
	/*all the World needs, are these:  */
	private SpriteController control;

	
	/**starts the game logic */
	public World() {
		control = new SpriteController();
	}
	
	/**updates all  
	 * @param input user keyboard input.
	 * @param delta the state of the screen. */
	public void update (Input input, int delta) {
		control.update(input, delta);
	}
	/**renders all  
	 * @param g Graphics for drawing. */
	public void render(Graphics g) {
		control.render(g);
	}
}
