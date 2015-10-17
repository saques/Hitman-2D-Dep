/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;

public class Context {
	private Set<Noise> noiseSet;
	private Vector2 playerPosition ;
	
	public Context() {
		this.noiseSet = new HashSet<Noise>();
	}
	public boolean playerIsVisible() {
		//TODO
		return true;
	}
	/*
	 * Devuelve el sonido mas fuerte, null si es vacio.
	 */
	public Noise getNoise() {
		Noise maxNoise = null;
		for (Noise n: noiseSet) {
			if ( maxNoise == null || n.getRange() > maxNoise.getRange()) {
				maxNoise = n;
			}
		}
		return maxNoise;
	}
	public boolean hearNoise() {
		//TODO
		return false;
	}
	public void add(Noise noise) {
		//TODO
		noiseSet.add(noise);
		System.out.println("BANG!!!");
	}
	
	public void setPlayerPosition(Vector2 playerPosition) {
		this.playerPosition = playerPosition ;
	}
	public void flush() {
		noiseSet = new HashSet<Noise>();
		return;
	}
	
}
