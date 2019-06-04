package org.mini2dx.connect4;

import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.connect4.Tile.TileColour.*;

public class Board {

    private final int BOARD_SIZE = 6, TILE_SIZE = 100, MINCOL = 0, SELECTION_TILE_Y = 20;
    public final int BOARD_XSTART = 300, BOARD_YSTART = 200;

    private Tile[][] board = new Tile[BOARD_SIZE][BOARD_SIZE];
    private static InputHandler input;
    private int selectRow = 0, selectCol = 0;
    private boolean isBluesTurn = true;
    private Tile rTile, bTile, eTile, selectionTile;

    public Board() {

        input = new InputHandler();
        eTile = new Tile(EMPTY);
        bTile = new Tile(BLUE);
        rTile = new Tile(RED);
        selectionTile = new Tile(BOARD_XSTART,SELECTION_TILE_Y);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Tile empty = eTile.clone();
                empty.setX(BOARD_XSTART + (TILE_SIZE * col));
                empty.setY(BOARD_YSTART + (TILE_SIZE * row));
                board[row][col] = empty;
                board[row][col].initialise();

            }
        }

    }

    public static Board instance() {
        Board instance = new Board();
        return instance;
    }


    public void addTileAt(Tile tile, int col) {

        for (int filledSpace = BOARD_SIZE - 1; filledSpace >= 0; filledSpace--) {
            if (board[filledSpace][col].getColour().equals(EMPTY)) {
                tile.setX(board[filledSpace][col].getX());
                tile.setY(board[filledSpace][col].getY());
                board[filledSpace][col] = tile;
                board[filledSpace][col].initialise();
                return;
            }

        }

    }

    public void update(float delta) {

        checkInput();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col].update(delta);
            }
        }

        selectionTile.update(delta);

    }


    public void render(Graphics g) {

        g.scale(0.5f, 0.5f);



        for (Tile row[] : board) {
            for (Tile tile : row) {
                tile.render(g);
            }
        }

        selectionTile.render(g);

    }


    public void initialise() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                board[row][col].initialise();

            }
        }

        selectionTile.initialise();

    }

    private void checkInput() {

        if (input.isEnterPressed()) {
            flipTile();
        }

        if (input.isLeftPressed()) {
            if (selectCol != MINCOL) {
                selectCol--;
                selectionTile.setX(selectionTile.getX()-TILE_SIZE);
            }
        }

        if (input.isRightPressed()) {
            if (selectCol != BOARD_SIZE - 1) {
                selectCol++;
                selectionTile.setX(selectionTile.getX()+TILE_SIZE);
            }
            }

    }

    private void flipTile() {
        if (isBluesTurn) {
                addTileAt(bTile.clone(), selectCol);
                isBluesTurn = false;
        } else {
                addTileAt(rTile.clone(), selectCol);
                isBluesTurn = true;
        }
    }



}
