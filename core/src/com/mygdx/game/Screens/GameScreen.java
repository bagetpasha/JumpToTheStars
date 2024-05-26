package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Objects.FormlessCloudObject;
import com.mygdx.game.Objects.ThunderCloudObject;
import com.mygdx.game.UserExperience.ButtonView;
import com.mygdx.game.UserExperience.ImageView;
import com.mygdx.game.UserExperience.MovingBackgroundView;
import com.mygdx.game.UserExperience.TextView;
import com.mygdx.game.settingsAndElse.GameSession;
import com.mygdx.game.settingsAndElse.GameSettings;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Objects.CharacterJoe;
import com.mygdx.game.Objects.CloudObject;
import com.mygdx.game.settingsAndElse.GameScreenState;
import com.mygdx.game.settingsAndElse.MemoryManager;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;
    CharacterJoe joe;
    ArrayList<CloudObject> cloudArray;
    GameSession session;
    MovingBackgroundView backgroundView;
    ImageView gameBlackout;
    ImageView settingsGameBlackout;
    ImageView totalBlackout;
    ButtonView pauseButton;
    ButtonView resumeButton;
    ButtonView homeButton;
    ButtonView buttonMusic;
    ButtonView buttonSound;
    ButtonView moveButtonLeft;
    ButtonView moveButtonRight;
    TextView scoreCounter;
    TextView pause;


    public GameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        joe = new CharacterJoe(GameSettings.SCREEN_WIDTH / 2
                - GameSettings.JOE_WIDTH / 2, 440);
        cloudArray = new ArrayList<>();
        session = new GameSession();

        backgroundView = new MovingBackgroundView
                ("textures/game_background.png");
        settingsGameBlackout = new ImageView(31, 627, 657, 470,
                "textures/settingsBlackout_Game.png");
        buttonMusic = new ButtonView(102, 929,
                myGdxGame.largeWhiteFont, "Music:     "
                + translateStateToText(myGdxGame.audioManager.isMusicOn));
        buttonSound = new ButtonView(102, 817,
                myGdxGame.largeWhiteFont, "Sounds:     "
                + translateStateToText(myGdxGame.audioManager.isSoundOn));
        gameBlackout = new ImageView(15, 1163, 690, 100,
                "textures/game_blackout.png");
        totalBlackout = new ImageView(0, 1280, 720, 1280,
                "textures/totalBlackout.png");
        pauseButton = new ButtonView(623, 1185, 48, 58,
                "textures/pauseButton.png");
        resumeButton = new ButtonView(101, 694, 127, 84,
                "textures/buttonReturnToGame.png");
        homeButton = new ButtonView(299, 685, 84, 94,
                "textures/menuButton.png");
        moveButtonLeft = new ButtonView(74, 71, 250, 205,
                "textures/moveButton_left.png");
        moveButtonRight = new ButtonView(399, 66, 250, 205,
                "textures/moveButton_right.png");
        scoreCounter = new TextView(325, 1195, myGdxGame.largeWhiteFont);
        pause = new TextView(250, 1007, myGdxGame.commonWhiteFont);
    }

    @Override
    public void render(float delta) {

        handleInput();
        //System.out.println(session.getState());
        if (session.getState() == GameScreenState.PLAYING) {

            joe.move();

            if (joe.isNeedToFall()) {

                session.pauseTimers();

            }

            // height delta
            deltaHeight(joe);


            for (int i = 0; i < cloudArray.size(); i++) {

                if (cloudArray.get(i).isHit(joe)) {

                    session.resumeTimers();

                    if (cloudArray.get(i) instanceof ThunderCloudObject) {
                        endGame();
                    }
                    session.score += 5;

                    if (myGdxGame.audioManager.isSoundOn) {
                        myGdxGame.audioManager.jumpSound.play();

                    }
                }

                cloudArray.get(i).y -= deltaHeight(joe);

                // clArray.get(i).y -= delta
            }

            //System.out.println(cloudArray.size());

            updateClouds();


            if (joe.maxHeightY == joe.heightY) {

                if (session.hasToSpawnThunderClouds()) {

                    CloudObject thunderCloud = new ThunderCloudObject();
                    cloudArray.add(thunderCloud);
                }

                if (session.hasToSpawnFormlessClouds()) {

                    CloudObject formlessCloud = new FormlessCloudObject();
                    cloudArray.add(formlessCloud);
                }

                if (session.hasToSpawnClouds()) {

                    CloudObject clouds = new CloudObject();
                    cloudArray.add(clouds);
                }
            }

            if (isGameOver()) {
               endGame();
            }
        }

        draw();

    }

    public void endGame() {

        session.endSession();

        myGdxGame.restartScreen.score = session.score;
        myGdxGame.restartScreen.clouds = joe.heightY;

        myGdxGame.setScreen(myGdxGame.restartScreen);

    }

    public int deltaHeight (CharacterJoe joe) {

        return joe.heightY - joe.pastHeightY;
    }

    public void handleInput() {

        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject
                    (new Vector3(Gdx.input.getX(),
                            Gdx.input.getY(), 0));

            if (pauseButton.isHit(touch.x, touch.y)) {
                session.pauseSession();
                myGdxGame.audioManager.clickSound.play();

                return;
            }
            if (resumeButton.isHit(touch.x, touch.y)) {
                session.resumeSession();
                myGdxGame.audioManager.clickSound.play();

            }
            if (homeButton.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.mainScreen);
                myGdxGame.audioManager.clickSound.play();

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

        if (session.getState() == GameScreenState.PLAYING &&
                Gdx.input.isTouched()) {
            Vector3 touch = myGdxGame.camera.unproject
                    (new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (moveButtonLeft.isHit(touch.x, touch.y)) {
                joe.x -= 10;
                joe.toLeft = true;
            }
            if (moveButtonRight.isHit(touch.x, touch.y)) {
                joe.x += 10;
                joe.toLeft = false;
            }
        }

    }

    public void updateClouds() {
        for (int i = 0; i < cloudArray.size(); i++) {
            if (!cloudArray.get(i).isInFrame()) {
                cloudArray.remove(i);
                i--;
            }
        }
    }

    public boolean isGameOver() {

        if (joe.jump) {
            return false;
        }

        for (int i = 0; i < cloudArray.size(); i++) {
            if (cloudArray.get(i).y < joe.y) {
                return false;
            }
        }
        return true;
    }

    private void restartGame() {
        joe = new CharacterJoe(GameSettings.SCREEN_WIDTH / 2
                - GameSettings.JOE_WIDTH / 2, 440);
        cloudArray.clear();
    }



    @Override
    public void show() {

        int cloudsDistance = GameSettings.CLOUDS_VELOCITY
                * GameSettings.CLOUDS_APPEARANCE_PERIOD / 1000 * 50;

        session.beginSession();

        restartGame();

        for (int i = 0; i < GameSettings.SCREEN_HEIGHT / cloudsDistance; i++) {

            CloudObject cloud = new CloudObject();
            cloud.y = cloudsDistance * i;
            cloudArray.add(cloud);
        }

    }

    public void draw() {

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix
                (myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLACK);

        myGdxGame.batch.begin();
        backgroundView.draw(myGdxGame.batch);
        for (CloudObject cloud : cloudArray)
            cloud.draw(myGdxGame.batch);
        joe.draw(myGdxGame.batch);
        gameBlackout.draw(myGdxGame.batch);
        scoreCounter.draw(myGdxGame.batch, "Score: " + session.score);

        if (session.getState() == GameScreenState.PAUSED) {
            totalBlackout.draw(myGdxGame.batch);
            settingsGameBlackout.draw(myGdxGame.batch);
            buttonMusic.draw(myGdxGame.batch);
            buttonSound.draw(myGdxGame.batch);
            resumeButton.draw(myGdxGame.batch);
            homeButton.draw(myGdxGame.batch);
            pause.draw(myGdxGame.batch, "Pause");

        }

        pauseButton.draw(myGdxGame.batch);
        moveButtonRight.draw(myGdxGame.batch);
        moveButtonLeft.draw(myGdxGame.batch);

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
