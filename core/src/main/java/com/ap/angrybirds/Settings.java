package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Settings extends ScreenAdapter {
    Main main;  // Important Attributes
    private Texture Background;
    private Texture settingsBackground;
    private Texture sound;
    private Texture music;
    private Texture sound_word;
    private Texture music_word;
    private Texture BackToHomePage;
    private Texture BackToLevelMenu;
    private Texture info;
    private Texture infoText;
    private Texture blueBox;
    private Music EnterSound;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    Rectangle BackToHomepageButton, BackToLevelMenuButton;
    public Settings(Main main) {
        this.main = main;
    } // Constructor
    public void show(){ // Show method to create all the attributes
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        viewport.apply();
        camera.position.set(1920 / 2, 1080 / 2, 0);
        camera.update();
        batch = new SpriteBatch();
        Background = new Texture("settingsBackground.jpg");
        settingsBackground = new Texture("settingBox.png");
        sound = new Texture("sound.png");
        music = new Texture("music.png");
        info = new Texture("InfoButton.png");
        sound_word = new Texture("soundWord.png");
        music_word = new Texture("musicWord.png");
        infoText = new Texture("infoText.png");
        BackToHomePage = new Texture("BackToHomePage.png");
        BackToLevelMenu = new Texture("BackToLevelMenu.png");
        blueBox = new Texture("blueBox.png");
        EnterSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        BackToHomepageButton = new Rectangle(740, 436, 500, 100);
        BackToLevelMenuButton = new Rectangle(740, 310, 500, 100);
    }

    public void render(float delta){ // Rendering
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(Background, 0, 0);
        batch.draw(settingsBackground, 400, 80, 1200,1000);
        batch.draw(music, 710, 660, 125,125);
        batch.draw(sound, 920, 660, 125,125);
        batch.draw(info, 1130, 655, 130,125);
        batch.draw(music_word, 710, 620, 127,33);
        batch.draw(sound_word, 920, 620, 127,33);
        batch.draw(infoText, 1130, 613, 127,40);
        batch.draw(blueBox, 740, 436, 500, 100);
        batch.draw(blueBox, 740, 310, 500, 100);
        batch.draw(BackToHomePage, 715, 450, 550,85);
        batch.draw(BackToLevelMenu, 685, 320, 580,80);
        batch.end();
        //Checking User Input
        if(Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            if (BackToHomepageButton.contains(touchPos.x, touchPos.y)) {
                EnterSound.play();
                main.setScreen(new HomeScreen(main)); // switch to Homescreen
            }else if (BackToLevelMenuButton.contains(touchPos.x, touchPos.y)) {
                EnterSound.play();
                main.setScreen(new LevelPage(main)); // switch ot Levelpage
            }
        }


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    } //Resizing

    public void dispose(){ // Disposing
        batch.dispose();
        EnterSound.dispose();
        Background.dispose();
        settingsBackground.dispose();
    }

}
