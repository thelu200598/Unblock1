package com.mygdx.game.Object;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import sdk.utils.component.AlignGroup;

public class RecTile extends AlignGroup {

    public int r;
    public int c;
    public int idx;

    public RecTile(int idx ,int r, int c){
        setSize(94,94);
        this.idx = idx;
        this.r = r;
        this.c = c;
//        this.debug();
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("click rec index: " + idx);
//                System.out.println("RecTile pos X: " + (int)RecTile.this.getX()/94);
//                System.out.println("RecTile pos Y: " + (int)RecTile.this.getY()/94);
                System.out.println("RecTile pos X: " + c);
                System.out.println("RecTile pos Y: " + r);

            }
        });


//        this.setTouchable(Touchable.disabled);
    }
}
