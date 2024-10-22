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
    private MafiaPig mafiaPig;
//    private WoodObstacles woodObstacle;
    private Catapult catapult;

    private Texture RedBirdTexture;
    private Texture MafiaPigTexture;
//    private Texture WoodObstacleTexture;
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
        MafiaPigTexture = new Texture(Gdx.files.internal("MafiaPig.png"));
//        WoodObstacleTexture = new Texture(Gdx.files.internal("WoodObstacle.png"));
        CatapultTexture = new Texture(Gdx.files.internal("Catapult.png"));

        // Initialize and add Red Bird to the stage
        redBird = new RedBird(RedBirdTexture);
        redBird.setPosition(200, 150);  // Set the bird's starting position
        stage.addActor(redBird);

        // Initialize and add Mafia Pig to the stage
        mafiaPig = new MafiaPig(MafiaPigTexture);
        mafiaPig.setPosition(1400, 150);  // Set Mafia Pig near the right
        stage.addActor(mafiaPig);

        // Initialize and add Wood Obstacle to the stage
//        woodObstacle = new WoodObstacles(WoodObstacleTexture);
//        woodObstacle.setPosition(1000, 150);  // Place Wood Obstacle between bird and pig
//        stage.addActor(woodObstacle);

        // Initialize and add Catapult to the stage
        catapult = new Catapult(CatapultTexture);
        catapult.setPosition(100, 100);
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
        mafiaPig.dispose();
//        woodObstacle.dispose();
        CatapultTexture.dispose();
        BackgroundTexture.dispose();
        batch.dispose();  // Don't forget to dispose the SpriteBatch
    }
}
