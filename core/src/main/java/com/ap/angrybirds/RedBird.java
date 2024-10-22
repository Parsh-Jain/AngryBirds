package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class RedBird extends Bird{
    public RedBird(Texture birdTexture) {
        super(new Texture("RedAngryBird.png"));
        setSize(100,100);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
