package com.ap.angrybirds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Input;

public class misc extends ApplicationAdapter {
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer shapeRenderer;
    float circleX;
    float circleY;

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        font.getData().setScale(3f);
        circleX = Gdx.graphics.getWidth()/4+50;
        circleY = 3*Gdx.graphics.getHeight()/4;
    }


    @Override
    public void render(){
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isTouched()){
            circleX = Gdx.input.getX();
            circleY = Gdx.graphics.getHeight()-Gdx.input.getY();
        }if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            circleX += 20;
        }if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            circleX -= 20;
        }if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            circleY += 20;
        }if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            circleY -= 20;
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1f,0.5f,0.5f,1);
        shapeRenderer.circle(circleX, circleY, 50);
        shapeRenderer.end();

        batch.begin();
        font.getData().setScale(5.2f);
        font.draw(batch, "New York", Gdx.graphics.getWidth()/4-50, 3*Gdx.graphics.getHeight()/4);
        font.draw(batch, "Boston", 3*Gdx.graphics.getWidth()/4, 3*Gdx.graphics.getHeight()/4);
        font.draw(batch, "Munich", 3*Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
        font.draw(batch, "Bangalore", Gdx.graphics.getWidth()/4-50, Gdx.graphics.getHeight()/4);
        font.getData().setScale(3.2f);
        font.draw(batch, "AIRPLANE", circleX-100, circleY);
        batch.end();

        if(circleX<Gdx.graphics.getWidth()/4+100 && circleX>Gdx.graphics.getWidth()/4-100 && circleY<Gdx.graphics.getHeight()/4+100 && circleY>Gdx.graphics.getHeight()/4-100){
            batch.begin();
            font.getData().setScale(6f);
            font.draw(batch, "Welcome to Bangalore!", Gdx.graphics.getWidth()/2-400, Gdx.graphics.getWidth()/2-200);
            batch.end();
        }
    }

    @Override
    public void dispose(){
        batch.dispose();
        font.dispose();
        shapeRenderer.dispose();
    }

}
