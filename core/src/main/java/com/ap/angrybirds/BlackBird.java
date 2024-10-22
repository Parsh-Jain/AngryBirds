package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class BlackBird extends Bird{
    public BlackBird(Texture birdTexture) {
        super(new Texture("BlackAngryBird.png"));
        setSize(75,75);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
