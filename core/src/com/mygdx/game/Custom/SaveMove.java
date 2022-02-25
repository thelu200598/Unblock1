package com.mygdx.game.Custom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveMove {
    private final Preferences pref;
    private int countmove;

    public int getCountmove() {
        return countmove;
    }

    private static SaveMove saveMove;

    public static SaveMove inst() {
        if (saveMove == null)
            saveMove = new SaveMove();
        return saveMove;
    }


    public SaveMove(){
        pref = Gdx.app.getPreferences("blockscore");
        countmove = pref.getInteger("countmove", 0);
    }

    public void setCountmove(int countmove) {
        this.countmove = countmove;
        pref.putLong("countmove",countmove);
        pref.flush();
    }
}
