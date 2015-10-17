package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameHandler {
	
	protected static final int MAX_SEARCH = 100 ;
	LevelMap map ;
	Goon goon;
	CharacterView goon_view;
	PathFinder path_finder;
	ControlHandler control;
	
	Player player;
	CharacterView player_view;
	Set<Goon> goon_set = new HashSet<Goon>();
	Set<CharacterView> goon_view_set = new HashSet<CharacterView>();

	public GameHandler(TiledMap tiled_map,int width,int height,int tile_width,int goons){
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
		// cambiar esto para que player no tenga que recibir el modelo
		player_view = new CharacterView("assets/hitman_walk.png", 18, 13, 15);
		player = new Player(new Rectangle(50,50,18,13),map, player_view);
		player_view.setPlayer(player) ;
		control = new ControlHandler(player,goon_set,map);
		Gdx.input.setInputProcessor(control);
	}
	
	public void updateModel(){
		control.update(); 
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
