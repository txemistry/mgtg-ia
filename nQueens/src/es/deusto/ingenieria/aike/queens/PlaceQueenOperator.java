package es.deusto.ingenieria.aike.queens;

import java.util.ArrayList;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class PlaceQueenOperator extends Operator 
{

	
	private int column;
	
	public PlaceQueenOperator(int column) 
	{
		super("Place Queen into column number '" + column);
		this.column = column;
	}
	
	protected boolean isApplicable(State state) 
	{
		//Cuando puedo posicionar una reina en una columna???
		//Cuando existe en el estado que me han pasado, al menos una casilla dentro de la columna que me dan
		//en la que puedo posicionar la reina sin generar conflicto con las que ya habia puestas
		Board board = (Board)state.getInformation();		
		boolean applicable = false;
		
		
		//Cojo la columna que me han dado
		ArrayList<Tile> posibleDestinations = new ArrayList<Tile>();
		
		for(int i = 0; i < board.getTiles()[0].length; i++)
		{
			Tile tile = board.getTile(i, column);
			if(tile.isReina() == false)
			{
				//la metemos en la lista de posibles destinos
				posibleDestinations.add(tile);
			}
			else
			{
				//seria mover una reina a una posicion en la que ya esta y no tiene sentido...
			}
		}
		

		
		//Ahora que ya tengo las celdas posibles y accesibles de forma mas facil, tengo qeu comprobar una por una
		//exepto en la que esta la reina, si estuviese, si crea algun conflicto con las reinas que estan en la 
		//lista de placedQueens
		int i = 0;
		while(i < posibleDestinations.size() && !applicable)
		{
			Tile currentTile = posibleDestinations.get(i);
			
			//primero comprobamos que la fila de la posible celda actual no entra en conflicto con las filas de placedQueens
			//enc = false;   enc ya era falso
			int j = 0;
			boolean enc = false;
			while(j < board.getPlacedQueens().size() && !enc)
			{

				if(board.getPlacedQueens().get(j).getRow() == currentTile.getRow())
				{
					//la poscion de la tile actual, entra en conflicto con otra reina
					enc = true;
					
				}
				j++;
			}
			
			if(enc == true)
			{
				//ha encontrado conflicto con otra fila asi que pruebo otra celda
				i++;
				continue;
			}
			else
			{
				//hay que comprobar que no existan conflictos con las diagonales
				//enc ya era false
				j = 0;
				while(j < board.getPlacedQueens().size() && !enc)
				{
					int absFila;
					int absColumna;
					
					absFila = Math.abs(currentTile.getRow() - board.getPlacedQueens().get(j).getRow());
					absColumna = Math.abs(currentTile.getColumn() - board.getPlacedQueens().get(j).getColumn());
					if(absFila == absColumna)
					{
						//Son diagonales y por lo tanto...
						enc = true;
					}
					//Si son distintas no son diagonales
					j++;
				}
				if(enc == true)
				{
					//conflicto con diagonales--- a probar otra celda
					i++;
					continue;
				}
				else
				{
					//el destination que me han pasdo no esntra en conflicto con ninguna reina posicionada anteriormente 
					//por lo que puede ser valido
					applicable = true;
					
				}
			}
		}
		
		return applicable;
	}

	protected State effect(State state) 
	{
		//cual es el resultado de aplicar ese operador?
		//una nueva distribucion en el tablero
		//y un cambio de la reina si ya estaba posicionada en esa columna
		//pero a que fila??? a la primera que encuentre que no le crea un conflicto
		
		Board board = (Board)state.getInformation(); //cogemos la situacion actual del tablero
		Board newBoard = (Board)board.clone(); //lo clonamos (situacion del tablero + lista de reinas posicionadas)
		
		//busco una tile en la columna que me han pasado si hubiese alguna con una reina ya colocada
		
		
		Tile tileConReina = null;
		boolean enc = false;
		int i = 0;
		
		while (i < board.getTiles()[0].length && !enc)
		{
			Tile tile = board.getTile(i, column);
			if(tile.isReina() == true)
			{
					enc = true;
					tileConReina = (Tile)tile.clone();
			}
			i++;
		}	

		
		//Ahora me tengo que recorrer la columna y quedarme con la primera celda
		//que encuentre que no sea en la que esta la reina, si es que hay y que no me cree conflictos
		//primero busco la primera celda optima
		
		boolean tileChosen = false;
		i = 0;
		while(i < board.getTiles()[0].length && !tileChosen)
		{
			Tile currentTile = board.getTile(i, column);
			//comprobamos que esa tile no tenga una reina
			
			if(!currentTile.isReina())
			{
				//primero comprobamos que la fila de la posible celda actual no entra en conflicto con las filas de placedQueens
				//enc = false;   enc ya era falso
				int j = 0;
				boolean enc2 = false;
				while(j < board.getPlacedQueens().size() && !enc2)
				{

					if(board.getPlacedQueens().get(j).getRow() == currentTile.getRow())
					{
						//la poscion de la tile actual, entra en conflicto con otra reina
						enc2 = true;
						
					}
					j++;
				}
				
				if(enc2 == true)
				{
					//se que hay un conflicto con la fila, no me detengo a mirar las diagonales
					i++;
					continue;
				}
				else
				{
					//hay que comprobar que no existan conflictos con las diagonales
					//enc ya era false
					j = 0;
					while(j < board.getPlacedQueens().size() && !enc2)
					{
						int absFila;
						int absColumna;
						
						absFila = Math.abs(currentTile.getRow() - board.getPlacedQueens().get(j).getRow());
						absColumna = Math.abs(currentTile.getColumn() - board.getPlacedQueens().get(j).getColumn());
						if(absFila == absColumna)
						{
							//Son diagonales y por lo tanto...
							enc2 = true;
						}
						//Si son distintas no son diagonales
						j++;
					}
					if(enc2 == true)
					{
						i++;
						continue; //ha habido conflicto con las diagonales, por lo que la current no es una buena opcion
					}
					else
					{
						//la celda current no tiene conflictos con niguna y ademas no es una reina por lo que hago lo que tenga que hacer
						tileChosen = true;
						

						if (enc == true) //habia una reina posicionada en la columna por lo que tengo que eliminarla y posicionalr en 
							//la tileChosen
						{
							//le quito la reina a la que la tenia y se la pongo en la nueva 
							newBoard.getTile(tileConReina.getRow(), tileConReina.getColumn()).setReina(false);
							//tendria que borrar diche tile de la lista de placedqueens
							newBoard.getPlacedQueens().remove(tileConReina);
						}
						
						//tenga o no tenga reina esto lo tengo que hacer
						newBoard.getTile(i, column).setReina(true); //modifico la propiedad de reina del Tile que me dan en destination
						newBoard.getPlacedQueens().add(new Tile(i, column, true)); //a–ado esa tile a la lista de reinas posicionadas
					}
				}
			}
			else
			{
				i++;
			}
			
		}
		return new State(newBoard);
	}
}