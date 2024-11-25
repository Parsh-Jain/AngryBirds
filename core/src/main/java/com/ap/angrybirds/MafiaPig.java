package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class MafiaPig extends Pigs{
    public MafiaPig(Texture pigTexture, Body body) {
        super(pigTexture, body);
        setSize(75,75);
    }
//    public MafiaPig(Texture pigTexture) { // Constructor
//        super(new Texture("MafiaPig.png"));  // Accessing Constructor of Pigs`
//        setSize(75,75);
//    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
