package com.mygdx.game.Object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.game.Algorithm.BlockUndo;
import com.mygdx.game.Algorithm.Command;
import com.mygdx.game.Algorithm.Controller;
import com.mygdx.game.Custom.SaveMove;
import com.mygdx.game.Screen.PlayScreen;
import com.mygdx.game.Screen.SelectScreen;
import com.mygdx.game.Unblock;

import java.util.ArrayList;
import java.util.Stack;

public class Block extends Image {

    String type;
    public String getType() {
        return type;
    }
    public boolean isDrag = true;
    ArrayList<Block> listblock = (ArrayList<Block>) PlayScreen.inst().getBlockList();



    public Vector2 basePos;

    public Vector2 getBasePos() {
        return basePos;
    }

    public void setBasePos(Vector2 basePos) {
        this.basePos = basePos;
    }

    int countmove;
    public static final String MAIN = "c2";
    public static final String H_TWO = "n2";
    public static final String H_THREE = "n3";
    public static final String V_TWO = "d2";
    public static final String V_THREE= "d3";
    Vector2 posBlock;
    Stack<Command> stack = Controller.inst().getStack();
    public Vector2 getPosBlock() {
        return posBlock;
    }

    public void setPosBlock(Vector2 posBlock) {
        this.posBlock = posBlock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static Block block;
    private Rectangle bounds;
    int id;
    public void setBoundss(Rectangle bounds) {
        this.bounds = bounds;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public Block(Vector2 pos, TextureAtlas textureAtlas, String type,int id){
        super(getRegion(textureAtlas,type));
        this.type = type;
        setPosition(pos.x, pos.y);
        bounds = new Rectangle(pos.x,pos.y, getWidth(),getHeight());
        this.id=id;
//        setDebug(true);
        posBlock = new Vector2();
//        bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }
    private static TextureRegion getRegion(TextureAtlas textureAtlas, String type) {
        TextureRegion textureRegion = null;
        switch (type) {
            case MAIN:
                textureRegion = textureAtlas.findRegion("block_main");
                break;

            case H_THREE:
                textureRegion = textureAtlas.findRegion("block_h_big");
                break;
            case V_THREE:
                textureRegion = textureAtlas.findRegion("block_v_big");
                break;
            case H_TWO:
                textureRegion = textureAtlas.findRegion("block_h_small");
                break;
            case V_TWO:
                textureRegion = textureAtlas.findRegion("block_v_small");
                break;
        }
        return textureRegion;
    }

    public void checkDragStop(){
        int xMove = (int) getX();
        int yMove = (int) getY();
        int nguyenX = xMove / BLOCKSIZE, duX = xMove % BLOCKSIZE;
        int nguyenY = yMove / BLOCKSIZE, duY = yMove % BLOCKSIZE;
        if (duY > 48)
            yMove = (nguyenY + 1) * BLOCKSIZE+3;
        else
            yMove = nguyenY * BLOCKSIZE+3;

        if (duX > 48)
            xMove = (nguyenX + 1) * BLOCKSIZE;
        else
            xMove = nguyenX * BLOCKSIZE;
        setPosition(xMove,yMove);
    }
    Vector2 lastPost = new Vector2();
    float px,py;
    private static final int BLOCKSIZE =94;
    public void RefreshPosBlock(){
        this.addAction(Actions.moveTo(basePos.x, basePos.y,0.3f));
    }
    ClickListener touch = new ClickListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            px=x;
            py=y;
            return super.touchDown(event, x, y, pointer, button);
        }
    };

    DragListener dragListener = new DragListener(){
        float dx, dy;
        @Override
        public void dragStart(InputEvent event, float x, float y, int pointer) {
            dx =x;
            dy =y;
            lastPost.x = Block.this.getX();
            lastPost.y = Block.this.getY();
//            currpos.x=x;
//            currpos.y=y;
            super.dragStart(event, x, y, pointer);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            moveBlock(x,y,dx,dy,true);
//                String types = String.valueOf(getType().charAt(0));
//                switch (types){
//                    case "c":
//                    case "n":
//                        dx = getX() + x - dx;
//                        dy = getY();
//                        break;
//                    case "d":
//                        dx = getX();
//                        dy = getY() + y - dy;
//                        break;
//                }
//
//                setPosition(dx,dy);
////
//                int xMove = (int) getX();
//                int yMove = (int) getY();
//
//                String tmp_type= getType();
//                if(tmp_type.equals("c2")){
//                    if (getX()> BLOCKSIZE*5-10)
//                        xMove = BLOCKSIZE*5-10;
//                }
//                else if(tmp_type.equals("n3")){
//                    if (getX()> BLOCKSIZE*3)
//                        xMove = BLOCKSIZE*3;
//                } else
//                if (getX()> BLOCKSIZE*4)
//                    xMove = BLOCKSIZE*4;
//
//
//                if (BeginnerScreen.inst().getGame_Screen() == null)
//                    System.out.println("null screen");
//
//                if (getX() < BeginnerScreen.inst().getGame_Screen().getX())
//                    xMove = (int)BeginnerScreen.inst().getGame_Screen().getX();
//                if(tmp_type.equals("d3")){
//                    if (getY() > BLOCKSIZE*3)
//                        yMove = BLOCKSIZE*3;
//                }
//                else if (getY() > BLOCKSIZE*4)
//                    yMove = BLOCKSIZE*4;
//                if (getY() < BeginnerScreen.inst().getGame_Screen().getY())
//                    yMove = (int) BeginnerScreen.inst().getGame_Screen().getY();
////
//                setX( xMove);
//                setY( yMove);
//
////                    for(int p=0;p<list_id.size();p++){
////                        if(String.valueOf(block.getType().charAt(0)).equals("c"))
////                            System.out.println(list_id.get(p));
////                    }
//////                    block.setBoundss(new Rectangle(block.getX(),block.getY(), block.getWidth(),block.getHeight()));
////                    System.out.println("id block move: "+block.getId());
////                    movingTarget.set(block.getX(),block.getY(),block.getWidth(),block.getHeight());
////                    if (movingTarget.overlaps(point_end))
////                        System.out.println("win");
//////                   if (block.getId()==) System.out.println("trung ID");
//////                        else
////
////                    boolean isOverlaps = movingTarget.overlaps(collideTarget);
////                    if(isOverlaps){
//////                        if(String.valueOf(block.getType().charAt(0)).equals("n"))
//////                        block.setX(collideTarget.getX());
////                            System.out.println("cham");
//////                        else if (String.valueOf(block.getType().charAt(0)).equals("d"))
//////                            block.setY(collideTarget.getY()+collideTarget.getHeight());
//////                        System.out.println("da cham");
////                        }
////                    else
////                        System.out.println("chua cham");
//
////                    for (int i = 0 ; i < blockList.size(); i ++){
////                        Block block = blockList.get(i);
////                        System.out.println("block id: " + block.getId());
////                    }
//
//
//
////                    blockList.add(new Block(new Vector2(row*BLOCKSIZE+3, col*BLOCKSIZE+3), blockAtlas, type, finalI));
////                    for(int i=0;i<blockList.size();i++){
////                        for(int j=0;j<blockList.size();j++){
////                            if(blockList.get(i).checkCollisions(blockList.get(j)))
////                                System.out.println("cham roi");
////                            System.out.println("chua cham");
////                        }
////                    }
////        Block moving;
////                    block.toFront();
//
////                    for(Block item : blockList){
////                        if (block.getId() == item.getId())
////                            System.out.println("trung ID");
////
////                        else
////                        {
////                            if (block.getBounds().overlaps(item.getBounds())){
////                                System.out.println("ok");
//////                                block.setY(item.getY() - block.getHeight());
////                            }
////                        }
////
//////                        if (Intersector.overlaps(block.getWood(),item.getWood())){
//////                            System.out.println("ok");
//////                            block.setY( item.getHeight()+item.getY());
//////                        }
////                    }
//
//                for (int i = 0; i < BeginnerScreen.inst().getBlockList().size(); i ++){
//                    Block block1 = BeginnerScreen.inst().getBlockList().get(i);
//                    if (getId() != block1.getId()){
//                        if (checkCollisions(block1)){
//                            System.out.println("cham");
//                        }
//                    }
//
//                }
            super.drag(event, x, y, pointer);
        }

        @Override
        public void dragStop(InputEvent event, float x, float y, int pointer) {
            checkDragStop();
            Command cmd = new BlockUndo(lastPost.x, lastPost.y, Block.this.getX(),Block.this.getY(),
                    Block.this);
            cmd.exc();
            stack.push(cmd);
            Controller.inst().countMove();
            if(Controller.inst().checkWinGame()){
                System.out.println("win");
                if(Controller.inst().flag)
                    PlayScreen.inst().drawWinGame();
                else Unblock.setViewScreen(new SelectScreen());
            }
            super.dragStop(event, x, y, pointer);
        }
    };



    public void addlistener(){
        this.addListener(dragListener);
    }

    public void undo(){
        if(stack.size()>0)
            stack.pop().undo();
    }



    public boolean checkCollisions (Block block){
        if(this == block)
            return false;
        return getBounds().overlaps(block.getBounds());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        bounds.setPosition(this.getX(),this.getY());
    }

    public void moveBlock(float x, float y, float dx, float dy,boolean canmove){
        float Px = x - dx;
        float Py = y - dy;
        if(this.getOrientation() == Orientation.HORIZONTAL){
//            float xcampR = this.getX() + this.getWidth() + x - dx;
//            float xcampL = this.getX() + x - dx;
//            System.out.println(xcampL);
//            float xPos = 0;
            Vector2 v = Controller.inst().getPos(Px,Py,this);
            if (isDrag)
                this.setPosition(this.getX() + v.x , this.getY());
//                   this.setPosition(xPos ,this.getY());
            isDrag = false;

        }
        else{
            Vector2 v = Controller.inst().getPosY(Px,Py,this);
//                    System.out.println("check is drag: " + isDrag);
//                    if (isDrag)
            this.setPosition(this.getX(), this.getY() + v.y);
            isDrag = false;
//
        }
    }

    public Orientation getOrientation(){
        return(this.getWidth() > this.getHeight()) ? Orientation.HORIZONTAL : Orientation.VERTICAL;
    }
}
