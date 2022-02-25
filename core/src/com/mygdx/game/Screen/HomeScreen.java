package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Algorithm.Controller;
import com.mygdx.game.Unblock;
import com.mygdx.game.load.Load_Assets;
import com.mygdx.game.load._AssetLoadAssetsL;


public class HomeScreen extends ScreenAdapter {

    Load_Assets loader;
    Stage stage;
    TextureAtlas playAtlas;
    BitmapFont font;
    float ww;
    float wh;
    float dist=133,xPad=25;

    public HomeScreen(){
        this.stage = Unblock.getStage();
        loader = new _AssetLoadAssetsL();
        playAtlas = loader.getTextureAtlas("playAtlas");
        font = loader.getBitmapFont("rubik");
        ww = stage.getWidth();
        wh = stage.getHeight();
    }

    public void drawMenu(){
        Gdx.input.setInputProcessor(stage);
        Image bg_lv1 = new Image(new TextureRegion(playAtlas.findRegion("level screen 1")));
        bg_lv1.setPosition(ww/2-bg_lv1.getWidth()/2,wh/2-bg_lv1.getHeight()/2);
        stage.addActor(bg_lv1);
        Group groupHeader = new Group();
        Image header = new Image(new TextureRegion(playAtlas.findRegion("Shape 2 (merged)1 1")));
        groupHeader.setSize(header.getWidth(),header.getHeight());
        groupHeader.setPosition(ww/2-groupHeader.getWidth()/2,wh-groupHeader.getHeight());
        groupHeader.addActor(header);
        Image title = new Image(new TextureRegion(playAtlas.findRegion("title")));
        groupHeader.addActor(title);
        title.setPosition(groupHeader.getWidth()/2-title.getWidth()/2,groupHeader.getHeight()/1.7f- title.getHeight()/2);
        stage.addActor(groupHeader);
//        Image imgCoin = new Image(new TextureRegion(playAtlas.findRegion("play_coin (merged) 1")));
        Image imgCoin = new Image(new Texture(Gdx.files.internal("coin_rect.png")));
        groupHeader.addActor(imgCoin);
        imgCoin.setPosition(groupHeader.getWidth()/1.2f-imgCoin.getWidth()/1.5f,groupHeader.getHeight()/3.5f-imgCoin.getHeight()/2);
        Group menuGroup = new Group();
        menuGroup.setSize(504,684);
        menuGroup.setPosition(ww/2-menuGroup.getWidth()/2,wh/2-menuGroup.getHeight()/1.5f);
        Image btnPlay = new Image(new TextureRegion(playAtlas.findRegion("btnPlay")));
        Image btnResume = new Image(new TextureRegion(playAtlas.findRegion("btnResume")));
        Image btnSetting = new Image(new TextureRegion(playAtlas.findRegion("btnSetting")));
        Image btnMoreGames = new Image(new TextureRegion(playAtlas.findRegion("btnMoreGames")));
        Image btnShare = new Image(new TextureRegion(playAtlas.findRegion("share")));
        Image btnLike = new Image(new TextureRegion(playAtlas.findRegion("istockphoto-1175303918-170667a (merged) 1")));
        Image btnAd = new Image(new TextureRegion(playAtlas.findRegion("btnAdCoin")));
        menuGroup.addActor(btnResume);
        menuGroup.addActor(btnSetting);
        menuGroup.addActor(btnMoreGames);
        menuGroup.addActor(btnShare);
        menuGroup.addActor(btnLike);
        menuGroup.addActor(btnAd);
        btnPlay.setPosition(0,btnPlay.getHeight()*2+dist*3);
        btnResume.setPosition(0,btnResume.getHeight()*2+dist*2);
        btnSetting.setPosition(0,btnResume.getHeight()*2+dist);
        btnMoreGames.setPosition(0,btnResume.getHeight()*2);
        btnAd.setPosition(menuGroup.getWidth()-btnAd.getWidth(),0);
        btnLike.setPosition(menuGroup.getWidth()/2-btnLike.getWidth()/2,xPad);
        btnShare.setPosition(0,xPad);

        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(0.5f)));
                Unblock.setViewScreen(new SelectScreen());
                super.clicked(event, x, y);
            }
        });
        menuGroup.addActor(btnPlay);

        btnResume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Unblock.setViewScreen(PlayScreen.inst());
                Controller.inst().resumeBlock();

                super.clicked(event, x, y);
            }
        });

//        btnSetting.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//
//
//                File file = new File("E:\\Androidgame\\Unblock\\123.txt");
//                try {
//                    Scanner scanner = new Scanner(file);
//                    String str = scanner.nextLine();
//                    System.out.println(str);
//                    String[] temp = str.split(";");
//                    System.out.println(Arrays.toString(temp));
//                    for ( int i =0;i< temp.length;i++) {
//                        String[] oneblock = temp[i].split(",");
//                        final String type = oneblock[0];
//                        final int row = Integer.parseInt(oneblock[1]);
//                        final int col = Integer.parseInt(oneblock[2]);
//                        System.out.println(type.charAt(0) +" "+row+" "+col);}
////                    while (scanner.hasNextLine()){
//////                        filecontent=filecontent.concat(scanner.nextLine()+"\n");
////                        System.out.println(scanner.nextLine());
////                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
////                JsonReader json = new JsonReader();
////                JsonValue base = json.parse(Gdx.files.internal("1.json"));
//
//                //print json to console
////                System.out.println(base);//1 row
//
//                //get the component values
////                JsonValue component = base.get("components");
//
//                //print class value to console
////                System.out.println(component.getString("class"));
//
////                for (JsonValue component : base.get("components"))
////                {
////                    System.out.println(component.getString("class"));
//////                    System.out.println(component.get("asset").getString("class"));
//////                    System.out.println(component.get("asset").getString("relativePath"));
////                }
//
//            }
//        });
        btnMoreGames.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        stage.addActor(menuGroup);
//        stage.addActor(btnPlay);
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
        drawMenu();
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
