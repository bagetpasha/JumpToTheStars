package com.mygdx.game.Objects;

import com.badlogic.gdx.graphics.Texture;

public class FormlessCloudObject extends CloudObject{

    public FormlessCloudObject() {
        super();

        texture = new Texture("textures/formless_cloud.png");
    }

    @Override
    public boolean isHit(CharacterJoe joe) {
        return false;
    }
}
