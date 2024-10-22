package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoseEndScreen extends ApplicationAdapter {

    SpriteBatch batch;
    Texture background;
    Texture retry;
    Texture back;
    Rectangle retryButton;
    Rectangle backButton;

    OrthographicCamera camera;
    Viewport viewport;

    float worldWidth = 1920;
    float worldHeight = 1080;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("LosingPage.jpg");
        retry = new Texture("retry.png");
        back = new Texture("Back.png");
        retryButton = new Rectangle(100, 100, 150, 150);
        backButton = new Rectangle(500, 100, 300, 300);

        // Set up the camera with the world size and apply the viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void render() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the camera and apply it to the batch
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Render the background and buttons
        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight); // Full screen background
        batch.draw(retry, retryButton.x, retryButton.y, retryButton.width, retryButton.height); // Retry button
        batch.draw(back, backButton.x, backButton.y, backButton.width, backButton.height);      // Back button
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport to maintain aspect ratio
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        retry.dispose();
        back.dispose();
    }
}
