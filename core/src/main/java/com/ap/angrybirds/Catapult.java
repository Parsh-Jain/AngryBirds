package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Catapult extends Actor {
    protected Texture CatapultTexture; // Attribute

    public Catapult(Texture CatapultTexture) { // Constructor
        this.CatapultTexture = CatapultTexture;
        setSize(450, 300);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { // Drawing Catapult Texture
        batch.draw(CatapultTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void dispose() {
        CatapultTexture.dispose();
    } // Disposing Catapult Texture
}

