package com.gpalacios.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gpalacios.gameobjects.Bird;
import com.gpalacios.gameobjects.Grass;
import com.gpalacios.gameobjects.Pipe;
import com.gpalacios.gameobjects.ScrollHandler;
import com.gpalacios.gameworld.GameWorld;
import com.gpalacios.zbhelpers.AssetLoader;

/**
 * Created by guille on 5/29/2016.
 */
public class GameRenderer{

  private GameWorld myWorld;
  private OrthographicCamera cam;
  private ShapeRenderer shapeRenderer;
  private SpriteBatch batcher;

  private int midPointY;
  private int gameHeight;

  private Bird bird;
  private ScrollHandler scroller;
  private Grass frontGrass, backGrass;
  private Pipe pipe1, pipe2, pipe3;

  private TextureRegion bg, grass;
  private Animation birdAnimation;
  protected TextureRegion birdMid, birdDown, birdUp;
  private TextureRegion skullUp, skullDown, bar;

  public GameRenderer(GameWorld world, int gameHeight, int midPointY){

    myWorld = world;

    this.gameHeight = gameHeight;
    this.midPointY = midPointY;

    cam = new OrthographicCamera();
    cam.setToOrtho(true, 136, gameHeight);

    batcher = new SpriteBatch();
    batcher.setProjectionMatrix(cam.combined);

    shapeRenderer = new ShapeRenderer();
    shapeRenderer.setProjectionMatrix(cam.combined);

    initGameObjects();
    initAssets();

  }

  public void render(float runTime){

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    //bg
    shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
    shapeRenderer.rect(0, 0, 136, midPointY + 66);
    //grass
    shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
    shapeRenderer.rect(0, midPointY + 66, 136, 11);
    //dirt
    shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
    shapeRenderer.rect(0, midPointY + 77, 136, 52);
    shapeRenderer.end();

    batcher.begin();
    batcher.disableBlending();
    batcher.draw(bg, 0, midPointY + 23, 136, 43);
    drawGrass();
    drawPipes();
    batcher.enableBlending();
    drawSkulls();
    if(bird.shoudntFlap()){
      batcher.draw(birdMid, bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
    }else{
      batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
    }
    batcher.end();

  }

  private void initAssets(){
    bg = AssetLoader.bg;
    grass = AssetLoader.grass;
    birdAnimation = AssetLoader.birdAnimation;
    birdMid = AssetLoader.bird;
    birdDown = AssetLoader.birdDown;
    birdUp = AssetLoader.birdUp;
    skullDown = AssetLoader.skullDown;
    skullUp = AssetLoader.skullUp;
    bar = AssetLoader.bar;
  }

  private void initGameObjects(){
    bird = myWorld.getBird();
    scroller = myWorld.getScroller();
    frontGrass = scroller.getFrontGrass();
    backGrass = scroller.getBackGrass();
    pipe1 = scroller.getPipe1();
    pipe2 = scroller.getPipe2();
    pipe3 = scroller.getPipe3();
  }

  private void drawGrass(){
    batcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
    batcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
  }

  private void drawSkulls(){
    batcher.draw(skullUp, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
    batcher.draw(skullDown, pipe1.getX() - 1, pipe1.getY() + pipe1.getHeight() - 14, 24, 14);

    batcher.draw(skullUp, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
    batcher.draw(skullDown, pipe2.getX() - 1, pipe2.getY() + pipe2.getHeight() - 14, 24, 14);

    batcher.draw(skullUp, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
    batcher.draw(skullDown, pipe3.getX() - 1, pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
  }

  private void drawPipes(){
    batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(), pipe1.getHeight());
    batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45, pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

    batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(), pipe2.getHeight());
    batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45, pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

    batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(), pipe3.getHeight());
    batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45, pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
  }
}
