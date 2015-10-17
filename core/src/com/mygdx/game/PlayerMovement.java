package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
/**
 * Pequeña clase que devuelve ControlProcessor en getPlayerMovement()
 * para informarle a PlayerHandler lo que tiene que hacer
 * @author masaques
 *
 */
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
