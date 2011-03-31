package es.deusto.ingenieria.aike.maze;


public class Board 
{
	
	private Tile[][] tiles;
	private Tile car;
	private Tile previousTile;
	private Tile flag;
	private double pathCost = 0;
	
	


	public Board(Tile[][] tiles, Tile car, Tile flag, double pathCost) 
	{
		this.tiles = new Tile [tiles.length][tiles[0].length];
		this.tiles = tiles;
		this.car = car;
		this.previousTile = car;
		this.flag = flag;
		this.pathCost = pathCost;
	}
	
	
	
	
	public double getPathCost() {
		return pathCost;
	}




	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}




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
		//las coordenadas que se me pasan aqui son de la vida real, por lo que en el array ser�n una unidad menos
		return this.tiles[row-1][column-1];		
	}	
		
	
	public void moveCar(Tile tile) 
	{
		//lo que tenemos que hacer es cambiar la casilla del coche y la previous sera la actual del coche antes de cambiarlo
		
		System.out.println("MOVECAR a " + tile.getRow() + "," + tile.getColumn());
		this.previousTile = this.car;
		this.car = tile;
	
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
			int i = 1;
			
			
			while(i <= tiles.length && equals)
			{
				int j = 1;
				while(j <= tiles[0].length && equals)
				{
					tile1 = this.getTile(i,j); // a getTile se le pasan coordenadas de la vida real por eso empezamos en 1
					tile2 = ((Board)obj).getTile(i,j);  //la que me pasan
					equals = tile1.equals(tile2); //se llamara al equals de Tile
					j++;
				}
				i++;
			}
			
			if(equals == true) //significa que el tablero es el mismo y ahora hay que comprobar la posicion del coche
				//actual y la previous 
			{
				equals = (this.car.equals(((Board)obj).getCar())) && (this.previousTile.equals(((Board)obj).getPreviousTile()) && (this.pathCost == ((Board)obj).getPathCost())); //llamara internamente al equals de Tile
				//mirara si sus coordenadas son iguales, si su tipo es igual y sis susparedes son las mismas
				//de hecho la unica que va a tener paredes es la bandera 
			}
		}
		
		return equals;
		
	}

	public String toString() 
	{
		//me recorro todo el array bidimensional y por eso empezamos en 0
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
		Double cost = this.pathCost;

		
		//como me voy a recorrer el array bidemensional , no necesito qeu la longitud
		//sea hasta -1 porque empiezo en 0. Asi si la longitud es 5; va de 0 a 4
		for(int i=0; i<this.tiles.length; i++) 
		{
			for(int j=0; j<this.tiles[i].length; j++) 
			{
				newBoard[i][j] = (Tile)this.tiles[i][j].clone();				
			}
		}
		return new Board(this.tiles, newCar,newFlag, cost);
	}
}