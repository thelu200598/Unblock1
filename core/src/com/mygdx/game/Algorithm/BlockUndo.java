package com.mygdx.game.Algorithm;

import com.mygdx.game.Object.Block;

public class BlockUndo implements Command{
    float x1,y1,x2,y2;
    private static final int BLOCKSIZE =94;
    Block block;

    public BlockUndo(float x1, float y1, float x2, float y2,Block block) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.block = block;

    }

    @Override
    public void exc() {
        System.out.println("di tu vi tri: "+x1+"-"+y1+"den vi tri: "+x2+"-"+y2);
    }

    @Override
    public void undo() {
        float tmpX = x1*BLOCKSIZE+3;
        float tmpy = y1*BLOCKSIZE+3;
//        block.setPosition(tmpX,tmpy);
        block.setPosition(x1,y1);
    }
}
