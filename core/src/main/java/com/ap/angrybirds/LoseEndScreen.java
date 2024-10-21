package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoseEndScreen extends ApplicationAdapter {
//    Main main;
//    public LoseEndScreen(Main main) {
//        this.main = main;
//    }
    SpriteBatch batch;
    Texture background;
    Texture Retry;
    Texture Back;
    Rectangle RetryButton;
    Rectangle BackButton;
    @Override
    public void create(){
        batch = new SpriteBatch();
        background = new Texture("LosingPage.jpg");
        Retry = new Texture("retry.png");
        Back = new Texture("Back.png");
        RetryButton = new Rectangle();
        BackButton = new Rectangle();
    }
    @Override
    public void render(){
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(Retry,100,100,150,150);
        batch.draw(Back,500,100,300,300);
        batch.end();
    }

}
