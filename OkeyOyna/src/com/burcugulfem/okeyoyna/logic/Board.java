package com.burcugulfem.okeyoyna.logic;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.burcugulfem.okeyoyna.actors.TileActor;

public class Board {
	public int size = 0,groupsize=0;
	Tile [] tiles;
	TileActor[] tileActors;
	int boardsidepadding,rackWidth;
	Rectangle boardrect;
	Group[] tileGroups;
	
	public Board(TileSet the_pool, int initialSize,int boardsidepadding,int rackWidth,Rectangle boardrect)
	{
		tileActors=new TileActor[30];
		tileGroups=new Group[7];
		tiles=new Tile[initialSize];
		size = 0;
		this.rackWidth=rackWidth;
		this.boardrect=boardrect;
		this.boardsidepadding=boardsidepadding;

		for(int i=0; i<initialSize; i++)
		{
			Tile addingTile = the_pool.removeRandomTile();
			this.addTile(addingTile);
		}		
		putTilesToInitialPositions();
	}
	
	public TileActor[] getAllTileActors()
	{
		return this.tileActors;
	}
	
	public void addTile(Tile tile)
	{
		size++;
		tiles[size-1] = tile;
		return;
	}

	
	public int rackSize()
	{
		return tileActors.length;
	}
	
	public void putTilesToInitialPositions()
	{
		for(int i=0;i<tileActors.length;i++)
		{
			int tilerectX;
			int tilerectY;
			if (i < 15) {
				
				// the top row of the board
				tilerectX = boardsidepadding + ((i) * rackWidth);
				tilerectY = (int) boardrect.y + (int) boardrect.height / 2
						+ 35;//35 is the top row padding	
			} else {
	
				// the bottom row of the board
				tilerectX = boardsidepadding + ((i-15) * rackWidth);
				tilerectY = (int) boardrect.y + 15;//15 is the padding
			}
			
			if(i<size)
			{
				TileActor newActor=new TileActor(tiles[i],tilerectX,tilerectY,i);
				tileActors[i]=newActor;
			}
			else
			{
				TileActor newActor=new TileActor(null,tilerectX,tilerectY,i);
				tileActors[i]=newActor;
			}
		}
	}
	
	public Vector2 getActorLocation(int i)
	{
		
		int tilerectX;
		int tilerectY;
		if (i < 15) {
			
			// the top row of the board
			tilerectX = boardsidepadding + ((i) * rackWidth);
			tilerectY = (int) boardrect.y + (int) boardrect.height / 2
					+ 35;//35 is the top row padding	
		} else {

			// the bottom row of the board
			tilerectX = boardsidepadding + ((i-15) * rackWidth);
			tilerectY = (int) boardrect.y + 15;//15 is the padding
		}
		return new Vector2(tilerectX,tilerectY);
	}
	
	
	/**
	* removes a given tile by index
	*/
	public void removeTile(int tileIndex)
	{
		for(int i=tileIndex; i<size-1; i++)
		{
			tiles[i] = tiles[i+1];
		}
		size--;
	}
	
	public String printAllTiles()
	{
		String s="";
		for(int i=0;i<size;i++)
		{
			//s+=i+";";
			s+=tiles[i].tileNumber+";";
		}
	return s;
	}
	
	public TileActor getActor(int i)
	{
		return tileActors[i];
	}

	
	public int getTilePosition(Tile t)
	{
		if(t==null)return -1;
		for(int i=0;i<tiles.length;i++)
		{
			if(tiles[i].getTileNumber()==t.getTileNumber())return i;
		}
		return -1;
	}
	
	public boolean moveTileOnBoardToRight(int from, int to)
	{
		//move tile if the location is empty
		if(tileActors[to].getTile()==null)
		{
			tileActors[to].setTile(tileActors[from].getTile());		
			tileActors[from].setTile(null);
			return true;
			
		}
		//else move target tile to right
		else if(to+1<tileActors.length)
		{
			boolean canMoveRight=moveTileOnBoardToRight(to,to+1);
			if(canMoveRight)
				{
				moveTileOnBoardToRight(from,to);
				return true;
				}
		}
		return false;	
	}
	
	public boolean moveTileOnBoardToLeft(int from, int to)
	{
		//move tile if the location is empty
		if(tileActors[to].getTile()==null)
		{
			tileActors[to].setTile(tileActors[from].getTile());		
			tileActors[from].setTile(null);
			return true;
			
		}
		//else move target tile to left
		else if(to-1>0)
		{
			boolean canMoveLeft=moveTileOnBoardToLeft(to,to-1);
			if(canMoveLeft)
				{
				moveTileOnBoardToLeft(from,to);
				return true;
				}
		}
		return false;	
	}
	
	public Group[] setDouble()
	{
		tileGroups=new Group[30];
		groupsize=0;
		HashMap<Tile,TileActor> doubleTest=new HashMap<Tile, TileActor>();
		
		for (TileActor tileactor : tileActors)
		{
		  if (tileactor.getTile()!=null)
			  {
			  if(doubleTest.containsKey(tileactor.getTile())) {
				  /* Found a double */ 
				  tileGroups[groupsize]=new Group();
				  tileGroups[groupsize].addActor(doubleTest.get(tileactor.getTile()));
				  tileGroups[groupsize].addActor(tileactor);
				  groupsize++;
			  }
			  doubleTest.put(tileactor.getTile(), tileactor);
			  }		 
		}
		return tileGroups;		
	}
	
	public Group[] setSeries()
	{
		HashMap<Integer, HashSet<Integer>> groupTest = new HashMap<Integer, HashSet<Integer>>();
		HashMap<Integer, boolean[]> straightTest = new HashMap<Integer, boolean[]>();
//		for (TileActor tile : tileActors)
//		{
//		  groupTest[tile.Number].Add(tile.Color);
//		  straightTest[tile.Color][tile.Number] = true;
//		}
//		for(int number : groupTest.keySet())
//		{
//		  HashSet<Tile> group = groupTest[number];
//		  if (group.size() >= 3) { /* Found a group */ }
//		}
//		for (int color : straightTest.keySet())
//		{
//		  HashSet<Integer> straight = straightTest[color];
//		  int consecutive = 0;
//		  for (int i = 1; i < HIGHEST_NUMBER; ++i)
//		  {
//		    if (straight[i-1] && straight[i])
//		      consecutive++;
//		    else
//		      consecutive = 0;
//
//		    if (consecutive >= 3) { /* Found a straight. */ }
//		  }
//		}
		return tileGroups;
	}
	
}
