package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.ingenieria.search.Node;

public class ManhattanDistance extends EvaluationFunction 
{

	public double calculateG(Node nodo) 
	{
		return 0;
	}

	public double calculateH(Node nodo) 
	{
		Board currentBoard = (Board) nodo.getState().getInformation();	
		Tile currentTile = currentBoard.getCar();
		Tile flagTile = currentBoard.getFlag();
		
		int distancia = 0;
		distancia = distanciaManhattan(currentTile, flagTile);
		
		return distancia;
	}
	
	private int distanciaManhattan(Tile ficha1, Tile ficha2) 
	{
		return Math.abs(ficha1.x - ficha2.x) + Math.abs(ficha1.y - ficha2.y);
	}
}