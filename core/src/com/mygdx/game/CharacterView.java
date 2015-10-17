package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CharacterView {
	Character character;
	SpriteBatch batch;
	Texture texture;
	TextureRegion[] walkFrames;
	Sprite sprite;
	Animation walkAnimation;
	TextureRegion currentFrame;
	float stateTime;
	
	public CharacterView(String sprite_path,int sprite_width,int spriteLength, int animation_length){
		
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal(sprite_path));
		TextureRegion[][] tmp = TextureRegion.split(texture, sprite_width, spriteLength);
		walkFrames = new TextureRegion[animation_length];
		for(int i=0; i<animation_length;i++){
			walkFrames[i] = tmp[0][i];
		}
		walkAnimation = new Animation(0.025f,walkFrames);
		stateTime = 0f;
	}
	public void setPlayer(Character character){
		this.character = character;
	}
	public void draw(){
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		batch.begin();
		float rotation = character.getDirection().angle() + 90f;
		batch.draw(currentFrame, character.getPosition().x, character.getPosition().y, 9f, 6.5f,18f,13f, 1f,1f, rotation);
		batch.end();
	}
	public void update(){
		 stateTime += Gdx.graphics.getDeltaTime();
	}
}
