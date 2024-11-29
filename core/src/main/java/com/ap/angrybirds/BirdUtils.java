package com.ap.angrybirds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BirdUtils {
    // Helper method to create a circular body for a bird
    public static Body createCircleBody(World world, Vector2 position, float radius, float density) {
        // Define the body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Birds are dynamic bodies
        bodyDef.position.set(position);

        // Create a circular shape
        CircleShape shape = new CircleShape();
        shape.setRadius(radius); // Set the radius in meters (adjust as needed)

        // Define the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density; // Set the density
        fixtureDef.friction = 0.4f;   // Set friction
        fixtureDef.restitution = 0.6f; // Bounciness

        // Create the body in the world
        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        // Dispose the shape to free memory
        shape.dispose();

        return body;
    }
}
