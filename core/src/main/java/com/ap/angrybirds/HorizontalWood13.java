package com.ap.angrybirds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.Texture;


public class HorizontalWood13 extends WoodObstacles {
    static float PPM = 100f;

    public HorizontalWood13(Texture woodTexture, Body body) {
        super(woodTexture, body);
        setSize(250, 25);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Vector2 position = getBody().getPosition();
        setPosition(position.x * PPM - getWidth() / 2, position.y * PPM - getHeight() / 2);
    }
}
