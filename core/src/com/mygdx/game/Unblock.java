package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screen.HomeScreen;
import com.mygdx.game.load._AssetLoadAssetsL;

public class Unblock extends Game {
	private static final float _WH = 1280;
	Stage stage;
	Viewport viewport;
	OrthographicCamera camera;
	_AssetLoadAssetsL loader;
	boolean finishingLoading = false;

	public Unblock(){
		loader = new _AssetLoadAssetsL();
	}

	@Override
	public void create () {
		int sw = Gdx.graphics.getWidth();
		int sh = Gdx.graphics.getHeight();
		float wh = _WH;
		float ww = (wh * sw) /sh;
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		viewport = new FitViewport(ww,wh,camera);
		stage = new Stage(viewport);
		setScreen(new HomeScreen());
	}
	public static void LoadItem(_AssetLoadAssetsL loader){
		loader = new _AssetLoadAssetsL();


	}

	public static Stage getStage(){
		return ((Unblock)Gdx.app.getApplicationListener()).stage;
	}

	public static void setViewScreen(Screen screen){
		((Unblock)Gdx.app.getApplicationListener()).setScreen(screen);
	}
	@Override
	public void render () {
		ScreenUtils.clear(Color.BLUE);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}


}
