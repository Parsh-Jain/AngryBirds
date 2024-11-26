package com.ap.angrybirds;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Body;
import java.util.ArrayList;
import java.util.List;

public class CollisionListener implements ContactListener {

    private final List<Body> bodiesToRemove = new ArrayList<>();

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
        // Handle end of contact if needed
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Handle pre-solve if needed
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Handle post-solve if needed
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

    public void processRemovals() {
        for (Body body : bodiesToRemove) {
            if (body != null) {
                body.setUserData(null);
                body.getWorld().destroyBody(body);
            }
        }
        bodiesToRemove.clear();
    }
}
