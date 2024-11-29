package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class MafiaPig extends Pigs{
    static float PPM = 100f;
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
        Vector2 position = getBody().getPosition();
        setPosition(position.x * PPM - getWidth() / 2, position.y * PPM - getHeight() / 2);
    }

    @Override
    public Body getBody() {
        return super.getBody();
    }
}
