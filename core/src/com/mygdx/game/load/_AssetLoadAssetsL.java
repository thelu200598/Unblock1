package com.mygdx.game.load;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class _AssetLoadAssetsL implements Load_Assets {
    AssetManager manager;
    public _AssetLoadAssetsL() {
        manager = new AssetManager();
    }

    public Sound getSound(String filename) {
        if (!manager.isLoaded(filename)) {
            manager.load(filename, Sound.class);
            manager.finishLoading();
        }
        return manager.get(filename, Sound.class);
    }

    public TextureAtlas getTextureAtlas(String filename){
        if(!manager.isLoaded(filename+".atlas")) {
            manager.load(filename+".atlas", TextureAtlas.class);
            manager.finishLoading();
        }
        return manager.get(filename+".atlas",TextureAtlas.class);
    }

    public BitmapFont getBitmapFont(String filename){
        if(!manager.isLoaded("Font/"+filename+".fnt")){
            manager.load("Font/"+filename+".fnt",BitmapFont.class);
            manager.finishLoading();
        }
        return manager.get("Font/"+filename+".fnt",BitmapFont.class);
    }

    public Texture getTexture(String filename){
        if(!manager.isLoaded(filename+".png")){
            manager.load(filename+".png",Texture.class);
            manager.finishLoading();
        }
        return manager.get(filename+".png",Texture.class);
    }
}
