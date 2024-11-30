package com.ap.angrybirds;

import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import static org.junit.Assert.*;

public class SaveGameStateTest {

    private l gameInstance;
    private GameState1 gameState;

    @Before
    public void setUp() {
        // Initialize the game instance and its dependencies
        gameState = new GameState1();
        gameInstance = new l(new Main(), gameState);

        // Populate the game state with sample data
        gameInstance.score = 500;
        gameInstance.birdcount = 3;

        // Add sample birds
        Bird bird = new Bird();
        bird.setPosition(new Vector2(100, 200));
        gameInstance.birds.add(bird);

        // Add sample pigs
        MafiaPig pig = new MafiaPig();
        pig.setPosition(new Vector2(300, 400));
        gameInstance.pigs.add(pig);

        // Add sample wood obstacles
        WoodObstacles wood = new WoodObstacles();
        wood.setPosition(new Vector2(500, 600));
        gameInstance.woodObstacles.add(wood);
    }

    @Test
    public void testSaveGameState() {
        // Call the saveGameState method
        gameInstance.saveGameState();

        // Verify the file is created
        File savedFile = new File("gamestate.ser");
        assertTrue("The game state file should be created.", savedFile.exists());

        // Deserialize the game state from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedFile))) {
            GameState1 savedState = (GameState1) ois.readObject();

            // Verify the saved state matches the current game state
            assertEquals("The saved score should match.", gameInstance.score, savedState.score);
            assertEquals("The saved bird count should match.", gameInstance.birdcount, savedState.birdCount);
            assertEquals("The saved bird positions should match.", 1, savedState.birdPositions.size());
            assertEquals("The saved pig positions should match.", 1, savedState.pigPositions.size());
            assertEquals("The saved wood positions should match.", 1, savedState.woodPositions.size());

            // Verify bird data
            GameState1.SerializableVector2 savedBird = savedState.birdPositions.get(0);
            assertEquals(100, savedBird.x, 0.01f);
            assertEquals(200, savedBird.y, 0.01f);

            // Verify pig data
            GameState1.SerializableVector2 savedPig = savedState.pigPositions.get(0);
            assertEquals(300, savedPig.x, 0.01f);
            assertEquals(400, savedPig.y, 0.01f);

            // Verify wood obstacle data
            GameState1.SerializableVector2 savedWood = savedState.woodPositions.get(0);
            assertEquals(500, savedWood.x, 0.01f);
            assertEquals(600, savedWood.y, 0.01f);

        } catch (Exception e) {
            fail("Deserialization of the game state failed: " + e.getMessage());
        } finally {
            // Clean up the test file
            savedFile.delete();
        }
    }
}
