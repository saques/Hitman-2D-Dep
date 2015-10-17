
package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * WIP
 * Control system for emitted noises
 * @author masaques
 * @author traies
 *
 */

public class NoiseHandler implements Handler{
	
	private Set<NPC> npc_set ;
	private List<Noise> noiseList;
	private PathFinder pathFinder;
	
	public NoiseHandler(Set<NPC> npc_set, PathFinder pathFinder){
		this.npc_set = npc_set ;
		this.noiseList = new ArrayList<Noise>();
		this.pathFinder = pathFinder;
	}
	
	/**
	 * Tells every NPC in range to act accordingly 
	 * TODO : distinction between NPCs
	 * @param noise
	 */
	public void warn(Noise noise){
		if (noise.isShot()){
			warnAll(noise);
		}
		for (NPC npc : npc_set){
//			Path noisePath = pathFinder.findPath(npc, npc.getPosition(), noise.getSource());
//			if (noisePath!= null) {
//				npc.addToContext(noise);
//			}
			if (noise.getSource().dst(npc.getPosition())<=noise.getRange()){
				npc.moveTo(noise.getSource()) ;
			}
		}
	}
	/**
	 * If the noise is a shot, then the range of the noise is infinite
	 * All NPCs must be warned
	 * TODO: NPCs that aren't Goons should escape, perhaps to the farthest position
	 * from the shot
	 * @param noise
	 */
	private void warnAll(Noise noise){
		for (NPC npc : npc_set){
			npc.addToContext(noise);
		}
	}
	
	@Override
	public void send (Message message) throws WrongMessageException{
		try {
			noiseList.add((Noise) message);
		}
		catch(ClassCastException e) {
			throw new WrongMessageException();
		}
	}
	@Override
	public void handle() {
		List<Noise> removeList = new ArrayList<Noise>();
		for (Noise n: noiseList) {
			warn(n);
			removeList.add(n);
		}
		noiseList.removeAll(removeList);
	}
	
}
