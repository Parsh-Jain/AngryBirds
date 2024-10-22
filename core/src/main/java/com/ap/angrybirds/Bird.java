package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bird extends Actor {
    protected Texture birdTexture;

    public Bird(Texture birdTexture) {
        this.birdTexture = birdTexture;
        setSize(100, 100);  // Default size
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(birdTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Bird-specific actions here (e.g., flying)
    }

    public void dispose() {
        birdTexture.dispose();
    }
}

