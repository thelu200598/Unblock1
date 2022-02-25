package com.mygdx.game.load;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public interface Load_Assets {
     Sound getSound(String filename);
     TextureAtlas getTextureAtlas(String filename);
     BitmapFont getBitmapFont(String filename);
     Texture getTexture(String filename);
}
