package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoadingPage extends ScreenAdapter {

    Main main;
    ShapeRenderer shapeRenderer;
    Sprite sprite;
    Texture image;
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;

    float worldWidth = 1920;
    float worldHeight = 1080;
    float lineWidth = 0; // Initial width of the loading line
    float maxLineWidth; // Maximum line width (screen width)
    float loadingPercentage = 0; // Starting percentage
    float timer;

    public LoadingPage(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        // Set up the camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        // Initialize renderers and textures
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        image = new Texture("loadingpage.jpg");
        sprite = new Sprite(image);
        sprite.setPosition(worldWidth / 2 - sprite.getWidth() / 2, worldHeight / 2 - sprite.getHeight() / 2);

        // Set maximum line width based on screen width
        maxLineWidth = worldWidth;
        timer = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background image
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();

        // Update line width and loading percentage
        lineWidth += (maxLineWidth / 4) * delta; // Slower speed for loading line
        loadingPercentage = Math.min((lineWidth / maxLineWidth) * 100, 100); // Percentage

        // Draw the loading line at a lower y-axis position
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(0, 100, lineWidth, 20); // Move line lower by adjusting y position
        shapeRenderer.end();

        // Draw the percentage text
        batch.begin();
        main.font.draw(batch, (int) loadingPercentage + "%", worldWidth / 2 - 25, 150); // Adjusted text position for new line height
        batch.end();

        // Check if loading is complete
        if (loadingPercentage >= 100) {
            main.setScreen(new HomeScreen(main));  // Move to the next screen
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        image.dispose();
        batch.dispose();
    }
}
