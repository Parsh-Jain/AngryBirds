package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Settings extends ScreenAdapter {
    Main main;
    private Texture Background;
    private Texture settingsBackground;
    private Texture sound;
    private Texture music;
    private Texture sound_word;
    private Texture music_word;
    private Texture BackToHomePage;
    private Texture BackToLevelMenu;
    private Texture blueBox;
    //private Texture blueBox1;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    public Settings(Main main) {
        this.main = main;
    }


    public void show(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();
        camera.position.set(1920 / 2, 1080 / 2, 0);
        camera.update();

        batch = new SpriteBatch();  // Initialize the batch

        Background = new Texture("settingsBackground.jpg");
        settingsBackground = new Texture("settingBox.png");
        sound = new Texture("sound.png");
        music = new Texture("music.png");
        sound_word = new Texture("soundWord.png");
        music_word = new Texture("musicWord.png");
        BackToHomePage = new Texture("BackToHomePage.png");
        BackToLevelMenu = new Texture("BackToLevelMenu.png");
        blueBox = new Texture("blueBox.png");
    }

    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        //

        batch.begin();
        batch.draw(Background, 0, 0);
        batch.draw(settingsBackground, 400, 80, 1200,1000);
        batch.draw(music, 680, 660, 125,125);
        batch.draw(sound, 870, 660, 125,125);
        batch.draw(sound_word, 870, 620, 127,33);
        batch.draw(music_word, 680, 620, 127,33);
        batch.draw(blueBox, 810, 436, 385, 100);
        batch.draw(blueBox, 810, 310, 385, 100);
        batch.draw(BackToHomePage, 839, 450, 320,65);
        batch.draw(BackToLevelMenu, 830, 320, 340,80);

        batch.end();

        System.out.println("X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose(){
        batch.dispose();
        Background.dispose();
        settingsBackground.dispose();
    }

}
