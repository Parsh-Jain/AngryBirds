package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;

public class MafiaPig extends Pigs{
    public MafiaPig(Texture pigTexture) { // Constructor
        super(new Texture("MafiaPig.png"));  // Accessing Constructor of Pigs`
        setSize(75,75);
    }
}
