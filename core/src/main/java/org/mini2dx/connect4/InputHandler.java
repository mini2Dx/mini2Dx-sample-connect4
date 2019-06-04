package org.mini2dx.connect4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {

    public boolean isEnterPressed()
    {
      return Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
    }

    public boolean isLeftPressed()
    {
        return Gdx.input.isKeyJustPressed(Input.Keys.LEFT);
    }


    public boolean isRightPressed()
    {
        return Gdx.input.isKeyJustPressed(Input.Keys.RIGHT);
    }

}
