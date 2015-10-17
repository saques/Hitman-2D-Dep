package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
/**
 * Esta clase es una modularizacion del contenido previo de Game
 * Es quien contiene el mapa y todos los personajes, como asi a los diferentes 
 * handlers para administrarlos
 * Se ocupa de actualizar el modelo y, luego, de actualizar la vista, tal como
 * dice MVC
 * @author masaques
 * @author traies
 */
public class GameManager {
	
	protected static final int MAX_SEARCH = 100 ;
	LevelMap map ;
	Goon goon;
	CharacterView goon_view;
	PathFinder path_finder;
	
	Player player;
	PlayerManager player_handler ;
	CharacterView player_view;
	Set<Goon> goon_set = new HashSet<Goon>();
	Set<CharacterView> goon_view_set = new HashSet<CharacterView>();

	public GameManager(TiledMap tiled_map,int width,int height,int tile_width,int goons){
		LevelMap map = new LevelMap(width,height, tile_width,tiled_map);
		PathFinder path_finder = new PathFinder(map, MAX_SEARCH);
		for(int i=0; i< goons; i++){		
			goon_view = new CharacterView("assets/hitman_walk.png", 18, 13, 15);
			goon = new Goon(new Rectangle(40,40, 18,13),map,goon_view);
			goon.setAStarPathFinder(path_finder);
			goon_view.setPlayer(goon);
			goon_view_set.add(goon_view);
			goon_set.add(goon);
		}
		/**
		 * Cambiar esto para que Character no tenga que recibir el Modelo:
		 * El Modelo en Character solo se usa en una linea, en el metodo update()
		 */
		player_view = new CharacterView("assets/hitman_walk.png", 18, 13, 15);
		player = new Player(new Rectangle(50,50,18,13),map, player_view);
		player_handler = new PlayerManager(player) ;
		player_view.setPlayer(player) ;
	}
	
	public void updateModel(){
		player_handler.manage();
		player.update();
		for (Goon g : goon_set){
			g.update();
		}
	}
	
	public void updateView() {
		player_view.draw();
        for (CharacterView gm:goon_view_set){
        	gm.draw();
		}
	}

}
