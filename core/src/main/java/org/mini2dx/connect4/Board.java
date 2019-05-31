package org.mini2dx.connect4;

import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.connect4.Tile.TileColour.*;

public class Board {

    private final int BOARD_SIZE = 6, TILE_SIZE = 100;
    public final  int  BOARD_XSTART = 300, BOARD_YSTART = 200;

    private Tile [][] board = new Tile[BOARD_SIZE][BOARD_SIZE];

    public Board()
    {

        for(int row = 0; row < BOARD_SIZE; row++)
        {
            for(int col = 0; col < BOARD_SIZE; col++)
            {
                Tile empty = new Tile((BOARD_XSTART + (TILE_SIZE*col)),(BOARD_YSTART + (TILE_SIZE*row)));
                board[row][col] = empty;
                board[row][col].initialise();

            }
        }

    }

   public static Board instance()
    {
         Board instance = new Board();
         return instance;
    }


    public void addTileAt(Tile tile, int col)
    {

        for(int filledSpace = BOARD_SIZE -1; filledSpace >= 0; filledSpace--)
        {
            if(board[filledSpace][col].getColour().equals(EMPTY))
            {
                tile.setX(board[filledSpace][col].getX());
                tile.setY(board[filledSpace][col].getY());
                board[filledSpace][col] = tile;
                board[filledSpace][col].initialise();
                return;
            }

        }

    }

    public void update(float delta)
    {
        for(int row = 0; row < BOARD_SIZE; row++)
        {
            for(int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].update(delta);
            }
        }
    }


    public void render(Graphics g)
    {

        g.scale(0.5f,0.5f);
        for(Tile row[] : board)
        {
            for(Tile tile : row)
            {
                tile.render(g);
            }
        }


    }


    public void initialise()
    {
        for(int row = 0; row < BOARD_SIZE; row++)
        {
            for(int col = 0; col < BOARD_SIZE; col++)
            {

                board[row][col].initialise();

            }
        }

    }



}
