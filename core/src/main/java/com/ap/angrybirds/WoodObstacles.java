package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class WoodObstacles extends Actor{
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

    public void dispose() { // Disposing Bird Texture
        woodTexture.dispose();
    }
}

//public class WoodObstacles extends Obstacles {
//    public WoodObstacles(Texture obstacleTexture, Body body) {
//        super(obstacleTexture, body);
//    }
//}


