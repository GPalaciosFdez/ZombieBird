package com.gpalacios.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gpalacios.gameworld.GameRenderer;
import com.gpalacios.gameworld.GameWorld;
import com.gpalacios.zbhelpers.InputHandler;

/**
 * Created by guille on 5/29/2016.
 */
public class GameScreen implements Screen{

  private GameWorld world;
  private GameRenderer renderer;
  private float runTime = 0;

  public GameScreen(){

    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    float gameWidth = 136;
    float gameHeight = screenHeight / (screenWidth / gameWidth);

    int midPointY = (int) (gameHeight / 2);

    world = new GameWorld(midPointY);
    renderer = new GameRenderer(world, (int) gameHeight, midPointY);

    Gdx.input.setInputProcessor(new InputHandler(world.getBird()));
  }

  @Override
  public void show(){
    Gdx.app.log("GameScreen", "show called");
  }

  @Override
  public void render(float delta){
    world.update(delta);
    runTime += delta;
    renderer.render(runTime);
  }

  @Override
  public void resize(int width, int height){
    Gdx.app.log("GameScreen", "resize called");
  }

  @Override
  public void pause(){
    Gdx.app.log("GameScreen", "pause called");
  }

  @Override
  public void resume(){
    Gdx.app.log("GameScreen", "resume called");
  }

  @Override
  public void hide(){
    Gdx.app.log("GameScreen", "hide called");
  }

  @Override
  public void dispose(){
  }
}
