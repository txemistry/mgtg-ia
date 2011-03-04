package es.deusto.ingenieria.aike.queens;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class PlaceQueenOperator extends Operator 
{

	
	private Tile destination;
	
	public PlaceQueenOperator(Tile destination) 
	{
		super("Place Queen into tile '" + destination.toString() + "'");
		this.destination = destination;
	}
	
	protected boolean isApplicable(State state) 
	{
		//Cuando puedo posicionar una reina en una celda???
		//Cuando no se ataque con otras ya puestas y antes de todo siempre y cuando no se hallan colocado ya todas las reinas
		Board board = (Board)state.getInformation();		
	
				//la columna esta vacia por lo que habra que comprobar que el destination que me han dado
				//no cree conflictos con otras reinas ya posicionadas
				
				//primero comprobamos que la fila que nos han pasado en el destination, no entra en conflicto con las filas de placedQueens
				//enc = false;   enc ya era falso
				int i = 0;
				boolean enc = false;
				while(i < board.getPlacedQueens().size() && !enc)
				{

					if(board.getPlacedQueens().get(i).getRow() == destination.getRow())
					{
						//la poscion que me han pasado entra en conflicto con otra reina
						//devolverŽ false
						enc = true;
					}
					i++;
				}
				
				if(enc == true)
				{
					//ha encontrado conflicto con otra fila
					return false;
				}
				else
				{
					//hay que comprobar que no existan conflictos con las diagonales
					//enc ya era false
					i = 0;
					while(i < board.getPlacedQueens().size() && !enc)
					{
						int absFila;
						int absColumna;
						
						absFila = Math.abs(destination.getRow() - board.getPlacedQueens().get(i).getRow());
						absColumna = Math.abs(destination.getColumn() - board.getPlacedQueens().get(i).getColumn());
						if(absFila == absColumna)
						{
							//Son diagonales y por lo tanto...
							enc = true;
						}
						//Si son distintas no son diagonales
						i++;
					}
					if(enc == true)
					{
						return false;
					}
					else
					{
						//el destination que me han pasdo no esntra en conflicto con ninguna reina posicionada anteriormente
						return true;
					}
				}
			
	
	}

	protected State effect(State state) 
	{
		//cual es el resultado de aplicar ese operador?
		//una nueva distribucion en el tablero
		//y un cambio de la reina si ya estaba posicionada en esa columna
		
		Board board = (Board)state.getInformation(); //cogemos la situacion actual del tablero
		Board newBoard = (Board)board.clone(); //lo clonamos (situacion del tablero + lista de reinas posicionadas)
		
		//busco una tile en la columna que me han pasado si hubiese alguna con una reina ya colocada
		
		Tile tileConReina = null;
		boolean enc = false;
		int i = 0;
		
		while (i < board.getTiles()[destination.getColumn()].length && !enc)
		{
			if(board.getTiles()[i][destination.getColumn()].isReina())
			{
				tileConReina = board.getTiles()[i][destination.getColumn()];
				enc = true;
			}
			i++;
		}
		
		if (enc == true)
		{
			//le quito la reina a la que la tenia y se la pongo en la nueva destination
			newBoard.getTile(tileConReina.getRow(), tileConReina.getColumn()).setReina(false);
			//tendria que borrar diche tile de la lista de placedqueens
			newBoard.getPlacedQueens().remove(tileConReina);
		}
		newBoard.getTile(destination.getRow(), destination.getColumn()).setReina(true); //modifico la propiedad de reina del Tile que me dan en destination
		newBoard.getPlacedQueens().add(new Tile(destination.getRow(), destination.getColumn(), true)); //a–ado esa tile a la lista de reinas posicionadas

		return new State(newBoard);
	}
}