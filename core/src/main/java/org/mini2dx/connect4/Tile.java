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
import org.mini2Dx.core.Graphics;
import org.mini2Dx.core.graphics.Sprite;


public class Tile  {

    private int x, y; //co-oridinates of the tile.
    private TileColour colour;
    private static TileColourSprites sprites;
    private  Sprite tileSprite;

    public enum TileColour
    {
        EMPTY,
        BLUE,
        RED
    }

    public Tile(TileColour colour)
    {
       sprites = new TileColourSprites();
        this.setColour(colour);

    }


    public Tile(int x, int y)
    {

        sprites = new TileColourSprites();
        this.setColour(TileColour.EMPTY);
        this.x = x;
        this.y = y;

    }


   public Tile clone() {
        return new Tile(this.colour);
    }

    public void render(Graphics g) {
        g.drawSprite(tileSprite);
    }

    public void initialise() {
        tileSprite.setPosition(getX(),getY());

    }

    public void update(float delta) {
        tileSprite.setPosition(getX(),getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileColour getColour() {
        return colour;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setColour(TileColour colour) {
        this.colour = colour;


        switch (colour) {
            case RED:
                tileSprite = sprites.getRedSprite();

                break;
            case BLUE:
                tileSprite = sprites.getBlueSprite();
                break;
            case EMPTY:
                tileSprite = sprites.getEmptySprite();
                break;
        }

    }





}
