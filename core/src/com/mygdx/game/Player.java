/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Character {

	
	public Player(Rectangle hitBox, LevelMap map, CharacterView model) {
		super(hitBox, map, model);
	}

	@Override
	public void update() {
		// TODO
		super.update();
	}
	
	public void stopMoving() {
		this.isMoving = false;
	}

}
