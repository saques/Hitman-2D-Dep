/*
 *@author Tomas Raies
 *@date   13 de oct. de 2015
 */

package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class NPC extends Character {
	protected static final float EPSILON = 2f;
	public  Path currentPath;
	protected Step finalStep;
	protected Step currentStep = null;
	protected PathFinder aStarPathFinder;
	protected PathFinder linearPathFinder;
	
	
	public NPC (Rectangle hitBox, LevelMap map, Model model){
		super(hitBox, map, model);
	}
	public void setAStarPathFinder(PathFinder pathFinder){
		this.aStarPathFinder = pathFinder;
	}
	
	public boolean moveTo(Vector2 position,boolean running) {
		
		if (finalStep != null && position.epsilonEquals(finalStep.getPosition(), EPSILON )){
			return false;
		}
		Vector2 currPosition = new Vector2();
		currPosition = hitBox.getPosition(currPosition);
		Path auxPath = aStarPathFinder.findPath(currPosition, position);
		if (auxPath != null && auxPath.hasNextStep()){
			this.running =running ;
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
				move(currentStep.getPosition().sub(getPosition()));
			}
			else {
				currentPath = null;
				isMoving = false;
			}
		}
		super.update();	
	}
	

}
