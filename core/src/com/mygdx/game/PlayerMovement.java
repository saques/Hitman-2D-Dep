package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class PlayerMovement {
	private boolean running ;
	private Vector2 direction ;
	public PlayerMovement (Vector2 direction,boolean running) {
		this.running = running ;
		this.direction = direction ;
	}
	public boolean isRunning(){
		return this.running;
	}
	public Vector2 getDirection(){
		return this.direction;
	}
}
