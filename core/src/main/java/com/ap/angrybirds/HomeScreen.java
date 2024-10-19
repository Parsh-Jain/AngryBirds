package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HomeScreen extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture AngryLevel;
    Texture PlayButton;
    Texture PlayerName;
    Texture Setting;
    Texture Back;
    Texture PlayerCoin;
    Texture AngryLevelText;


    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("homeScreen background.jpg");
        AngryLevel=new Texture("Angry Level.png");
        PlayButton=new Texture("Play.png");
        PlayerName=new Texture("Player Name.png");
        Setting=new Texture("Setting.png");
        Back=new Texture("Back.png");
        PlayerCoin=new Texture("Player Coin.png");
        AngryLevelText=new Texture("AngryLevel Text.png");

        //Transparency
        batch.enableBlending();

    }
    public void render(){
        batch.begin();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.draw(background, 0, 0);
        batch.draw(AngryLevel,Gdx.graphics.getWidth()/2-165,Gdx.graphics.getHeight()/2-200,400,550);
        batch.draw(Back,100,100,150,150);
        batch.draw(Setting,Gdx.graphics.getWidth()-250,100,150,150);
        batch.draw(PlayerName,Gdx.graphics.getWidth()-315,850,300,150);
        batch.draw(PlayerCoin,100,850,150,150);
        batch.draw(PlayButton,Gdx.graphics.getWidth()/2-90,345,225,85);
        batch.draw(AngryLevelText,Gdx.graphics.getWidth()/2-95,785,250,50);
        batch.end();



    }
}
