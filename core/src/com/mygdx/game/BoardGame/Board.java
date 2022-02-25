package com.mygdx.game.BoardGame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Algorithm.Controller;
import com.mygdx.game.Object.Block;
import com.mygdx.game.Screen.PlayScreen;
import com.mygdx.game.Unblock;

import java.util.List;

public class Board extends Group {
    private static final int BLOCKSIZE =94;
    private static Board board ;
    TextureAtlas blockAtlas;
    public int currlv= Controller.inst().currlv;
    List<Block> blockList = PlayScreen.inst().getBlockList();
    Stage stage;

//    public static Board inst(){
//        if (board ==null)
//            board = new Board();
//        return board;
//    }
    public Board(int level){
        this.stage = Unblock.getStage();
        this.currlv=level-1;

    }




}
