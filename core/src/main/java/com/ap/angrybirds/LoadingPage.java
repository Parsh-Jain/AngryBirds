package com.ap.angrybirds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    Main main; // Important Attributes
    ShapeRenderer shapeRenderer;
    Sprite sprite;
    Texture image;
    Batch batch;
    OrthographicCamera camera;
    Viewport viewport;
    float worldWidth = 1920;
    float worldHeight = 1080;
    float lineWidth = 0;
    float maxLineWidth;
    float loadingPercentage = 0;
    float timer;
    public LoadingPage(Main main) {
        this.main = main;
    }
    @Override
    public void show() { // Show method for creating all the Attributes
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        image = new Texture("loadingpage.jpg");
        sprite = new Sprite(image);
        sprite.setPosition(worldWidth / 2 - sprite.getWidth() / 2, worldHeight / 2 - sprite.getHeight() / 2);
        maxLineWidth = worldWidth;
        timer = 0;
    }
    @Override
    public void render(float delta) { // Rendering
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        lineWidth += (maxLineWidth / 4) * delta;
        loadingPercentage = Math.min((lineWidth / maxLineWidth) * 100, 100);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(0, 100, lineWidth, 20);
        shapeRenderer.end();
        batch.begin();
        main.font.draw(batch, (int) loadingPercentage + "%", worldWidth / 2 - 25, 150);
        batch.end();
        if (loadingPercentage >= 100) {
            main.setScreen(new HomeScreen(main)); // switch to HomeScreen
        }if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            main.setScreen(new l(main));
        }
    }
    @Override
    public void resize(int width, int height) { // Resizing
        viewport.update(width, height);
    }
    @Override
    public void dispose() { // Disposing
        shapeRenderer.dispose();
        image.dispose();
        batch.dispose();
    }
}
