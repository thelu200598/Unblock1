package com.mygdx.game.Custom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveLevel {
    private final Preferences pref;
    private int level;

    public int getLevel() {
        return level;
    }

    private static SaveLevel saveLevel;

    public static SaveLevel inst() {
        if (saveLevel == null)
            saveLevel = new SaveLevel();
        return saveLevel;
    }


    public SaveLevel(){
        pref = Gdx.app.getPreferences("blockscore");
        level = pref.getInteger("countlevel", 0);
    }

    public void setLevel(int level) {
        this.level = level;
        pref.putLong("countlevel", level);
        pref.flush();
    }
}
