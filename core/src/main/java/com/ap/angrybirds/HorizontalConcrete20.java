package com.ap.angrybirds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.Texture;


public class HorizontalConcrete20 extends WoodObstacles {
    float height;
    static float PPM = 100f;

    public HorizontalConcrete20(Texture woodTexture, Body body) {
        super(woodTexture, body);
        setSize(700, 25);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Vector2 position = getBody().getPosition();
        setPosition(position.x * PPM - getWidth() / 2, position.y * PPM - getHeight() / 2);
    }
}
