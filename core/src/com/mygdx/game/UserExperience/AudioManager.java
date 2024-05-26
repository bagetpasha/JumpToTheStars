package com.mygdx.game.UserExperience;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.settingsAndElse.MemoryManager;

public class AudioManager {

    public Sound jumpSound;
    public Sound clickSound;
    public boolean isSoundOn;
    public boolean isMusicOn;


    public AudioManager() {
        clickSound = Gdx.audio.newSound(Gdx.files.internal
                ("Sounds/click.mp3"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal
                ("Sounds/jump.mp3"));

        updateSoundFlag();
        updateMusicFlag();
    }

    public void updateSoundFlag() {
        isSoundOn = MemoryManager.loadIsSoundOn();
    }
    public void updateMusicFlag() {
        isMusicOn = MemoryManager.loadIsMusicOn();
    }


}
