package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class GameObject {


    public int x;
    public int y;
    int width;
    int height;
    int velocityX;
    int velocityY;

    Texture texture;


    public GameObject(int x, int y, int width, int height, String texturePath){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        velocityX = 0;
        velocityY = 0;

        texture = new Texture(texturePath);

    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }



}
