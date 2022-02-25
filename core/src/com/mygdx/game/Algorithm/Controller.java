package com.mygdx.game.Algorithm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.BoardGame.Board;
import com.mygdx.game.Custom.SaveLevel;
import com.mygdx.game.Custom.SaveMove;
import com.mygdx.game.Object.Block;
import com.mygdx.game.Screen.PlayScreen;
import com.mygdx.game.Unblock;
import com.mygdx.game.dto.PackDT;
import com.mygdx.game.load.Load_Assets;
import com.mygdx.game.load._AssetLoadAssetsL;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Controller {
    private int countmove = PlayScreen.inst().getCountmove();
    private static final int BLOCKSIZE = 94;
    private static Controller controller;
    List<Block> blockList = PlayScreen.inst().getBlockList();
    private final SmoothCollide smoothCollide = new SmoothCollide();
    public boolean flag = false;
    public String packname = "";
    Block block;
    TextureAtlas atlas;
    private Stage stage;
    Board board;
    TextureAtlas blockAtlas;
    AssetManager manager;
    public int currlv = 0;
    public int showlevel = currlv + 1;
    Stack<Command> stack = new Stack<>();
    public int packLevel;
    public int col=4,row;
    Load_Assets loader;

    public Stack<Command> getStack() {
        return stack;
    }

    public static Controller inst() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }

    public Controller() {
        this.stage = Unblock.getStage();
        loader = new _AssetLoadAssetsL();
        blockAtlas= loader.getTextureAtlas("block");
    }

    public Vector2 getPos(float px, float py, Block blockTile) {
        return smoothCollide.clamp(px, py, blockTile);
    }

    public Vector2 getPosY(float px, float py, Block blockTile) {
        return smoothCollide.clampY(px, py, blockTile);
    }


    public boolean checkWinGame() {
        for (int i = 0; i < blockList.size(); i++) {
            if (String.valueOf(blockList.get(i).getType().charAt(0)).equals("c")) {
                int x = (int) blockList.get(i).getX() / 94;
                int y = (int) blockList.get(i).getY() / 94;
//                Block blockTile = lstBlockTile.get(i);
                if (x == 4 && y == 3) {
                    currlv++;
                    newGame(packname, currlv);
                    nextlevel();
                    return true;
                }
            }
        }

        return false;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void moveWin() {
        for (int i = 0; i < blockList.size(); i++) {
            if (String.valueOf(blockList.get(i).getType().charAt(0)).equals("c")) {
                Block blockTile = blockList.get(i);
                blockTile.addAction(Actions.moveTo(PlayScreen.inst().getBoardUp().getWidth() + 20, blockTile.getY(), 1f));
            }
        }
    }

    public void refreshBlock() {
        for (int i = 0; i < blockList.size(); i++) {
            blockList.get(i).RefreshPosBlock();
            resetCountMove();
        }

    }

    public void newGame(String packname, int lv) {
        clearGame();
        resetCountMove();
        genAllblock(packname, lv);
    }

    public void clearGame() {
        if (!blockList.isEmpty()) {
            for (int i = 0; i < blockList.size(); i++) {
                blockList.get(i).remove();
                blockList.get(i).clear();
            }
            blockList.clear();
        }
    }

    public String choosePack(int id) {
        for (Map.Entry<Integer, PackDT> entry : PackDT.packs.entrySet()) {
            if (entry.getKey() == id) {
                System.out.println(entry.getValue().packName);
                return entry.getValue().packName;
            }
        }
        return null;
    }

    public void undoBlock() {
        block.undo();
        countMoveUndo();
    }

    public void countMoveUndo(){
        countmove--;
        if(countmove>=0) {
            SaveMove.inst().setCountmove(countmove);
            PlayScreen.inst().update(countmove);
        }
    }


    public void genAllblock(String packname, int lv) {
        String str = LoadTextFile(packname, lv);
        if (str != null) {
            String[] temp = str.split(";");
            for (int i = 0; i < temp.length; i++) {
                String[] oneblock = temp[i].split(",");
                final String type = oneblock[0];
                final int row = Integer.parseInt(oneblock[1]);
                final int col = Integer.parseInt(oneblock[2]);
//            System.out.println(type.charAt(0) +" "+row+" "+col);
                block = new Block(new Vector2(row * BLOCKSIZE + 3, col * BLOCKSIZE + 3), blockAtlas, type, i);
                block.setBasePos(new Vector2(block.getX(), block.getY()));
                block.setPosBlock(new Vector2(block.getX(), block.getY()));
                blockList.add(block);
                block.addlistener();
                PlayScreen.inst().getGroupBoard().addActor(block);
                flag = true;
            }
        } else flag = false;

    }

    public void resetCountMove() {
        countmove = 0;
        SaveMove.inst().setCountmove(countmove);
        PlayScreen.inst().update(SaveMove.inst().getCountmove());
    }

    public void countMove() {
        countmove++;
        SaveMove.inst().setCountmove(countmove);
        PlayScreen.inst().update(SaveMove.inst().getCountmove());
    }

    public void nextlevel() {
        showlevel++;
        SaveLevel.inst().setLevel(showlevel);
        PlayScreen.inst().updatelevel(SaveLevel.inst().getLevel());
    }

    public int getlevelPack(String packname){

        String[] mapGenBlock;
        FileHandle handle = Gdx.files.internal("level/" + packname + ".txt");
        String text = handle.readString();
        mapGenBlock = text.split("\\r?\\n");
        int k =mapGenBlock.length;
        System.out.println("so level trong pack: "+k);
        if(k%col!=0) {
            return row = (k / col) + 1;
        }
        else
            return row = k/col;

    }

    public String[] getMapgenblock (String packname){
        String[] mapGenBlock;
        FileHandle handle = Gdx.files.internal("level/" + packname + ".txt");
        String text = handle.readString();
        mapGenBlock = text.split("\\r?\\n");
        return  mapGenBlock;
    }



    public String LoadTextFile(String packname, int level) {
//        String[] mapGenBlock;
//        FileHandle handle = Gdx.files.internal("level/" + packname + ".txt");
//        String text = handle.readString();
//        mapGenBlock = text.split("\\r?\\n");

//        for(String dt : mapGenBlock) {
//            String[] data = dt.split(",");
//            System.out.println("map block"+mapGenBlock[0]);
//        }
        int k = level + 1;
        if (getMapgenblock(packname).length >= k)
            return getMapgenblock(packname)[level];
        return null;
    }


    public void writeLocalFile() {
        FileHandle handle = Gdx.files.local("resume/resume.txt");
        String str ;
        if (handle.length() != 0) {
            deleteFileLocal();
            for (int i = 0; i < blockList.size(); i++) {
                int xpos = (int) (Math.round((blockList.get(i).getX() - 3) / BLOCKSIZE));
                int ypos = (int) (Math.round((blockList.get(i).getY() - 3) / BLOCKSIZE));
                str = blockList.get(i).getType() + "," + xpos + "," + ypos + ";";

                handle.writeString(str, true);
            }
        } else {
            for (int i = 0; i < blockList.size(); i++) {
                int xpos = (int) (Math.round((blockList.get(i).getX() - 3) / BLOCKSIZE));
                int ypos = (int) (Math.round((blockList.get(i).getY() - 3) / BLOCKSIZE));
                str = blockList.get(i).getType() + "," + xpos + "," + ypos + ";";
                handle.writeString(str, true);
            }
        }
        System.out.println("da luu chuoi map block: "+handle.readString());
    }

    public void deleteFileLocal() {
        FileHandle handle = Gdx.files.local("resume/resume.txt");
        handle.delete();
    }

    public void resumeBlock() {
        FileHandle handle = Gdx.files.local("resume/resume.txt");
        String str = handle.readString();
        System.out.println("file doc len la: ");
        String[] temp = str.split(";");
        for (int i = 0; i < temp.length; i++) {
            String[] oneblock = temp[i].split(",");
            final String type = oneblock[0];
            final int row = Integer.parseInt(oneblock[1]);
            final int col = Integer.parseInt(oneblock[2]);
            System.out.println(type +" "+row+" "+col);
            block = new Block(new Vector2(row * BLOCKSIZE + 3, col * BLOCKSIZE + 3), blockAtlas, type, i);
//            block.setPosition(row,col);
            blockList.add(block);
            block.addlistener();
            PlayScreen.inst().getGroupBoard().addActor(block);
        }
    }
}
