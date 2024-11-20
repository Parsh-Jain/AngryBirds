package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class BlueBird extends Bird{
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
    }

}
