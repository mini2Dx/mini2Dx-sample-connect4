package org.mini2dx.connect4;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.viewport.FitViewport;


public class Connect4Game extends BasicGame {
	public static final String GAME_IDENTIFIER = "org.mini2dx.connect4";
	private Board board;
	private FitViewport viewport;
    private final int WORLD_WIDTH = 1400, WORLDHEIGHT = 1000;
	
	@Override
    public void initialise() {

        viewport = new FitViewport(WORLD_WIDTH,WORLDHEIGHT);

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
        viewport.apply(g);
        board.render(g);
    }
}
