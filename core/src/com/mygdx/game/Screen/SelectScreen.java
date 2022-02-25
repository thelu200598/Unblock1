package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Algorithm.Controller;
import com.mygdx.game.Unblock;
import com.mygdx.game.load._AssetLoadAssetsL;

public class SelectScreen extends ScreenAdapter {
    AssetManager manager;
    _AssetLoadAssetsL loader;
    Stage stage;
    TextureAtlas playAtlas;
    TextureAtlas play2Atlas;
    BitmapFont font;
    float ww;
    float wh;

    public SelectScreen(){
        this.stage = Unblock.getStage();
        loader = new _AssetLoadAssetsL();
        playAtlas = loader.getTextureAtlas("playAtlas");
        font = loader.getBitmapFont("rubik");
        play2Atlas = loader.getTextureAtlas("play2");
        ww = stage.getWidth();
        wh = stage.getHeight();
    }

    public void drawScreen(){
        Gdx.input.setInputProcessor(stage);
        Image bg_lv1 = new Image(new TextureRegion(playAtlas.findRegion("level screen 1")));
        bg_lv1.setPosition(ww/2-bg_lv1.getWidth()/2,wh/2-bg_lv1.getHeight()/2);
        stage.addActor(bg_lv1);
        Group groupHeader = new Group();
        Image header = new Image(new TextureRegion(playAtlas.findRegion("Shape 2 (merged)1 1")));
        groupHeader.setSize(header.getWidth(),header.getHeight());
        groupHeader.setPosition(ww/2-groupHeader.getWidth()/2,wh-groupHeader.getHeight());
        groupHeader.addActor(header);
        Image title = new Image(new TextureRegion(playAtlas.findRegion("Select Mode 1")));
        groupHeader.addActor(title);
        title.setPosition(groupHeader.getWidth()/2-title.getWidth()/2,groupHeader.getHeight()/1.7f- title.getHeight()/2);
        stage.addActor(groupHeader);
        Group gSelect = new Group();
        gSelect.setSize(665,443);
        gSelect.setPosition(ww/2-gSelect.getWidth()/2,wh/2-gSelect.getHeight()/2);
        Image challenge = new Image(new TextureRegion(play2Atlas.findRegion("lv_challenge")));
        Image easy = new Image(new TextureRegion     (play2Atlas.findRegion("lv_easy")));
        Image relax = new Image(new TextureRegion    (play2Atlas.findRegion("lv_relax")));
        Image begin = new Image(new TextureRegion    (play2Atlas.findRegion("lv_beginner")));
        gSelect.addActor(challenge);
        gSelect.addActor(easy);
        gSelect.addActor(begin);
        gSelect.addActor(relax);
        challenge.setPosition(0,gSelect.getHeight()-challenge.getHeight());
        easy.setPosition(gSelect.getWidth()- easy.getWidth(),0);
        relax.setPosition(gSelect.getWidth()-relax.getWidth(),gSelect.getHeight()-relax.getHeight());
        begin.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String str = Controller.inst().choosePack(1);
                Controller.inst().packname=str;
                Unblock.setViewScreen(PlayScreen.inst());
                Controller.inst().newGame(str,Controller.inst().currlv);
                super.clicked(event, x, y);
            }
        });
        relax.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String str = Controller.inst().choosePack(3);
                Controller.inst().packname=str;
                Unblock.setViewScreen(PlayScreen.inst());
                Controller.inst().newGame(str,Controller.inst().currlv);
                super.clicked(event, x, y);
            }
        });
        challenge.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String str = Controller.inst().choosePack(4);
                Controller.inst().packname=str;
                Unblock.setViewScreen(PlayScreen.inst());
                Controller.inst().newGame(str,Controller.inst().currlv);
                super.clicked(event, x, y);
            }
        });
        easy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String str = Controller.inst().choosePack(2);
                Controller.inst().packname=str;
                Unblock.setViewScreen(PlayScreen.inst());
                Controller.inst().newGame(str,Controller.inst().currlv);
                super.clicked(event, x, y);
            }
        });
        stage.addActor(gSelect);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }

    @Override
    public void show() {
        drawScreen();
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
