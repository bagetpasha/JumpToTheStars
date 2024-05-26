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

public class RestartScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;
    MovingBackgroundView restartBackground;
    ButtonView menuButton;
    ButtonView restartButton;
    ImageView restartBlackout;
    TextView gameOver;
    TextView scoreCounter;
    //TextView cloudsCounter;
    public int score;
    public int clouds;



    public RestartScreen(MyGdxGame myGdxGame) {

        this.myGdxGame = myGdxGame;

        menuButton = new ButtonView(136, 410, 84, 94,
                "textures/menuButton.png");
        restartButton = new ButtonView(277, 406, 116, 98,
                "textures/restartButton.png");
        restartBlackout = new ImageView(32, 284, 657, 555,
                "textures/restartBlackout.png");
        scoreCounter = new TextView(121, 690, myGdxGame.largeWhiteFont);
        //cloudsCounter = new TextView(121, 584, myGdxGame.largeWhiteFont);

        restartBackground = new MovingBackgroundView
                ("textures/restartBackground.png");

        gameOver = new TextView(140, 900,
                myGdxGame.overLargeWhiteFont);
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject
                    (new Vector3(Gdx.input.getX(),
                            Gdx.input.getY(), 0));

            if (menuButton.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.mainScreen);
            }

            if (restartButton.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.gameScreen);
            }
        }

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.BLUE);

        myGdxGame.batch.begin();

        restartBackground.draw(myGdxGame.batch);
        gameOver.draw(myGdxGame.batch, "GAME \n OVER");
        restartBlackout.draw(myGdxGame.batch);
        menuButton.draw(myGdxGame.batch);
        restartButton.draw(myGdxGame.batch);
        scoreCounter.draw(myGdxGame.batch, "Score:     " + score);
        //cloudsCounter.draw(myGdxGame.batch, "Clouds:     " + clouds);

        myGdxGame.batch.end();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
