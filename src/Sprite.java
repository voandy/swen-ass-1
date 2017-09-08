import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {
	protected static final String FLOOR_SRC = "res/floor.png";
	protected static final String WALL_SRC = "res/wall.png";
	protected static final String STONE_SRC = "res/stone.png";
	protected static final String TARGET_SRC = "res/target.png";
	protected static final String PLAYER_SRC = "res/player_left.png";
	
	Image tile;
	String type;
	boolean collider;
	
	int x;
	int y;
	
	public Sprite(String image_src, int x, int y) 
	throws SlickException 
	{
		type = image_src;
		// collider status defaults to false
		collider = false;
		this.x = x;
		this.y = y;
		
		// gets image source from tile type
		String src = "";
		switch(type) {
		case "floor":
			src = FLOOR_SRC;
			break;
		case "wall":
			src = WALL_SRC;
			collider = true;
			break;
		case "stone":
			src = STONE_SRC;
			break;
		case "target":
			src = TARGET_SRC;
			break;
		case "player":
			src = PLAYER_SRC;
			break;
		default:
			throw new RuntimeException("Tile src invalid.");
		}
		tile = new Image(src);
	}
	
	public void update(Input input, int delta) {
	}
	
	public void render(Graphics g, float xOffset, float yOffset) {
		tile.draw(x * App.TILE_SIZE + xOffset, y * App.TILE_SIZE + yOffset);
	}
	
	// parses a sprite to a String for debugging
	public String toString() {
		return String.format("%s, %f, %f", this.tile, this.x, this.y);
	}
}
