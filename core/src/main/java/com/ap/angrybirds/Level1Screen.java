package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level1Screen extends ScreenAdapter {
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    Main main;
    private RedBird redBird;
    private MafiaPig mafiaPig;
    private WoodObstacles woodObstacle;
    private Catapult catapult;
    private Texture RedBirdTexture;
    private Texture MafiaPigTexture;
    private Texture WoodObstacleTexture;
    private Texture CatapultTexture;
    public  Level1Screen(Main main){
        this.main=main;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        stage = new Stage(viewport);

        // Initialize and add Red Bird to the stage
        RedBirdTexture = new Texture(Gdx.files.internal("RedAngryBird.png"));
        redBird = new RedBird(RedBirdTexture);
        redBird.setPosition(200, 150);  // Set the bird's starting position
        stage.addActor(redBird);

        // Initialize and add Mafia Pig to the stage
        MafiaPigTexture=new Texture("MafiaPig.png");
        mafiaPig = new MafiaPig(MafiaPigTexture);
        mafiaPig.setPosition(1400, 150);  // Set Mafia Pig near the right
        stage.addActor(mafiaPig);

        // Initialize and add Wood Obstacle to the stage
        woodObstacle = new WoodObstacles(WoodObstacleTexture);
        woodObstacle.setPosition(1000, 150);  // Place Wood Obstacle between bird and pig
        stage.addActor(woodObstacle);
        CatapultTexture = new Texture(Gdx.files.internal("Catapult.png"));

        // Create the Catapult instance and pass the texture
        catapult = new Catapult(CatapultTexture);

        // Set catapult position (optional)
        catapult.setPosition(100, 100);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the stage and render all actors
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
        woodObstacle.dispose();
    }


}
