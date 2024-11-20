package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class WoodObstacles extends Obstacles {
    public WoodObstacles(Texture obstacleTexture, Body body) {
        super(obstacleTexture, body);
    }

//    public WoodObstacles(Texture obstacleTexture) { //  Constructor
//        super(obstacleTexture); // Accessing Constructor of Obstacles
//    }
}
