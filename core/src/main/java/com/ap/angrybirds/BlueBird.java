package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class BlueBird extends Bird{
    public BlueBird(Texture birdTexture) {
        super(new Texture("BlueAngryBird.png"));
        setSize(75,75);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
