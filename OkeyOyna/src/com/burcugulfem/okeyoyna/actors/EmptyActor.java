package com.burcugulfem.okeyoyna.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EmptyActor extends Actor{
	int position;

	 public EmptyActor(int actorX,int actorY,int width,int height,int position) {
		 this.position=position;
		 setPosition(actorX, actorY);
		 setBounds(actorX,actorY,width,height);
	}
	 
	 @Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
	 
	 public int getPosition()
	 {
		 return position;
	 }
}
