package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class BlackBird extends Bird{
    float PPM = 100f;
    public BlackBird(Texture birdTexture, Body body) {
        super(birdTexture, body);
        setSize(75,75);
    }

    //    public BlackBird(Texture birdTexture) {
//        super(new Texture("BlackAngryBird.png")); //Accessing Constructor of Bird class
//        setSize(75,75);
//    }
    @Override
    public void act(float delta) {
        super.act(delta);
        Vector2 position = getBody().getPosition();
        setPosition(position.x * PPM - getWidth() / 2, position.y * PPM - getHeight() / 2);
    }
    public void activateSpecialAbility() {

            // Increase size of the bird's texture
            float newWidth = getWidth() * 2f; // Increase by 50%
            float newHeight = getHeight() * 2f;
            setSize(newWidth, newHeight);

            // Adjust the physics body size
            Body body = getBody();

            // Save the current body's position to reapply later
            Vector2 currentPosition = body.getPosition();
            float currentAngle = body.getAngle();

            // Destroy existing fixture
            body.destroyFixture(body.getFixtureList().first());

            // Create a new shape with updated dimensions
            PolygonShape newShape = new PolygonShape();
            float newBodyWidth = (newWidth / 2) / PPM;
            float newBodyHeight = (newHeight / 2) / PPM;
            newShape.setAsBox(newBodyWidth, newBodyHeight);

            // Attach the new fixture to the body
            body.createFixture(newShape, 1.0f); // Density = 1.0f (adjust as needed)
            newShape.dispose(); // Clean up the shape

            // Restore the position and rotation of the body
            body.setTransform(currentPosition, currentAngle);

            System.out.println("Special ability activated: Size increased!");
        }




}
