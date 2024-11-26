package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
public class LoseEndScreen extends ScreenAdapter {
    Main main; // Important Attributes
    SpriteBatch batch;
    Texture background;
    Texture retry;
    Texture back;
    Rectangle retryButton;
    Rectangle backButton;
    Music BackButtonSound;
    Music RetryButtonSound;
    OrthographicCamera camera;
    Viewport viewport;
    float worldWidth = 1920;
    float worldHeight = 1080;
    public LoseEndScreen(Main main) {  // Constructor
        this.main = main;
    }
    @Override
    public void show() { // Show method for creating the attributes
        batch = new SpriteBatch();
        background = new Texture("LosingPage.jpg");
        retry = new Texture("retry.png");
        back = new Texture("Back.png");
        retryButton = new Rectangle(725, 0, 490, 220);
        backButton = new Rectangle(80, 500, 140, 140);
        BackButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        RetryButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }
    @Override
    public void render(float delta) { // Rendering
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight); // Full screen background
        batch.draw(retry, retryButton.x, retryButton.y, retryButton.width, retryButton.height); // Retry button
        batch.draw(back, backButton.x, backButton.y, backButton.width, backButton.height);      // Back button
        batch.end();
        // Checking User Input
        if(Gdx.input.isTouched()){
            Vector2 touchPos=new Vector2(Gdx.input.getX(),Gdx.input.getY());
            viewport.unproject(touchPos);
            if(retryButton.contains(touchPos.x,touchPos.y)){
                RetryButtonSound.play();
                main.setScreen(new l(main)); // switch to Level1Screen
            }
            else if(backButton.contains(touchPos.x,touchPos.y)){
                BackButtonSound.play();
                main.setScreen(new LevelPage(main)); // Switch to LevelPage

            }
        }

    }
    @Override
    public void resize(int width, int height) { // Resizing
        // Update the viewport to maintain aspect ratio
        viewport.update(width, height);
    }
    @Override
    public void dispose() { //Disposing
        batch.dispose();
        BackButtonSound.dispose();
        RetryButtonSound.dispose();

























        background.dispose();
        retry.dispose();
        back.dispose();
    }
}
