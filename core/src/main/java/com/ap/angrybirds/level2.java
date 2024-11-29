package com.ap.angrybirds;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.badlogic.gdx.graphics.Color.WHITE;


public class level2 extends ScreenAdapter{
    private static final float PPM=100f;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Stage stage;  // Important Attributes
    private OrthographicCamera camera;
    private Viewport viewport;
    private Main main;
    private Array<Body> bodiesToDestroy = new Array<>();
    private Array<WoodObstacles> woodObstacles; // Store all the wood obstacles
    private Map<Body, Boolean> groundedMap = new HashMap<>();
    private Array<MafiaPig> pigs; // Store all the pigs
    private Array<Bird> birds;
    private boolean allWoodDestroyed = false;
    private boolean allPigsDestroyed = false;
    private boolean anyBirdsLeft = true;
    private int score = 0;
    private Rectangle pauseButton,endbutton, resumeButton, EndButton2, restartLevelButton;
    Music PauseButtonSound, ResumeButtonSound, EndButtonSound;
    private RedBird redBird;
    private YellowBird yellowBird;
    private BlueBird blueBird;
    private BlackBird blackBird;
    private MafiaPig mafiaPig1;
    private VerticalWood13 woodVertical1, woodVertical2;
    private HorizontalWood13 woodHorizontal1;
    private Vector2 dragVector = new Vector2();
    private Vector2 slingPosition = new Vector2(480, 181);
    private Array<Vector2> trajectoryPoints = new Array<>();
    private ShapeRenderer shapeRenderer;
    // private static final float TRAJECTORY_POINTS = 10;
    private boolean isDragging = false;
    private Body currentBirdBody;
    private float dragStartX, dragStartY;
    private float maxDragDistance = 100f;
    private BitmapFont font;
    private static final short BIRD_CATEGORY = 0x0001;
    private static final short WOOD_CATEGORY = 0x0002;
    private static final short PIG_CATEGORY = 0x0003;
    private static final short GROUND_CATEGORY = 0x0004;
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
    private int birdcount;
    public level2(Main main) { // Constructor
        this.main = main;
        woodObstacles = new Array<>();
        pigs = new Array<>();
        birds = new Array<>();
    }
    @Override
    public void show() { // Show method to creating all the attributes
        world=new World(new Vector2(0,-9.8f),true);
        //collisionListener = new CollisionListener(world);
        collisionListener = new CollisionListener(bodiesToDestroy);
        world.setContactListener(collisionListener);
        debugRenderer = new Box2DDebugRenderer();
        birdcount=4;
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
        BackgroundTexture = new Texture("level2Background.jpg");
        DulledBackground = new Texture("level2Background_dulled.jpg");
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

        Body catapultBody = createCatapult(498, 310); // Adjusted position
        catapult = new Catapult(CatapultTexture, catapultBody);
        stage.addActor(catapult);
        currentBirdBody = null;
        createGround();
        createBirds();
        createBoundaries();
        currentBirdBody = null;
        createWoodObstacles();
        setPigs();
        // Track wood and pigs
        woodObstacles.add(woodVertical1);
        woodObstacles.add(woodVertical2);
        woodObstacles.add(woodHorizontal1);
        pigs.add(mafiaPig1);
        // Add all birds
        birds.add(redBird);
        birds.add(yellowBird);
        birds.add(blueBird);
        birds.add(blackBird);

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
        Body mafiapig1body = createPig(1480 / PPM, 220 / PPM);
        mafiaPig1 = new MafiaPig(MafiaPig1Texture, mafiapig1body);
        mafiapig1body.setUserData(mafiaPig1);
        stage.addActor(mafiaPig1);
        updatePigPosition(mafiaPig1, mafiapig1body);
        mafiapig1body.setUserData(mafiaPig1);
    }

    private void updatePigPosition(MafiaPig pig, Body body) {
        Vector2 bodyPosition = body.getPosition();
        float groundY = 245 / PPM; // Ground level in world units

        // Prevent pig from falling below the ground
        if (bodyPosition.y < groundY) {
            body.setLinearVelocity(0, 0); // Stop vertical movement
            body.setTransform(bodyPosition.x, groundY, 0); // Reset to ground level
        }

        pig.setPosition(bodyPosition.x * PPM - pig.getWidth() / 2, bodyPosition.y * PPM - pig.getHeight() / 2);
    }

    private void updateWoodObstaclePosition(WoodObstacles wood, Body body) {
        Vector2 bodyPosition = body.getPosition(); // Center position of the body in world coordinates
        float angle = body.getAngle(); // Rotation angle of the body

        // Calculate the actual bottom of the body (considering its height)
        float woodHalfHeight = (wood.getHeight() / 2) / PPM; // Half-height in meters
        float woodBottom = bodyPosition.y - woodHalfHeight;  // Bottom-most point in meters

        // Update the visual position and rotation of the wood obstacle
        wood.setPosition(bodyPosition.x * PPM - wood.getWidth() / 2, bodyPosition.y * PPM - wood.getHeight() / 2);
        wood.setRotation(angle * MathUtils.radiansToDegrees);

        // Ground level
        float floorY = 200 / PPM; // Adjust to your ground level in world coordinates

        // Check if the wood is grounded or needs to stop at the floor
        boolean isGrounded = groundedMap.getOrDefault(body, false);

        if (isGrounded) {
            // Stop movement and stabilize position if grounded
            body.setLinearVelocity(0, 0); // Stop all motion
            body.setAngularVelocity(0); // Prevent rotation
            body.setTransform(bodyPosition.x, Math.max(bodyPosition.y, floorY + woodHalfHeight), 0);
        } else if (woodBottom <= floorY) {
            // Stop the body when its bottom-most point touches the floor
            body.setTransform(bodyPosition.x, floorY + woodHalfHeight, angle); // Adjust position to sit on the floor
            body.setLinearVelocity(body.getLinearVelocity().x, 0); // Stop vertical movement
        }
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
        fixtureDef.restitution = 0.3f; // Bouncy effect
        fixtureDef.filter.categoryBits = PIG_CATEGORY;
        fixtureDef.filter.maskBits = BIRD_CATEGORY | WOOD_CATEGORY;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }


    private void createGround() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 200/PPM); // Position in Box2D world
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1920 / (2f * PPM), 10 / PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.filter.categoryBits = 0x0004; // Ground category
        fixtureDef.filter.maskBits = 0x0001;    // Collides with birds

        groundBody.createFixture(fixtureDef);
        groundBox.dispose();

    }
    private void createBirds() {
        // Create Red Bird
        Body redBirdBody = createBird(480 / PPM, 201 / PPM, "RedBird");
        redBird = new RedBird(new Texture("RedAngryBird.png"), redBirdBody);
        redBirdBody.setUserData(redBird);
        System.out.println("Bird userData: " + redBirdBody.getUserData());
        stage.addActor(redBird);

        Body yellowBirdBody = createBird(190 / PPM, 201 / PPM, "YellowBird");
        yellowBird = new YellowBird(new Texture("YellowAngryBird.png"), yellowBirdBody);
        yellowBirdBody.setUserData(yellowBird);
        stage.addActor(yellowBird);

        Body blueBirdBody = createBird(330 / PPM, 201 / PPM, "BlueBird");
        blueBird = new BlueBird(new Texture("BlueAngryBird.png"), blueBirdBody);
        blueBirdBody.setUserData(blueBird);
        System.out.println("Bird userData: " + blueBirdBody.getUserData());
        stage.addActor(blueBird);

        Body blackBirdBody = createBird(260 / PPM, 201 / PPM, "BlackBird");
        blackBird = new BlackBird(new Texture("BlackAngryBird.png"), blackBirdBody);
        blackBirdBody.setUserData(blackBird);
        stage.addActor(blackBird);

        redBird.setPosition(alignLeft(400), alignBottom(217));
        blackBird.setPosition(alignLeft(320), alignBottom(217));
        blueBird.setPosition(alignLeft(240), alignBottom(217));
        yellowBird.setPosition(alignLeft(160), alignBottom(210));
    }

    private void createWoodObstacles() {
        Body verticalWood1Body = createObstacle(1411/ PPM, 370 / PPM, "VerticalWood1", 25, 250);
        Body verticalWood2Body = createObstacle(1548 / PPM, 370 / PPM, "VerticalWood2",25, 250);
        Body horizontalWood1Body = createObstacle(1480/PPM, 460/PPM, "HorizontalWood1",250,25);

        woodVertical1 = new VerticalWood13(new Texture("13.png"), verticalWood1Body);
        woodVertical2 = new VerticalWood13(new Texture("13.png"), verticalWood2Body);
        woodHorizontal1 = new HorizontalWood13(new Texture("13_H.png"), horizontalWood1Body);


        Vector2 bodyPosition = verticalWood1Body.getPosition();
        woodVertical1.setPosition(bodyPosition.x * PPM - woodVertical1.getWidth() / 2, bodyPosition.y * PPM - woodVertical1.getHeight() / 2);
        Vector2 bodyPosition2 = verticalWood2Body.getPosition();
        woodVertical2.setPosition(bodyPosition2.x * PPM - woodVertical2.getWidth() / 2, bodyPosition2.y * PPM - woodVertical2.getHeight() / 2);

        Vector2 bodyPosition3 = horizontalWood1Body.getPosition();
        woodHorizontal1.setPosition(bodyPosition3.x * PPM - woodHorizontal1.getWidth() / 2, bodyPosition3.y * PPM - woodHorizontal1.getHeight() / 2);



        verticalWood1Body.setUserData(woodVertical1);
        verticalWood2Body.setUserData(woodVertical2);
        horizontalWood1Body.setUserData(woodHorizontal1);

        stage.addActor(woodVertical1);
        stage.addActor(woodVertical2);
        stage.addActor(woodHorizontal1);

        woodObstacles.add(woodVertical1);
        woodObstacles.add(woodVertical2);
        woodObstacles.add(woodHorizontal1);
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
        fixtureDef.restitution = 0.3f;

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
        fixtureDef.restitution = 0.4f; // Bouncy effect
        fixtureDef.filter.categoryBits = 0x0001; // Bird category
        fixtureDef.filter.maskBits = 0x0002 | 0x0004; // Birds collide with obstacles and ground

        body.createFixture(fixtureDef);
//    body.setUserData(birdType); // Set the bird type as user data
        shape.dispose();
        return body;
    }

    private Body createObstacle(float x, float y, String obstacleType, int sizeX, int sizeY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeX / (2f * PPM), sizeY / (2f * PPM));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 6.0f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.1f; // Bouncy effect
        fixtureDef.filter.categoryBits = WOOD_CATEGORY;
        fixtureDef.filter.maskBits = WOOD_CATEGORY | BIRD_CATEGORY | GROUND_CATEGORY;

        body.createFixture(fixtureDef);
//        body.setUserData(obstacleType); // Set the obstacle type as user data
        shape.dispose();
        return body;
    }

    private void createBoundaries() {
        // Bottom boundary at y = 150
        createBoundary(viewport.getWorldWidth() / 2f, 180 / PPM, viewport.getWorldWidth() / 2f, 10 / PPM);

        // Left boundary
        // createBoundary(5 / PPM, viewport.getWorldHeight() / 2f, 10 / PPM, viewport.getWorldHeight() / 2f);

        // Right boundary
        //createBoundary((viewport.getWorldWidth() - 5) / PPM, viewport.getWorldHeight() / 2f, 10 / PPM, viewport.getWorldHeight() / 2f);

        // Top boundary
        //createBoundary(viewport.getWorldWidth() / 2f, (viewport.getWorldHeight() - 5) / PPM, viewport.getWorldWidth() / 2f, 10 / PPM);
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

    private Vector2 catapultPosition = new Vector2(500, 345); // Adjust these values as needed
    private static final int TRAJECTORY_POINTS = 30;
    private static final float MAX_PULL_DISTANCE = 100f;
    private static final float LAUNCH_SPEED_MULTIPLIER = 15f;
    private static final float GRAVITY = -9.8f; // Gravity constant

    private void setCurrentBird(Bird bird) {
        currentBirdBody = bird.getBody();
        isDragging = true;
        // Set the bird's position to the center of the catapult
        currentBirdBody.setTransform(catapultPosition.x / PPM, catapultPosition.y / PPM, 0);
        dragVector.setZero();
    }

    private void checkGameState() {
        // Check if all birds are destroyed
        boolean allBirdsDestroyed = birds.isEmpty();

        // Check if all obstacles are remaining
        boolean pigsRemaining = !pigs.isEmpty();

        // Check if all obstacles are destroyed
        boolean allObstaclesDestroyed = pigs.isEmpty() && woodObstacles.isEmpty();

        // If all birds are destroyed and obstacles remain, go to lose screen
        if ((allBirdsDestroyed && pigsRemaining)||birdcount==0) {
            main.setScreen(new LoseEndScreen(main));
        }
        // If all obstacles are destroyed, go to successful end screen
        else if (!pigsRemaining) {
            main.setScreen(new SuccessfulEndScreen(main));
        }
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

            System.out.println("contact1");

            Body bodyA = fixtureA.getBody();
            Body bodyB = fixtureB.getBody();

            if (isWoodFixture(fixtureA) && isWoodFixture(fixtureB)) {
                System.out.println("contact2");
                if (isGroundBelow(bodyA, bodyB)) {
                    groundedMap.put(bodyA, true);
                    System.out.println("Put A in B");
                } else if (isGroundBelow(bodyB, bodyA)) {
                    groundedMap.put(bodyB, true);
                    System.out.println("Put B in A");
                }
            }

            if (isBirdAndWoodCollision(fixtureA, fixtureB) || isBirdAndPigCollision(fixtureA, fixtureB)) {
                Body bird = fixtureA.getBody().getUserData() instanceof Bird ? fixtureA.getBody() : fixtureB.getBody();
                Body other = fixtureA.getBody().getUserData() instanceof Bird ? fixtureB.getBody() : fixtureA.getBody();

                // Delay destruction using Timer
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        // Get the actors
                        Bird birdActor = (Bird) bird.getUserData();

                        // Remove the bird from the stage
                        stage.getActors().removeValue(birdActor, true);
                        birds.removeValue((Bird)birdActor, true);

                        // Check and remove the other object (Wood or Pig)
                        if (other.getUserData() instanceof WoodObstacles) {
                            WoodObstacles woodActor = (WoodObstacles) other.getUserData();
                            stage.getActors().removeValue(woodActor, true);
                            woodObstacles.removeValue(woodActor, true);
                        } else if (other.getUserData() instanceof MafiaPig) {
                            MafiaPig pigActor = (MafiaPig) other.getUserData();
                            stage.getActors().removeValue(pigActor, true);
                            pigs.removeValue(pigActor, true);
                        }

                        // Add bodies to destroy
                        bodiesToDestroy.add(bird);
                        bodiesToDestroy.add(other);
                    }
                }, 0.5f); // Delay of 0.5 seconds

                System.out.println("Collision detected. Destruction scheduled in 2 seconds.");
            }

            // Log collisions
            Gdx.app.log("Collision", "FixtureA: " + fixtureA.getBody().getUserData() +
                ", FixtureB: " + fixtureB.getBody().getUserData());
            for (Body b : bodiesToDestroy) {
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
        public void endContact(Contact contact) {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            Body bodyA = fixtureA.getBody();
            Body bodyB = fixtureB.getBody();

            // If the contact ends, update the grounded state
            if (isWoodFixture(fixtureA)) {
                groundedMap.put(bodyA, false);
            }
            if (isWoodFixture(fixtureB)) {
                groundedMap.put(bodyB, false);
            }
        }

        private boolean isGroundBelow(Body woodBody, Body otherBody) {
            Vector2 woodPos = woodBody.getPosition();
            Vector2 otherPos = otherBody.getPosition();

            // Get the height and width of both bodies
            float woodHalfHeight = woodBody.getFixtureList().first().getShape().getRadius();
            float woodHalfWidth = woodBody.getFixtureList().first().getShape().getRadius();
            float otherHalfHeight = otherBody.getFixtureList().first().getShape().getRadius();
            float otherHalfWidth = otherBody.getFixtureList().first().getShape().getRadius();

//            // Check if the bottom of the woodBody touches the top of the otherBody
//            boolean verticalOverlap = (woodPos.y - woodHalfHeight) <= (otherPos.y + otherHalfHeight) &&
//                (woodPos.y - woodHalfHeight) > (otherPos.y - otherHalfHeight);
//
//            // Check if the woodBody and otherBody overlap horizontally
//            boolean horizontalOverlap = Math.abs(woodPos.x - otherPos.x) <= (woodHalfWidth + otherHalfWidth);
//
//            return verticalOverlap && horizontalOverlap;

            boolean collision = (woodPos.y - woodHalfHeight) <= (otherPos.y + otherHalfHeight);
            return collision;
        }


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
        // Invert the drag vector to get the launch direction
        Vector2 launchVector = dragVector.cpy().scl(-1); // Invert the direction

        // Calculate launch velocity based on drag vector length
        float launchSpeed = Math.min(launchVector.len(), MAX_PULL_DISTANCE) * (LAUNCH_SPEED_MULTIPLIER-2f);

        // Normalize the launch vector and scale by launch speed
        launchVector.nor().scl(launchSpeed / PPM);

        float velocityX = launchVector.x;
        float velocityY = launchVector.y;

        float startX = catapultPosition.x / PPM;
        float startY = catapultPosition.y / PPM;

        for (int i = 0; i < TRAJECTORY_POINTS; i++) {
            float t = i * 0.1f; // Time step

            // Calculate x and y positions using projectile motion equations
            float x = startX + velocityX * t;
            float y = startY + velocityY * t + 0.5f * GRAVITY * t * t;

            // Stop adding points if the trajectory goes below ground level
            if (y < 0) break;

            // Scale the trajectory points back to pixels for rendering
            trajectoryPoints.add(new Vector2(x * PPM, y * PPM));
        }
    }
    private void renderScore() {
        String scoreText = "Score: " + score;
        font.draw(batch, scoreText, 10, Gdx.graphics.getHeight() - 10); // Position it appropriately
    }

    private void launchBird(Vector2 dragVector) {
        if (currentBirdBody != null) {
            // Invert the drag vector to get the launch direction
            Vector2 launchVector = dragVector.cpy().scl(-1);

            // Calculate launch velocity based on drag vector length
            float launchSpeed = Math.min(launchVector.len(), MAX_PULL_DISTANCE) * (LAUNCH_SPEED_MULTIPLIER-2f);

            // Normalize the launch vector and scale by launch speed
            launchVector.nor().scl(launchSpeed / PPM);

            // Set the bird's initial velocity
            currentBirdBody.setLinearVelocity(launchVector);

            // Reset the current bird to prevent multiple launches
            currentBirdBody = null;
            isDragging = false;

            Timer timer = new Timer();
            timer.schedule(new Timer.Task() {
                public void run(){
                    birdcount--;
                }
            },4);
        }
    }

    private void handleInput() {
        Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(touchPos);

        if (Gdx.input.justTouched()) {
            if (!isDragging) {
                // Check if a bird is touched and set it as the current bird
                if (isBirdTouched(redBird, touchPos)) {
                    setCurrentBird(redBird);
                } else if (isBirdTouched(yellowBird, touchPos)) {
                    setCurrentBird(yellowBird);
                } else if (isBirdTouched(blueBird, touchPos)) {
                    setCurrentBird(blueBird);
                } else if (isBirdTouched(blackBird, touchPos)) {
                    setCurrentBird(blackBird);
                }
            }
        } else if (Gdx.input.isTouched() && isDragging) {
            // Calculate drag vector from catapult to touch position
            dragVector.set(catapultPosition.x - touchPos.x, catapultPosition.y - touchPos.y);

            // Limit the pull distance
            if (dragVector.len() > MAX_PULL_DISTANCE) {
                dragVector.setLength(MAX_PULL_DISTANCE);
            }

            // Update bird's position while dragging
            if (currentBirdBody != null) {
                currentBirdBody.setTransform(
                    (catapultPosition.x - dragVector.x) / PPM,
                    (catapultPosition.y - dragVector.y) / PPM,
                    0
                );
            }

            // Calculate trajectory
            calculateTrajectory(dragVector);
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
        //super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        world.step(1 / 60f, 6, 2);
        Array<Body> bodiesMarkedForRemoval = new Array<>(collisionListener.bodiesToDestroy);
        for (Body body : bodiesMarkedForRemoval) {
            if (body != null && world != null) {
                // Remove associated actor from stage
                Object userData = body.getUserData();
                if (userData instanceof Actor) {
                    stage.getActors().removeValue((Actor) userData, true);
                }
                // Destroy the body
                world.destroyBody(body);
            }
        }
        collisionListener.bodiesToDestroy.clear();
        handleInput();
        bodiesToDestroy.clear();
        if(!isPaused){ // Checking if Pause Button is clicked
            batch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(pauseButtonTexture, 50, 900, 100, 100);
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
            System.out.println("X:" + Gdx.input.getX() + " Y: " + Gdx.input.getY());
        }
        updatePigPosition(mafiaPig1, mafiaPig1.getBody());
        updateWoodObstaclePosition(woodVertical1, woodVertical1.getBody());
        updateWoodObstaclePosition(woodVertical2, woodVertical2.getBody());
        updateWoodObstaclePosition(woodHorizontal1, woodHorizontal1.getBody());
//        collisionListener.processRemovals();

        if (isDragging) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(WHITE);
            for (Vector2 point : trajectoryPoints) {
                shapeRenderer.circle(point.x, point.y, 6); // Adjust the radius as needed
            }
            shapeRenderer.end();
        }

//        if (trajectoryPoints.size > 0) {
//            shapeRenderer.setProjectionMatrix(camera.combined);
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//            for (Vector2 point : trajectoryPoints) {
//                shapeRenderer.circle(point.x, point.y, 5 / PPM); // Adjust radius for visibility
//            }
//            shapeRenderer.end();
//        }


        checkGameState();
        //System.out.println("Bird Counter: " + birdcount);
        stage.act(delta);
        stage.draw();
    }
    //
    //
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
