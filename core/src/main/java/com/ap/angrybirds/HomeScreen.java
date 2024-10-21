package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HomeScreen extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture AngryLevel;
    Texture PlayButton;
    Texture PlayerName;
    Texture Setting;
    Texture Back;
    Texture PlayerCoin;
    Texture AngryLevelText;
    Texture RedAngryBird;
    Texture YellowAngryBird;
    Texture BasicButton;
    Texture BasicSelection;
    Texture View;
    Texture AngryStory;
    Texture AngryHighlights;

    OrthographicCamera camera;
    Viewport viewport;

    float worldWidth = 1920;
    float worldHeight = 1080;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("homeScreen background.jpg");
        AngryLevel = new Texture("Angry Level.png");
        PlayButton = new Texture("Play.png");
        PlayerName = new Texture("Player Name.png");
        Setting = new Texture("Setting.png");
        Back = new Texture("Back.png");
        PlayerCoin = new Texture("Player Coin.png");
        AngryLevelText = new Texture("AngryLevel Text.png");
        RedAngryBird = new Texture("RedAngryBird.png");
        YellowAngryBird = new Texture("YellowAngryBird.png");
        BasicButton = new Texture("BasicButton.png");
        BasicSelection = new Texture("BasicSelection.png");
        View = new Texture("View.png");
        AngryStory = new Texture("AngryStory.png");
        AngryHighlights = new Texture("AngryHighlights.png");

        // Create camera and viewport with fixed world dimensions
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();

        // Enable blending for transparency
        batch.enableBlending();
    }

    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // Update all draw positions to world coordinates
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        batch.draw(AngryLevel, worldWidth / 2 - 165, worldHeight / 2 - 200, 400, 550);
        batch.draw(Back, 100, 100, 150, 150);
        batch.draw(Setting, worldWidth - 250, 100, 150, 150);
        batch.draw(PlayerName, worldWidth - 425, 850, 350, 150);
        batch.draw(PlayerCoin, 100, 850, 150, 150);
        batch.draw(PlayButton, worldWidth / 2 - 72, 355, 225, 85);
        batch.draw(AngryLevelText, worldWidth / 2 - 95, 785, 250, 50);
        batch.draw(BasicSelection, 200, 300, 400, 550);
        batch.draw(BasicSelection, worldWidth - 520, 300, 400, 550);
        batch.draw(RedAngryBird, 290, 500, 200, 200);
        batch.draw(YellowAngryBird, worldWidth - 445, 490, 250, 250);
        batch.draw(BasicButton, 275, 315, 225, 85);
        batch.draw(BasicButton, worldWidth - 430, 315, 225, 85);
        batch.draw(View, 340, 330, 120, 50);
        batch.draw(View, worldWidth - 360, 330, 120, 50);
        batch.draw(AngryStory, 280, 750, 250, 50);
        batch.draw(AngryHighlights, worldWidth - 440, 750, 250, 50);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        AngryLevel.dispose();
        PlayButton.dispose();
        PlayerName.dispose();
        Setting.dispose();
        Back.dispose();
        PlayerCoin.dispose();
        BasicButton.dispose();
        BasicSelection.dispose();
        View.dispose();
        AngryStory.dispose();
        AngryHighlights.dispose();
    }
}
