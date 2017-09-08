import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite {

	public Player(int x, int y) 
	throws SlickException
	{
		super("player", x, y);
	}
	
	// updates player position based on input and tests for collisions with walls
	public void update(Input input, int delta, boolean[][] collisionMap) {
		if (input.isKeyPressed(Input.KEY_UP)) {
			if (!collisionMap[this.x][this.y - 1]) {
				this.y--;
			}
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (!collisionMap[this.x][this.y + 1]) {
				this.y++;
			}
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			if (!collisionMap[this.x - 1][this.y]) {
				this.x--;
			}
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			if (!collisionMap[this.x + 1][this.y]) {
				this.x++;
			}
		}
	}
}
