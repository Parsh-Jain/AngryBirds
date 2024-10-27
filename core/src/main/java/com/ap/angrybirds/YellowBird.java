package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class YellowBird extends Bird{
    public YellowBird(Texture birdTexture) { // Constructor
        super(new Texture("YellowAngryBird.png")); // Accessing Constructor of Bird
        setSize(80,80);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
