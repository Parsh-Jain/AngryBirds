package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.physics.box2d.Body;

public class Catapult extends Actor {
    protected Texture CatapultTexture; // Attribute
    private Body body;

    public Catapult(Texture CatapultTexture, Body body) { // Constructor
        this.CatapultTexture = CatapultTexture;
        this.body=body;
        setSize(370, 220);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Update the actor's position based on the Box2D body
        setPosition(
            body.getPosition().x - getWidth() / 2,
            body.getPosition().y - getHeight() / 2
        );
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

