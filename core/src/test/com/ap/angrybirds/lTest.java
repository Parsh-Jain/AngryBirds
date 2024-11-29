//package com.ap.angrybirds;
//
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.Array;
//
//public class lTest {
//
//    private l gameScreen;
//    private GameState1 gameState;
//
//    @Before
//    public void setUp() {
//        // Mock Main object
//        Main main = new Main(); // You might need to mock or stub Main class
//
//        // Create a mock GameState1 for testing
//        gameState = new GameState1();
//        gameState.birdPositions.add(new GameState1.SerializableVector2(4.8f, 2.0f, "RedBird"));
//        gameState.pigPositions.add(new GameState1.SerializableVector2(14.6f, 2.2f, null));
//        gameState.woodPositions.add(new GameState1.SerializableVector2(13.54f, 4.1f, null));
//
//        // Initialize game screen with mock GameState1
//        gameScreen = new l(main, gameState);
//    }
//
//    @Test
//    public void testInitializeFromSavedState() {
//        gameScreen.initializeFromSavedState();
//
//        // Check that birds are correctly initialized
//        Array<Bird> birds = gameScreen.getBirds();
//        assertEquals(1, birds.size);
//        Bird bird = birds.get(0);
//        assertEquals(4.8f, bird.getPosition().x, 0.01);
//        assertEquals(2.0f, bird.getPosition().y, 0.01);
//        assertTrue(bird instanceof RedBird);
//
//        // Check that pigs are correctly initialized
//        Array<MafiaPig> pigs = gameScreen.getPigs();
//        assertEquals(1, pigs.size);
//        MafiaPig pig = pigs.get(0);
//        assertEquals(14.6f, pig.getBody().getPosition().x, 0.01);
//        assertEquals(2.2f, pig.getBody().getPosition().y, 0.01);
//
//        // Check that wood obstacles are correctly initialized
//        Array<VerticalWood13> woods = gameScreen.getWoodObstacles();
//        assertEquals(1, woods.size);
//        VerticalWood13 wood = woods.get(0);
//        assertEquals(13.54f, wood.getBody().getPosition().x, 0.01);
//        assertEquals(4.1f, wood.getBody().getPosition().y, 0.01);
//    }
//
//    @Test
//    public void testAddBird() {
//        GameState1.SerializableVector2 newBirdPos = new GameState1.SerializableVector2(5.0f, 3.0f, "YellowBird");
//        gameScreen.addBird(newBirdPos);
//
//        Array<Bird> birds = gameScreen.getBirds();
//        assertEquals(2, birds.size);
//
//        Bird addedBird = birds.get(1);
//        assertEquals(5.0f, addedBird.getPosition().x, 0.01);
//        assertEquals(3.0f, addedBird.getPosition().y, 0.01);
//        assertTrue(addedBird instanceof YellowBird);
//    }
//
//    @Test
//    public void testAddPig() {
//        GameState1.SerializableVector2 newPigPos = new GameState1.SerializableVector2(10.0f, 2.5f, null);
//        gameScreen.addPig(newPigPos);
//
//        Array<MafiaPig> pigs = gameScreen.getPigs();
//        assertEquals(2, pigs.size);
//
//        MafiaPig addedPig = pigs.get(1);
//        assertEquals(10.0f, addedPig.getBody().getPosition().x, 0.01);
//        assertEquals(2.5f, addedPig.getBody().getPosition().y, 0.01);
//    }
//
//    @Test
//    public void testAddWood() {
//        GameState1.SerializableVector2 newWoodPos = new GameState1.SerializableVector2(12.0f, 4.5f, null);
//        gameScreen.addWood(newWoodPos);
//
//        Array<VerticalWood13> woods = gameScreen.getWoodObstacles();
//        assertEquals(2, woods.size);
//
//        VerticalWood13 addedWood = woods.get(1);
//        assertEquals(12.0f, addedWood.getBody().getPosition().x, 0.01);
//        assertEquals(4.5f, addedWood.getBody().getPosition().y, 0.01);
//    }
//}
