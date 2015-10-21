/*
 *@author Tomas Raies
 *@date   17 de oct. de 2015
 */

package com.mygdx.game;

/*
 * Interfaz para los Handler, objetos que manejan los mensajes que les mande el postOffice.
 */
public interface Handler {
	/*
	 * Maneja los mensajes que tiene en la cola y los elimina una vez los ejecuto.
	 */
	public void handle();
	/*
	 * Reciben el mensaje, idealmente del postOffice (los demas objetos no deberian interactuar
	 * con los handlers directamente). Tiran WrongMessageException si al tipo del mensaje no
	 * lo puede manejar el handler
	 */
	public void send(Message message) throws WrongMessageException;
}
