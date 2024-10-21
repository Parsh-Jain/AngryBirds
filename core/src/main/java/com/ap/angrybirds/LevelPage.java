package com.ap.angrybirds;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class LevelPage extends ScreenAdapter {
    Main main;
    SpriteBatch batch;
    Texture background;
    Texture level1;
    Texture level2;
    Texture level3;
    Texture Setting;
    Texture Back;
    Texture PlayerName;
    Texture PlayerCoin;

    public LevelPage(Main main) {
        this.main = main;
    }
    @Override
    public void show(){
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("LevelpageBackground.png"));
        level1 = new Texture(Gdx.files.internal("Level1.png"));
        level2 = new Texture(Gdx.files.internal("Level2.png"));
        level3 = new Texture(Gdx.files.internal("Level3.png"));
        Setting = new Texture(Gdx.files.internal("Setting.png"));
        Back = new Texture(Gdx.files.internal("Back.png"));
        PlayerName = new Texture(Gdx.files.internal("Player Name.png"));
        PlayerCoin = new Texture(Gdx.files.internal("Player Coin.png"));



    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(level1, 230, 525, 125, 125);
        batch.draw(level2, 500, 525, 125, 125);
        batch.draw(level3, 770, 645, 125, 125);
        batch.draw(Back, 100, 50, 150, 150);
        batch.draw(Setting,1650,50,150,150);
        batch.draw(PlayerName,1450,900,450,150);
        batch.draw(PlayerCoin,100,900,150,150);
        batch.end();
    }

}
