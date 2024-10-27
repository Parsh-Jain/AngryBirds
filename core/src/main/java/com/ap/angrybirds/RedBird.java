package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class RedBird extends Bird{
    public RedBird(Texture birdTexture)  { // Constructor
        super(new Texture("RedAngryBird.png")); // Accessing constructor of Bird
        setSize(75,75);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
