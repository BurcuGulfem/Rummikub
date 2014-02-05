package com.burcugulfem.okeyoyna.logic;

public class TileSet {
	Tile[] tileSet;
	final int setSize = 106;
	int currentSize;
	final int red = 0;
	final int blue = 1;
	final int yellow = 2;
	final int green = 3;
	final int joker = 4;
	
	public TileSet()
	{
		tileSet = new Tile[setSize];
		int times = 0;
		int tileNum = 0;

		for(int colour=red; colour<green+1; colour++)
		{
			for(int value=1; value<14; value++)
			{
				times = 0;
				do{
					tileSet[tileNum] = new Tile(value, colour, tileNum);
					tileNum++;
					times++;
				}while(times<2);
			}
		}

		for(int i=1; i<3; i++)
		{
			tileSet[tileNum] = new Tile(i, joker, tileNum);
			tileNum++;
		}
		currentSize=tileNum;
	}

	/**
	* gets a tile of a given index
	* @param index number of the tile to be returned
	* @return <code>Tile</code> the tile corresponding to the index
	*/
	public Tile getTile(int number)
	{
		return tileSet[number];
	}
	
	public Tile removeRandomTile()
	{
		if(currentSize==0)
		{
			return null;
		}
		else
		{
			int whichTile = (int)(Math.random()*currentSize);
			Tile returnTile=tileSet[whichTile];
			this.removeTile(whichTile);
			return returnTile;
		}
	}
	
	public void removeTile(int tileIndex)
	{
		for(int i=tileIndex; i<currentSize-1; i++)
		{
			tileSet[i] = tileSet[i+1];
		}
		currentSize--;
	}
}
