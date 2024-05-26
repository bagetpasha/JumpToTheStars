package com.mygdx.game.UserExperience;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.settingsAndElse.GameSettings;

public class MovingBackgroundView {

    int y1;
    int y2;
    Texture texture;
    int speed = -2;

    public MovingBackgroundView(String path) {
        y1 = 0;
        y2 = GameSettings.SCREEN_HEIGHT;
        texture = new Texture(path);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, 0 , y1,
                GameSettings.SCREEN_WIDTH,
                GameSettings.SCREEN_HEIGHT);

        batch.draw(texture, 0 , y2,
                GameSettings.SCREEN_WIDTH,
                GameSettings.SCREEN_HEIGHT);
    }

    public void dispose() {
        texture.dispose();
    }

    public void move() {
        y1 += speed;
        y2 += speed;

        if (y1 <= -GameSettings.SCREEN_HEIGHT)
            y1 = GameSettings.SCREEN_HEIGHT;

        if (y2 <= -GameSettings.SCREEN_HEIGHT)
            y2 = GameSettings.SCREEN_HEIGHT;
    }


}
