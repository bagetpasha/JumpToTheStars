package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.settingsAndElse.GameSettings;

import java.util.ArrayList;

public class CharacterJoe extends GameObject {

    int speedY;
    public int heightY;
    public int maxHeightY;
    public int pastHeightY;
    int speedX;
    float jumpHeight;
    int lifeLeft;
    long lastTimeJump;
    public boolean jump = true;
    public boolean toLeft = true;
    Texture[] textureArray;
    int textureIdx;
    MyGdxGame myGdxGame;

    public CharacterJoe(int x, int y) {
        super(x, y, GameSettings.JOE_WIDTH,
                GameSettings.JOE_HEIGHT,
                "textures/joe.png");
        speedY = 5;
        heightY = 0;
        maxHeightY = heightY;
        pastHeightY = 0;
        textureArray = new Texture[]{
                new Texture("textures/rightJoe.png"),
                new Texture("textures/jumpJoe.png"),
                new Texture("textures/joe.png"),
                new Texture("textures/joe3.png")
        };


        lifeLeft = 1;
        lastTimeJump = TimeUtils.millis();

    }

    public void move() {

        pastHeightY = heightY;

        if (jump) {
            heightY += speedY;

            if (heightY > maxHeightY) {
                maxHeightY = heightY;
            }

        } else {
            heightY -= speedY;
        }
    }

    public boolean hasToJump() {

        if (TimeUtils.millis() - lastTimeJump
                >= GameSettings.JOE_JUMP_COOL_DOWN) {

            lastTimeJump = TimeUtils.millis();
            return true;
        }

        return false;
    }
    public boolean isNeedToFall() {

        if (TimeUtils.millis() - lastTimeJump
                >= GameSettings.JOE_JUMP_COOL_DOWN && jump) {

            jump = false;

            return true;
        }
        return false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (jump && toLeft) {
            batch.draw(textureArray[1], x, y, width, GameSettings.JOE_HEIGHT2);
        }
        if (jump && !toLeft) {
            batch.draw(textureArray[3], x, y, width, GameSettings.JOE_HEIGHT2);
        }
        if (!jump && toLeft) {
            batch.draw(textureArray[2], x, y, width, height);
        }
        if (!jump && !toLeft) {
            batch.draw(textureArray[0], x, y, width, height);
        }
    }
}


