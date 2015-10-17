package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LevelMap {
	
	private int width;
	private int height;
	private TiledMap tiled_map;
	private int tile_width;
	TiledMapTileLayer foreground;
	
	public LevelMap(int width, int height,int tile_width, TiledMap tiled_map){
		this.width = width;
		this.height = height;
		this.tile_width = tile_width;
		this.tiled_map = tiled_map;
		this.foreground =  (TiledMapTileLayer)tiled_map.getLayers().get(1);
		//level_array[5][10] = true;
	}
	public int getTileWidth(){
		return tile_width;
	}
	
	public int getWidthInTiles() {
		return width / tile_width;
	}

	
	public int getHeightInTiles() {
		return height /tile_width;
	}

	
	public boolean blocked( Vector2 position) {
		float x,y;
		x = position.x;
		y = position.y;
		
		if (x < 0 || (y < 0) || x >= width || y >= height){
			return true;
		}
		return foreground.getCell((int)x / tile_width, (int)y / tile_width) != null;
	}
	
	public boolean blocked(Vector2 start, Vector2 end) {
		if (blocked(end)) {
			return false ;
		}
		
		return false ;
	}

	
	public float getCost(int sx, int sy, int tx, int ty) {
		if (sx != tx && sy != ty){
			return 1.41f;
		}
		return 1f;
	}


	public int tilePosition(int x){
		return x / tile_width;
	}
	
	public boolean isValid(Rectangle hit_box) {
		int max_height =(int) (hit_box.getY() + hit_box.getHeight());
		int max_width = (int) (hit_box.getX() + hit_box.getWidth());
		
		Vector2 position = new Vector2();
		
		//posiblemente ineficiente?
		for (int x = (int) hit_box.getX(); x < max_width; x+=1){
			for (int y = (int) hit_box.getY(); y <  max_height; y+= 1){
				position.set(x, y);
				if (blocked( position)){
					return false;
				}
			}
		}
		return true;
	}
	
}
