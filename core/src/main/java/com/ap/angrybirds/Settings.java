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
    private Texture Background;
    private Texture settingsBackground;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    public void show(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        batch = new SpriteBatch();  // Initialize the batch

        Background = new Texture("Background.png");
        settingsBackground = new Texture("settingBox.png");
    }

    public void render(float delta){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(Background, 0, 0);
        batch.draw(settingsBackground, 100, 100);
        batch.end();

        System.out.println("X: " + Gdx.graphics.getWidth() + " Y: " + Gdx.graphics.getHeight());
    }

    public void dispose(){
        batch.dispose();
        Background.dispose();
        settingsBackground.dispose();
    }

}
