package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class CarMovement extends Operator {

	public static enum Destination 
	{
		UP,
		DOWN,
		RIGHT,
		LEFT;
	}
	
	private Destination detination;
	
	public CarMovement(Destination detination) 
	{
		super("Move car '" + detination.toString() + "'");
		this.detination = detination;
	}
	
	protected boolean isApplicable(State state) 
	{
		Board board = (Board)state.getInformation();	
		Tile currentTile= board.getCar();
		
	
		switch (this.detination) 
		{
			case UP:
			{
				
				//We move up when the up tile is in the board and depending if the current tile is an O or a X
				
				
				//We compare with the real life
				if((currentTile.getRow() > 1) && (currentTile.getColumn() >= 1) && (currentTile.getColumn() <= board.getTiles()[0].length)) 
				{
								
					//The second and the third conditions are for not doing the first move out of the board
				
					//We have to see if there is a wall too
					if(board.getTile(currentTile.getRow()-1, currentTile.getColumn()).isDown_wall())
					{
						//It has a wall, we cant move
						return false;
					}
					else
					{
						//No wall
						Tile previousTile = board.getPreviousTile();
						
						
						if(currentTile.getType().equals("X"))
						{
							
							//I am in an X so we have to move 90º, seeing the column and the rows
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//I am in the same row so i have moved of column, I can move up
								 return true;
							}
							else
							{
								//I can move right or left
								return  false;
							}
						}
						else
						{
							//it is an O
							//If are the same we move
							
							//The second part of the condition is for not going back
							if((currentTile.getColumn() == previousTile.getColumn()) && (currentTile.getRow()-1 != previousTile.getRow()))
							{
								return  true;
							}
							else
							{
								return false;
							}
						}
					}
				}
				else
				{
					//If I move up I am out of the board
					return false;
				}
			}
			
			case DOWN:
			{				
				
				if((currentTile.getRow() < board.getTiles().length) && (currentTile.getColumn() >= 1) && (currentTile.getColumn() <= board.getTiles()[0].length))
				{
					
					if(board.getTile(currentTile.getRow()+1, currentTile.getColumn()).isUp_wall())
					{
						return false;
					}
					else
					{
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								 return true;
							}
							else
							{
								return  false;
							}
						}
						else
						{
							if((currentTile.getColumn() == previousTile.getColumn()) && (currentTile.getRow()+1 != previousTile.getRow()))
							{
								return  true;
							}
							else
							{
								return false;
							}
						}
					}
				}
				else
				{
					return false;
				}
			}
			case RIGHT:
			{
				
				if((currentTile.getColumn() < board.getTiles()[0].length) && (currentTile.getRow() >= 1) && (currentTile.getRow() <= board.getTiles().length))
				{
					
					if(board.getTile(currentTile.getRow(), currentTile.getColumn()+1).isLeft_wall())
					{
						return false;
					}
					else
					{
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{

							
							if(currentTile.getRow() == previousTile.getRow())
							{
								 return false;
							}
							else
							{
								System.out.println("RIGHT true");
								return  true;
							}
						}
						else
						{
							
							if((currentTile.getRow() == previousTile.getRow()) && (currentTile.getColumn()+1 != previousTile.getColumn()))
							{
								return  true;
							}
							else
							{
								return false;
							}
						}
					}
				}
				else
				{
					return false;
				}
			}
			case LEFT:
			{

				
				if((currentTile.getColumn() > 1) && (currentTile.getRow() >= 1) && (currentTile.getRow() <= board.getTiles().length))
				{

					if(board.getTile(currentTile.getRow(), currentTile.getColumn()-1).isRight_wall())
					{
						return false;
					}
					else
					{
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							
							if(currentTile.getRow() == previousTile.getRow())
							{

								 return false;
							}
							else
							{
								return  true;
							}
						}
						else
						{
							
							if((currentTile.getRow() == previousTile.getRow()) && (currentTile.getColumn()-1 != previousTile.getColumn()))
							{
								return  true;
							}
							else
							{
								return false;
							}
						}
					}
				}
				else
				{
					return false;
				}
			}
			default:
				return false;
		}
	}

	protected State effect(State state)
	{
		Board board = (Board)state.getInformation(); //We take the current situation
		Board newBoard = (Board)board.clone();	//we clone the actal situation
		
		Tile currentTile= board.getCar();
		
		switch (this.detination) 
		{
			case UP:
			{
				//We want to move the car to the up tile, so we take it
				Tile destinationTile = newBoard.getTile((currentTile.getRow()-1), currentTile.getColumn());
				newBoard.moveCar(destinationTile);
				break;
			}
			case DOWN:
			{
				Tile destinationTile = newBoard.getTile(currentTile.getRow()+1, currentTile.getColumn());
				newBoard.moveCar(destinationTile);
				
				break;
			}
			case RIGHT:
			{
				Tile destinationTile = newBoard.getTile(currentTile.getRow(), currentTile.getColumn()+1);
				newBoard.moveCar(destinationTile);
				break;
			}
			case LEFT:
			{
				Tile destinationTile = newBoard.getTile(currentTile.getRow(), currentTile.getColumn()-1);
				newBoard.moveCar(destinationTile);
				break;
			}

		}

		//newBoard.setPathCost(board.getPathCost() + this.getCost());
		return new State(newBoard);
	}
}