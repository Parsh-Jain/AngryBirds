package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

import javax.swing.*;

public class SuccessfulEndScreen extends ApplicationAdapter {

    SpriteBatch batch;
    Texture background;
    Texture levelSelectionMenu;
    Texture text;
    Rectangle levelSelectionMenuButton;

    OrthographicCamera camera;
    Viewport viewport;

    float worldWidth = 1920;
    float worldHeight = 1080;


    @Override
    public void create(){
        batch = new SpriteBatch();
        background = new Texture("SuccessfullBackground.png");
        text = new Texture("LevelSelectionMenu.png");
        levelSelectionMenu = new Texture("BasicButton.png");
        levelSelectionMenuButton = new Rectangle(100,100,500,100);

        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(levelSelectionMenu, 100, 100 );
        batch.draw(text, 100, 100,600,200);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
