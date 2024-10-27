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
public class SuccessfulEndScreen extends ScreenAdapter {
    Main main; // Important Attributes
    SpriteBatch batch;
    Texture background;
    Texture levelMenu;
    Texture text;
    Rectangle levelMenu_Button;
    Music LevelButtonSound;
    OrthographicCamera camera;
    Viewport viewport;
    float worldWidth = 1920;
    float worldHeight = 1080;
    public SuccessfulEndScreen(Main main){
        this.main = main;
    } // Constructor
    @Override
    public void show(){ // show method to create all the attributes
        batch = new SpriteBatch();
        background = new Texture("SuccessfullBackground.png");
        text = new Texture("Level Menu.png");
        levelMenu = new Texture("BasicButton.png");
        levelMenu_Button = new Rectangle(690,45,520,122);
        LevelButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
    }

    @Override
    public void render(float delta) { // Rendering
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(levelMenu,690,45,520,122);
        batch.draw(text, 755, 65,400,80);
        batch.end();
        // Checking User Input
        if(Gdx.input.isTouched()){
            Vector2 touchPos=new Vector2(Gdx.input.getX(),Gdx.input.getY());
            viewport.unproject(touchPos);
            if(levelMenu_Button.contains(touchPos.x,touchPos.y)){
                LevelButtonSound.play();
                main.setScreen(new LevelPage(main)); // switch to Levelpage
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    } // Resizing

    @Override
    public void dispose() {
        super.dispose();
    } // Disposing
}
