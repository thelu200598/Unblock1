package com.mygdx.game.Custom;


public class LabelCustom extends com.badlogic.gdx.scenes.scene2d.ui.Label {

    private String text;
    public LabelCustom(final CharSequence text, final LabelStyle style) {
        super(text, style);
        this.text = text.toString();
    }

    @Override
    public void act(final float delta) {
        this.setText(text);
        super.act(delta);
    }

    public void updateText(final String text) {
        this.text = text;
    }

}
