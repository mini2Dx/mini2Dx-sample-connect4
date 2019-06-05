package org.mini2dx.connect4;

import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.connect4.Tile.TileColour.*;

public class Board {

    private final int BOARD_SIZE = 6, TILE_SIZE = 100, MINCOL = 0, SELECTION_TILE_Y = 20;
    private final int BOARD_XSTART = 400, BOARD_YSTART = 200, WIN_CONDITION = 4;

    private Tile[][] board = new Tile[BOARD_SIZE][BOARD_SIZE];
    private static InputHandler input;
    private int selectedCol = 0;
    private boolean isBluesTurn = true;
    private Tile rTile, bTile, eTile, selectionTile;

    public Board() {

        input = new InputHandler();
        eTile = new Tile(EMPTY);
        bTile = new Tile(BLUE);
        rTile = new Tile(RED);
        selectionTile = new Tile(BOARD_XSTART, SELECTION_TILE_Y);
        selectionTile.setColour(BLUE);
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
                if (hasGameBeenWon(filledSpace)) {
                    System.out.println("Game over " + tile.getColour() + " has won!");
                }

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
            if (selectedCol != MINCOL) {
                selectedCol--;
                selectionTile.setX(selectionTile.getX() - TILE_SIZE);
            }
        }

        if (input.isRightPressed()) {
            if (selectedCol != BOARD_SIZE - 1) {
                selectedCol++;
                selectionTile.setX(selectionTile.getX() + TILE_SIZE);
            }

        }

    }

    private void flipTile() {
        if (isBluesTurn) {
            addTileAt(bTile.clone(), selectedCol);
            isBluesTurn = false;
            selectionTile.setColour(RED);
        } else {
            addTileAt(rTile.clone(), selectedCol);
            isBluesTurn = true;
            selectionTile.setColour(BLUE);
        }
    }

    private boolean hasGameBeenWon(int selectedRow) {

        return verticalWin() || horizontalWin(selectedRow) || diagonalWin(selectedRow);

    }

    private boolean verticalWin() {
        int colourMatchs = 0, colourMismatch = 0;
        int row = 5;
        Tile.TileColour colour = board[row][selectedCol].getColour();

        do {
            if (!(colour.equals(board[row][selectedCol].getColour()))) {
                colourMismatch++;
                colourMatchs = 0;
                if (colourMismatch > (BOARD_SIZE - WIN_CONDITION)) {
                    return false;
                }
            } else {
                colourMatchs++;
                if (colourMatchs == WIN_CONDITION) {
                    return true;
                }
            }

            row--;
        }
        while (row >= 0);

        return false;
    }

    private boolean horizontalWin(int selectedRow) {
        int colourMatches = 0, colourMismatches = 0;
        Tile.TileColour colour = board[selectedRow][selectedCol].getColour();

        for (int col = 0; col < BOARD_SIZE; col++) {
            if (!(colour.equals(board[selectedRow][col].getColour()))) {
                colourMismatches++;
                colourMatches = 0;
                if (colourMismatches > (BOARD_SIZE - WIN_CONDITION)) {
                    return false;
                }
            } else {
                colourMatches++;
                if (colourMatches == WIN_CONDITION) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean diagonalWin(int selectedRow) {

        if(bottomLeftDiagonal(selectedRow)){
            return true;
        }
        else if(bottomRightDiagonal(selectedRow)){
            return true;
        }
         else if (topLeftDiagonal(selectedRow)){
             return true;
        }
         else if (topRightDiagonal(selectedRow)){
             return true;
        }
        return false;
    }

    public boolean bottomLeftDiagonal(int selectedRow) {
        int colourMatches = 0, colourMismatches = 0;
        Tile.TileColour colour = board[selectedRow][selectedCol].getColour();
        int stepX = selectedRow;
        int stepY = selectedCol;

        while (stepX < BOARD_SIZE && stepY < BOARD_SIZE) {
            if (colour.equals(board[stepX][stepY].getColour())) {
                colourMatches++;
                if (colourMatches == WIN_CONDITION) {
                    return true;
                }
            } else {
                colourMismatches++;
                colourMatches = 0;
                if (colourMismatches > (BOARD_SIZE - WIN_CONDITION)) {
                    return false;
                }

            }
            stepX++;
            stepY++;
        }
        return false;
    }

    public boolean bottomRightDiagonal(int selectedRow) {
        int colourMatches = 0, colourMismatches = 0;
        Tile.TileColour colour = board[selectedRow][selectedCol].getColour();
        int stepX = selectedRow;
        int stepY = selectedCol;

        while (stepX < BOARD_SIZE && stepY >= MINCOL) {
            if (colour.equals(board[stepX][stepY].getColour())) {
                colourMatches++;
                if (colourMatches == WIN_CONDITION) {
                    return true;
                }
            } else {
                colourMismatches++;
                colourMatches = 0;
                if (colourMismatches > (BOARD_SIZE - WIN_CONDITION)) {
                    return false;
                }

            }
            stepX++;
            stepY--;
        }

        return false;
    }

    public boolean topLeftDiagonal(int selectedRow) {
        int colourMatches = 0, colourMismatches = 0;
        Tile.TileColour colour = board[selectedRow][selectedCol].getColour();
        int stepX = selectedRow;
        int stepY = selectedCol;

        while (stepX >= MINCOL && stepY < BOARD_SIZE) {
            if (colour.equals(board[stepX][stepY].getColour())) {
                colourMatches++;
                if (colourMatches == WIN_CONDITION) {
                    return true;
                }
            } else {
                colourMismatches++;
                colourMatches = 0;
                if (colourMismatches > (BOARD_SIZE - WIN_CONDITION)) {
                    return false;
                }

            }
            stepX--;
            stepY++;
        }

        return false;
    }


    public boolean topRightDiagonal(int selectedRow) {
        int colourMatches = 0, colourMismatches = 0;
        Tile.TileColour colour = board[selectedRow][selectedCol].getColour();
        int stepX = selectedRow;
        int stepY = selectedCol;

        while (stepX >= MINCOL && stepY >= MINCOL) {
            if (colour.equals(board[stepX][stepY].getColour())) {
                colourMatches++;
                if (colourMatches == WIN_CONDITION) {
                    return true;
                }
            } else {
                colourMismatches++;
                colourMatches = 0;
                if (colourMismatches > (BOARD_SIZE - WIN_CONDITION)) {
                    return false;
                }

            }
            stepX--;
            stepY--;
        }


        return false;
    }

}
