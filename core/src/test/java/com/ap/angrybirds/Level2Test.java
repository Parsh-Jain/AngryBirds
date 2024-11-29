package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Level2Test {

    private level2 level;
    private Stage stage;
    private World world;

    @Before
    public void setUp() {
        Main main=new Main();
        level = new level2(main);
        stage = new Stage();
        world = new World(new Vector2(0, -9.81f), true);

        // Initialize important fields in level2
        level.stage = stage;
        level.birds = new Array<>();
        level.pigs = new Array<>();
        level.bodiesToDestroy = new Array<>();
        level.score = 0;
    }

    @Test
    public void testHandleBirdAndPigCollision() {
        // Mock bird and pig objects
        Texture birdTexture = new Texture("RedAngryBird.png");

        // Create a Body for the bird
        BodyDef birdBodyDef = new BodyDef();
        birdBodyDef.type = BodyDef.BodyType.DynamicBody; // Set the body type
        birdBodyDef.position.set(0, 0); // Set the initial position

        Body birdBody = world.createBody(birdBodyDef); // Create the body in the world

        // Create a Bird object
        Bird bird = new Bird(birdTexture, birdBody);

        // Create a Texture for the pig
        Texture pigTexture = new Texture("MafiaPig.png");

        // Create a Body for the pig
        BodyDef pigBodyDef = new BodyDef();
        pigBodyDef.type = BodyDef.BodyType.DynamicBody; // Set the body type
        pigBodyDef.position.set(1, 0); // Set the initial position

        Body pigBody = world.createBody(pigBodyDef); // Create the body in the world

        // Create a Pigs object
        Pigs pig = new Pigs(pigTexture, pigBody);

        // Mock their addition to stage and respective lists
        stage.addActor(bird);
        stage.addActor(pig);
        level.birds.add(bird);
        level.pigs.add(pig);

        // Call the method under test
        level.handleBirdAndPigCollision(bird, pig);

        // Assertions
        assertFalse(level.birds.contains(bird, true)); // Bird should be removed from the list
        assertFalse(level.pigs.contains(pig, true)); // Pig should be removed from the list
        assertEquals(200, level.score); // Score should be incremented by 200
        assertTrue(level.bodiesToDestroy.contains(bird.getBody(), true)); // Bird body should be marked for destruction
        assertTrue(level.bodiesToDestroy.contains(pig.getBody(), true)); // Pig body should be marked for destruction
        assertFalse(stage.getActors().contains(bird, true)); // Bird should be removed from the stage
        assertFalse(stage.getActors().contains(pig, true)); // Pig should be removed from the stage
    }
}
