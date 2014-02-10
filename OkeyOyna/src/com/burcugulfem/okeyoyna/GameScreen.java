package com.burcugulfem.okeyoyna;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.burcugulfem.okeyoyna.actors.TileActor;
import com.burcugulfem.okeyoyna.logic.Board;
import com.burcugulfem.okeyoyna.logic.TileSet;

public class GameScreen implements Screen {
	final Okey game;
	private Texture rack_texture, board_texture;
	private Stage stage;
	OrthographicCamera camera;
	Rectangle boardrect;
	int rackWidth, rackHeight, boardsidepadding;
	TileSet pool;
	Board player;
	int initialSize = 15; // # of tiles on the board
	public static BitmapFont tileFontRed, tileFontBlue, tileFontYellow,
			tileFontGreen;
	DragAndDrop dragAndDrop;
	TextButton button_newDeck, button_groupDoubles, button_groupSeries;
	Skin uiSkin;

	public GameScreen(Okey game) {
		Texture.setEnforcePotImages(false);
		this.game = game;

		stage = new Stage();
		dragAndDrop = new DragAndDrop();

		Gdx.input.setInputProcessor(stage);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		defineResources();
		defineVariables();

		pool = new TileSet(); // creates a full set of tiles

		startNewDeck();
		placeButtons();
	}

	public void defineResources() {
		uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		tileFontRed = new BitmapFont(
				Gdx.files.internal("data/fonts/calibri.fnt"),
				Gdx.files.internal("data/fonts/calibri.png"), false);
		tileFontRed.setColor(Color.RED);

		tileFontBlue = new BitmapFont(
				Gdx.files.internal("data/fonts/calibri.fnt"),
				Gdx.files.internal("data/fonts/calibri.png"), false);
		tileFontBlue.setColor(Color.BLUE);

		tileFontYellow = new BitmapFont(
				Gdx.files.internal("data/fonts/calibri.fnt"),
				Gdx.files.internal("data/fonts/calibri.png"), false);
		tileFontYellow.setColor(Color.YELLOW);

		tileFontGreen = new BitmapFont(
				Gdx.files.internal("data/fonts/calibri.fnt"),
				Gdx.files.internal("data/fonts/calibri.png"), false);
		tileFontGreen.setColor(Color.GREEN);

		rack_texture = new Texture(Gdx.files.internal("tile_small.png"));
		board_texture = new Texture(Gdx.files.internal("board_small.png"));

	}

	public void defineVariables() {
		boardrect = new Rectangle();
		boardrect.x = 800 / 2 - board_texture.getWidth() / 2; // center the
																// board
																// horizontally
		boardrect.y = 20; // bottom left corner of the board is 20 pixels above
							// the bottom screen edge
		boardrect.width = board_texture.getWidth();
		boardrect.height = board_texture.getHeight();

		rackHeight = rack_texture.getHeight();
		rackWidth = rack_texture.getWidth();

		boardsidepadding = (int) boardrect.getX() + rackWidth + 5;
		// (int)(Gdx.graphics.getWidth()-boardrect.width)/2;

	}

	public void placeButtons() {
		Table table = new Table();
		table.setPosition(100, Gdx.graphics.getHeight() - 150);
		
		//table.set

		button_groupDoubles = new TextButton("Group Doubles", uiSkin);
		button_groupDoubles.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
			//	player.setDouble();
			
				return true;
			}
		});

		button_groupSeries = new TextButton("Group Series", uiSkin);
		button_groupSeries.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				
				return true;
			}
		});

		button_newDeck = new TextButton("New Deck", uiSkin);
		button_newDeck.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				startNewDeck();
				return true;
			}
		});

		
		table.add(button_groupDoubles).width(150).height(60);
		table.row();//starts a new row. like /n
		table.add(button_groupSeries).width(150).height(60);;
		table.row();
		table.add(button_newDeck).width(150).height(60);;
		stage.addActor(table);
	}

	public void startNewDeck() {
		// remove all previous actors from the stage.
		if (player != null)
			for (TileActor actor : player.getAllTileActors())
				stage.getRoot().removeActor(actor);
		
		player = new Board(pool, initialSize, boardsidepadding, rackWidth,
				boardrect); // creates the player's hand by
		// selecting 14 random tiles and
		// removing these tiles from the
		// rack.

		final Skin skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));

		for (int i = 0; i < player.rackSize(); i++) {
			TileActor actor = player.getActor(i);
			stage.addActor(actor);
			dragAndDrop.addSource(new Source(actor) {
				@Override
				public Payload dragStart(InputEvent event, float x, float y,
						int pointer) {
					Payload payload = new Payload();
					payload.setObject("Some payload!");
					TileActor temp = new TileActor((TileActor) getActor());
					payload.setDragActor(temp);

					Label validLabel = new Label("Valid move!", skin);
					validLabel.setColor(0, 1, 0, 1);
					payload.setValidDragActor(validLabel);

					Label invalidLabel = new Label("Invalid!", skin);
					invalidLabel.setColor(1, 0, 0, 1);
					payload.setInvalidDragActor(invalidLabel);

					return payload;
				}

				@Override
				public void dragStop(InputEvent event, float x, float y,
						int pointer, Target target) {

				}

			});

			dragAndDrop.addTarget(new Target(actor) {
				public boolean drag(Source source, Payload payload, float x,
						float y, int pointer) {
					getActor().setColor(Color.GREEN);
					return true;
				}

				public void reset(Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}

				public void drop(Source source, Payload payload, float x,
						float y, int pointer) {

					int movingTile = ((TileActor) source.getActor())
							.getPosition();

					boolean canMove = player.moveTileOnBoardToRight(movingTile,
							((TileActor) getActor()).getPosition());
					if (!canMove) {
						player.moveTileOnBoardToLeft(movingTile,
								((TileActor) getActor()).getPosition());
					}
				}
			});

		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		// if (player.size > 0)
		// game.font.draw(game.batch,
		// "Tiles delivered:" + player.printAllTiles(), 0, 480);
		game.batch.draw(board_texture, boardrect.x, boardrect.y);
		game.batch.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		rack_texture.dispose();
		board_texture.dispose();
		tileFontRed.dispose();
		tileFontBlue.dispose();
		tileFontYellow.dispose();
		tileFontGreen.dispose();
	}
	
	
}
