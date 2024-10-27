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

public class LevelPage extends ScreenAdapter {
    Main main;
    Music BackGroundMusic;
    SpriteBatch batch;
    Texture background;
    Texture level1;
    Texture level2;
    Texture level3;
    Texture Setting;
    Texture Back;
    Texture PlayerName;
    Texture PlayerCoin;
    Rectangle BackButton;
    Rectangle Level1Button;
    Rectangle Level2Button;
    Rectangle Level3Button;
    Rectangle SettingButton;
    Music BackButtonSound;
    Music Level1ButtonSound;
    Music SettingButtonSound;

    // Declare Camera and Viewport
    OrthographicCamera camera;
    Viewport viewport;
    int worldWidth = 1920; // Adjust world width and height according to your game's design
    int worldHeight = 1080;

    public LevelPage(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        BackGroundMusic=Gdx.audio.newMusic(Gdx.files.internal("LevelMenuBackgroundMusic.mp3"));
        BackGroundMusic.setLooping(true);
        BackGroundMusic.setLooping(true);
        BackGroundMusic.setVolume(1);
        BackGroundMusic.play();
        background = new Texture(Gdx.files.internal("LevelpageBackground.png"));
        level1 = new Texture(Gdx.files.internal("Level1.png"));
        level2 = new Texture(Gdx.files.internal("Level2.png"));
        level3 = new Texture(Gdx.files.internal("Level3.png"));
        Setting = new Texture(Gdx.files.internal("Setting.png"));
        Back = new Texture(Gdx.files.internal("Back.png"));
        PlayerName = new Texture(Gdx.files.internal("Player Name.png"));
        PlayerCoin = new Texture(Gdx.files.internal("Player Coin.png"));
        BackButton = new Rectangle(100, 30, 125, 125);
        Level1Button = new Rectangle(230, 525, 125, 125);
        Level2Button = new Rectangle(500, 525, 125, 125);
        Level3Button = new Rectangle(740, 770, 125, 125);
        SettingButton = new Rectangle(1650, 50, 150, 150);
        BackButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        Level1ButtonSound=Gdx.audio.newMusic(Gdx.files.internal("EnterButtonSound.mp3"));
        SettingButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));

        // Create OrthographicCamera and FitViewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();

        // Position the camera in the middle of the screen
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera and set projection matrix
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight); // Make sure to scale for world size
        batch.draw(level1, 230, 525, 125, 125);
        batch.draw(level2, 500, 525, 125, 125);
        batch.draw(level3, 740, 770, 125, 125);
        batch.draw(Back, 100, 40, 125, 125);
        batch.draw(Setting, 1650, 50, 150, 150);
        batch.draw(PlayerName, 1450, 900, 450, 150);
        batch.draw(PlayerCoin, 100, 900, 150, 150);
        batch.end();

        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos); // Convert screen coordinates to world coordinates
            if (BackButton.contains(touchPos.x, touchPos.y)) {
                BackButtonSound.play();
                main.setScreen(new HomeScreen(main));
            }
            if(Level1Button.contains(touchPos.x, touchPos.y)) {
                Level1ButtonSound.play();
                main.setScreen(new Level1Screen(main));
            }else if(SettingButton.contains(touchPos.x,touchPos.y)){
                SettingButtonSound.play();
                main.setScreen(new Settings(main));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        // Update viewport on resize
        viewport.update(width, height);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0); // Re-center the camera
    }
    @Override
    public void hide(){
        if(BackGroundMusic!=null){
            BackGroundMusic.stop();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        BackGroundMusic.dispose();
        BackButtonSound.dispose();
        SettingButtonSound.dispose();
        Level1ButtonSound.dispose();
        background.dispose();
        level1.dispose();
        level2.dispose();
        level3.dispose();
        Setting.dispose();
        Back.dispose();
        PlayerName.dispose();
        PlayerCoin.dispose();
    }
}
