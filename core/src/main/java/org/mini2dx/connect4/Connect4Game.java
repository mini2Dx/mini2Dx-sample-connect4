package org.mini2dx.connect4;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import static org.mini2dx.connect4.Tile.TileColour.BLUE;
import static org.mini2dx.connect4.Tile.TileColour.RED;



public class Connect4Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "org.mini2dx.connect4";
	private Board board;
	
	@Override
    public void initialise() {

		board = new Board();
		board.initialise();

    }
    
    @Override
    public void update(float delta) {
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
