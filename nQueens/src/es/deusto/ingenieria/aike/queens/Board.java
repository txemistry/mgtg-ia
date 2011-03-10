package es.deusto.ingenieria.aike.queens;

import java.util.ArrayList;


public class Board 
{
	
	private Tile[][] tiles;
	private ArrayList<Tile> placedQueens;
	

	public Board(int number)
	{
		
		tiles = new Tile[number][number];

		for (int i=0; i<number; i++) 
		{
			for(int j=0; j<number; j++)
			{
				tiles[i][j] = new Tile(i,j,false);
			}
		}
		placedQueens = new ArrayList<Tile>();
	}


	public Board(Tile[][] tiles, ArrayList<Tile> placedQueens) 
	{
		this.tiles = tiles;
		this.placedQueens = placedQueens;
	}
	
	public Tile[][] getTiles()
	{
		return this.tiles;
	}
	
	
	
	public ArrayList<Tile> getPlacedQueens() 
	{
		return placedQueens;
	}


	public void setPlacedQueens(ArrayList<Tile> placedQueens) 
	{
		this.placedQueens = placedQueens;
	}


	public Tile getTile(int row, int column)
	{
		return this.tiles[row][column];		
	}	
		
	public void placeQueen(int row, int column)
	{
		
		//change the "reina" property of the Tile (row, column) to true.
		//Furthermore, that queen hast to be added to the placedQueens list
		
		this.tiles[row][column].setReina(true);
		this.placedQueens.add(this.tiles[row][column]);
	}
	
	
	
	public boolean equals(Object obj) 
	//When are two boards equals?.. when they have the same placed queens 
	//it means, when its placedQueens list are equal
	
	{
		if (obj != null	&& obj instanceof Board  ) 
		{
			if(((Board)obj).placedQueens.equals(this.placedQueens))//it will invoke equals method of Tile class
			{
				return true;
			}
		}
		return false;
	
	}

	public String toString() 
	{
		String str = "(";		
		
		for(int i=0; i<this.tiles.length; i++) 
		{
			for(int j=0; j<this.tiles[0].length; j++) 
			{
				str += this.tiles[i][j] + "///"; //it will invoke toString method of Tile class
			}
			if(i != this.tiles.length-1)
				str += "\n";
		}
		str += ")";
		str += "\n\n";
		str += this.placedQueens.toString();
		str += "\n\n";
		return str ;
	}
	
	public Object clone() 
	{
		Tile[][] newBoard = new Tile[this.tiles.length][this.tiles[0].length];
		@SuppressWarnings("unchecked")
		ArrayList<Tile> newPlacedQueens = (ArrayList<Tile>) this.placedQueens.clone(); //it will invoke clone method of Tile class

		for(int i=0; i<this.tiles.length; i++) 
		{
			for(int j=0; j<this.tiles[0].length; j++) 
			{
				newBoard[i][j] = (Tile)this.tiles[i][j].clone();				
			}
		}
		return new Board(newBoard, newPlacedQueens);
	}
}