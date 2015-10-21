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
	AStarPathFinder path_finder;
	Player player;
	PlayerManager player_manager ;
	CharacterView player_view;
	Set<NPC> goon_set = new HashSet<NPC>();
	Set<CharacterView> goon_view_set = new HashSet<CharacterView>();
	PostOffice postOffice = PostOffice.getInstance();
	NoiseHandler noiseHandler ;
	VisionHandler visionHandler ;
	
	public GameManager(TiledMap tiled_map,int width,int height,int tile_width,int goons){
		LevelMap map = new LevelMap(width,height, tile_width,tiled_map);
		AStarPathFinder path_finder = new AStarPathFinder(map, MAX_SEARCH);
		for(int i=0; i< goons; i++){		
			goon_view = new CharacterView("assets/hitman_walk.png", 18, 13, 15);
			goon = new Goon(new Rectangle(40,40, 18,13),map);
			goon.setAStarPathFinder(path_finder);
			goon_view.setPlayer(goon);
			goon_view_set.add(goon_view);
			goon_set.add(goon);
		}
		player_view = new CharacterView("assets/hitman_walk.png", 18, 13, 15);
		player = new Player(new Rectangle(50,50,18,13),map);
		player_manager = new PlayerManager(player) ;
		player_view.setPlayer(player) ;
		noiseHandler = new NoiseHandler(goon_set, path_finder);
		postOffice.setNoiseHandler(noiseHandler);
		visionHandler = new VisionHandler(goon_set,player) ;
	}
	
	public void updateModel(){
		try{
			postOffice.manage();
		}
		catch(WrongMessageException e){
			System.out.println("Wrong Message");
		}
		player_manager.manage();
		player.update();
		noiseHandler.handle();
		visionHandler.handle();
		for (NPC g : goon_set){
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
