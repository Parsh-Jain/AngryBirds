package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Catapult extends Actor {
    protected Texture CatapultTexture;

    public Catapult(Texture CatapultTexture) {
        this.CatapultTexture = CatapultTexture;
        setSize(300, 300);  // Default size
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(CatapultTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Pig-specific actions here
    }

    public void dispose() {
        CatapultTexture.dispose();
    }
}

