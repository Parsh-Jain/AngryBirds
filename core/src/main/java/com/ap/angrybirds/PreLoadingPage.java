package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PreLoadingPage extends ScreenAdapter {

    Main main;
    Sprite sprite;
    Texture image;
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;

    // Flicker and timer variables
    float worldWidth = 1920;
    float worldHeight = 1080;
    float flickerTimer = 0f;
    boolean showPressEnter = false; // Toggle for flickering

    Music enterSound; // Sound for Enter key

    public PreLoadingPage(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        // Initialize camera, viewport, and background image
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        batch = new SpriteBatch();
        image = new Texture("loadingpage.jpg");
        sprite = new Sprite(image);
        sprite.setPosition(worldWidth / 2 - sprite.getWidth() / 2, worldHeight / 2 - sprite.getHeight() / 2);

        // Load sound
        enterSound = Gdx.audio.newMusic(Gdx.files.internal("EnterSound.mp3"));
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();

        // Flicker effect for "Please press Enter to start!" message
        flickerTimer += delta;
        if (flickerTimer > 0.5f) { // Flicker every half second
            showPressEnter = !showPressEnter;
            flickerTimer = 0;
        }

        // Draw "Please press Enter" message if toggle is on
        if (showPressEnter) {
            batch.begin();
            main.font.getData().setScale(2f);
            main.font.draw(batch, "Please press Enter to start!", worldWidth / 2 - 200, 100);
//            main.font.getData().setScale(1.0f);
            batch.end();
        }

        // Check for Enter key press to play sound and switch screen
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            enterSound.play();
            main.setScreen(new LoadingPage(main)); // Change to next screen
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        enterSound.dispose();
    }
}
