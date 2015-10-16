package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Path {
	public ArrayList<Step> steps = new ArrayList<Step>();
	private int counter = 0;
	
	public int getLength(){
		//TODO
		return 0;
	}
	public Step nextStep(){
		return steps.get(--counter);
	}
	public boolean hasNextStep(){
		if (counter > 0)
			return true;
		return false;
	}
	
	public void appendStep(int x, int y){
		//TODO
	}
	public void prependStep(Vector2 stepPosition){
		steps.add(counter,new Step(stepPosition));
		counter++;
	}
	
	
	public boolean equals(Object o){
		if ( o == null){
			return false;
		}
		if (o.getClass() != Path.class){
			return false;
		}
		return ((Path)o).steps.equals(this.steps);
	}
}
