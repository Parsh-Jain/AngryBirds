package com.ap.angrybirds.learning;
import com.ap.angrybirds.*;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.physics.box2d.*;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.awt.*;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.WHITE;

public class l extends ScreenAdapter {
    private static  final float PPM=100f;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;  // Important Attributes
    private OrthographicCamera camera;
    private Viewport viewport;
    private Main main;
    private Rectangle pauseButton;
    private Rectangle endbutton;
    private Rectangle resumeButton;
    private Rectangle restartLevelButton;
    private Rectangle EndButton2;
    Music PauseButtonSound;
    Music ResumeButtonSound;
    Music EndButtonSound;
    private RedBird redBird;
    private YellowBird yellowBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private MafiaPig mafiaPig1;
    private MafiaPig mafiaPig2;
    private MafiaPig mafiaPig3;
    private MafiaPig mafiaPig4;
    private VerticalWood13 woodVertical1, woodVertical2;
    private Vector2 dragVector = new Vector2();
    private Vector2 slingPosition = new Vector2(480, 181);
    private Array<Vector2> trajectoryPoints = new Array<>();
    private ShapeRenderer shapeRenderer;
    private static final float TRAJECTORY_POINTS = 30;
    private boolean isDragging = false;
    private Body currentBirdBody;
    private float dragStartX, dragStartY;
    private float maxDragDistance = 100f;


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
    private SpriteBatch batch;
    public l(Main main) { // Constructor
        this.main = main;
    }
    @Override
    public void show() { // Show method to creating all the attributes
        world=new World(new Vector2(0,-9.8f),true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        PauseButtonSound=Gdx.audio.newMusic(Gdx.files.internal("PauseButtonSound.mp3"));
        ResumeButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        EndButtonSound=Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
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
        musicButtonTexture=new Texture("music.png");
        soundButtonTexture=new Texture("sound.png");
        MafiaPig1Texture = new Texture("MafiaPig.png");
        CatapultTexture = new Texture(Gdx.files.internal("Catapult.png"));
        slingPosition = new Vector2(560, 280); // Adjust to match your catapult position
        dragVector = new Vector2();
        trajectoryPoints = new Array<>();
        shapeRenderer = new ShapeRenderer();
//



        Body catapultBody = createCatapult(560, 280); // Adjusted position
        catapult = new Catapult(CatapultTexture, catapultBody);
        stage.addActor(catapult);

        //  catapult.setPosition(alignLeft(500)/PPM, alignBottom(190)/PPM);
        createGround();
        createBirds();
        currentBirdBody = redBird.getBody();
        createWoodObstacles();
        //createObstacles();
        setPigs();
        Gdx.input.setInputProcessor(stage);
            Gdx.input.setInputProcessor(new InputAdapter() {
                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                    Vector2 touchPos = new Vector2(screenX, screenY);
                    viewport.unproject(touchPos);

                    // Assign the bird being dragged
                    if (redBird.getBounds().contains(touchPos)) {
                        currentBirdBody = redBird.getBody();
                    }

                    isDragging = true;
                    dragStartX = touchPos.x;
                    dragStartY = touchPos.y;
                    return true;
                }

                @Override
                public boolean touchDragged(int screenX, int screenY, int pointer) {
                    if (isDragging) {
                        Vector2 touchPos = new Vector2(screenX, screenY);
                        viewport.unproject(touchPos);

                        dragVector.set(touchPos.x - dragStartX, touchPos.y - dragStartY);

                        // Limit the bird's movement within the slingshot range
                        float maxDragDistance = 100f / PPM; // Adjust this value as needed
                        if (dragVector.len() > maxDragDistance) {
                            dragVector.nor().scl(maxDragDistance);
                        }

                        // Update bird's position while dragging
                        if (currentBirdBody != null) {
                            currentBirdBody.setTransform(
                                slingPosition.x / PPM - dragVector.x,
                                slingPosition.y / PPM - dragVector.y,
                                0
                            );
                        }

                        // Calculate trajectory
                        calculateTrajectory(dragVector);
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                    if (isDragging) {
                        launchBird(dragVector);
                        isDragging = false;
                        return true;
                    }
                    return false;
                }
            });
        }


    //

    private float centerX(Texture texture) {
        return (viewport.getWorldWidth() - texture.getWidth()) / 2;
    }

    private float centerY(Texture texture) {
        return (viewport.getWorldHeight() - texture.getHeight()) / 2;
    }

    private float alignLeft(float margin) {
        return margin;
    }

    private float alignRight(Texture texture, float margin) {
        return viewport.getWorldWidth() - texture.getWidth() - margin;
    }

    private float alignBottom(float margin) {
        return margin;
    }

    private float alignTop(Texture texture, float margin) {
        return viewport.getWorldHeight() - texture.getHeight() - margin;
    }

    private void setPigs() {
        Body mafiapig1body = createPig(1460 / PPM, 220 / PPM);
        mafiaPig1 = new MafiaPig(MafiaPig1Texture, mafiapig1body);
        stage.addActor(mafiaPig1);
        updatePigPosition(mafiaPig1, mafiapig1body);
    }

    private void updatePigPosition(MafiaPig pig, Body body) {
        Vector2 bodyPosition = body.getPosition();
        pig.setPosition(bodyPosition.x * PPM - pig.getWidth() / 2, bodyPosition.y * PPM - pig.getHeight() / 2);
    }

    private Body createPig(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(25 / PPM); // Bird radius
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.6f; // Bouncy effect
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }


    private void createGround() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 0); // Position in Box2D world
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1920 / 2f, 10); // Box dimensions
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 0f;
        fixtureDef.friction = 0.5f;
        groundBody.createFixture(fixtureDef);
        groundBox.dispose();
    }
    ////
    private void createBirds() {
        // Create Red Bird
        Body redBirdBody = createBird(480/ PPM, 181 / PPM); // Position
        redBird = new RedBird(new Texture("RedAngryBird.png"), redBirdBody);
        stage.addActor(redBird);
        // Create Yellow Bird
        Body yellowBirdBody = createBird(190 / PPM, 179 / PPM);
        yellowBird = new YellowBird(new Texture("YellowAngryBird.png"), yellowBirdBody);
        stage.addActor(yellowBird);

        // Create Blue Bird
        Body blueBirdBody = createBird(330/ PPM, 181 / PPM);
        blueBird = new BlueBird(new Texture("BlueAngryBird.png"), blueBirdBody);
        stage.addActor(blueBird);

        Body blackBirdBody = createBird(260 / PPM, 181 / PPM);
        blackBird = new BlackBird(new Texture("BlackAngryBird.png"), blackBirdBody);
        stage.addActor(blackBird);

        redBird.setPosition(alignLeft(400), alignBottom(190));
        blackBird.setPosition(alignLeft(320), alignBottom(190));
        blueBird.setPosition(alignLeft(240), alignBottom(190));
        yellowBird.setPosition(alignLeft(160), alignBottom(180));
    }

    private void createWoodObstacles() {
        Body verticalWood1Body = createObstacle(1354 / PPM, 310 / PPM);
        Body verticalWood2Body = createObstacle(1554 / PPM, 310 / PPM);

        woodVertical1 = new VerticalWood13(new Texture("13.png"), verticalWood1Body);
        woodVertical2 = new VerticalWood13(new Texture("13.png"), verticalWood2Body);

        Vector2 bodyPosition = verticalWood1Body.getPosition();
        woodVertical1.setPosition(bodyPosition.x * PPM - woodVertical1.getWidth() / 2, bodyPosition.y * PPM - woodVertical1.getHeight() / 2);
        Vector2 bodyPosition2 = verticalWood2Body.getPosition();
        woodVertical2.setPosition(bodyPosition2.x * PPM - woodVertical2.getWidth() / 2, bodyPosition2.y * PPM - woodVertical2.getHeight() / 2);

        stage.addActor(woodVertical1);
        stage.addActor(woodVertical2);
    }


    private Body createCatapult(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody; // Catapult is static
        bodyDef.position.set(x, y); // Convert to world units

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(112.5f / PPM, 75f / PPM); // Half-width and half-height

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

//

    private Body createBird(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(25 / PPM); // Bird radius
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.6f; // Bouncy effect
        fixtureDef.filter.categoryBits = 0x0001; // Bird category
        fixtureDef.filter.maskBits = 0x0002;    // Collides with obstacles

        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    private Body createObstacle(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25/PPM, 250/PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 1f; // Bouncy effect
        fixtureDef.filter.categoryBits = 0x0002; // Example category
        fixtureDef.filter.maskBits = 0x0001;    // Collides with bird category
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    private void launchBird(Vector2 dragVector) {
        if (currentBirdBody != null) {
            Vector2 launchVector = dragVector.cpy().scl(-10f); // Adjust multiplier
            currentBirdBody.applyLinearImpulse(launchVector, currentBirdBody.getWorldCenter(), true);

            currentBirdBody = null; // Clear the bird reference after launch
            dragVector.setZero();
        }
    }


    private void calculateTrajectory(Vector2 dragVector) {
        trajectoryPoints.clear();
        Vector2 launchVector = dragVector.cpy().scl(-1);
        float velocity = launchVector.len() * 10; // Use the same multiplier as in launchBird()
        float angle = launchVector.angleDeg(); // Use angleDeg() for degrees

        for (int i = 0; i < TRAJECTORY_POINTS; i++) {
            float t = i / 60f; // Time step
            float x = slingPosition.x / PPM + velocity * MathUtils.cosDeg(angle) * t;
            float y = slingPosition.y / PPM + velocity * MathUtils.sinDeg(angle) * t - 0.5f * 9.8f * t * t;
            trajectoryPoints.add(new Vector2(x * PPM, y * PPM));
        }
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (isDragging) {
                dragVector.set(touchPos.x - dragStartX, touchPos.y - dragStartY);

                // Limit the bird's movement within the slingshot range
                float maxDragDistance = 100f / PPM; // Adjust this value as needed
                if (dragVector.len() > maxDragDistance) {
                    dragVector.nor().scl(maxDragDistance);
                }

                // Update bird's position while dragging
                if (currentBirdBody != null) {
                    currentBirdBody.setTransform(
                        slingPosition.x / PPM - dragVector.x,
                        slingPosition.y / PPM - dragVector.y,
                        0
                    );
                }

                // Calculate trajectory
                calculateTrajectory(dragVector);
            }
        } else if (isDragging && Gdx.input.isTouched()) {
            // Launch the bird when the user releases the touch
            launchBird(dragVector);
            isDragging = false;
        }
    }

//    private void launchBird() {
////        Vector2 impulse = dragVector.cpy().scl(3); // Adjust multiplier for desired launch speed
////        currentBirdBody.setLinearDamping(0.5f);
////        currentBirdBody.applyLinearImpulse(impulse, currentBirdBody.getWorldCenter(), true);
//        float launchX = dragVector.x * 3; // Adjust multiplier for desired launch speed
//        float launchY = dragVector.y * 3; // Adjust multiplier for desired launch speed
//
//        // Apply launch impulse to the bird's body
//        Vector2 impulse = new Vector2(launchX, launchY);
//        currentBirdBody.applyLinearImpulse(impulse, currentBirdBody.getWorldCenter(), true);
//        currentBirdBody = null;
//        dragVector.setZero();
//        dragStartX = 0;
//        dragStartY = 0;
//    }

    @Override
    public void render(float delta) { //Rendering
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        world.step(1 / 60f, 6, 2);
        if(!isPaused){ // Checking if Pause Button is clicked
            //batch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(pauseButtonTexture, 50, 900, 100, 100);
            handleInput(); // Add this line to call handleInput() when the game is not paused
            world.step(1 / 60f, 6, 2);
        }else{ // Drawing Pause Screen
            batch.draw(DulledBackground,0,0);
            batch.draw(resumeButtontexture, 800,480,250,250);
            batch.draw(restartLevelTexture,50, 690, 100, 100);
            batch.draw(musicButtonTexture, 50, 525,100,100);
            batch.draw(soundButtonTexture,50,360, 100, 100);
        }
        batch.draw(endbuttonTexture,1700,50,200,100);
        batch.draw(EndButton2Texture,20,50,200,100);
        batch.end();
        catapult.setPosition(alignLeft(500)/PPM, alignBottom(190)/PPM);
        // Handle input for dragging
        //handleInput();
        // Checking User input
        if(Gdx.input.isTouched()){
            Vector2 touchPos=new Vector2(Gdx.input.getX(),Gdx.input.getY());
            viewport.unproject(touchPos);
            if(pauseButton.contains(touchPos.x,touchPos.y)){
                PauseButtonSound.play();
                isPaused = true;
            } else if (endbutton.contains(touchPos.x,touchPos.y)) {
                EndButtonSound.play();
                main.setScreen(new SuccessfulEndScreen(main));
            }
            else if(EndButton2.contains(touchPos.x,touchPos.y)){
                EndButtonSound.play();
                main.setScreen(new LoseEndScreen(main));
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                main.setScreen(new LoseEndScreen(main));
            }else if(resumeButton.contains(touchPos.x,touchPos.y)){
                ResumeButtonSound.play();
                isPaused = false;
            }
            //System.out.println("X:" + Gdx.input.getX() + " Y: " + Gdx.input.getY());
        }

        if (isDragging) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(WHITE);
            for (int i = 1; i < trajectoryPoints.size; i++) {
                Vector2 point1 = trajectoryPoints.get(i - 1);
                Vector2 point2 = trajectoryPoints.get(i);
                shapeRenderer.line(point1, point2);
            }
            shapeRenderer.end();
        }


        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { // Rendering
        viewport.update(width, height, true);
    }
    @Override
    public void dispose() {
        stage.dispose();
        if (redBird != null) redBird.dispose();
        if (yellowBird != null) yellowBird.dispose();
        if (blueBird != null) blueBird.dispose();
        if (blackBird != null) blackBird.dispose();
        if (mafiaPig1 != null) mafiaPig1.dispose();
        if (shapeRenderer != null) shapeRenderer.dispose();
        if (woodVertical1 != null) woodVertical1.dispose();
        if (catapult != null) catapult.dispose();
        if (BackgroundTexture != null) BackgroundTexture.dispose();
        if (EndButton2Texture != null) EndButton2Texture.dispose();
        if (PauseButtonSound != null) PauseButtonSound.dispose();
        if (ResumeButtonSound != null) ResumeButtonSound.dispose();
        if (EndButtonSound != null) EndButtonSound.dispose();
        if (batch != null) batch.dispose();
    }
}
