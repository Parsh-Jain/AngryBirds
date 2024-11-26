package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pigs extends Actor {
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

    public void dispose() { // Disposing
        pigTexture.dispose();
    }
}

