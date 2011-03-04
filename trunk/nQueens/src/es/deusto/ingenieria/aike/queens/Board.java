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
		//cambiar la propiedad de reina? de la tile correspondiente a true
		//a–adir esa reina a la lista de reinas colocadas
		
		this.tiles[row][column].setReina(true);
		this.placedQueens.add(this.tiles[row][column]);
	}
	
	
	
	public boolean equals(Object obj) 
	//Cuando son iguales dos tableros? cuando tienen las mismas reinas colocadas
	//es decir, cuando su array de placequeens es igual
	{
		if (obj != null	&& obj instanceof Board  ) 
		{
			if(((Board)obj).placedQueens.equals(this.placedQueens))//lamara internamente al equals de Tile
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
				str += this.tiles[i][j] + "///"; //llamar‡ internamente al toString de Tile
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
		ArrayList<Tile> newPlacedQueens = (ArrayList<Tile>) this.placedQueens.clone(); //llamar‡ internamente al clone de Tile

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