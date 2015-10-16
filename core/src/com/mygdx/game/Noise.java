

package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
/**
 * WIP
 * This class represents all noises emitted by the player and NPCs
 * Every time a player shoots or starts running must emit a noise
 * TODO : Must add proper behavior in Character-related classes
 * TODO : Shoots must have a distinctive property, causing greater disturbance in NPCs
 * @author masaques
 *
 */

public class Noise {
	private Vector2 source ;
	private double effectiveRange ;
	private Character emitter; 
	private NoiseHandler handler ;
	private boolean isShot ;
	
	public Noise(Vector2 source, Character emitter,double effectiveRange, NoiseHandler handler, boolean isShot) {
		this.source=source ;
		this.emitter=emitter ;
		this.effectiveRange = effectiveRange ;
		this.handler=handler; 
		this.isShot= isShot ;
	}
	
	public Vector2 getSource(){
		return this.source;
	}
	
	public double getRange(){
		return this.effectiveRange;
	}
	
	public Character getEmitter(){
		return this.emitter;
	}
	
	public boolean isShot() {
		return isShot ;
	}
	
	public void manage() {
		handler.warn(this);
	}
}
