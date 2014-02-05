package com.burcugulfem.okeyoyna.logic;

public class Tile{
	int tileNumber; //number of tile from 1 to 106
	int tileValue; //value of tile from 1 to 13
	int tileColor;
	
	final int red = 0;
	final int blue = 1;
	final int yellow = 2;
	final int green = 3;
	final int joker = 4;
	
	public Tile()
	{
		this.tileNumber=0;
		this.tileValue=0;
		this.tileColor=0;
	}
	
	public Tile(int tileValue, int tileColor,int tileNumber) {
		this.tileNumber=tileNumber;
		this.tileValue=tileValue;
		this.tileColor=tileColor;
	}

	public int getTileNumber() {
		return tileNumber;
	}

	public int getTileValue() {
		return tileValue;
	}

	public int getTileColor() {
		return tileColor;
	}

	public void setTileNumber(int tileNumber) {
		this.tileNumber = tileNumber;
	}

	public void setTileValue(int tileValue) {
		this.tileValue = tileValue;
	}

	public void setTileColor(int tileColor) {
		this.tileColor = tileColor;
	}
}
