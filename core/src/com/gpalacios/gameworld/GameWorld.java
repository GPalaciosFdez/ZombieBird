package com.gpalacios.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.gpalacios.gameobjects.Bird;

/**
 * Created by guille on 5/29/2016.
 */
public class GameWorld {

    private Bird bird;

    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY -5, 17, 12);
    }

    public void update(float delta){
        bird.update(delta);
    }

    public Bird getBird(){
        return bird;
    }

}
