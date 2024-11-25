package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.ap.angrybirds.BlueBird.PPM;

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
        Vector2 position = getBody().getPosition();
        setPosition(position.x * PPM - getWidth() / 2, position.y * PPM - getHeight() / 2);
    }

}
