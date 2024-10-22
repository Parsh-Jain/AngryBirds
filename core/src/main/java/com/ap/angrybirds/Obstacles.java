package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Obstacles extends Actor {
    protected Texture obstacleTexture;

    public Obstacles(Texture obstacleTexture) {
        this.obstacleTexture = obstacleTexture;
        setSize(200, 200);  // Default size
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(obstacleTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Obstacle-specific actions here
    }

    public void dispose() {
        obstacleTexture.dispose();
    }
}
