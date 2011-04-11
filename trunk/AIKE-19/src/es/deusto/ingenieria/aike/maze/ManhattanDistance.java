package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.ingenieria.search.Node;

public class ManhattanDistance extends EvaluationFunction 
{

	public double calculateG(Node nodo) 
	{
		//el pathcost esta relacionado con el valor de cada operador, aqui no se puede calcular, solo devolver!!!
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
		
		//System.out.println("estoy calculando la heuristica del nodo: " + ((Board) nodo.getState().getInformation()).toString() + " y es: " + distancia);
		
		return distancia * -1;
	}
	
	private int distanciaManhattan(Tile ficha1, Tile ficha2) 
	{
		return Math.abs(ficha1.x - ficha2.x) + Math.abs(ficha1.y - ficha2.y);
	}
}