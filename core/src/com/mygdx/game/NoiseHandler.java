
package com.mygdx.game;

import java.util.Set;

/**
 * WIP
 * Control system for emitted noises
 * @author masaques
 *
 */

public class NoiseHandler {
	private Set<NPC> npc_set ;
	
	public NoiseHandler(Set<NPC> npc_set){
		this.npc_set = npc_set ;
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
			if (npc.getPosition().dst(noise.getSource())<= noise.getRange()){
				if (!noise.getEmitter().getClass().isInstance(npc)) {
					npc.moveTo(noise.getSource());
				}
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
			npc.moveTo(noise.getSource());
		}
	}
}
