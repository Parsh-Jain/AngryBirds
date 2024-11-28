package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.io.Serializable;

public class Pigs extends Actor implements Serializable {
    protected Texture pigTexture; // Attribute
    protected Body body;

    public Pigs(Texture pigTexture,Body body) { // Constructor
        this.pigTexture = pigTexture;
        this.body = body;
        setSize(100, 100);  // Default size
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // Drawing Pig texture
        batch.draw(pigTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Pig-specific actions here
    }

    public Body getBody() {
        return body;
    }
    public Vector2 getPosition() {
        return body != null ? body.getPosition() : new Vector2(getX(), getY());
    }

    public void dispose() { // Disposing
        pigTexture.dispose();
    }
    public void setUserData(Object userData) {
        body.setUserData(userData);
    }
    public Object getUserData() {
        return body.getUserData();
    }
}

