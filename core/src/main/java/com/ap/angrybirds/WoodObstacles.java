package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.io.Serializable;

public class WoodObstacles extends Actor implements Serializable {
    protected Texture woodTexture;
    private Body body;

    public WoodObstacles(Texture woodTexture, Body body) {
        this.woodTexture = woodTexture;
        this.body = body;
        this.setSize(50,100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { //Drawing Bird Texture
        batch.draw(woodTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public Body getBody() {
        return body;
    }

    public void dispose() { // Disposing Bird Texture
        woodTexture.dispose();
    }
    public void setUserData(Object userData) {
        body.setUserData(userData);
    }
    public Object getUserData() {
        return body.getUserData();
    }
    public Vector2 getPosition() {
        return body != null ? body.getPosition() : new Vector2(getX(), getY());
    }
}


