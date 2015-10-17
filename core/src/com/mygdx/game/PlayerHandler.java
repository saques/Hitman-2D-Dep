package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class PlayerHandler {
	private ControlHandler control;
	private Player player;
	
	public PlayerHandler(Player player){
		this.player = player ;
		control = new ControlHandler() ;
		Gdx.input.setInputProcessor(control);
	}
	
	public void handle() {
		control.update();
		PlayerMovement movement ;
		movement = control.getPlayerMovement();
		if (movement.getDirection().x != 0 || movement.getDirection().y != 0) {
			player.move(movement.getDirection().nor(),movement.isRunning()) ;
		}
		else {
			player.stopMoving();
		}
	}
}
