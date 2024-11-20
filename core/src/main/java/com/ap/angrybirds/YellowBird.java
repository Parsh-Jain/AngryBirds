package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class YellowBird extends Bird{
    public YellowBird(Texture birdTexture, Body body) {
        super(birdTexture, body);
        setSize(80,80);
    }

    //    public YellowBird(Texture birdTexture) { // Constructor
//        super(new Texture("YellowAngryBird.png")); // Accessing Constructor of Bird
//        setSize(80,80);
//    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
