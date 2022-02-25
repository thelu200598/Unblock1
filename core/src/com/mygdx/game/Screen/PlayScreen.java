package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Algorithm.Command;
import com.mygdx.game.Algorithm.Controller;
import com.mygdx.game.Object.Block;
import com.mygdx.game.Custom.LabelCustom;
import com.mygdx.game.Unblock;
import com.mygdx.game.load.Load_Assets;
import com.mygdx.game.load._AssetLoadAssetsL;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlayScreen extends ScreenAdapter {
    private static final int BLOCKSIZE =94;
    private static final int xPad =3;
    private static final int xPad2 =68;
    private static final int xPadHeader =36;
    private static final int xPadCoin =16;
    TextureAtlas blockAtlas;
    TextureAtlas playAtlas;
    Stage stage;
    float ww,wh;
    BitmapFont font;
    static LabelCustom lbCountMove;
    static LabelCustom lbcountLv;
    int countmove=0;
    Label.LabelStyle labelStyle ;

    public int getCountmove() {
        return countmove;
    }

    private Rectangle point_end;
    List<Block> blockList = new ArrayList<>();
    ArrayList<Integer> list_id = new ArrayList<>();
    Image boardUp;
    Image boardDown;
    Stack<Command> stack ;
    Block block;
    Load_Assets loader;

    private static PlayScreen playScreen = new PlayScreen();

    public static PlayScreen inst(){
        if (playScreen ==null)
            playScreen = new PlayScreen();
        return playScreen;
    }

    public PlayScreen(){
        this.stage = Unblock.getStage();
        loader = new _AssetLoadAssetsL();
        playAtlas = loader.getTextureAtlas("playAtlas");
        font = loader.getBitmapFont("rubik");
        blockAtlas = loader.getTextureAtlas("block");
        ww = stage.getWidth();
        wh = stage.getHeight();
        labelStyle= new Label.LabelStyle(font, Color.WHITE);
    }


    @Override
    public void show() {
        loadgame();
    }


    public Image getBoardUp() {
        return boardUp;
    }

    public List<Block> getBlockList() {
        return blockList;
    }
    Group groupBoard;

    public Group getGroupBoard() {
        return groupBoard;
    }

    public void drawClearboard(){

    }

    public void drawWinGame(){
        Group gNotify = new Group();
        Image notify = new Image(new Texture(Gdx.files.internal("notify_board.png")));
        Image clear = new Image(new Texture(Gdx.files.internal("clear.png")));
        Image opi = new Image(new Texture(Gdx.files.internal("Rectangle 3.png")));
        gNotify.setSize(notify.getWidth(),notify.getHeight());
        gNotify.setPosition(ww/2-notify.getWidth()/2,wh/2-notify.getHeight()/2);
        stage.addActor(opi);
        opi.getColor().set(0,0,0,0.5f);
        gNotify.addActor(notify);
//        gNotify.addActor(button);
//        button.setPosition(gNotify.getWidth()/2-button.getWidth()/2, button.getHeight()/4);
        gNotify.addActor(clear);
        clear.setPosition(gNotify.getWidth()/2-clear.getWidth()/2,
                gNotify.getHeight()/2-clear.getHeight()/1.5f);
        clear.setOrigin(Align.center);
        clear.setScale(2);
        clear.addAction(Actions.sequence(
                Actions.scaleTo(1,1,1.2f),
                Actions.removeActor(opi),
                Actions.removeActor(gNotify)
        ));
        stage.addActor(gNotify);
    }
    private void loadgame() {
        Image bg_lv1 = new Image(new TextureRegion(playAtlas.findRegion("level screen 1")));
        bg_lv1.setPosition(ww/2-bg_lv1.getWidth()/2,wh/2-bg_lv1.getHeight()/2);
        stage.addActor(bg_lv1);
        groupBoard  = new Group();
        groupBoard.setSize(600,574);
        groupBoard.setPosition(ww/2-groupBoard.getWidth()/2,wh/2-groupBoard.getHeight()/2);
        Image icon_out = new Image(new TextureRegion(blockAtlas.findRegion("out")));
        icon_out.setPosition(groupBoard.getWidth()/2+icon_out.getWidth()/0.3f,groupBoard.getHeight()/1.9f-icon_out.getHeight()/8);
//        icon_out.debug();
        Group gheader = new Group();
        Group gMenuHeader = new Group();
        Image header = new Image(new TextureRegion(playAtlas.findRegion("Shape 2 (merged)1 1")));
        gheader.setSize(header.getWidth(),header.getHeight());
        gheader.setPosition(ww/2-header.getWidth()/2,wh-header.getHeight());
        gheader.addActor(header);
        stage.addActor(gheader);

        Image rectLv = new Image(new TextureRegion(playAtlas.findRegion("level_rect")));
        Image recMove = new Image(new TextureRegion(playAtlas.findRegion("move_rect")));
        Image rectCoin = new Image(new TextureRegion(playAtlas.findRegion("coin_rect")));
        Image coin = new Image(new TextureRegion(playAtlas.findRegion("coin")));


        gMenuHeader.setSize(recMove.getX()+recMove.getWidth()+xPadHeader+rectLv.getWidth()+xPadCoin+rectCoin.getWidth(),
                                    recMove.getHeight());
        gMenuHeader.setPosition(ww/2-gMenuHeader.getWidth()/2,wh-gMenuHeader.getHeight()/0.7f);
//        gMenuHeader.debug();
        gMenuHeader.addActor(recMove);
        gMenuHeader.addActor(rectCoin);
        gMenuHeader.addActor(rectLv);
        gMenuHeader.addActor(coin);
        rectCoin.setPosition(gMenuHeader.getWidth()-rectCoin.getWidth()/1.1f,0);
        rectLv.setPosition(recMove.getWidth()+xPadHeader,0);
        coin.setScale(3);
        coin.setPosition(rectCoin.getX()-coin.getWidth()/2,rectCoin.getY()-coin.getHeight()/2);
        stage.addActor(gMenuHeader);


        Label lbMove=  new Label( "MOVE",labelStyle);
        gMenuHeader.addActor(lbMove);
        lbMove.setPosition(recMove.getWidth()/2-lbMove.getWidth()/2,
                            recMove.getHeight()-lbMove.getHeight()/0.8f);


        lbCountMove = new LabelCustom(countmove+ "",labelStyle);
        gMenuHeader.addActor(lbCountMove);
        lbCountMove.setPosition(recMove.getWidth()/2-lbCountMove.getWidth()/2,
                recMove.getHeight()/2-lbCountMove.getHeight()/2);

        Label level = new Label("LEVEL",labelStyle);
        gMenuHeader.addActor(level);
        level.setPosition(rectLv.getX()+(rectLv.getWidth()/2-level.getWidth()/2),
                rectLv.getY()+(rectLv.getHeight()-level.getHeight()/0.8f));
        lbcountLv = new LabelCustom(Controller.inst().showlevel+"",labelStyle);
        gMenuHeader.addActor(lbcountLv);
        lbcountLv.setPosition(rectLv.getX()+(rectLv.getWidth()/2-lbcountLv.getWidth()/2),
                rectLv.getY()+(rectLv.getHeight()/2-lbcountLv.getHeight()/2));
        Label lbPackname = new Label(Controller.inst().packname+"",labelStyle);
        gMenuHeader.addActor(lbPackname);
        lbPackname.setPosition(gMenuHeader.getWidth()/2-lbPackname.getWidth()/2,
                                     lbPackname.getHeight()/4);

        final Image block_h = new Image(new TextureRegion(blockAtlas.findRegion("block_h_small")));
        block_h.setPosition(xPad,block_h.getY()+xPad);

        block_h.setBounds(block_h.getX(),block_h.getY(),block_h.getWidth(),block_h.getHeight());

         boardUp = new Image(new TextureRegion(blockAtlas.findRegion("gameScreen 2")));
        boardDown = new Image(new TextureRegion(blockAtlas.findRegion("board")));

        Image block_v = new Image(new TextureRegion(blockAtlas.findRegion("block_v_small")));
        point_end = new Rectangle();
        point_end.set(icon_out.getX(),icon_out.getY(),icon_out.getWidth(),icon_out.getHeight());

        block_v.setPosition(block_v.getX()+BLOCKSIZE*3+xPad,0);

        groupBoard.addActor(boardDown);
        groupBoard.addActor(boardUp);
//        game_Screen.debug();
//        groupBoard.addActor(block_h);
//        groupBoard.addActor(block_v);
        stage.addActor(groupBoard);
        groupBoard.addActor(icon_out);
//        Controller.inst().genBlock();

        //gen all block
        Controller.inst().setStage(stage);
//        Controller.inst().newGame(Controller.inst().currlv);

        Group gTools = new Group();
        Image home = new Image(new TextureRegion(playAtlas.findRegion("Home")));
        Image refresh = new Image(new TextureRegion(playAtlas.findRegion("btnRefresh")));
        Image undo = new Image(new TextureRegion(playAtlas.findRegion("undo")));
        Image hint = new Image(new TextureRegion(playAtlas.findRegion("kisspng-computer-icons-icon-design-light-bulb-map-5b4967f3762e34 1")));
        gTools.setSize(home.getWidth()*4+xPad2*3,hint.getHeight());
        gTools.setPosition(ww/2-gTools.getWidth()/2,wh/2-gTools.getHeight()/0.25f);
        gTools.addActor(home);
        gTools.addActor(refresh);
        gTools.addActor(undo);
        gTools.addActor(hint);

        refresh.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Controller.inst().refreshBlock();
                super.clicked(event, x, y);
            }
        });
        hint.setPosition(gTools.getWidth()-hint.getWidth(),0);
        undo.setPosition(home.getX()+home.getWidth()+xPad2,0);
        refresh.setPosition(undo.getX()+undo.getWidth()+xPad2,0);
        stage.addActor(gTools);
        undo.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Controller.inst().undoBlock();
                super.clicked(event, x, y);
            }
        });
        home.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Controller.inst().clearGame();
                Unblock.setViewScreen(new HomeScreen());
                super.clicked(event, x, y);
            }
        });

        hint.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Controller.inst().writeLocalFile();
                super.clicked(event, x, y);
            }
        });



        Gdx.input.setInputProcessor(stage);
    }

    public void update(int move){
        lbCountMove.updateText(move+"");
    }
    public void updatelevel(int level){
        lbcountLv.updateText(level+"");
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

    public void update(float dt){

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
