package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class HomeScreen extends ScreenAdapter {
    Main main; // Important Attributes of type Main, Texture,Rectangle and Music
    Music BackGroundMusic;;
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
    Texture LoadGame;
    Texture Exit;
    Rectangle Play_Button,SettingButton,BackButton,PlayerCoinButton,ViewButton1,ViewButton2,ExitButton;
    Music ExitButtonSound;
    Music PlayButtonSound;
    Music SettingButtonSound;

    OrthographicCamera camera;
    Viewport viewport;

    int worldWidth = 1920;
    int worldHeight = 1080;
    public HomeScreen(Main main){
        this.main = main;
    } // Constructor

    @Override
    public void show() { // Show method to create all the Attributes
        batch = new SpriteBatch();
        BackGroundMusic=Gdx.audio.newMusic(Gdx.files.internal("AngryBirdTheme.mp3"));
        BackGroundMusic.setLooping(true);
        BackGroundMusic.setLooping(true);
        BackGroundMusic.setVolume(1);
        BackGroundMusic.play();
        background = new Texture("homeScreen background.jpg");
        AngryLevel = new Texture("Angry Level.png");
        PlayButton = new Texture("Play.png");
        PlayerName = new Texture("Player Name.png");
        Setting = new Texture("Setting.png");
        Back = new Texture("Back.png");
        Exit = new Texture("Exit.png");
        PlayerCoin = new Texture("Player Coin.png");
        AngryLevelText = new Texture("AngryLevel Text.png");
        RedAngryBird = new Texture("RedAngryBird.png");
        YellowAngryBird = new Texture("YellowAngryBird.png");
        BasicButton = new Texture("BasicButton.png");
        BasicSelection = new Texture("BasicSelection.png");
        View = new Texture("View.png");
        AngryStory = new Texture("AngryStory.png");
        LoadGame = new Texture("Load Game.png");
        Play_Button = new Rectangle(worldWidth/2-72,355,225,85);
        SettingButton = new Rectangle(1650, 50, 150, 150);
        PlayerCoinButton = new Rectangle(100,850,150,150);
        ViewButton1 = new Rectangle(340,330,120,50);
        ViewButton2 = new Rectangle(worldWidth-360,330,120,50);
        ExitButton = new Rectangle(130,180,150,50);
        ExitButtonSound=Gdx.audio.newMusic(Gdx.files.internal("EnterButtonSound.mp3"));
        PlayButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        SettingButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        camera = new OrthographicCamera(); // Create camera and viewport with fixed world dimensions
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
    }
    @Override
    public void render(float delta) { //Rendering
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Update all draw positions to world coordinates
        batch.draw(background, 0, 0, worldWidth, worldHeight);
        batch.draw(AngryLevel, worldWidth / 2 - 165, worldHeight / 2 - 200, 400, 550);
        batch.draw(Setting, 1650, 50, 150, 150);
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
        batch.draw(LoadGame, worldWidth - 440, 750, 250, 50);
        batch.draw(BasicButton,100,140,200,100);
        batch.draw(Exit,130,170,150,40);
        batch.end();
        // Checking User Inputs
        if(Gdx.input.isTouched()){
           Vector2 touchPos=new Vector2(Gdx.input.getX(),Gdx.input.getY());
          viewport.unproject(touchPos);
           if(Play_Button.contains(touchPos.x,touchPos.y)){
               PlayButtonSound.play();
                main.setScreen(new LevelPage(main));
            }
           else if(ExitButton.contains(touchPos.x,touchPos.y)){
               ExitButtonSound.play();
               Gdx.app.exit(); // exiting the program
           }else if(SettingButton.contains(touchPos.x,touchPos.y)){
               SettingButtonSound.play();
               main.setScreen(new Settings(main));
           }
       }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    } //Resizing
    @Override
    public void hide(){ // stopping Music
        if(BackGroundMusic!=null){
            BackGroundMusic.stop();
        }
    }

    @Override
    public void dispose() { //Disposing all thing
        batch.dispose();
        BackGroundMusic.dispose();
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
        LoadGame.dispose();
        PlayButtonSound.dispose();
        ExitButtonSound.dispose();
        SettingButtonSound.dispose();
    }
}
