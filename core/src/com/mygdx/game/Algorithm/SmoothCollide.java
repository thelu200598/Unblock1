package com.mygdx.game.Algorithm;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Object.Block;
import com.mygdx.game.Screen.PlayScreen;


public class SmoothCollide implements Collider {
  private static final float MN = 5; //todo magic number

  private float clampX(float dx, Block tile) {
    for (Block blockTile : PlayScreen.inst().getBlockList()) {
      Block obs = blockTile;

      if (obs.getId() != tile.getId() && Math.abs(tile.getY() - obs.getY()) < obs.getHeight()) //-----
      {
        if (dx > 0) {
          float boundX = tile.getX() + tile.getWidth();
          float reachX = boundX + dx;

          if (boundX - MN < obs.getX() && reachX >= obs.getX() && tile.getY() >= obs.getY() && tile.getY() <= obs.getY() + obs.getHeight() ){
            dx -= reachX - obs.getX();
//            System.out.println("x1: " + (obs.getY() - obs.getHeight() + 6));
          }
        }

        if (dx < 0) {
//          float obsFarX = obs.getX() + obs.getWidth();
          float obsFarX = obs.getX() + obs.getWidth();
          float reachX  = tile.getX() + dx;

          if (tile.getX() + MN > obsFarX && reachX <= obsFarX && tile.getY() >= obs.getY() && tile.getY() <= obs.getY() + obs.getHeight()){
            dx += obsFarX - reachX;
//            System.out.println("x2");

          }
        }
      }
    }

    return dx;
  }

  private float clampY(float dy, Block tile) {
    for (Block blockTile : PlayScreen.inst().getBlockList()) {
      Block obs = blockTile;
      if (obs.getId() != tile.getId()  && Math.abs(tile.getX() - obs.getX()) < obs.getWidth() /*- 30*/) //-----
      {

        if (dy > 0) {
          float boundY  = tile.getY() + tile.getHeight();
          float reachY  = boundY + dy;

          if (boundY - MN < obs.getY() && reachY >= obs.getY() && tile.getX() >= obs.getX() && tile.getX() <= obs.getX() + obs.getWidth()){
            dy -= reachY - obs.getY();
//            System.out.println("vao dayyyyyyyyyyyyyyy");
          }
        }

        if (dy < 0) {
          float obsFarY = obs.getY() + obs.getHeight();
          float reachY  = tile.getY() + dy;

          if (tile.getY() + MN > obsFarY && reachY <= obsFarY && tile.getX() >= obs.getX() && tile.getX() <= obs.getX() + obs.getWidth()){
              dy += obsFarY - reachY;
//              System.out.println("camp yyyyyyyyyy");
          }
        }
      }
    }

    return dy;
  }

  @Override
  public Vector2 clamp(float dx, float dy, Block tile) {
    float ox = tile.getX();
    float oy = tile.getY();

    Vector2 bound = clampB(dx, dy, tile);
    float nx = clampX(bound.x, tile);
    tile.setX(tile.getX() + nx);
//    float ny = clampY(bound.y, tile);
//    tile.setPosition(ox, oy);
    return Vector2.Zero.set(nx, oy);
  }

  @Override
  public Vector2 clampY(float dx, float dy, Block blockTile) {
    float ox = blockTile.getX();
    float oy = blockTile.getY();

    Vector2 bound = clampBY(dx, dy, blockTile);

    float ny = clampY(bound.y, blockTile);
    blockTile.setPosition(ox, oy /*+ ny*/);
//    block.setY(block.getY() + ny);
    return Vector2.Zero.set(ox, ny);
  }

  private Vector2 clampB(float dx, float dy, Block tile) {
    if (tile.getX() + dx <= 0){
      dx = 0 - tile.getX();
    }
    if (tile.getX() + tile.getWidth() + dx >= PlayScreen.inst().getBoardUp().getWidth() ){
      dx = PlayScreen.inst().getBoardUp().getWidth() - (tile.getWidth() + tile.getX());
    }

//
//    if (tile.getY() + dy <= 0){
//      dy = 0 - tile.getY();
//    }
//    if (tile.getY() + tile.getHeight() + dy > GameController.inst().board.getHeight()){
//      dy = GameController.inst().board.getHeight() - (tile.getHeight() + tile.getY());
//      System.out.println("vao day a` camobbbbbbbbbbb");
//    }

    return Vector2.Zero.set(dx, dy);
  }

  private Vector2 clampBY(float dx, float dy, Block tile) {
//    if (tile.getX() + dx < 0){
//      dx = 0 - tile.getX();
//    }
//    if (tile.getX() + tile.getWidth() + dx >= GameController.inst().board.getWidth() ){
//      dx = GameController.inst().board.getWidth() - (tile.getWidth() + tile.getX());
//    }

    if (tile.getY() + dy < 0){
      dy = 0 - tile.getY();
//      System.out.println("11111111111111111");
    }
    if (tile.getY() + tile.getHeight() + dy >= PlayScreen.inst().getBoardUp().getHeight()){
      dy = PlayScreen.inst().getBoardUp().getHeight() - (tile.getHeight() + tile.getY());
//      System.out.println("22222222222222222");
    }

    return Vector2.Zero.set(dx, dy);
  }


}
