package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.awt.*;
public class Level1Screen extends ScreenAdapter {
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Main main;
    private Rectangle pauseButton;
    private Rectangle endbutton;
    private Rectangle resumeButton;
    private Rectangle restartLevelButton;
    private Rectangle EndButton2;
    Music BackgroundMusic;


    private RedBird redBird;
    private YellowBird yellowBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private MafiaPig mafiaPig1;
    private MafiaPig mafiaPig2;
    private MafiaPig mafiaPig3;
    private MafiaPig mafiaPig4;
    private WoodObstacles woodObstacle9a;
    private WoodObstacles woodObstacle9b;
    private WoodObstacles woodObstacle13c;
    private WoodObstacles woodObstacle13d;
    private WoodObstacles woodObstacle14;
    private Catapult catapult;
    private Texture pauseButtonTexture;
    private Texture endbuttonTexture;
    private Texture RedBirdTexture;
    private Texture YellowBirdTexture;
    private Texture BlueBirdTexture;
    private Texture BlackBirdTexture;
    private Texture MafiaPig1Texture;
    private Texture MafiaPig2Texture;
    private Texture MafiaPig3Texture;
    private Texture MafiaPig4Texture;
    private Texture WoodObstacleTexture9a;
    private Texture WoodObstacleTexture9b;
    private Texture WoodObstacleTexture13_Ha;
    private Texture WoodObstacleTexture13_Hb;
    private Texture WoodObstacleTexture14;
    private Texture BackgroundTexture;
    private Texture CatapultTexture;
    private Texture DulledBackground;
    private boolean isPaused;

    private Texture resumeButtontexture;
    private Texture restartLevelTexture;
    private Texture musicButtonTexture;
    private Texture soundButtonTexture;
    private Texture EndButton2Texture;
    private SpriteBatch batch; // SpriteBatch to draw the background
    public Level1Screen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        BackgroundMusic=Gdx.audio.newMusic(Gdx.files.internal("LevelScreen1BackgroundMusic.mp3"));// Initialize the batch
        BackgroundMusic.setVolume(1);
        BackgroundMusic.play();
        BackgroundTexture = new Texture("GameScreenBackground.png");
        DulledBackground = new Texture("DulledBackground.png");
        pauseButtonTexture=new Texture("PauseButton.png");
        pauseButton=new Rectangle(50,900,100,100);
        endbuttonTexture=new Texture("EndLevelButton.png");
        endbutton=new Rectangle(1700,50,200,100);
        EndButton2Texture=new Texture("EndLevelButton.png");
        EndButton2=new Rectangle(20,50,200,100);
        resumeButtontexture = new Texture("Resume.png");
        resumeButton = new Rectangle(800,480,250,250);
        restartLevelTexture = new Texture("RestartLevel.png");
        restartLevelButton = new Rectangle(50,660,100,100);
        restartLevelButton = new Rectangle(50,690,100,100);
//        gameLogo = new Texture("GameLogo.png");

        musicButtonTexture=new Texture("music.png");
        soundButtonTexture=new Texture("sound.png");
        RedBirdTexture = new Texture("RedAngryBird.png");
        YellowBirdTexture = new Texture("YellowAngryBird.png");
        BlueBirdTexture=new Texture("BlueAngryBird.png");
        BlackBirdTexture=new Texture("BlackAngryBird.png");
        MafiaPig1Texture = new Texture("MafiaPig.png");
        MafiaPig2Texture=new Texture("MafiaPig.png");
        MafiaPig3Texture=new Texture("MafiaPig.png");
        MafiaPig4Texture=new Texture("MafiaPig.png");
        WoodObstacleTexture9a = new Texture("9.png");
        WoodObstacleTexture9b = new Texture("9.png");
        WoodObstacleTexture13_Ha = new Texture("13_H.png");
        WoodObstacleTexture13_Hb = new Texture("13_H.png");
        WoodObstacleTexture14 = new Texture("Block.png");

        CatapultTexture = new Texture(Gdx.files.internal("Catapult.png"));
        redBird = new RedBird(RedBirdTexture);
        redBird.setPosition(480, 450);
        stage.addActor(redBird);
        yellowBird = new YellowBird(YellowBirdTexture);
        yellowBird.setPosition(190, 179);
        stage.addActor(yellowBird);
        blackBird = new BlackBird(BlackBirdTexture);
        blackBird.setPosition(260, 181);
        stage.addActor(blackBird);
        blueBird = new BlueBird(BlueBirdTexture);
        blueBird.setPosition(330, 181);
        stage.addActor(blueBird);
        mafiaPig1 = new MafiaPig(MafiaPig1Texture);
        mafiaPig1.setPosition(1335, 220);
        stage.addActor(mafiaPig1);
        mafiaPig2 = new MafiaPig(MafiaPig2Texture);
        mafiaPig2.setPosition(1415, 360);
        stage.addActor(mafiaPig2);
        mafiaPig3 = new MafiaPig(MafiaPig3Texture);
        mafiaPig3.setPosition(1415, 580);
        stage.addActor(mafiaPig3);
        mafiaPig4 = new MafiaPig(MafiaPig4Texture);
        mafiaPig4.setPosition(1490, 220);
        stage.addActor(mafiaPig4);


        woodObstacle13c = new WoodObstacles(WoodObstacleTexture13_Ha);
        woodObstacle13c.setPosition(1258, 320);
        woodObstacle13c.setSize(350,40);
        stage.addActor(woodObstacle13c);
        woodObstacle13d = new WoodObstacles(WoodObstacleTexture13_Hb);
        woodObstacle13d.setPosition(1258, 185);
        woodObstacle13d.setSize(350,40);
        stage.addActor(woodObstacle13d);

        woodObstacle9a = new WoodObstacles(WoodObstacleTexture9a);
        woodObstacle9a.setPosition(1260, 185);
        woodObstacle9a.setSize(40,175);
        stage.addActor(woodObstacle9a);
        woodObstacle9b = new WoodObstacles(WoodObstacleTexture9b);
        woodObstacle9b.setPosition(1600, 185);
        woodObstacle9b.setSize(40,175);
        stage.addActor(woodObstacle9b);

        woodObstacle9a = new WoodObstacles(WoodObstacleTexture9a);
        woodObstacle9a.setPosition(1310, 350);
        woodObstacle9a.setSize(40,175);
        stage.addActor(woodObstacle9a);
        woodObstacle9b = new WoodObstacles(WoodObstacleTexture9b);
        woodObstacle9b.setPosition(1550, 350);
        woodObstacle9b.setSize(40,175);
        stage.addActor(woodObstacle9b);

        woodObstacle13c = new WoodObstacles(WoodObstacleTexture13_Ha);
        woodObstacle13c.setPosition(1278, 510);
        woodObstacle13c.setSize(350,40);
        stage.addActor(woodObstacle13c);

        woodObstacle14 = new WoodObstacles(WoodObstacleTexture14);
        woodObstacle14.setPosition(1368, 545);
        woodObstacle14.setSize(170,170);
        stage.addActor(woodObstacle14);
        catapult = new Catapult(CatapultTexture);
        catapult.setPosition(300, 183);
        stage.addActor(catapult);
        Gdx.input.setInputProcessor(stage);
        isPaused = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(!isPaused){
            batch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(pauseButtonTexture, 50, 900, 100, 100);

            //render(0);
        }else{
            batch.draw(DulledBackground,0,0);
            batch.draw(resumeButtontexture, 800,480,250,250);
            batch.draw(restartLevelTexture,50, 690, 100, 100);
            batch.draw(musicButtonTexture, 50, 525,100,100);
            batch.draw(soundButtonTexture,50,360, 100, 100);
//            batch.draw(gameLogo, 30, 900,150,120);
            //render(0);
        }
        batch.draw(endbuttonTexture,1700,50,200,100);
        batch.draw(EndButton2Texture,20,50,200,100);
        batch.end();

        if(Gdx.input.isTouched()){
            Vector2 touchPos=new Vector2(Gdx.input.getX(),Gdx.input.getY());
            viewport.unproject(touchPos);
            if(pauseButton.contains(touchPos.x,touchPos.y)){
                isPaused = true;
            } else if (endbutton.contains(touchPos.x,touchPos.y)) {
                main.setScreen(new SuccessfulEndScreen(main));
            }
            else if(EndButton2.contains(touchPos.x,touchPos.y)){
                main.setScreen(new LoseEndScreen(main));
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                main.setScreen(new LoseEndScreen(main));
            }else if(resumeButton.contains(touchPos.x,touchPos.y)){
                isPaused = false;
            }
        }
        stage.act(delta);
        stage.draw();
    }
    @Override
    public void hide(){
        if(BackgroundMusic!=null){
            BackgroundMusic.stop();
        }
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        redBird.dispose();
        pauseButtonTexture.dispose();
        endbuttonTexture.dispose();
        yellowBird.dispose();
        blueBird.dispose();
        blackBird.dispose();
        mafiaPig1.dispose();
        mafiaPig2.dispose();
        mafiaPig3.dispose();
        mafiaPig4.dispose();
        woodObstacle9a.dispose();
        woodObstacle9b.dispose();
        woodObstacle13c.dispose();
        woodObstacle13d.dispose();
        woodObstacle14.dispose();
        CatapultTexture.dispose();
        BackgroundTexture.dispose();
        BackgroundMusic.dispose();
        EndButton2Texture.dispose();
        batch.dispose();
    }
}
