/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class NPC extends Character {
	private static final float VISUAL_RANGE = 100f ;
	private static final float VISUAL_ANGLE = 45f ;
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
	public void setAStarPathFinder(AStarPathFinder pathFinder){
		this.aStarPathFinder = pathFinder;
	}
	
	
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
	
	public boolean isPlayerinSight(Vector2 playerPosition) {
		Vector2 directionN ;
		Vector2 directionS ;
		Vector2 goonPosition = new Vector2((float)this.hitBox.x,(float)this.hitBox.y) ;
		Vector2 goonDirection = this.direction ;
		float dirAngle = goonDirection.angle() ;
		float nAngle = dirAngle + VISUAL_ANGLE/2 ;
		float x,y ;
		directionN = new Vector2(x=(float)Math.cos(nAngle),y=(float)Math.sin(nAngle)) ;
		directionS = new Vector2(y,x) ;
		
		if (playerPosition.dst2(goonPosition)> VISUAL_RANGE){
			return false ;
		}
		/**
		 * TODO :
		 * Implementar en LevelMap un metodo para
		 * ver hay obstaculos entre dos posiciones dadas
		 */
		if (map.blocked(goonPosition, playerPosition)) {
			return false ;
		}
		Vector2 relativeDirection = playerPosition.sub(goonPosition).nor() ;
		float z ;
		if ( (z = relativeDirection.angle()) < directionS.angle() || z >directionN.angle() ) {
			return false ;
		}
		return true ;
	}
	
	public void addNoisetoContext(Noise n){
		context.add(n);
	}
	
	public void addPlayertoContext(Vector2 playerPosition) {
		context.setPlayerPosition(playerPosition);
	}
}
