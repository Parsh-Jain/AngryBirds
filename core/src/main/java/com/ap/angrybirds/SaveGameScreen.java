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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SaveGameScreen extends ScreenAdapter {
    Main main;  // Important Attributes
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
    int worldWidth = 1920;
    int worldHeight = 1080;
    public SaveGameScreen(Main main) {
        this.main = main;
    }
    @Override
    public void show() { // Show method for creating all the attributes
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
        Level1ButtonSound=Gdx.audio.newMusic(Gdx.files.internal("ExitButtonSound.mp3"));
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
    public void render(float delta) { // Rendering
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Update camera and set projection matrix
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        batch.draw(level1, 230, 525, 125, 125);
        batch.draw(level2, 500, 525, 125, 125);
        batch.draw(level3, 740, 770, 125, 125);
        batch.draw(Back, 100, 40, 125, 125);
        batch.draw(Setting, 1650, 50, 150, 150);
        batch.draw(PlayerName, 1450, 900, 450, 150);
        batch.draw(PlayerCoin, 100, 900, 150, 150);
        batch.end();
        // Checking User Input
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            if (BackButton.contains(touchPos.x, touchPos.y)) {
                BackButtonSound.play();
                main.setScreen(new HomeScreen(main));  // switch to Homescreen
            }
            if(Level1Button.contains(touchPos.x, touchPos.y)) {
//                loadGameState();
                main.setScreen(new l(main,loadGameState())); // switch to Level1screen
            }else if(SettingButton.contains(touchPos.x,touchPos.y)){
                SettingButtonSound.play();
                main.setScreen(new Settings(main)); // switch to setting screen
            }else if(Level2Button.contains(touchPos.x,touchPos.y)){
                Level1ButtonSound.play();
                main.setScreen(new level2(main)); // switch to Level2screen
            }
        }
    }
    private GameState1 loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gamestate.ser"))) {
            GameState1 gameState = (GameState1) ois.readObject();
            System.out.println("Game state loaded successfully.");
            return gameState;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load game state.");
        }
        return null;
    }



    @Override
    public void resize(int width, int height) { // Resizing
        // Update viewport on resize
        viewport.update(width, height);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0); // Re-center the camera
    }
    @Override
    public void hide(){ // Stopping the music
        if(BackGroundMusic!=null){
            BackGroundMusic.stop();
        }
    }

    @Override
    public void dispose() { // Disposing
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
