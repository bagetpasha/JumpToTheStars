package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainScreen;
import com.mygdx.game.Screens.RestartScreen;
import com.mygdx.game.Screens.SettingsScreen;
import com.mygdx.game.UserExperience.AudioManager;
import com.mygdx.game.UserExperience.FontBuilder;
import com.mygdx.game.settingsAndElse.GameSettings;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public GameScreen gameScreen;
	public MainScreen mainScreen;
	public RestartScreen restartScreen;
	public SettingsScreen settingsScreen;
	public BitmapFont largeWhiteFont;
	public BitmapFont largeYellowFont;
	public BitmapFont overLargeWhiteFont;
	public BitmapFont commonWhiteFont;
	public BitmapFont commonBlackFont;
	public BitmapFont commonYellowFont;
	public AudioManager audioManager;


	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameSettings.SCREEN_WIDTH,
				GameSettings.SCREEN_HEIGHT);

		audioManager = new AudioManager();

		largeWhiteFont = FontBuilder.generate
				(48, Color.WHITE,
						"fonts/LilitaOne-Regular.ttf");
		overLargeWhiteFont = FontBuilder.generate
				(160, Color.WHITE,
						"fonts/LilitaOne-Regular.ttf");
		largeYellowFont = FontBuilder.generate
				(108, Color.YELLOW,
						"fonts/LilitaOne-Regular.ttf");
		commonWhiteFont = FontBuilder.generate
				(88, Color.WHITE,
						"fonts/LilitaOne-Regular.ttf");
		commonBlackFont = FontBuilder.generate
				(88, Color.BLACK,
						"fonts/LilitaOne-Regular.ttf");
		commonYellowFont = FontBuilder.generate
				(88, Color.YELLOW,
						"fonts/LilitaOne-Regular.ttf");

		gameScreen = new GameScreen(this);
		mainScreen = new MainScreen(this);
		settingsScreen = new SettingsScreen(this);
		restartScreen = new RestartScreen(this);

		setScreen(mainScreen);

	}
}
