package es.deusto.ingenieria.aike.maze;


public class Board 
{
	
	private Tile[][] tiles;
	private Tile car;
	private Tile previousTile;
	private Tile flag;
	//private double pathCost = 0;
	
	


	public Board(Tile[][] tiles, Tile car, Tile flag) 
	{
		this.tiles = new Tile [tiles.length][tiles[0].length];
		this.tiles = tiles;
		this.car = car;
		this.previousTile = car;
		this.flag = flag;
		//this.pathCost = pathCost;
	}
	
	
	
	
	/*public double getPathCost() {
		return pathCost;
	}




	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}*/




	public Tile getCar() 
	{
		return car;
	}



	public void setCar(Tile car) 
	{
		this.car = car;
	}
	



	public Tile getFlag() {
		return flag;
	}




	public void setFlag(Tile flag) {
		this.flag = flag;
	}




	public Tile getPreviousTile() 
	{
		return previousTile;
	}



	public void setPreviousTile(Tile previousTile) 
	{
		this.previousTile = previousTile;
	}


	public void setTiles(Tile[][] tiles) 
	{
		this.tiles = tiles;
	}



	public Tile[][] getTiles()
	{
		return this.tiles;
	}
	
	
	public Tile getTile(int row, int column)
	{
		//the coordenates of the real life, on the array is minus 1 (starts in 0)
		return this.tiles[row-1][column-1];		
	}	
		
	
	public void moveCar(Tile tile) 
	{
		
		// Change the car tile and the previous will be the current one before changing it
		System.out.println("MOVECAR to " + tile.getRow() + "," + tile.getColumn());
		this.previousTile = this.car;
		this.car = tile;
	
	}
	
	public boolean equals(Object obj) 
	{
		//The boards are equals when the car position is the same and the tile is the same
		boolean equals = true;
		if (obj != null	&& obj instanceof Board) 
		{
			Tile tile1;
			Tile tile2;
			int i = 1;
			
			
			while(i <= tiles.length && equals)
			{
				int j = 1;
				while(j <= tiles[0].length && equals)
				{
					tile1 = this.getTile(i,j); // to getTile the coordenatez of the real life
					tile2 = ((Board)obj).getTile(i,j);  //what they pass me
					equals = tile1.equals(tile2); //the equals of tile
					j++;
				}
				i++;
			}
			
			if(equals == true) //the board is the same
				//the current and the previous
			{
				equals = (this.car.equals(((Board)obj).getCar())) && (this.previousTile.equals(((Board)obj).getPreviousTile()));// && (this.pathCost == ((Board)obj).getPathCost())); //llamara internamente al equals de Tile 
			}
		}
		
		return equals;
		
	}

	public String toString() 
	{
		// All the bidimensional array, we start in 0
		String str = "";		
		
		/*for(int i=0; i<this.tiles.length; i++) 
		{
			for(int j=0; j<this.tiles[0].length; j++) 
			{
				str += this.tiles[i][j] + " | "; //llamara internamente al toString de Tile
			}
			if(i != this.tiles.length-1)
				str += "\n";
		}
		str += "\n\n";*/
		str += this.car.toString();
		//str += "\n\n";
		return str ;
	}
	
	public Object clone() 
	{
		Tile[][] newBoard = new Tile[this.tiles.length][this.tiles[0].length];
		Tile newCar = (Tile) this.car.clone(); 
		Tile newFlag = (Tile) this.flag.clone();
		//Double cost = this.pathCost;

		
		for(int i=0; i<this.tiles.length; i++) 
		{
			for(int j=0; j<this.tiles[i].length; j++) 
			{
				newBoard[i][j] = (Tile)this.tiles[i][j].clone();				
			}
		}
		return new Board(this.tiles, newCar,newFlag);
	}
}