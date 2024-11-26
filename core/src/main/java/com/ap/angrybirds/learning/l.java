package com.ap.angrybirds.learning;
import com.ap.angrybirds.*;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

import static com.badlogic.gdx.graphics.Color.*;

public class l extends ScreenAdapter {
    private static final float PPM=100f;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;  // Important Attributes
    private OrthographicCamera camera;
    private Viewport viewport;
    private Main main;
    private Array<Body> bodiesToDestroy = new Array<>();
    private int score = 0;
    private Rectangle pauseButton,endbutton, resumeButton, EndButton2, restartLevelButton;
    Music PauseButtonSound, ResumeButtonSound, EndButtonSound;
    private RedBird redBird;
    private YellowBird yellowBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private MafiaPig mafiaPig1;
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
    private BitmapFont font;
    private static final short BIRD_CATEGORY = 0x0001;
    private static final short WOOD_CATEGORY = 0x0002;
    private static final short PIG_CATEGORY = 0x0004;
    private CollisionListener collisionListener;
    private ShapeRenderer trajectoryRenderer = new ShapeRenderer();

    private Catapult catapult;
    private Texture pauseButtonTexture;
    private Texture endbuttonTexture;
    private Texture MafiaPig1Texture;
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
        collisionListener = new CollisionListener(bodiesToDestroy);
        world.setContactListener(collisionListener);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, camera);
        stage = new Stage(viewport);
        font = new BitmapFont();
        font.setColor(com. badlogic. gdx. graphics. Color.WHITE);
        font.getData().setScale(2);
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

        Body catapultBody = createCatapult(560, 280); // Adjusted position
        catapult = new Catapult(CatapultTexture, catapultBody);
        stage.addActor(catapult);
        currentBirdBody = null;
        createGround();
        createBirds();
        createBoundaries();
        createWoodObstacles();
        setPigs();
        Gdx.input.setInputProcessor(stage);
            Gdx.input.setInputProcessor(new InputAdapter() {
                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                    System.out.println("Touch down detected.");
                    Vector2 touchPos = new Vector2(screenX, screenY);
                    viewport.unproject(touchPos);
                    if (redBird.getBounds().contains(touchPos)) {
                        currentBirdBody = redBird.getBody();
                        System.out.println("Red bird selected for dragging.");
                    }
                    if (yellowBird.getBounds().contains(touchPos)) {
                        currentBirdBody = yellowBird.getBody();
                        System.out.println("Yellow bird selected for dragging.");
                    }
                    if (blueBird.getBounds().contains(touchPos)) {
                        currentBirdBody = blueBird.getBody();
                        System.out.println("Blue bird selected for dragging.");
                    }
                    if (blackBird.getBounds().contains(touchPos)) {
                        currentBirdBody = blackBird.getBody();
                        System.out.println("Black bird selected for dragging.");
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
                            currentBirdBody.setTransform(slingPosition.x / PPM - dragVector.x, slingPosition.y / PPM - dragVector.y, 0);
                            shapeRenderer.setProjectionMatrix(camera.combined);
                            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                            shapeRenderer.line(slingPosition.x / PPM, slingPosition.y / PPM, touchPos.x, touchPos.y);
                            shapeRenderer.end();
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

    private float alignLeft(float margin) {
        return margin;
    }

    private float alignBottom(float margin) {
        return margin;
    }

    private void setPigs() {
        Body mafiapig1body = createPig(1460 / PPM, 220 / PPM);
        mafiaPig1 = new MafiaPig(MafiaPig1Texture, mafiapig1body);
        mafiapig1body.setUserData(mafiaPig1);
        stage.addActor(mafiaPig1);
        updatePigPosition(mafiaPig1, mafiapig1body);
    }

    private void updatePigPosition(MafiaPig pig, Body body) {
        Vector2 bodyPosition = body.getPosition();
        float groundY = 150 / PPM; // Ground level in world units

        // Prevent pig from falling below the ground
        if (bodyPosition.y < groundY) {
            body.setLinearVelocity(0, 0); // Stop vertical movement
            body.setTransform(bodyPosition.x, groundY, 0); // Reset to ground level
        }

        pig.setPosition(bodyPosition.x * PPM - pig.getWidth() / 2, bodyPosition.y * PPM - pig.getHeight() / 2);
    }

    private void updateWoodObstaclePosition(VerticalWood13 wood, Body body) {
        Vector2 bodyPosition = body.getPosition();
        float groundY = 150 / PPM; // Ground level in world units

        // Prevent wood obstacle from falling below the ground
        if (bodyPosition.y < groundY) {
            body.setLinearVelocity(0, 0); // Stop vertical movement
            body.setTransform(bodyPosition.x, groundY, 0); // Reset to ground level
        }

        wood.setPosition(bodyPosition.x * PPM - wood.getWidth() / 2, bodyPosition.y * PPM - wood.getHeight() / 2);
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
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.1f; // Bouncy effect
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }


    private void createGround() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 150/PPM); // Position in Box2D world
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1920 / (2f * PPM), 10 / PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 0f;
        fixtureDef.friction = 1f;
        fixtureDef.filter.categoryBits = 0x0004; // Ground category
        fixtureDef.filter.maskBits = 0x0001;    // Collides with birds

        groundBody.createFixture(fixtureDef);
        groundBox.dispose();

    }
    private void createBirds() {
        // Create Red Bird
        Body redBirdBody = createBird(480 / PPM, 181 / PPM, "RedBird");
        redBird = new RedBird(new Texture("RedAngryBird.png"), redBirdBody);
        redBirdBody.setUserData(redBird);
        System.out.println("Bird userData: " + redBirdBody.getUserData());
        stage.addActor(redBird);

        Body yellowBirdBody = createBird(190 / PPM, 179 / PPM, "YellowBird");
        yellowBird = new YellowBird(new Texture("YellowAngryBird.png"), yellowBirdBody);
        yellowBirdBody.setUserData(yellowBird);
        stage.addActor(yellowBird);

        Body blueBirdBody = createBird(330 / PPM, 181 / PPM, "BlueBird");
        blueBird = new BlueBird(new Texture("BlueAngryBird.png"), blueBirdBody);
        blueBirdBody.setUserData(blueBird);
        System.out.println("Bird userData: " + blueBirdBody.getUserData());
        stage.addActor(blueBird);

        Body blackBirdBody = createBird(260 / PPM, 181 / PPM, "BlackBird");
        blackBird = new BlackBird(new Texture("BlackAngryBird.png"), blackBirdBody);
        blackBirdBody.setUserData(blackBird);
        stage.addActor(blackBird);

        redBird.setPosition(alignLeft(400), alignBottom(190));
        blackBird.setPosition(alignLeft(320), alignBottom(190));
        blueBird.setPosition(alignLeft(240), alignBottom(190));
        yellowBird.setPosition(alignLeft(160), alignBottom(180));
    }

    private void createWoodObstacles() {
        Body verticalWood1Body = createObstacle(1354 / PPM, 410 / PPM, "VerticalWood1");
        Body verticalWood2Body = createObstacle(1554 / PPM, 410 / PPM, "VerticalWood2");

        woodVertical1 = new VerticalWood13(new Texture("13.png"), verticalWood1Body);
        woodVertical2 = new VerticalWood13(new Texture("13.png"), verticalWood2Body);

        Vector2 bodyPosition = verticalWood1Body.getPosition();
        woodVertical1.setPosition(bodyPosition.x * PPM - woodVertical1.getWidth() / 2, bodyPosition.y * PPM - woodVertical1.getHeight() / 2);
        Vector2 bodyPosition2 = verticalWood2Body.getPosition();
        woodVertical2.setPosition(bodyPosition2.x * PPM - woodVertical2.getWidth() / 2, bodyPosition2.y * PPM - woodVertical2.getHeight() / 2);
        verticalWood1Body.setUserData(woodVertical1);
        verticalWood2Body.setUserData(woodVertical2);
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
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

//
//
private Body createBird(float x, float y, String birdType) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(x, y);
    Body body = world.createBody(bodyDef);
    CircleShape shape = new CircleShape();
    shape.setRadius(25 / PPM); // Bird radius
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 1.0f;
    fixtureDef.friction = 1f;
    fixtureDef.restitution = 0.1f; // Bouncy effect
    fixtureDef.filter.categoryBits = 0x0001; // Bird category
    fixtureDef.filter.maskBits = 0x0002 | 0x0004; // Birds collide with obstacles and ground

    body.createFixture(fixtureDef);
//    body.setUserData(birdType); // Set the bird type as user data
    shape.dispose();
    return body;
}

    private Body createObstacle(float x, float y, String obstacleType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25 / PPM, 250 / PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.1f; // Bouncy effect
        fixtureDef.filter.categoryBits = 0x0002; // Example category
        fixtureDef.filter.maskBits = 0x0001;    // Collides with bird category

        body.createFixture(fixtureDef);
//        body.setUserData(obstacleType); // Set the obstacle type as user data
        shape.dispose();
        return body;
    }

    private void createBoundaries() {
        // Bottom boundary at y = 150
        createBoundary(viewport.getWorldWidth() / 2f, 150 / PPM, viewport.getWorldWidth() / 2f, 10 / PPM);

        // Left boundary
        createBoundary(5 / PPM, viewport.getWorldHeight() / 2f, 10 / PPM, viewport.getWorldHeight() / 2f);

        // Right boundary
        createBoundary((viewport.getWorldWidth() - 5) / PPM, viewport.getWorldHeight() / 2f, 10 / PPM, viewport.getWorldHeight() / 2f);

        // Top boundary
        createBoundary(viewport.getWorldWidth() / 2f, (viewport.getWorldHeight() - 5) / PPM, viewport.getWorldWidth() / 2f, 10 / PPM);
    }


    private void createBoundary(float x, float y, float halfWidth, float halfHeight) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth, halfHeight);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.filter.categoryBits = 0x0004; // Boundary category
        fixtureDef.filter.maskBits = 0x0001;
        body.createFixture(fixtureDef);
        shape.dispose();
    }


    private void launchBird(Vector2 dragVector) {
        if (currentBirdBody != null) {
            Vector2 launchVector = dragVector.scl(-3f); // Adjust multiplier
            currentBirdBody.applyLinearImpulse(launchVector, currentBirdBody.getWorldCenter(), true);
            System.out.println("Launch vector applied: " + launchVector);
            currentBirdBody = null; // Prevent multiple launches
        } else {
            System.out.println("No bird body to launch!");
        }
//        if (currentBirdBody.getPosition().y < 150 / PPM) { // Ensure bird doesn't go below ground
//            currentBirdBody.setLinearVelocity(0, 0);
//            currentBirdBody.setTransform(currentBirdBody.getPosition().x, 150 / PPM, 0);
//        }
    }


    private class CollisionListener implements ContactListener {
        private Array<Body> bodiesToDestroy;
        public CollisionListener(Array<Body> bodiesToDestroy) {
            this.bodiesToDestroy = bodiesToDestroy;
        }
        @Override
        public void beginContact(Contact contact) {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            if (isBirdContactingWood(fixtureA, fixtureB)) {
                score += 100; // 100 points for hitting wood
            } else if (isBirdContactingPig(fixtureA, fixtureB)) {
                score += 200; // 200 points for hitting pig
            }
            if (isBirdAndWoodCollision(fixtureA, fixtureB) || isBirdAndPigCollision(fixtureA, fixtureB)) {
                Body bird = fixtureA.getBody().getUserData() instanceof Bird ? fixtureA.getBody() : fixtureB.getBody();
                Body other = fixtureA.getBody().getUserData() instanceof Bird ? fixtureB.getBody() : fixtureA.getBody();
                System.out.println("Added to ARRAY");
                // Get the actors
                Bird birdActor = (Bird) bird.getUserData();
                WoodObstacles woodActor = (WoodObstacles) other.getUserData();

                // Remove actors from stage
                stage.getActors().removeValue(birdActor, true);
                stage.getActors().removeValue(woodActor, true);

                // Dispose of textures (if necessary)
//                birdActor.getTexture().dispose();
//                woodActor.getTexture().dispose();
                bodiesToDestroy.add(bird);
                bodiesToDestroy.add(other);

//                // Remove the actors
//                stage.getActors().removeValue(Bird(bird), true);
//                stage.getActors().removeValue(other, true);
            }
            Gdx.app.log("Collision", "FixtureA: " + fixtureA.getBody().getUserData() +
                ", FixtureB: " + fixtureB.getBody().getUserData());
            for(Body b : bodiesToDestroy) {
                System.out.println(b);
            }

        }
        private boolean isBirdAndWoodCollision(Fixture fixtureA, Fixture fixtureB) {
            return (fixtureA.getBody().getUserData() instanceof Bird && fixtureB.getBody().getUserData() instanceof WoodObstacles) ||
                (fixtureB.getBody().getUserData() instanceof Bird && fixtureA.getBody().getUserData() instanceof WoodObstacles);
        }
        private boolean isBirdAndPigCollision(Fixture fixtureA, Fixture fixtureB) {
            return (fixtureA.getBody().getUserData() instanceof Bird && fixtureB.getBody().getUserData() instanceof MafiaPig) ||
                (fixtureB.getBody().getUserData() instanceof Bird && fixtureA.getBody().getUserData() instanceof MafiaPig);
        }


        @Override
        public void endContact(Contact contact) {}

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {}

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {}

        private boolean isBirdContactingWood(Fixture fixtureA, Fixture fixtureB) {
            // Replace with your logic for identifying bird and wood fixtures
            return (isBirdFixture(fixtureA) && isWoodFixture(fixtureB)) || (isBirdFixture(fixtureB) && isWoodFixture(fixtureA));
        }

        private boolean isBirdContactingPig(Fixture fixtureA, Fixture fixtureB) {
            // Replace with your logic for identifying bird and pig fixtures
            return (isBirdFixture(fixtureA) && isPigFixture(fixtureB)) || (isBirdFixture(fixtureB) && isPigFixture(fixtureA));
        }

        private boolean isBirdFixture(Fixture fixture) {
            return fixture.getFilterData().categoryBits == BIRD_CATEGORY; // Example category bit for Bird
        }

        private boolean isWoodFixture(Fixture fixture) {
            return fixture.getFilterData().categoryBits == WOOD_CATEGORY; // Example category bit for Wood
        }

        private boolean isPigFixture(Fixture fixture) {
            return fixture.getFilterData().categoryBits == PIG_CATEGORY; // Example category bit for Pig
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
    private void renderScore() {
        String scoreText = "Score: " + score;
        font.draw(batch, scoreText, 10, Gdx.graphics.getHeight() - 10); // Position it appropriately
    }
    private void renderTrajectory() {
        if (isDragging) {
            trajectoryRenderer.setProjectionMatrix(camera.combined);
            trajectoryRenderer.begin(ShapeRenderer.ShapeType.Filled);
            trajectoryRenderer.setColor(BLUE);
            float radius = 3f / PPM;
            int segments = 8;
            for (Vector2 point : trajectoryPoints) {
                trajectoryRenderer.circle(point.x, point.y, radius, segments);
            }
            trajectoryRenderer.end();
        }
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            if (!isDragging) {
                // Check if a bird is touched and set it as the current bird
                if (isBirdTouched(redBird, touchPos)) {
                    currentBirdBody = redBird.getBody();
                    isDragging = true;
                    dragStartX = touchPos.x;
                    dragStartY = touchPos.y;
                } else if (isBirdTouched(yellowBird, touchPos)) {
                    currentBirdBody = yellowBird.getBody();
                    isDragging = true;
                    dragStartX = touchPos.x;
                    dragStartY = touchPos.y;
                } else if (isBirdTouched(blueBird, touchPos)) {
                    currentBirdBody = blueBird.getBody();
                    isDragging = true;
                    dragStartX = touchPos.x;
                    dragStartY = touchPos.y;
                } else if (isBirdTouched(blackBird, touchPos)) {
                    currentBirdBody = blackBird.getBody();
                    isDragging = true;
                    dragStartX = touchPos.x;
                    dragStartY = touchPos.y;
                }
            }

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
                    Gdx.app.log("CurrentBody", "x: " + currentBirdBody.getPosition().x + ", y: " + currentBirdBody.getPosition().y);
                }

                // Calculate trajectory
                calculateTrajectory(dragVector);
            }
        } else if (isDragging && !Gdx.input.isTouched()) {
            // Launch the bird when the user releases the touch
            launchBird(dragVector);
            isDragging = false;
        }
    }
    private boolean isBirdTouched(Bird bird, Vector2 touchPos) {
        float birdX = bird.getX() / PPM; // Adjust for Box2D scale
        float birdY = bird.getY() / PPM; // Adjust for Box2D scale
        float birdWidth = bird.getWidth() / PPM;
        float birdHeight = bird.getHeight() / PPM;

        return touchPos.x >= birdX && touchPos.x <= birdX + birdWidth &&
            touchPos.y >= birdY && touchPos.y <= birdY + birdHeight;
    }

    @Override
    public void render(float delta) { //Rendering
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        world.step(1 / 60f, 6, 2);
        for (Body body : collisionListener.bodiesToDestroy) {
            if (body != null && world != null) {
                world.destroyBody(body);
            }
        }
        bodiesToDestroy.clear();
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
        renderScore();
        batch.draw(endbuttonTexture,1700,50,200,100);
        batch.draw(EndButton2Texture,20,50,200,100);
        batch.end();
        catapult.setPosition(alignLeft(500)/PPM, alignBottom(190)/PPM);
        debugRenderer.render(world, camera.combined);

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
        updatePigPosition(mafiaPig1, mafiaPig1.getBody());
        updateWoodObstaclePosition(woodVertical1, woodVertical1.getBody());
        updateWoodObstaclePosition(woodVertical2, woodVertical2.getBody());
//        collisionListener.processRemovals();

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

        if (trajectoryPoints.size > 0) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (Vector2 point : trajectoryPoints) {
                shapeRenderer.circle(point.x, point.y, 5 / PPM); // Adjust radius for visibility
            }
            shapeRenderer.end();
        }


        stage.act(delta);
        stage.draw();
    }
//
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
