package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter {
	Texture img;
	TiledMap tiled_map;
	Goon goon;
	Model goon_model;
	PathFinder path_finder;
	LevelMap map;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	int[][] moveset;
	int i = 0;
	FPSLogger fps_logger;
	ControlHandler control;
	RandArray<Vector2> rand_array;
	Player player;
	Model player_model;
	Set<Goon> goon_set = new HashSet<Goon>();
	Set<Model> goon_model_set = new HashSet<Model>();
	
	@Override
	public void create () {
		int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w,h );
		camera.update();
		
		
		tiled_map = new TmxMapLoader().load("assets/test5.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiled_map);
		
		
		LevelMap map = new LevelMap(w,h, 32,tiled_map);
		
		fps_logger = new FPSLogger();
		
		
		PathFinder path_finder = new PathFinder(map, 100);
	
		for(int i=0; i< 50; i++){
			
			goon_model = new Model("assets/hitman_walk.png", 18, 13, 15);
			goon = new Goon(new Rectangle(40,40, 18,13),map,goon_model);
			goon.setAStarPathFinder(path_finder);
			goon_model.setPlayer(goon);
			goon_model_set.add(goon_model);
			goon_set.add(goon);
		}
		
		
//		rand_array = new RandArray<Vector2>();
//		rand_array.add(new Vector2(200, 150));
//		rand_array.add(new Vector2(700,700));
//		rand_array.add(new Vector2(73,792));
//		rand_array.add(new Vector2(817,48));
		
		player_model = new Model("assets/hitman_walk.png", 18, 13, 15);
		player = new Player(new Rectangle(50,50,18,13),map, player_model);
		player_model.setPlayer(player);
		control = new ControlHandler(player,goon_set,map);
		Gdx.input.setInputProcessor(control);
		
	}

	@Override
	public void render () {
		fps_logger.log();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		control.update();
		player.update();
		
//		if (player.getPosition().x < 400 && player.getPosition().y < 400){
//			for(Goon g:goon_set) {
//				g.move(new Vector2(player.getPosition().x, player.getPosition().y),player.running);
//				
//			}
//		}
		
//		for(Goon g: goon_set){
//			
//			if (!g.isMoving()){
//				
//				Vector2 next = rand_array.get();
//				g.moveTo(next);
//			}
//		}
		
//		goon.moveTo(new Vector2 (100,50));
		
		if (i == 20){
			for (Goon g:goon_set){
				g.moveTo(player.getPosition(),player.running);
			}
			i=0;
		}
		else{
			i++;
		}
		for (Goon g:goon_set){
			g.update();
		}
        camera.update();
        renderer.setView(camera);
        renderer.render();
        player_model.draw();
        for (Model gm:goon_model_set){
			gm.draw();
		}
        
	}
}
