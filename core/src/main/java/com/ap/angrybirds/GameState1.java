package com.ap.angrybirds;

import java.io.Serializable;
import java.util.HashSet;

public class GameState1 implements Serializable {
    private static final long serialVersionUID = 1L;

    public int score;
    public int birdCount;

    // Hardcoded positions for birds, pigs, and wood obstacles
    public static final SerializableVector2 RED_BIRD_POSITION = new SerializableVector2(4.8f, 2.0f, "RedBird");
    public static final SerializableVector2 YELLOW_BIRD_POSITION = new SerializableVector2(1.9f, 2.0f, "YellowBird");
    public static final SerializableVector2 BLUE_BIRD_POSITION = new SerializableVector2(3.3f, 2.0f, "BlueBird");
    public static final SerializableVector2 BLACK_BIRD_POSITION = new SerializableVector2(2.6f, 2.0f, "BlackBird");

    public static final SerializableVector2 PIG_POSITION = new SerializableVector2(14.6f, 2.2f, null);
    public static final SerializableVector2 WOOD1_POSITION = new SerializableVector2(13.54f, 4.1f, null);
    public static final SerializableVector2 WOOD2_POSITION = new SerializableVector2(15.54f, 4.1f, null);

    // Record destroyed entities
    public HashSet<String> destroyedEntities;
    public HashSet<SerializableVector2> birdPositions;
    public HashSet<SerializableVector2> pigPositions;
    public HashSet<SerializableVector2> woodPositions;

    public GameState1() {
        // Initialize the collections
        this.birdPositions = new HashSet<>();
        this.pigPositions = new HashSet<>();
        this.woodPositions = new HashSet<>();
        this.destroyedEntities = new HashSet<>();
    }

    // Add an entity to the destruction record
    public void recordDestroyed(String entityName) {
        destroyedEntities.add(entityName);
    }

    // Inner class to make Vector2 serializable
    public static class SerializableVector2 implements Serializable {
        private static final long serialVersionUID = 1L;
        public float x, y;
        public String birdType; // Use consistent naming

        // Constructor
        public SerializableVector2(float x, float y, String birdType) {
            this.x = x;
            this.y = y;
            this.birdType = birdType; // Can be null for non-bird entities
        }

//        @Override
//        public String toString() {
//            return "SerializableVector2{" +
//                "x=" + x +
//                ", y=" + y +
//                ", birdType='" + birdType + '\'' +
//                '}';
//        }
    }
}
