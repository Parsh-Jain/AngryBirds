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

public class Settings {
    private Texture Background;
    private Texture settingsBackground;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    public void create(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        batch = new SpriteBatch();  // Initialize the batch

        Background = new Texture("Background.png");
        settingsBackground = new Texture("settings.png");
    }


//

}
