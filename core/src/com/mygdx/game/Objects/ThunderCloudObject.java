package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class ThunderCloudObject extends CloudObject {

    public ThunderCloudObject() {
        super();

        texture = new Texture("textures/thunder_cloud.png");
    }

    @Override
    public boolean isHit(CharacterJoe joe) {

        if (joe.y >= y
                && joe.y <= height + y
                && joe.x + joe.width >= x
                && joe.x < x + width) {

            joe.jump = true;
            joe.lastTimeJump = TimeUtils.millis();

            return true;
        }
        return false;
    }
}
