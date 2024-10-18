package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class helloworld1 extends ApplicationAdapter{
    ShapeRenderer shapeRenderer;
    Sprite sprite;
    Texture image;
    Batch batch;

    float speedX = 130;

    float rectX;
    float rectY;

    @Override
    public void create () {
        shapeRenderer  = new ShapeRenderer();
        batch = new SpriteBatch();
        image = new Texture("angryb.png");
        sprite = new Sprite(image);
        sprite.setScale(0.8f);
        sprite.setPosition(Gdx.graphics.getWidth()/2-image.getWidth()/2, Gdx.graphics.getHeight()/2-image.getHeight()/2);
//        sprite.getBoundingRectangle();
        sprite.setAlpha(0.8f);
        rectX = Gdx.graphics.getWidth()/2-200;
        rectY = 270;
    }

    float width = 0;

    @Override
    public void render () {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(width<400){
            width += speedX * Gdx.graphics.getDeltaTime();;
        }


        batch.begin();
        sprite.draw(batch);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.15f, 0.1f, 0.1f, 1);
        shapeRenderer.arc(Gdx.graphics.getWidth()/2-220,270,20,1,180);
        shapeRenderer.rect(rectX, rectY, width, 40);
        shapeRenderer.end();
    }

    @Override
    public void dispose () {
        shapeRenderer.dispose();
        image.dispose();
        batch.dispose();
    }
}
