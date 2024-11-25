package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BlueBird extends Bird{
    static float PPM = 100f;
    public BlueBird(Texture birdTexture, Body body) {
        super(birdTexture, body);
        setSize(75,75);
    }

    //    public BlueBird(Texture birdTexture) {
//        super(new Texture("BlueAngryBird.png")); // Accessing Constructor of Bird class
//        setSize(75,75);
//    }
    @Override
    public void act(float delta) {
        super.act(delta);
        Vector2 position = getBody().getPosition();
        setPosition(position.x * PPM - getWidth() / 2, position.y * PPM - getHeight() / 2);
    }

}
