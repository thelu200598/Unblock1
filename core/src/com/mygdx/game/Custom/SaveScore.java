package com.mygdx.game.Custom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveScore {
    private final Preferences pref;
    private int highScore;
    private static SaveScore saveScore;

    public static SaveScore inst() {
        if (saveScore == null)
            saveScore = new SaveScore();
        return saveScore;
    }


    public SaveScore(){
        pref = Gdx.app.getPreferences("blockscore");
        highScore = pref.getInteger("highscore", 0);
    }
    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
        pref.putLong("highscore",highScore);
        pref.flush();
    }
}
