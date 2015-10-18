/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;
/*
 * Clase Singleton que maneja la distrubucion de los mensajes a sus respectivos
 * handlers.
 */
public class PostOffice {
	private static final PostOffice INSTANCE = new PostOffice();
	public static final int NOISE = 0;
	public static final int BULLET = 1;
	private Handler noiseHandler;	
	private List<Post> postList;
	
	/*
	 * Esta implementada como un Singleton (solo exite una instancia de esta clase), asi que 
	 * el constructor es privado. Usar getInstace en lugar de new.
	 */
	private PostOffice() {
		this.postList = new ArrayList<Post>();
	}
	public static PostOffice getInstance() {
		return INSTANCE;
	}
	/*
	 * anade un post al buzon.
	 */
	public void post(Post post) {
		postList.add(post);
	}
	
	/*
	 * Recorre cada post en el buzon y lo manda al handler que le corresponde.
	 */
	public void manage() throws WrongMessageException{
		List<Post> removeList = new ArrayList<Post>();
		for (Post p : postList) {
			if (p.checkTimer()) {
				handle(p);
				removeList.add(p);
			}
		}
		postList.removeAll(removeList);
		noiseHandler.handle();
		
	}
	
	public void setNoiseHandler(Handler noiseHandler) {
		this.noiseHandler = noiseHandler;
	}
	
	/*
	 * manda un post al handler que le corresponde segun su tipo
	 */
	private void handle(Post p) throws WrongMessageException{
		switch (p.getType()) {
		case NOISE:
			noiseHandler.send(p.getMessage());
			break;
		case BULLET:
//			bulletHandler.send(p.getMessage());
			break;
		default:
			/*
			 * TODO: Aca deberia haber una excepcion
			 */
			break;
		}
	}
}
