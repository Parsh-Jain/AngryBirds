package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class BlueBird extends Bird {
    static float PPM = 100f;

    // Constructor
    public BlueBird(Texture birdTexture, Body body) {
        super(birdTexture, body);
        setSize(75, 75);
    }

    // Override the act method to update bird position
    @Override
    public void act(float delta) {
        super.act(delta);
        Vector2 position = getBody().getPosition();
        setPosition(position.x * PPM - getWidth() / 2, position.y * PPM - getHeight() / 2);
    }

    // Special ability: Multiply into 3 birds moving in random directions
    public void activateSpecialAbility(World world, Texture birdTexture, Stage stage) {
        // Get the current bird's position and body
        Body originalBody = getBody();
        Vector2 originalPosition = originalBody.getPosition();

        // Generate 3 new birds
        for (int i = 0; i < 3; i++) {
            // Create a new body for each bird
            Body newBody = createNewBirdBody(world, originalPosition);

            // Apply a random direction to the new bird
            Random random = new Random();
            float randomX = (random.nextFloat() - 0.5f) * 5; // Random value between -2.5 and 2.5
            float randomY = (random.nextFloat() - 0.5f) * 5; // Random value between -2.5 and 2.5
            newBody.applyLinearImpulse(new Vector2(randomX, randomY), newBody.getWorldCenter(), true);

            // Create a new bird instance
            BlueBird newBird = new BlueBird(birdTexture, newBody);
            stage.addActor(newBird);
            System.out.println("New bird created with random direction: " + randomX + ", " + randomY);
        }

        System.out.println("Special ability activated: BlueBird multiplied into 3!");
    }

    // Helper method to create a new bird's body in the world
    private Body createNewBirdBody(World world, Vector2 position) {
        // Define a new body for the bird
        Body body = BirdUtils.createCircleBody(world, position, 0.37f, 1.0f); // 0.37 is radius in meters (adjust as needed)
        return body;
    }
}
