package com.burcugulfem.okeyoyna.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.burcugulfem.okeyoyna.GameScreen;
import com.burcugulfem.okeyoyna.logic.Tile;

public class TileActor extends Actor{
	 Texture texture =  new Texture(Gdx.files.internal("tile_small.png"));
	 Tile tile;//can be null
	 int positionOnBoard;
	 
	 
	 public TileActor(Tile tile,int actorX,int actorY,int position) {
		 this.tile=tile;
		 this.positionOnBoard=position;
		 setPosition(actorX, actorY);
		 setOrigin(actorX, actorY);
		 setBounds(actorX,actorY,texture.getWidth(),texture.getHeight());
	}
	 public void setTile(Tile tile)
	 {
		 this.tile=tile;
	 }

	 
	 public TileActor(TileActor actor)
	 {
		 this.tile=actor.tile;
		 setPosition(actor.getX(), actor.getY());
		 setBounds(actor.getX(),actor.getY(),texture.getWidth(),texture.getHeight());
	 }	 
	 
	 public Tile getTile()
	 {
		 return this.tile;
	 }
	 
	 
	 public int getPosition()
	 {
		 return this.positionOnBoard;
	 }
	 
	 public void setPosition(int newPosition)
	 {
		 this.positionOnBoard=newPosition;
	 }
	 

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {		
		if(tile!=null){
		batch.draw(texture, getX(), getY());
		float textX=getX()+texture.getWidth()/2- GameScreen.tileFontRed.getBounds(this.tile.getTileValue()+"").width/2;
		float textY=getY()+texture.getHeight()- GameScreen.tileFontRed.getBounds(this.tile.getTileValue()+"").height/2;
		getFont().draw(batch, ""+this.tile.getTileValue(),textX,textY);
		}
		}
		
		private BitmapFont getFont()
		{
			switch(this.tile.getTileColor())
			{
			case 0:return GameScreen.tileFontRed;
			case 1:return  GameScreen.tileFontBlue;
			case 2:return GameScreen.tileFontYellow;
			case 3:return GameScreen.tileFontGreen;
			default:return GameScreen.tileFontRed;
			}
		
		
	}

	
	
}
