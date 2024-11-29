package com.ap.angrybirds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.*;

public class SuccessfulEndScreen extends ScreenAdapter implements Serializable {
    Main main; // Important Attributes
    SpriteBatch batch;
    Texture background;
    Texture levelMenu;
    Texture text;
    Rectangle levelMenu_Button;
    Music LevelButtonSound;
    OrthographicCamera camera;
    Viewport viewport;
    float worldWidth = 1920;
    float worldHeight = 1080;
    BitmapFont font; // Font to display score
    int score;// Store the score
    GameState1 gameState;

    public SuccessfulEndScreen(Main main,GameState1 gameState){
        this.gameState=gameState;
        this.main = main;
    } // Constructor

    @Override
    public void show() { // show method to create all the attributes
        batch = new SpriteBatch();
        background = new Texture("SuccessfullBackground.png");
        text = new Texture("Level Menu.png");
        levelMenu = new Texture("BasicButton.png");
        levelMenu_Button = new Rectangle(690, 45, 520, 122);
        LevelButtonSound = Gdx.audio.newMusic(Gdx.files.internal("NormalButtonSound.mp3"));
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        viewport.apply();
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();

        // Initialize font
        font = new BitmapFont();
        font.getData().setScale(3f); // Adjust scale for visibility

        // Load score from the saved game file
        score = loadScoreFromSavedState();
    }

    private int loadScoreFromSavedState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gamestate.ser"))) {
            GameState1 gameState = (GameState1) ois.readObject();
            return gameState.score; // Extract the score
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0; // Return 0 if loading fails
        }
    }
    private void saveGameState() {
        gameState.score = this.score; // Save the score

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gamestate.ser"))) {
            oos.writeObject(gameState);
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(float delta) { // Rendering
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Draw background and UI elements
        batch.draw(background, 0, 0);
        batch.draw(levelMenu, 690, 45, 520, 122);
        batch.draw(text, 755, 65, 400, 80);

        // Render score
        String scoreText = "Score: " + score;
        font.draw(batch, scoreText, worldWidth / 2 - 100, worldHeight / 2 + 100); // Centered position
        batch.end();

        // Checking User Input
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            if (levelMenu_Button.contains(touchPos.x, touchPos.y)) {
                LevelButtonSound.play();
                saveGameState(); // Save the game state before switching to LevelPage
                main.setScreen(new LevelPage(main)); // switch to LevelPage
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    } // Resizing

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        background.dispose();
        levelMenu.dispose();
        text.dispose();
        font.dispose();
        LevelButtonSound.dispose();
    } // Disposing
}
