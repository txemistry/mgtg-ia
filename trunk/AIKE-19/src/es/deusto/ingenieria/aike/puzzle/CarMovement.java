package es.deusto.ingenieria.aike.puzzle;

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
	
	protected boolean isApplicable(State state) //mequeda mirar que no valla para atras y que no este fuera
	{
		Board board = (Board)state.getInformation();		
	
		switch (this.detination) 
		{
			case UP:
			{
				//cuando me puedo mover hacia arriba???
				//cuando la casilla de encima de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 
				
				
				Tile currentTile= board.getCar();
				
				if(board.getCar().getRow() > 0)
				{
				
					//significa que aunque me mueva una posicion hacia arriba, no me salgo del tablero
					//ahora compruebo que la casilla de encima no tiene una pared en su parte baja
					
					if(board.getTile(currentTile.getRow()-1, currentTile.getColumn()).isDown_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia arriba
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y si, me podria mover hacia arriba
								 return true;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y no me podria mover hacia arriba, solo izquierda o derecha
								return  false;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia arriba si las columnas de curretn y previous coinciden
							
							//la segunda parte de la comprobacion es para comprobar que no volvamos para atras
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
					//como me mueva para arriba, me salgo del tablero...
					return false;
				}
			}
			
			case DOWN:
			{
				//cuando me puedo mover hacia abajo???
				//cuando la casilla de debajo de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 
				
				
				Tile currentTile= board.getCar();
				
				if(board.getCar().getRow() < board.getTiles().length-1)
				{
				
					//significa que aunque me mueva una posicion hacia abajo, no me salgo del tablero
					//ahora compruebo que la casilla de debajo no tiene una pared en su parte superior
					
					if(board.getTile(currentTile.getRow()+1, currentTile.getColumn()).isUp_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia abajo
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y si, me podria mover hacia arriba
								 return true;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y no me podria mover hacia abajo, solo izquierda o derecha
								return  false;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia arriba si las columnas de curretn y previous coinciden
							
							//la segunda parte de la comprobacion es para comprobar que no volvamos para atras
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
					//como me mueva para abajo, me salgo del tablero...
					return false;
				}
			}
			case RIGHT:
			{
				//cuando me puedo mover hacia la derecha???
				//cuando la casilla a la derecha de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 
				
				
				Tile currentTile= board.getCar();
				
				if(board.getCar().getColumn() < board.getTiles()[0].length-1)
				{
				
					//significa que aunque me mueva una posicion hacia la derecha, no me salgo del tablero
					//ahora compruebo que la casilla de mi derecha no tiene una pared en su parte izquierda
					
					if(board.getTile(currentTile.getRow(), currentTile.getColumn()+1).isLeft_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia la derecha
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y no me podria mover a la derecha
								 return false;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y n me podria mover hacia la derecha
								return  true;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia la derecha si las filas de curretn y previous coinciden
							
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
					//como me mueva para la derecha, me salgo del tablero...
					return false;
				}
			}
			case LEFT:
			{
				//cuando me puedo mover hacia la izquierda???
				//cuando la casilla a la izuierda de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 
				
				
				Tile currentTile= board.getCar();
				
				if(board.getCar().getColumn() > 0)
				{
				
					//significa que aunque me mueva una posicion hacia la izquierda, no me salgo del tablero
					//ahora compruebo que la casilla de mi izquierda no tiene una pared en su parte derecha
					
					if(board.getTile(currentTile.getRow(), currentTile.getColumn()-1).isRight_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia la izquierda
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y no me podria mover a la izquierda
								 return false;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y n me podria mover hacia la izquierda
								return  true;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia la derecha si las filas de curretn y previous coinciden
							
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
					//como me mueva para la izquierda, me salgo del tablero...
					return false;
				}
			}
			default:
				return false;
		}
	}

	protected State effect(State state) //ME HE QUEDADO AQUI...
	{
		Board board = (Board)state.getInformation();
		Board newBoard = (Board)board.clone();
		Tile blank = newBoard.getBlank();		
		
		switch (this.detination) {
			case UP:
				newBoard.moveTile(blank, blank.y-1, blank.x);
				break;
			case DOWN:
				newBoard.moveTile(blank, blank.y+1, blank.x);
				break;
			case RIGHT:
				newBoard.moveTile(blank, blank.y, blank.x+1);
				break;
			case LEFT:
				newBoard.moveTile(blank, blank.y, blank.x-1);
				break;
		}

		return new State(newBoard);
	}
}