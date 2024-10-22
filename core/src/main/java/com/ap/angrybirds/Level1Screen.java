package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level1Screen extends ScreenAdapter {
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Main main;

    private RedBird redBird;
    private YellowBird yellowBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private MafiaPig mafiaPig;
    private WoodObstacles woodObstacle9;
    private WoodObstacles woodObstacle10;
    private WoodObstacles woodObstacle13;
    private Catapult catapult;

    private Texture RedBirdTexture;
    private Texture YellowBirdTexture;
    private Texture BlueBirdTexture;
    private Texture BlackBirdTexture;
    private Texture MafiaPigTexture;
    private Texture WoodObstacleTexture9;
    private Texture WoodObstacleTexture10;
    private Texture WoodObstacleTexture13;
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

        RedBirdTexture = new Texture(Gdx.files.internal("RedAngryBird.png"));
        YellowBirdTexture = new Texture(Gdx.files.internal("YellowAngryBird.png"));
        BlueBirdTexture=new Texture(Gdx.files.internal("BlueAngryBird.png"));
        BlackBirdTexture=new Texture(Gdx.files.internal("BlackAngryBird.png"));
        MafiaPigTexture = new Texture(Gdx.files.internal("MafiaPig.png"));
        WoodObstacleTexture9 = new Texture(Gdx.files.internal("9.png"));
        WoodObstacleTexture10 = new Texture(Gdx.files.internal("10.png"));
        WoodObstacleTexture13 = new Texture(Gdx.files.internal("13.png"));

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
        mafiaPig = new MafiaPig(MafiaPigTexture);
        mafiaPig.setPosition(1400, 183);  // Set Mafia Pig near the right
        stage.addActor(mafiaPig);

        // Initialize and add Wood Obstacle to the stage
        woodObstacle9 = new WoodObstacles(WoodObstacleTexture9);
        woodObstacle9.setPosition(1000, 150);  // Place Wood Obstacle between bird and pig
        stage.addActor(woodObstacle9);
        woodObstacle10 = new WoodObstacles(WoodObstacleTexture10);
        woodObstacle10.setPosition(1000, 150);  // Place Wood Obstacle between bird and pig
        stage.addActor(woodObstacle10);
        woodObstacle13 = new WoodObstacles(WoodObstacleTexture13);
        woodObstacle13.setPosition(1000, 150);  // Place Wood Obstacle between bird and pig
        stage.addActor(woodObstacle13);


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
        batch.end();

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
        yellowBird.dispose();
        blueBird.dispose();
        blackBird.dispose();
        mafiaPig.dispose();
        woodObstacle9.dispose();
        woodObstacle10.dispose();
        woodObstacle13.dispose();
        CatapultTexture.dispose();
        BackgroundTexture.dispose();
        batch.dispose();  // Don't forget to dispose the SpriteBatch
    }
}
