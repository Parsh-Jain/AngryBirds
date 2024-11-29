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
    Main main; // Important Attributes
    Sprite sprite;
    Texture image;
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;
    float worldWidth = 1920;
    float worldHeight = 1080;
    float flickerTimer = 0f;
    boolean showPressEnter = false;
    Music enterSound;

    public PreLoadingPage(Main main) { // Constructor
        this.main = main;
    }

    @Override
    public void show() { // Show method to create all the attributes
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        batch = new SpriteBatch();
        image = new Texture("loadingpage.jpg");
        sprite = new Sprite(image);
        sprite.setPosition(worldWidth / 2 - sprite.getWidth() / 2, worldHeight / 2 - sprite.getHeight() / 2);
        enterSound = Gdx.audio.newMusic(Gdx.files.internal("EnterSound.mp3"));
    }

    @Override
    public void render(float delta) { // Rendering
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        flickerTimer += delta;
        if (flickerTimer > 0.5f) {
            showPressEnter = !showPressEnter;
            flickerTimer = 0;
        }
        if (showPressEnter) {
            batch.begin();
            main.font.getData().setScale(2f);
            main.font.draw(batch, "Please press Enter to start!", worldWidth / 2 - 200, 100);
            batch.end();
        }
        // Checking User Input
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            enterSound.play();
            main.setScreen(new LoadingPage(main)); // Switch to LoadingPage
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            enterSound.play();
            main.setScreen(new level2(main)); // Switch to LoadingPage
        }
    }
    @Override
    public void resize(int width, int height) { // Resizing
        viewport.update(width, height);
    }
    @Override
    public void dispose() { // Disposing
        batch.dispose();
        image.dispose();
        enterSound.dispose();
    }
}
