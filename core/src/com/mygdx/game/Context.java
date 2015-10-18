/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
/*
 * Maneja el contexto sobre el cual los Behaviours de los npc deciden sus
 * acciones, incluyendo informacion como sonidos y la posicion del jugador.
 */
public class Context {
	private Set<Noise> noiseSet;
	private Vector2 playerPosition ;
	
	public Context() {
		this.noiseSet = new HashSet<Noise>();
	}
	/*
	 * Devuelve si el npc puede ver al jugador.
	 */
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
	/*
	 * Devuleve si hay algun sonido en el contexto.
	 */
	public boolean hearNoise() {
		//TODO
		return false;
	}
	/*
	 * Agrega un sonido al contexto.
	 */
	public void add(Noise noise) {
		//TODO
		noiseSet.add(noise);
		System.out.println("BANG!!!");
	}
	/*
	 * Agrega la posicion del jugador. Deberia ser un add(). TODO
	 */
	public void setPlayerPosition(Vector2 playerPosition) {
		this.playerPosition = playerPosition ;
	}
	/*
	 * Vacia el contexto. deberia ser llamado por el update del NPC.
	 */
	public void flush() {
		noiseSet = new HashSet<Noise>();
		return;
	}
	
}
