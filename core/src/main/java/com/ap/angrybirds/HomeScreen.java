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
    Texture RedAngryBird;
    Texture YellowAngryBird;
    Texture BasicButton;
    Texture BasicSelection;
    Texture View;
    Texture AngryStory;
    Texture AngryHighlights;


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
        RedAngryBird=new Texture("RedAngryBird.png");
        YellowAngryBird=new Texture("YellowAngryBird.png");
        BasicButton=new Texture("BasicButton.png");
        BasicSelection=new Texture("BasicSelection.png");
        View=new Texture("View.png");
        AngryStory=new Texture("AngryStory.png");
        AngryHighlights=new Texture("AngryHighlights.png");



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
        batch.draw(PlayerName,Gdx.graphics.getWidth()-425,850,350,150);
        batch.draw(PlayerCoin,100,850,150,150);
        batch.draw(PlayButton,Gdx.graphics.getWidth()/2-90,345,225,85);
        batch.draw(AngryLevelText,Gdx.graphics.getWidth()/2-95,785,250,50);
        batch.draw(BasicSelection,200,300,400,550);
        batch.draw(BasicSelection,1400,300,400,550);
        batch.draw(RedAngryBird,290,500,200,200);
        batch.draw(YellowAngryBird,1475,490,250,250);
        batch.draw(BasicButton,275,315,225,85);
        batch.draw(BasicButton,1490,315,225,85);
        batch.draw(View,340,330,120,50);
        batch.draw(View,1560,330,120,50);
        batch.draw(AngryStory,280,750,250,50);
        batch.draw(AngryHighlights,1480,750,250,50);


        batch.end();



    }
}
