package com.mygdx.game.settingsAndElse;

import com.badlogic.gdx.utils.TimeUtils;

public class GameSession {

    GameScreenState state;

    public int score;
    public int cloudCounter;
    boolean died;
    long timeOfCloudsAppearance;
    long timeOfThunderCloudsAppearance;
    long timeOfFormlessCloudsAppearance;

    long timeOfPause;


    public GameScreenState getState() {
        return state;
    }

    public void beginSession() {

        score = 0;
        cloudCounter = 0;
        died = false;
        state = GameScreenState.PLAYING;
        timeOfCloudsAppearance = TimeUtils.millis();
        timeOfThunderCloudsAppearance = TimeUtils.millis();
        timeOfFormlessCloudsAppearance = TimeUtils.millis();
    }

    public void pauseSession() {
        state = GameScreenState.PAUSED;
    }

    public void resumeSession() {
        state = GameScreenState.PLAYING;
    }
    public void endSession() {
        state = GameScreenState.ENDED;
    }

    public boolean hasToSpawnClouds() {

        if (TimeUtils.millis() - timeOfCloudsAppearance
                >= GameSettings.CLOUDS_APPEARANCE_PERIOD) {
            timeOfCloudsAppearance = TimeUtils.millis();
            return true;
        }
        return false;
    }

    public boolean hasToSpawnThunderClouds() {

        if (TimeUtils.millis() - timeOfThunderCloudsAppearance
                >= GameSettings.THUNDER_CLOUDS_APPEARANCE_PERIOD) {
            timeOfThunderCloudsAppearance = TimeUtils.millis();
            return true;
        }
        return false;
    }

    public boolean hasToSpawnFormlessClouds() {

        if (TimeUtils.millis() - timeOfFormlessCloudsAppearance
                >= GameSettings.FORMLESS_CLOUDS_APPEARANCE_PERIOD) {
            timeOfFormlessCloudsAppearance = TimeUtils.millis();
            return true;
        }
        return false;
    }

    public void pauseTimers () {
        timeOfPause = TimeUtils.millis();
    }

    public void resumeTimers () {
        timeOfFormlessCloudsAppearance += TimeUtils.millis() - timeOfPause;
        timeOfThunderCloudsAppearance += TimeUtils.millis() - timeOfPause;
        timeOfCloudsAppearance += TimeUtils.millis() - timeOfPause;
    }






}
