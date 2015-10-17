/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

public class Post {
	private Integer type;
	private Timer timer;
	private Message message;
	
	public Post (Integer type, Timer timer, Message message) {
		this.type = type;
		this.timer = timer;
		this.message = message;
	}
	
	public Integer getType() {
		return type;
	}
	
	public boolean checkTimer() {
		return timer.isOver();
	}
	
	public Message getMessage() {
		return message;
	}
}
