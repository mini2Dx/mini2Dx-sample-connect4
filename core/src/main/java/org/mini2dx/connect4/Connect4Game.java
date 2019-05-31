package org.mini2dx.connect4;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import static org.mini2dx.connect4.Tile.TileColour.BLUE;
import static org.mini2dx.connect4.Tile.TileColour.RED;



public class Connect4Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "org.mini2dx.connect4";
	private Tile rTile, bTile;
	private Board board;
	
	@Override
    public void initialise() {

		board = new Board();
		board.initialise();
		rTile = new Tile(RED);
		rTile.initialise();
		bTile = new Tile(BLUE);
		bTile.initialise();

    }
    
    @Override
    public void update(float delta) {
		board.addTileAt(rTile.clone(), 5);
		board.addTileAt(bTile.clone(), 5);
		board.update(delta);
    }
    
    @Override
    public void interpolate(float alpha) {
	}
    
    @Override
    public void render(Graphics g) {
		board.render(g);
    }
}
