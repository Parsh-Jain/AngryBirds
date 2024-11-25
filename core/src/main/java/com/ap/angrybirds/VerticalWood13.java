package com.ap.angrybirds;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.graphics.Texture;
//
//public class VerticalWood13 extends Actor {
//    private Body body;
//    private final float widthPixels = 1.5f * 100; // Assuming 1 meter = 10 pixels
//    private final float heightPixels = 13 * 100;
//
//    public VerticalWood13(World world, float x, float y) {
//        // Create body definition
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.position.set(x, y); // Coordinates in meters
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//
//        // Create the body
//        this.body = world.createBody(bodyDef);
//
//        // Define shape and size
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(widthPixels / 2 / 100f, heightPixels / 2 / 100f); // Convert to meters
//
//        // Define fixture
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 0.5f;
//        fixtureDef.friction = 0.6f;
//        fixtureDef.restitution = 0.3f;
//
//        // Attach fixture to body
//        this.body.createFixture(fixtureDef);
//        shape.dispose();
//    }
//
//    public Body getBody() {
//        return body;
//    }
//}

public class VerticalWood13 extends WoodObstacles{
    public VerticalWood13(Texture woodTexture, Body body){
        super(woodTexture, body);
        setSize(25,250);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}

