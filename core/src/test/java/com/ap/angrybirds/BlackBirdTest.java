package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlackBirdTest {

    private World world;
    private Body body;
    private BlackBird blackBird;

    @Before
    public void setUp() {
        // Set up a Box2D world for physics simulation
        world = new World(new Vector2(0, -9.8f), true);

        // Create a body for the BlackBird
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(5, 5); // Initial position

        body = world.createBody(bodyDef);

        // Add a fixture to the body (initial size)
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.375f, 0.375f); // 75 / 2 / PPM = 0.375
        body.createFixture(shape, 1.0f);
        shape.dispose();

        // Create the BlackBird instance
        Texture mockTexture = new Texture("mockTexture.png"); // Replace with an actual mock if needed
        blackBird = new BlackBird(mockTexture, body);
    }

    @Test
    public void testActivateSpecialAbility() {
        // Verify initial size
        assertEquals(75, blackBird.getWidth(), 0.01f);
        assertEquals(75, blackBird.getHeight(), 0.01f);

        // Verify initial body dimensions
        Fixture initialFixture = body.getFixtureList().first();
        PolygonShape initialShape = (PolygonShape) initialFixture.getShape();
        Vector2 initialDimensions = new Vector2();
        initialShape.getVertex(1, initialDimensions); // Get half-width and half-height
        assertEquals(0.375f, initialDimensions.x, 0.01f);
        assertEquals(0.375f, initialDimensions.y, 0.01f);

        // Activate special ability
        blackBird.activateSpecialAbility();

        // Verify updated size
        assertEquals(150, blackBird.getWidth(), 0.01f); // Doubled width
        assertEquals(150, blackBird.getHeight(), 0.01f); // Doubled height

        // Verify updated body dimensions
        Fixture updatedFixture = body.getFixtureList().first();
        PolygonShape updatedShape = (PolygonShape) updatedFixture.getShape();
        Vector2 updatedDimensions = new Vector2();
        updatedShape.getVertex(1, updatedDimensions); // Get updated half-width and half-height
        assertEquals(0.75f, updatedDimensions.x, 0.01f); // Doubled half-width
        assertEquals(0.75f, updatedDimensions.y, 0.01f); // Doubled half-height

        // Verify position remains unchanged
        Vector2 position = body.getPosition();
        assertEquals(5, position.x, 0.01f);
        assertEquals(5, position.y, 0.01f);
    }
}
