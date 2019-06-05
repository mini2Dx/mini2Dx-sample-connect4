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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class TileAudio {

    Sound tile_fall, game_won;

    enum SoundId {
        TILE_FALL,
        GAME_WON
    }

    public TileAudio() {
        tile_fall = Gdx.audio.newSound(Gdx.files.internal("coin1.ogg"));
        game_won = Gdx.audio.newSound(Gdx.files.internal("jingles_SAX10.ogg"));
    }

    public void play(SoundId sound) {
        switch (sound) {
            case TILE_FALL:
                tile_fall.play();
                break;
            case GAME_WON:
                game_won.play();
                break;
        }
    }

}
