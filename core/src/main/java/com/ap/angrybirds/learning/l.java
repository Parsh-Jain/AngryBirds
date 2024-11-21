package com.ap.angrybirds.learning;
import com.ap.angrybirds.*;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.awt.*;
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
    private WoodObstacles woodObstacle9a;
    private WoodObstacles woodObstacle9b;
    private WoodObstacles woodObstacle9c;
    private WoodObstacles woodObstacle9d;
    private WoodObstacles woodObstacle13a;
    private WoodObstacles woodObstacle13b;
    private WoodObstacles woodObstacle13c;
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
//        RedBirdTexture = new Texture("RedAngryBird.png");
//        YellowBirdTexture = new Texture("YellowAngryBird.png");
//        BlueBirdTexture=new Texture("BlueAngryBird.png");
//        BlackBirdTexture=new Texture("BlackAngryBird.png");
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
//        redBird = new RedBird(RedBirdTexture);
//        redBird.setPosition(480, 450);
//        stage.addActor(redBird);
//        yellowBird = new YellowBird(YellowBirdTexture);
//        yellowBird.setPosition(190, 179);
//        stage.addActor(yellowBird);
//        blackBird = new BlackBird(BlackBirdTexture);
//        blackBird.setPosition(260, 181);
//        stage.addActor(blackBird);
//        blueBird = new BlueBird(BlueBirdTexture);
//        blueBird.setPosition(330, 181);
//        stage.addActor(blueBird);
//        mafiaPig1 = new MafiaPig(MafiaPig1Texture);
//        mafiaPig1.setPosition(1335, 220);
//        stage.addActor(mafiaPig1);
//        mafiaPig2 = new MafiaPig(MafiaPig2Texture);
//        mafiaPig2.setPosition(1415, 360);
//        stage.addActor(mafiaPig2);
//        mafiaPig3 = new MafiaPig(MafiaPig3Texture);
//        mafiaPig3.setPosition(1415, 580);
//        stage.addActor(mafiaPig3);
//        mafiaPig4 = new MafiaPig(MafiaPig4Texture);
//        mafiaPig4.setPosition(1490, 220);
//        stage.addActor(mafiaPig4);
//        woodObstacle13c = new WoodObstacles(WoodObstacleTexture13_Ha);
//        woodObstacle13c.setPosition(1258, 320);
//        woodObstacle13c.setSize(350,40);
//        stage.addActor(woodObstacle13c);
//        woodObstacle13d = new WoodObstacles(WoodObstacleTexture13_Hb);
//        woodObstacle13d.setPosition(1258, 185);
//        woodObstacle13d.setSize(350,40);
//        stage.addActor(woodObstacle13d);
//        woodObstacle9a = new WoodObstacles(WoodObstacleTexture9a);
//        woodObstacle9a.setPosition(1260, 185);
//        woodObstacle9a.setSize(40,175);
//        stage.addActor(woodObstacle9a);
//        woodObstacle9b = new WoodObstacles(WoodObstacleTexture9b);
//        woodObstacle9b.setPosition(1600, 185);
//        woodObstacle9b.setSize(40,175);
//        stage.addActor(woodObstacle9b);
//        woodObstacle9a = new WoodObstacles(WoodObstacleTexture9a);
//        woodObstacle9a.setPosition(1310, 350);
//        woodObstacle9a.setSize(40,175);
//        stage.addActor(woodObstacle9a);
//        woodObstacle9b = new WoodObstacles(WoodObstacleTexture9b);
//        woodObstacle9b.setPosition(1550, 350);
//        woodObstacle9b.setSize(40,175);
//        stage.addActor(woodObstacle9b);
//        woodObstacle13c = new WoodObstacles(WoodObstacleTexture13_Ha);
//        woodObstacle13c.setPosition(1278, 510);
//        woodObstacle13c.setSize(350,40);
//        stage.addActor(woodObstacle13c);
//        woodObstacle14 = new WoodObstacles(WoodObstacleTexture14);
//        woodObstacle14.setPosition(1368, 545);
//        woodObstacle14.setSize(170,170);
//        stage.addActor(woodObstacle14);


        Body catapultBody = createCatapult(300, 183); // Position for the catapult
        catapult = new Catapult(CatapultTexture, catapultBody);
        stage.addActor(catapult);
        //catapult.setPosition(300, 183);
        createGround();
        createBirds();
        createObstacles();
        createPigs();
        Gdx.input.setInputProcessor(stage);
        isPaused = false;
    }

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

    private void createPigs() {
        Body mafiapig1body=createPig(1335,220);
        mafiaPig1=new MafiaPig(MafiaPig1Texture,mafiapig1body);
        stage.addActor(mafiaPig1);
        Body mafiapig2body=createPig(1415,360);
        mafiaPig2=new MafiaPig(MafiaPig2Texture,mafiapig2body);
        stage.addActor(mafiaPig2);
        Body mafiapig3body=createPig(1415,580);
        mafiaPig3=new MafiaPig(MafiaPig3Texture,mafiapig3body);
        stage.addActor(mafiaPig3);
        Body mafiapig4body=createPig(1415,220);
        mafiaPig4=new MafiaPig(MafiaPig4Texture,mafiapig4body);
        stage.addActor(mafiaPig4);
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

    private Body createCatapult(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody; // Catapult is static
        bodyDef.position.set(x / PPM, y / PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(225 / PPM, 150 / PPM); // Half-width and half-height of the catapult

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }


    private void createObstacles() {
        Body woodBody9a=createWood(1260,185);
        woodObstacle9a=new WoodObstacles(new Texture("9.png"),woodBody9a);
        woodObstacle9a.setSize(40,175);
        stage.addActor(woodObstacle9a);
        Body woodBody9b=createWood(1600,185);
        woodObstacle9b=new WoodObstacles(new Texture("9.png"),woodBody9b);
        woodObstacle9b.setSize(40,175);
        stage.addActor(woodObstacle9b);
        Body woodBody9c=createWood(1310,350);
        woodObstacle9c=new WoodObstacles(new Texture("9.png"),woodBody9c);
        woodObstacle9c.setSize(40,175);
        stage.addActor(woodObstacle9c);
        Body woodBody9d=createWood(1550,350);
        woodObstacle9d=new WoodObstacles(new Texture("9.png"),woodBody9d);
        woodObstacle9d.setSize(40,175);
        stage.addActor(woodObstacle9d);
        Body woodBody13a=createWood(1258,320);
        woodObstacle13a=new WoodObstacles(new Texture("13_H.png"),woodBody13a);
        woodObstacle13a.setSize(350,40);
        stage.addActor(woodObstacle13a);
        Body woodBody13b=createWood(1258,185);
        woodObstacle13b=new WoodObstacles(new Texture("13_H.png"),woodBody13b);
        woodObstacle13b.setSize(350,40);
        stage.addActor(woodObstacle13b);
        Body woodBody13c=createWood(1278,510);
        woodObstacle13c=new WoodObstacles(new Texture("13_H.png"),woodBody13c);
        woodObstacle13c.setSize(350,40);
        stage.addActor(woodObstacle13c);
        Body woodBody14=createWood(1368,545);
        woodObstacle14=new WoodObstacles(new Texture("Block.png"),woodBody14);
        woodObstacle14.setSize(170,170);
        stage.addActor(woodObstacle14);
    }
    private Body createWood(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20/PPM,87.5f/PPM);
//        shape.setRadius(25 / PPM); // Bird radius
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
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
    @Override
    public void render(float delta) { //Rendering
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        world.step(1 / 60f, 6, 2);
        if(!isPaused){ // Checking if Pause Button is clicked
            batch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
            batch.draw(pauseButtonTexture, 50, 900, 100, 100);
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
        if (mafiaPig2 != null) mafiaPig2.dispose();
        if (mafiaPig3 != null) mafiaPig3.dispose();
        if (mafiaPig4 != null) mafiaPig4.dispose();
        if (woodObstacle9a != null) woodObstacle9a.dispose();
        if (woodObstacle9b != null) woodObstacle9b.dispose();
        if (woodObstacle13c != null) woodObstacle13c.dispose();
        if (woodObstacle13a != null) woodObstacle13a.dispose();
        if (woodObstacle14 != null) woodObstacle14.dispose();
        if (catapult != null) catapult.dispose();
        if (BackgroundTexture != null) BackgroundTexture.dispose();
        if (EndButton2Texture != null) EndButton2Texture.dispose();
        if (PauseButtonSound != null) PauseButtonSound.dispose();
        if (ResumeButtonSound != null) ResumeButtonSound.dispose();
        if (EndButtonSound != null) EndButtonSound.dispose();
        if (batch != null) batch.dispose();
    }
}
