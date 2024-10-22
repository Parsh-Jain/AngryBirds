package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private WoodObstacles woodObstacle13a;
    private WoodObstacles woodObstacle13b;
    private WoodObstacles woodObstacle13c;
    private WoodObstacles woodObstacle13d;
    private WoodObstacles woodObstacle13e;
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
    private Texture WoodObstacleTexture13a;
    private Texture WoodObstacleTexture13b;
    private Texture WoodObstacleTexture13c;
    private Texture WoodObstacleTexture13d;
    private Texture WoodObstacleTexture13e;
    private Texture WoodObstacleTexture14;
    private Texture BackgroundTexture;
    private Texture CatapultTexture;

    private SpriteBatch batch; // SpriteBatch to draw the background

    public Level1Screen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();  // Initialize the batch

        // Load the textures
        BackgroundTexture = new Texture("GameScreenBackground.png");
        pauseButtonTexture=new Texture("PauseButton.png");
        endbuttonTexture=new Texture("EndLevelButton.png");
        pauseButton=new Rectangle(50,900,100,100);
        endbutton=new Rectangle(1700,50,200,100);
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
        WoodObstacleTexture13a = new Texture("13.png");
        WoodObstacleTexture13b = new Texture("13.png");
        WoodObstacleTexture13c = new Texture("13.png");
        WoodObstacleTexture13d = new Texture("13.png");
        WoodObstacleTexture13e = new Texture("13.png");
        WoodObstacleTexture14 = new Texture("Block.png");

        CatapultTexture = new Texture(Gdx.files.internal("Catapult.png"));

        // Initialize and add Red Bird to the stage
        redBird = new RedBird(RedBirdTexture);
        redBird.setPosition(400, 450);  // Set the bird's starting position
        stage.addActor(redBird);
        yellowBird = new YellowBird(YellowBirdTexture);
        yellowBird.setPosition(150, 179);  // Set the bird's starting position
        stage.addActor(yellowBird);
        blackBird = new BlackBird(BlackBirdTexture);
        blackBird.setPosition(220, 181);  // Set the bird's starting position
        stage.addActor(blackBird);
        blueBird = new BlueBird(BlueBirdTexture);
        blueBird.setPosition(290, 181);  // Set the bird's starting position
        stage.addActor(blueBird);

        // Initialize and add Mafia Pig to the stage
        mafiaPig1 = new MafiaPig(MafiaPig1Texture);
        mafiaPig1.setPosition(1400, 183);  // Set Mafia Pig near the right
        stage.addActor(mafiaPig1);
        mafiaPig2 = new MafiaPig(MafiaPig2Texture);
        mafiaPig2.setPosition(1400, 183);  // Set Mafia Pig near the right
        stage.addActor(mafiaPig2);
        mafiaPig3 = new MafiaPig(MafiaPig3Texture);
        mafiaPig3.setPosition(1400, 183);  // Set Mafia Pig near the right
        stage.addActor(mafiaPig3);
        mafiaPig4 = new MafiaPig(MafiaPig4Texture);
        mafiaPig4.setPosition(1400, 183);  // Set Mafia Pig near the right
        stage.addActor(mafiaPig4);

        // Initialize and add Wood Obstacle to the stage
        woodObstacle9a = new WoodObstacles(WoodObstacleTexture9a);
        woodObstacle9a.setPosition(1000, 150);  // Place Wood Obstacle between bird and pig
        woodObstacle9a.setSize(40,150);
        stage.addActor(woodObstacle9a);
        woodObstacle9b = new WoodObstacles(WoodObstacleTexture9b);
        woodObstacle9b.setPosition(1000, 200);  // Place Wood Obstacle between bird and pig
        woodObstacle9b.setSize(40,150);
        stage.addActor(woodObstacle9b);
        woodObstacle13a = new WoodObstacles(WoodObstacleTexture13a);
        woodObstacle13a.setPosition(1400, 150);  // Place Wood Obstacle between bird and pig
        woodObstacle13a.setSize(40,300);
        stage.addActor(woodObstacle13a);
        woodObstacle13b = new WoodObstacles(WoodObstacleTexture13b);
        woodObstacle13b.setPosition(1400, 200);  // Place Wood Obstacle between bird and pig
        woodObstacle13b.setSize(40,300);
        stage.addActor(woodObstacle13b);
        woodObstacle13c = new WoodObstacles(WoodObstacleTexture13c);
        woodObstacle13c.setPosition(1400, 250);  // Place Wood Obstacle between bird and pig
        woodObstacle13c.setSize(40,300);
        stage.addActor(woodObstacle13c);
        woodObstacle13d = new WoodObstacles(WoodObstacleTexture13d);
        woodObstacle13d.setPosition(1400, 300);  // Place Wood Obstacle between bird and pig
        woodObstacle13d.setSize(40,300);
        stage.addActor(woodObstacle13d);
        woodObstacle13e = new WoodObstacles(WoodObstacleTexture13e);
        woodObstacle13e.setPosition(1400, 350);  // Place Wood Obstacle between bird and pig
        woodObstacle13e.setSize(40,300);
        stage.addActor(woodObstacle13e);

        woodObstacle14 = new WoodObstacles(WoodObstacleTexture14);
        woodObstacle14.setPosition(1400, 400);  // Place Wood Obstacle between bird and pig
        woodObstacle14.setSize(140,140);
        stage.addActor(woodObstacle14);


        // Initialize and add Catapult to the stage
        catapult = new Catapult(CatapultTexture);
        catapult.setPosition(300, 183);
        stage.addActor(catapult);

        // Set the input processor for handling input events
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background image using SpriteBatch
        batch.begin();
        batch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.draw(pauseButtonTexture,50,900,100,100);
        batch.draw(endbuttonTexture,1700,50,200,100);
        batch.end();
        if(Gdx.input.isTouched()){
            Vector2 touchPos=new Vector2(Gdx.input.getX(),Gdx.input.getY());
            viewport.unproject(touchPos);
            if(pauseButton.contains(touchPos.x,touchPos.y)){
                main.setScreen(new LevelPage(main));
            } else if (endbutton.contains(touchPos.x,touchPos.y)) {
                main.setScreen(new SuccessfulEndScreen(main));

            } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                main.setScreen(new LoseEndScreen(main));
            }
        }



        // Update the stage and render all actors (red bird, pig, obstacles, etc.)
        stage.act(delta);
        stage.draw();
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
        woodObstacle13a.dispose();
        woodObstacle13b.dispose();
        woodObstacle13c.dispose();
        woodObstacle13d.dispose();
        woodObstacle13e.dispose();
        woodObstacle14.dispose();
        CatapultTexture.dispose();
        BackgroundTexture.dispose();
        batch.dispose();
    }
}
