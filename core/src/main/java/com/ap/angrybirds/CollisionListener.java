package com.ap.angrybirds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class CollisionListener implements ContactListener {
    private World world;
    private Array<Body> bodiesToRemove;
    private Array<Texture> texturesToRemove;
    private float birdRemovalTimer;
    private Body birdToRemove;

    public CollisionListener(World world) {
        this.world = world;
        this.bodiesToRemove = new Array<>();
        this.texturesToRemove = new Array<>();
        this.birdRemovalTimer = 0f;
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        // Check if one of the bodies is a bird
        if (isBird(bodyA) || isBird(bodyB)) {
            // Check if the other body is a pig or wood obstacle
            if (isPig(bodyA) || isPig(bodyB) || isWoodObstacle(bodyA) || isWoodObstacle(bodyB)) {
                System.out.println("Collision detected");
                bodiesToRemove.add(bodyA);
                bodiesToRemove.add(bodyB);
                System.out.println("Bodies marked for removal");
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

    private void handleCollision(Body bird, Body target) {
        bodiesToRemove.add(target);
        // Assuming the texture is stored as user data in the body
        Texture texture = (Texture) target.getUserData();
        if (texture != null) {
            texturesToRemove.add(texture);
        }
        birdToRemove = bird;
        birdRemovalTimer = 2; // Set timer to 2 seconds
    }

    private boolean isBird(Body body) {
        return "RedBird".equals(body.getUserData()) || "YellowBird".equals(body.getUserData()) ||
            "BlueBird".equals(body.getUserData()) || "BlackBird".equals(body.getUserData());
    }

    private boolean isPig(Body body) {
        return "MafiaPig".equals(body.getUserData());
    }

    private boolean isWoodObstacle(Body body) {
        return "VerticalWood1".equals(body.getUserData()) || "VerticalWood2".equals(body.getUserData());
    }

    public void update(float delta) {
        if (birdRemovalTimer > 0) {
            birdRemovalTimer -= delta;
            if (birdRemovalTimer <= 0) {
                bodiesToRemove.add(birdToRemove);
                Texture birdTexture = (Texture) birdToRemove.getUserData();
                if (birdTexture != null) {
                    texturesToRemove.add(birdTexture);
                }
                birdToRemove = null;
            }
        }

    }

    public void processRemovals() {
        for (Body body : bodiesToRemove) {
            world.destroyBody(body);
        }
        bodiesToRemove.clear();

        for (Texture texture : texturesToRemove) {
            texture.dispose();
        }
        texturesToRemove.clear();
    }
}
