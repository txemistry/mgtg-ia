package es.deusto.ingenieria.aike.maze;


public class Board 
{
	
	private Tile[][] tiles;
	private Tile car;
	private Tile previousTile;
	

	public Board(int rows, int columns)
	{
		//nos creamos un tablero con las tiles vacias
		
		for(int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				tiles[i][j] = new Tile(i,j);
			}	
		}
		
		this.car = new Tile();
		this.previousTile = new Tile();
	}
	


	public Board(Tile[][] tiles, Tile car) 
	{
		this.tiles = tiles;
		this.car = car;
	}
	
	
	
	
	public Tile getCar() 
	{
		return car;
	}



	public void setCar(Tile car) 
	{
		this.car = car;
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
		return this.tiles[row][column];		
	}	
		
	
	public void moveCar(Tile tile) 
	{
		//lo que tenemos que hacer es cambiar la casilla del coche y la previous sera la actual del coche antes de cambiarlo
		
		this.previousTile = this.car;
		this.car.setRow(tile.getRow());
		this.car.setColumn(tile.getColumn());
	
	}
	
	public boolean equals(Object obj) 
	{
		//Cuando dos tableros son iguales??
		//cuando la posicion del cohe es igual
		//Cuando cada tipo de celda es igual (aqui se incluye la comprobacion de la bandera: coordenadas, tipo y paredes)
		boolean equals = true;
		if (obj != null	&& obj instanceof Board) 
		{
			Tile tile1;
			Tile tile2;
			int i = 0;
			
			while(i < tiles.length && equals)
			{
				int j = 0;
				while(j < tiles[i].length && equals)
				{
					tile1 = this.getTile(i,j); // la mia
					tile2 = ((Board)obj).getTile(i,j);  //la que me pasan
					equals = tile1.equals(tile2); //se llamara al equals de Tile
					j++;
				}
				i++;
			}
			
			if(equals == true) //significa que el tablero es el mismo y ahora hay que comprobar la posicion del coche
			{
				equals = this.car.equals(((Board)obj).getCar()); //llamara internamente al equals de Tile
				//mirara si sus coordenadas son iguales, si su tipo es igual y sis susparedes son las mismas
				//de hecho la unica que va a tener paredes es la bandera 
			}
		}
		
		return equals;
		
	}

	public String toString() 
	{
		String str = "(";		
		
		for(int i=0; i<this.tiles.length; i++) 
		{
			for(int j=0; j<this.tiles[0].length; j++) 
			{
				str += this.tiles[i][j] + "///"; //llamara internamente al toString de Tile
			}
			if(i != this.tiles.length-1)
				str += "\n";
		}
		str += ")";
		str += "\n\n";
		str += this.car.toString();
		str += "\n\n";
		return str ;
	}
	
	public Object clone() 
	{
		Tile[][] newBoard = new Tile[this.tiles.length][this.tiles[0].length];
		Tile newCar = (Tile) this.car.clone(); 

		for(int i=0; i<this.tiles.length; i++) 
		{
			for(int j=0; j<this.tiles[i].length; j++) 
			{
				newBoard[i][j] = (Tile)this.tiles[i][j].clone();				
			}
		}
		return new Board(newBoard, newCar);
	}
}