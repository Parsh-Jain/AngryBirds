package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pigs extends Actor {
    protected Texture pigTexture;

    public Pigs(Texture pigTexture) {
        this.pigTexture = pigTexture;
        setSize(100, 100);  // Default size
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pigTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Pig-specific actions here
    }

    public void dispose() {
        pigTexture.dispose();
    }
}

