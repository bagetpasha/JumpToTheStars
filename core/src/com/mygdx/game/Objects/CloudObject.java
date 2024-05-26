package com.mygdx.game.Objects;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.settingsAndElse.GameSettings;

import java.util.Random;

public class CloudObject extends GameObject {

    public int hitBoxHeight;

    public CloudObject() {
        super((new Random()).nextInt
                (GameSettings.SCREEN_WIDTH
                        - GameSettings.CLOUDS_WIDTH),
                GameSettings.SCREEN_HEIGHT,
                GameSettings.CLOUDS_WIDTH,
                GameSettings.CLOUDS_HEIGHT,
                "textures/simple_Cloud.png");

        velocityX = 0;
        velocityY = -GameSettings.CLOUDS_VELOCITY;
        hitBoxHeight = 52;
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

    public boolean isHit(CharacterJoe joe) {

        if (joe.y >= y
                && joe.y <= hitBoxHeight + y
                && joe.x + joe.width >= x
                && joe.x < x + width && ! joe.jump) {

            joe.jump = true;
            joe.lastTimeJump = TimeUtils.millis();

            return true;
        }
        return false;
    }

    public boolean isInFrame() {
        return y + height >= 0;
    }

}
