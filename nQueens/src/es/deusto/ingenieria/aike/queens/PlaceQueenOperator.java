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
		//when can i placed a queen in a column? 
		//when in the state passed as an argument, there is at least one tile inside that column where
		//I can place a queen without faced any conflicts between the other placed queens
		
		Board board = (Board)state.getInformation();		
		boolean applicable = false;
		
		
		//I Obtain the column where i want to place a queen
		ArrayList<Tile> posibleDestinations = new ArrayList<Tile>();
		
		for(int i = 0; i < board.getTiles()[0].length; i++)
		{
			Tile tile = board.getTile(i, column);
			if(tile.isReina() == false)
			{
				//we add that tile to the posibleDestinations list
				posibleDestinations.add(tile);
			}
			else
			{
				//we will be in the situation of moving a placed queen to the same Tile and it does not make any sense
			}
		}
		

		
		//now i have to chek if the possible destinations, create some conflicts with the placed queens
		int i = 0;
		while(i < posibleDestinations.size() && !applicable)
		{
			Tile currentTile = posibleDestinations.get(i);
		
			//fisrt, we chek if there is some conflict inside the same row
			int j = 0;
			boolean enc = false;
			while(j < board.getPlacedQueens().size() && !enc)
			{

				if(board.getPlacedQueens().get(j).getRow() == currentTile.getRow())
				{
					//there's a conflict
					enc = true;
					
				}
				j++;
			}
			
			if(enc == true)
			{
				//I have found a conflict with other row, sow i have to continue with the next Tile
				i++;
				continue;
			}
			else
			{
				//now i check for conflicts in the diagonals
				j = 0;
				while(j < board.getPlacedQueens().size() && !enc)
				{
					int absFila;
					int absColumna;
					
					absFila = Math.abs(currentTile.getRow() - board.getPlacedQueens().get(j).getRow());
					absColumna = Math.abs(currentTile.getColumn() - board.getPlacedQueens().get(j).getColumn());
					if(absFila == absColumna)
					{
						//there's a conflict...
						enc = true;
					}
					//if they are distinct, there's no problem
					j++;
				}
				if(enc == true)
				{
					//I have found a problem...
					i++;
					continue;
				}
				else
				{
					//There's no conflict with the Tile i'm checking
					applicable = true;
					
				}
			}
		}
		
		return applicable;
	}

	protected State effect(State state) 
	{
		//which is the result of applying that operator?
		//a new distribution of the board,
		//and a change of the position of the queen, if there just was a previous queen in that column
		//but,... what row do I choose?, the fisrt that does not make any problem with the others
		
		Board board = (Board)state.getInformation(); //actual board situation
		Board newBoard = (Board)board.clone(); //we clone it
		
		//I check if there is a queen placed in the column where I have to place the new queen
		
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

		

		
		//now I have to find the best first Tile to place it
		
		boolean tileChosen = false;
		i = 0;
		while(i < board.getTiles()[0].length && !tileChosen)
		{
			Tile currentTile = board.getTile(i, column);
			//We check if that Tile is a Queen
			
			if(!currentTile.isReina())
			{
				//cheking rows problems
				int j = 0;
				boolean enc2 = false;
				while(j < board.getPlacedQueens().size() && !enc2)
				{

					if(board.getPlacedQueens().get(j).getRow() == currentTile.getRow())
					{
						//there's a problem
						enc2 = true;
						
					}
					j++;
				}
				
				if(enc2 == true)
				{
					//there's a problem
					i++;
					continue;
				}
				else
				{
					//we check diagonal problems
					j = 0;
					while(j < board.getPlacedQueens().size() && !enc2)
					{
						int absFila;
						int absColumna;
						
						absFila = Math.abs(currentTile.getRow() - board.getPlacedQueens().get(j).getRow());
						absColumna = Math.abs(currentTile.getColumn() - board.getPlacedQueens().get(j).getColumn());
						if(absFila == absColumna)
						{
							//there's a problem
							enc2 = true;
						}
						//if they are distinct there's no problem
						j++;
					}
					if(enc2 == true)
					{
						i++;
						continue; //There has been a problem
					}
					else
					{
						//Current Tile is not a Queen and there's no problem with other placedQueens, so is a suitable Tile
						//to place a new one
						tileChosen = true;
						

						if (enc == true) //there was an old place queen in that column, so I have to update
							//the placedQueens list
						{
							//I change the tile
							newBoard.getTile(tileConReina.getRow(), tileConReina.getColumn()).setReina(false);
							//I remove that tile from the list
							newBoard.getPlacedQueens().remove(tileConReina);
						}
						
						//anyway, queen or no queen...
						newBoard.getTile(i, column).setReina(true); //I change the suitable Tile
						newBoard.getPlacedQueens().add(new Tile(i, column, true)); //I add that tile to the placedQueen list
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