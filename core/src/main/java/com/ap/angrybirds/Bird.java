package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bird extends Actor {
    protected Texture birdTexture; // Attribute

    public Bird(Texture birdTexture) { // Constructor
        this.birdTexture = birdTexture;
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

    public void dispose() { // Disposing Bird Texture
        birdTexture.dispose();
    }
}

