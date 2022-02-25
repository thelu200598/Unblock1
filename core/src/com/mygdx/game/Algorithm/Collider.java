package com.mygdx.game.Algorithm;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Object.Block;

/*
this interface define from contract to detect collision of Rect widget
inside from container
 */
public interface Collider
{
  Vector2 clamp(float dx, float dy, Block blockTile);
  Vector2 clampY(float dx, float dy, Block blockTile);
}