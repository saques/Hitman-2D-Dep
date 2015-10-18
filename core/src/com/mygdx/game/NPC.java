/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/*
 * Personajes no controlados por el jugador. Debem implementar comportamientos diferentes
 * segun el contexto del juego. Ademas, deben ser capaces de encontrar el camino entre dos 
 * puntos en el mapa.
 */
public abstract class NPC extends Character {
	private static final float VISUAL_RANGE = 100f ;
	private static final float VISUAL_ANGLE = 90f ;
	protected static final float EPSILON = 2f;
	protected  Path currentPath;
	protected Step finalStep;
	protected Step currentStep = null;
	private AStarPathFinder aStarPathFinder;
	private AStarPathFinder linearPathFinder;
	private Context context;
	
	public NPC (Rectangle hitBox, LevelMap map){
		super(hitBox, map);
		context = new Context();
	}
	/*
	 * Setea el pathFinder para el movimento no lineal.
	 * @param pathFinder
	 */
	public void setAStarPathFinder(AStarPathFinder pathFinder){
		this.aStarPathFinder = pathFinder;
	}
	
	/*
	 * Setea el camino para llegar a un punto en el mapa, si es posible.
	 * @param position
	 */
	public boolean moveTo(Vector2 position) {
		if (finalStep != null && position.epsilonEquals(finalStep.getPosition(), EPSILON )){
			return false;
		}
		Vector2 currPosition = new Vector2();
		currPosition = hitBox.getPosition(currPosition);
		Path auxPath = aStarPathFinder.findPath(this, currPosition, position);
		if (auxPath != null && auxPath.hasNextStep()){
			currentPath = auxPath;
			currentStep = currentPath.nextStep();
			move(currentStep.getPosition().sub(getPosition()));
			isMoving = true;
			return true;
		}
		return false;
	}
	
	/*
	 * Actualiza el estado del NPC
	 * TODO hay una version mas nueva en el otro repositorio.
	 */
	@Override
	public void update() {
		if (!isMoving || currentPath == null){
			isMoving = false;
			currentPath = null;
			currentStep = null;
			return;
		}
	
		if (currentStep == null || currentStep.getPosition().epsilonEquals(getPosition(), EPSILON)){
			if (currentPath.hasNextStep()){
				currentStep = currentPath.nextStep();
			}
			else {
				currentPath = null;
				isMoving = false;
			}
		}
		if (isMoving){
			/*
			 * Ineficiente? Probablemete, pero me soluciona la vida por ahora. (Tiene que hacer una raiz cuadrada por frame) 
			 */
			move(currentStep.getPosition().sub(getPosition()));
		}
		super.update();	
	}
	
	/**
	 * Metodo para calcular si el jugador esta en el campo visual de este NPC
	 * Utilizado por VisionHandler
	 * @param playerPosition
	 * @return true si el jugador es visible, false si no
	 */
	
	public boolean canSee(Vector2 playerPosition) {
		Vector2 goonPosition = new Vector2((float)this.hitBox.x,(float)this.hitBox.y) ;
		Vector2 goonDirection = this.direction ;
		if (playerPosition.dst2(goonPosition)> VISUAL_RANGE){
			return false ;
		}
		if (!map.isValid(goonPosition, playerPosition)) {
			return false ;
		}
		float dirAngle = goonDirection.angle() ;
		Vector2 relativeDirection = playerPosition.sub(goonPosition).nor() ;
		float z ;
		if ( (z= relativeDirection.angle())>=dirAngle-VISUAL_ANGLE/2 && z <= dirAngle+VISUAL_ANGLE/2) {
			return true ;
		}
		return false ;
	}
	
	/*
	 * Anade un mensaje al contexsto. TODO en realidad, el NPC deberia mandar cualquier mensaje,
	 * sin importar si es un Noise o de cualquier otro tipo. El contexto deberia saber manejar
	 * distintos tipos de mensajes. Por eso, deberia haber un unico metodo addToContext(Message m).
	 */
	public void addNoisetoContext(Noise n){
		context.add(n);
	}
	
	public void addPlayertoContext(Vector2 playerPosition) {
		context.setPlayerPosition(playerPosition);
	}
}
