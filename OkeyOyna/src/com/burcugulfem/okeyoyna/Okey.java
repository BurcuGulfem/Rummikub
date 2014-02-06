package com.burcugulfem.okeyoyna;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Okey extends Game {
	SpriteBatch batch;
	BitmapFont font;
	
	
	
	@Override
	public void create() {		
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
