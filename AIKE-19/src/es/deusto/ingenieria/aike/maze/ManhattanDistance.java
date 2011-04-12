package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.ingenieria.search.Node;

public class ManhattanDistance extends EvaluationFunction 
{

	public double calculateG(Node nodo) 
	{
		//The pathcost is related to the value of every operator
		double pathCost = nodo.getG();
		return pathCost;
	}

	public double calculateH(Node nodo) 
	{
		Board currentBoard = (Board) nodo.getState().getInformation();	
		Tile currentTile = currentBoard.getCar();
		Tile flagTile = currentBoard.getFlag();
		
		int distancia = 0;
		distancia = distanciaManhattan(currentTile, flagTile);
		
		//System.out.println("calculating the node heuristic: " + ((Board) nodo.getState().getInformation()).toString() + " y es: " + distancia);
		
		return distancia * -1;
	}
	
	private int distanciaManhattan(Tile ficha1, Tile ficha2) 
	{
		return Math.abs(ficha1.x - ficha2.x) + Math.abs(ficha1.y - ficha2.y);
	}
}