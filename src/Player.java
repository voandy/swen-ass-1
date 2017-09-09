import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite {
	
	protected Image man;
	protected Image sprite;
	
	public Player(int x, int y) 
	throws SlickException
	{
		super("player", x, y);
		man = new Image(Sprite.PLAYER_SRC);
		sprite = new Image(Sprite.SPRITE_SRC);
	}
	
	// updates player position based on input and tests for collisions with walls
	public void update(Input input, int delta, boolean[][] collisionMap) 
	throws SlickException 
	{
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
		
		// optimises thirst quenching
		if (input.isKeyPressed(Input.KEY_S)) {
			if (this.type.equals("player")) {
				this.type = "sprite";
				this.tile = sprite;
			} else {
				this.type = "player";
				this.tile = man;
			}
		}
	}
}