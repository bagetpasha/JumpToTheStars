package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.UserExperience.AudioManager;
import com.mygdx.game.UserExperience.ButtonView;
import com.mygdx.game.UserExperience.ImageView;
import com.mygdx.game.UserExperience.MovingBackgroundView;
import com.mygdx.game.UserExperience.TextView;

public class MainScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;
    MovingBackgroundView backgroundView;
    TextView titleView;
    ButtonView startButton;
    ButtonView settingsButton;
    ButtonView exitButton;
    ImageView mainJoe;

    public MainScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        backgroundView = new MovingBackgroundView
                ("textures/mainScreen.png");
        titleView = new TextView(160, 960,
                myGdxGame.largeYellowFont);
        startButton = new ButtonView(123, 789,
                517, 137,
                "textures/button.png",
                myGdxGame.commonWhiteFont, "Start");
        settingsButton = new ButtonView(123, 610,
                517, 137,
                "textures/button.png",
                myGdxGame.commonWhiteFont, "Settings");
        exitButton = new ButtonView(123, 431,
                517, 137,
                "textures/button.png",
                myGdxGame.commonWhiteFont, "Exit");
        mainJoe = new ImageView(415, 115, 344, 425,
                "textures/mainScreen_Joe.png");
    }

    @Override
    public void show() {
        System.out.println("mainScreen");
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject
                    (new Vector3(Gdx.input.getX(),
                            Gdx.input.getY(), 0));

            if (exitButton.isHit(touch.x, touch.y)) {
                Gdx.app.exit();
                if (myGdxGame.audioManager.isSoundOn) {
                    myGdxGame.audioManager.clickSound.play();

                }
            }

            if (settingsButton.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.settingsScreen);
                if (myGdxGame.audioManager.isSoundOn) {
                    myGdxGame.audioManager.clickSound.play();

                }
            }

            if (startButton.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.gameScreen);
                if (myGdxGame.audioManager.isSoundOn) {
                    myGdxGame.audioManager.clickSound.play();

                }
            }
        }

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLUE);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        titleView.draw(myGdxGame.batch, " Jump To\nThe Stars");
        settingsButton.draw(myGdxGame.batch);
        startButton.draw(myGdxGame.batch);
        exitButton.draw(myGdxGame.batch);
        mainJoe.draw(myGdxGame.batch);


        myGdxGame.batch.end();
    }
}
