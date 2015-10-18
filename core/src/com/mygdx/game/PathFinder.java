package com.mygdx.game;
/*
 * Interfaz para un pathFinder. en particular, implementamos AStarPathFinder y
 * linearPathFinder.
 */
public interface PathFinder {
	public Path findPath(Vector2 startPosition, Vector2 finalPosition);
}
