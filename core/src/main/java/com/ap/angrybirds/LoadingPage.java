package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
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

import javax.script.ScriptEngineManager;
import java.util.jar.Manifest;


public class LoadingPage extends ScreenAdapter {

    Main main;
    public LoadingPage(Main main){
        this.main = main;
    }
    ShapeRenderer shapeRenderer;
    Sprite sprite;
    Texture image;
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;

    float speedX = 170;

    float rectX;
    float rectY;
    float timer;

    float worldWidth = 1920; // Define the world width
    float worldHeight = 1080; // Define the world height

    @Override
    public void show () {
        // Create a camera with world size and set up the viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        image = new Texture("angryb.png");
        sprite = new Sprite(image);

        // Convert pixel-based sprite positioning to world coordinates
        sprite.setScale(0.8f);
        sprite.setPosition(worldWidth / 2 - sprite.getWidth() / 2, worldHeight / 2 - sprite.getHeight() / 2);
        sprite.setAlpha(0.8f);

        // Set the rectangle (loading bar) position using world coordinates
        rectX = worldWidth / 2 - 200;
        rectY = 100;

        timer = 0;

    }

    float width = 0;

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera and apply it to the batch
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        // Draw loading bar using world coordinates
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0f, 0f, 0f, 1);
        shapeRenderer.arc(worldWidth / 2 - 200, rectY+20, 30, 90, 180); // Outer arc (left)
        shapeRenderer.arc(worldWidth / 2 + 200, rectY+20, 30, 270, 180); // Outer arc (right)
        shapeRenderer.rect(rectX, rectY + 40, 400, 10); // Top rectangle of loading bar
        shapeRenderer.rect(rectX, rectY - 10, 400, 10); // Bottom rectangle of loading bar
        shapeRenderer.end();

        if (width < 400) {
            width += speedX * Gdx.graphics.getDeltaTime();
        } else {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0.9f, 0.9f, 0.9f, 1);
            shapeRenderer.arc(worldWidth / 2 + 200, rectY+20, 20, 270, 180); // Right inner arc after loading completion
            shapeRenderer.end();
        }

        // Draw the actual loading progress bar using the `width`
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.9f, 0.9f, 0.9f, 1);
        shapeRenderer.arc(worldWidth / 2 - 200, rectY+20, 20, 90, 180); // Left inner arc
        shapeRenderer.rect(rectX, rectY, width, 40); // Progress rectangle
        shapeRenderer.end();

        timer += delta;

        if (timer > 2.358) {
            main.setScreen(new HomeScreen(main));  // Change to the next screen
        }
    }

    @Override
    public void resize(int width, int height) {
        // Adjust viewport on resize to maintain aspect ratio
        viewport.update(width, height);
    }

    @Override
    public void dispose () {
        shapeRenderer.dispose();
        image.dispose();
        batch.dispose();
    }
}
