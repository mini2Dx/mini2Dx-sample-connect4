/*
/*******************************************************************************
 * Copyright 2019 Viridian Software Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


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
