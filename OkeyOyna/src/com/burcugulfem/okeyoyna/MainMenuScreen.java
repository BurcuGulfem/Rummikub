package com.burcugulfem.okeyoyna;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen{

		  final Okey game;
		 
			OrthographicCamera camera;
			
		 
			public MainMenuScreen(final Okey gam) {
				game = gam;
		 
				camera = new OrthographicCamera();
				camera.setToOrtho(false, 800, 480);
		 
			}
		 
			@Override
			public void render(float delta) {
				Gdx.gl.glClearColor(0, 0, 0.2f, 1);
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		 
				camera.update();
				game.batch.setProjectionMatrix(camera.combined);
		 
				game.batch.begin();
				game.font.draw(game.batch, "Welcome to Rummikub! ", 100, 150);
				game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
				game.batch.end();
		 
				if (Gdx.input.isTouched()) {
					game.setScreen(new GameScreen(game));
					dispose();
				}
			}
		 
			@Override
			public void resize(int width, int height) {
			}
		 
			@Override
			public void show() {
			}
		 
			@Override
			public void hide() {
			}
		 
			@Override
			public void pause() {
			}
		 
			@Override
			public void resume() {
			}
		 
			@Override
			public void dispose() {
			}	

}
