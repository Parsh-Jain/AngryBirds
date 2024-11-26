package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bird extends Actor {
    protected Texture birdTexture;
    private Body body;// Attribute

    public Bird(Texture birdTexture, Body body) { // Constructor
        this.birdTexture = birdTexture;
        this.body=body;
        setSize(100, 100);  // Default size
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { //Drawing Bird Texture
        batch.draw(birdTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    // Method to return the physics body of the bird
    public Body getBody() {
        return body;
    }
    public void setUserData(Object userData) {
        body.setUserData(userData);
    }
    public Object getUserData() {
        return body.getUserData();
    }


    public void dispose() { // Disposing Bird Texture
        birdTexture.dispose();
    }
}

