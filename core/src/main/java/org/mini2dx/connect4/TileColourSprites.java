package org.mini2dx.connect4;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;

public class TileColourSprites {

    private static final String EMPTY_TILE = "tileGrey_27.png";
    private static final String RED_TILE = "tileRed_27.png";
    private static final String BLUE_TILE = "tileBlue_27.png";
    private Sprite blueSprite, redSprite, emptySprite;

     TileColourSprites() {
        redSprite = new Sprite(new Texture(RED_TILE));
        blueSprite = new Sprite(new Texture(BLUE_TILE));
        emptySprite = new Sprite(new Texture(EMPTY_TILE));
    }

    Sprite getBlueSprite() {
        return blueSprite;
    }

    Sprite getRedSprite() {
        return redSprite;
    }

    Sprite getEmptySprite() {
        return emptySprite;
    }


}
