package com.mygdx.game;

import java.util.Set;

/**
 * Maneja los metodos de vision de cada uno de los goons
 * Si algun goon ve al jugador, este agrega a su contexto la
 * posicion del mismo
 * @author masaques
 *
 */

public class VisionHandler implements Handler {

	private Set<NPC> npc_set ;
	private Player player ;
	
	public VisionHandler(Set<NPC> npc_set , Player player){
		this.player = player ;
		this.npc_set = npc_set ;
	}
	public void handle() {
		for (NPC npc : npc_set){
			if(npc.canSee(player.getPosition())){
				npc.addPlayertoContext(player.getPosition());
			}
		}
		
	}
	/**
	 * Esto no tiene sentido aca
	 */
	@Override
	public void send(Message message) throws WrongMessageException {
		// TODO Auto-generated method stub
		
	}

}
