package com.mygdx.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/*
 * Representacion en el modelo del mapa del nivel. Esta implementado como un array de Rectangles,
 * que representan los obstaculos por los que los personajes no pueden navegar.
 * TODO revisar nueva implementacion
 */
public class LevelMap {
	
	private int width;
	private int height;
	private TiledMap tiled_map;
	private int tile_width;
	TiledMapTileLayer foreground;
	
	/*
	 * El constructor necesita el TiledMap del cual se basa, asi como el
	 * tileWidth que representa los Rectangulos navegables. No es necesario
	 * que el tileWidth del tiledMap coincida con el tileWidth del LevelMap.
	 * //TODO revisar nueva implementacion
	 */
	public LevelMap(int width, int height,int tile_width, TiledMap tiled_map){
		this.width = width;
		this.height = height;
		this.tile_width = tile_width;
		this.tiled_map = tiled_map;
		this.foreground =  (TiledMapTileLayer)tiled_map.getLayers().get(1);
		//level_array[5][10] = true;
	}
	/*
	 * Devuelve el ancho del tile segun el LevelMap
	 */
	public int getTileWidth(){
		return tile_width;
	}
	
	/*
	 * Devuleve el ancho del mapa en tiles
	 */
	public int getWidthInTiles() {
		return width / tile_width;
	}
	/*
	 * Devuelve en alto del mapa en tiles.
	 */
	public int getHeightInTiles() {
		return height /tile_width;
	}
	/*
	 * Devuleve si la posicion esta bloqueada. 
	 * //TODO deberia recibir un Movable que deberia entender los permisos
	 * de paso que tiene (Noise podria ser un movable que pueda atravesar paredes
	 * mientras que Bullet no) 
	 * @param position 
	 */
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

	/*
	 * Devuelve el costo de un movimiento desde un tile al otro. En nuestro caso,
	 * solo existe un unico costo, 1.
	 * @param sx - Posicion en x del origen
	 * @param sy - posicion en y del origen
	 * @param tx - posicion en x del destino
	 * @param ty - posicion en y del destino
	 * TODO cambiar a Vector2 o que directamente se fije en el tile. 
	 */
	public float getCost(int sx, int sy, int tx, int ty) {
		if (sx != tx && sy != ty){
			return 1.41f;
		}
		return 1f;
	}

	
	public int tilePosition(int x){
		return x / tile_width;
	}
	
	/*
	 * Detecta las colisiones de un hit box con el mapa.
	 * @param hit_box
	 */
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
