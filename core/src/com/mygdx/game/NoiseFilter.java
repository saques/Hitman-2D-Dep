
package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WIP
 * Control system for emitted noises
 * @author masaques
 * @author traies
 *
 */

public class NoiseFilter implements MessageFilter{
	
	private PathFinder pathFinder;
	
	public NoiseFilter(PathFinder pathFinder){
		this.pathFinder = pathFinder;
	}
	/*
	 * Este metodo filtra los npc que escuchan el sonido segun su proximidad al sonido.
	 * @param Noise
	 * @param listeners
	 */
	
	
	@Override
	public Set<Listener> filter (Message message, Set<Listener> listeners) {
		if (message.getClass() != Noise.class){
			throw new WrongMessageException();
		}
		Noise noise = (Noise)message;
		if (listeners == null) {
			return listeners;
		}
		
		Set<Listener> removeSet = new HashSet<Listener>();
		for (Listener l : listeners){
			NPC npc = (NPC)l;
			Path path = pathFinder.findPath(null, npc.getPosition() , noise.getPosition());
			if (path == null || path.getLength() > noise.getRange()) {
				removeSet.add(l);
			}
		}
		listeners.removeAll(removeSet);
		return listeners;
	}
}
