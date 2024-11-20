package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class BlackBird extends Bird{
    public BlackBird(Texture birdTexture, Body body) {
        super(birdTexture, body);
        setSize(75,75);
    }

    //    public BlackBird(Texture birdTexture) {
//        super(new Texture("BlackAngryBird.png")); //Accessing Constructor of Bird class
//        setSize(75,75);
//    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
