package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.UserExperience.ButtonView;
import com.mygdx.game.UserExperience.ImageView;
import com.mygdx.game.UserExperience.MovingBackgroundView;
import com.mygdx.game.UserExperience.TextView;
import com.mygdx.game.settingsAndElse.MemoryManager;

public class SettingsScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;

    MovingBackgroundView backgroundView;
    ImageView blackout;
    ButtonView buttonSound;
    ButtonView buttonMusic;
    ButtonView buttonReturn;
    ImageView cloud1;
    ImageView cloud2;
    ImageView cloud3;
    ImageView cloud4;
    ImageView cloud5;
    ImageView joe;


    public SettingsScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        backgroundView = new MovingBackgroundView
                ("textures/settings_background.png");
        blackout = new ImageView(31, 475,
                "textures/settings_blackout.png");

        buttonMusic = new ButtonView(103, 1004,
                myGdxGame.largeWhiteFont, "Music:     "
                + translateStateToText(myGdxGame.audioManager.isMusicOn));
        buttonSound = new ButtonView(103, 845,
                myGdxGame.largeWhiteFont, "Sounds:     "
                + translateStateToText(myGdxGame.audioManager.isSoundOn));
        buttonReturn = new ButtonView(130, 587, 114, 127,
                "textures/return_button.png");

        joe = new ImageView(187, 68, 407, 496,
                "textures/joe2.png");

        cloud1 = new ImageView(-72, 1092, 265, 125,
                "textures/simple_Cloud.png");
        cloud2 = new ImageView(381, 985, 360, 170,
                "textures/simple_Cloud.png");
        cloud3 = new ImageView(587, 707, 174, 96,
                "textures/simple_Cloud.png");
        cloud4 = new ImageView(-81, 440, 253, 125,
                "textures/simple_Cloud.png");
        cloud5 = new ImageView(390, 406, 390, 228,
                "textures/simple_Cloud.png");


    }

    @Override
    public void show() {
        System.out.println("settingsScreen");

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject
                    (new Vector3(Gdx.input.getX(),
                            Gdx.input.getY(), 0));

            if (buttonReturn.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.mainScreen);
            }
            if (buttonSound.isHit(touch.x, touch.y)) {
                MemoryManager.saveSoundSettings(!MemoryManager.loadIsSoundOn());
                buttonSound.setTextView
                        ("Sounds:     " + translateStateToText
                                (MemoryManager.loadIsSoundOn()));
                myGdxGame.audioManager.updateSoundFlag();
                System.out.println(MemoryManager.loadIsSoundOn());
            }

            if (buttonMusic.isHit(touch.x, touch.y)) {
                MemoryManager.saveMusicSettings(!MemoryManager.loadIsMusicOn());
                buttonMusic.setTextView
                        ("Music:     " + translateStateToText
                                (MemoryManager.loadIsMusicOn()));
                myGdxGame.audioManager.updateMusicFlag();
                System.out.println(MemoryManager.loadIsMusicOn());
            }


        }

        ScreenUtils.clear(Color.CLEAR);
        myGdxGame.batch.setProjectionMatrix(
                myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLACK);

        draw();
    }

    public void draw() {

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        cloud2.draw(myGdxGame.batch);
        blackout.draw(myGdxGame.batch);
        cloud1.draw(myGdxGame.batch);
        cloud3.draw(myGdxGame.batch);
        cloud4.draw(myGdxGame.batch);
        cloud5.draw(myGdxGame.batch);
        joe.draw(myGdxGame.batch);
        buttonReturn.draw(myGdxGame.batch);
        buttonSound.draw(myGdxGame.batch);
        buttonMusic.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    private String translateStateToText(boolean state) {

        if (state) {
            return "ON";
        } else {
            return "OFF";
        }
    }

}