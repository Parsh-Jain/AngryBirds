package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import org.w3c.dom.Text;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private SpriteBatch batch;
//    private Texture image;
//    Sprite sprite;
    BitmapFont font;
    ShapeRenderer shapeRenderer;


    @Override
    public void create() {
//        batch = new SpriteBatch();
//        image = new Texture("angryb.png");
//        sprite = new Sprite(image);
//        sprite.setPosition((Gdx.graphics.getWidth()-image.getWidth()) / 2, (Gdx.graphics.getHeight()-image.getHeight()) / 2);
//        sprite.setScale(2f);
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        setScreen(new LoadingPage(this));


    }


//    public void render() {
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        //batch.draw(image, (Gdx.graphics.getWidth()-image.getWidth())/2, (Gdx.graphics.getHeight()-image.getHeight())/2);
//        batch.draw(sprite, sprite.getX(), sprite.getY());
//        batch.end();
//    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
