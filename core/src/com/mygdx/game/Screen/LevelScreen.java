package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Algorithm.Controller;
import com.mygdx.game.Unblock;
import com.mygdx.game.load.Load_Assets;
import com.mygdx.game.load._AssetLoadAssetsL;

public class LevelScreen extends ScreenAdapter {
    Stage stage;
    BitmapFont font;
    TextureAtlas playAtlas;
    TextureAtlas blockAtlas;
    TextureAtlas blockDtAtlas;
    float ww,wh;
    Label.LabelStyle labelStyle ;
    Load_Assets loader;
    public int col= Controller.inst().col;
    private static final int BLOCKSIZE =92;


    public LevelScreen(){
        this.stage = Unblock.getStage();
        loader = new _AssetLoadAssetsL();
        playAtlas = loader.getTextureAtlas("playAtlas");
        font = loader.getBitmapFont("rubik");
        blockAtlas = loader.getTextureAtlas("block");
        blockDtAtlas = loader.getTextureAtlas("blockDT");
        ww = stage.getWidth();
        wh=stage.getHeight();
        labelStyle= new Label.LabelStyle(font, Color.WHITE);
    }
    @Override
    public void render(float delta) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }

    @Override
    public void show() {
        draw();
    }

    private void draw() {
        Image bg = new Image(new Texture(Gdx.files.internal("bg.png")));
        bg.setPosition(ww/2-bg.getWidth()/2,wh/2-bg.getHeight()/2);
        stage.addActor(bg);
        Group gboard = new Group();
        gboard.setSize((BLOCKSIZE*1.75f)*4,(BLOCKSIZE*1.81f)*5);
//        gboard.debug();
        stage.addActor(gboard);
        gboard.setPosition(ww/2-gboard.getWidth()/2,wh/2- gboard.getHeight()/2);

//        int row = Controller.inst().getlevelPack(Controller.inst().choosePack(2));
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    Image iconLevel = new Image(new Texture(Gdx.files.internal("icon_p1.png")));
//                    iconLevel.setPosition(j * BLOCKSIZE * 2, i * BLOCKSIZE * 2);
//                    gboard.addActor(iconLevel);
//                    Label lblevel = new Label("0",labelStyle);
//                    lblevel.setPosition(j * BLOCKSIZE * 2+35, i * BLOCKSIZE * 2+30);
//                    gboard.addActor(lblevel);
//                    }
//            }


//        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
//        Table container = new Table();
//        Table table = new Table();
//        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
//        ScrollPane pane = new ScrollPane(table,paneStyle);
//        container.add(pane).width(700).height(900);
//        container.setBounds(ww/2-container.getWidth()/2,
//                wh/2-container.getHeight()/2,
//                710,910);
//        container.setPosition(ww/2- container.getWidth()/2,wh/2-container.getHeight()/2);
//
//        container.debug();
//        stage.addActor(container);
//
//        for (Map.Entry<Integer, BlockDT> entry : BlockDT.blocks.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue().region);
//            Image img_pack ;
//            Image icon_block= new Image(new TextureRegion(blockDtAtlas.findRegion(entry.getValue().region)));
//            Label lId = new Label(entry.getValue().id+"",labelStyle);
//            Group groupScroll = new Group();
//            Group groupCoin = new Group();
//            img_pack = new Image(new TextureRegion(playAtlas.findRegion("move_rect")));
//            groupCoin.addActor(lId);
//            lId.setPosition(groupCoin.getWidth()/2-lId.getWidth()/3f,
//                    groupCoin.getHeight()/2-lId.getHeight()/2);
//            groupScroll.addActor(img_pack);
//            groupScroll.addActor(icon_block);
//            groupCoin.setPosition(icon_block.getWidth()-groupCoin.getWidth()/2,icon_block.getHeight()/2-groupCoin.getHeight()/2);
//            icon_block.setPosition(img_pack.getX()+(img_pack.getWidth()/2-icon_block.getWidth()/2),
//                                        img_pack.getY()+(img_pack.getHeight()/2-icon_block.getHeight()/2));
//            table.add(groupScroll).width(700).height(900);
//            if(entry.getKey()%2==1)
//                table.row();
//        }
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
