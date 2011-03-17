package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.ingenieria.search.Node;

public class ManhattanDistance extends EvaluationFunction {

	public double calculateG(Node nodo) {
		return 0;
	}

	public double calculateH(Node nodo) {
		Board tableroFinal = new Board(0,1,2,3,4,5,6,7,8);
		Board tableroActual = (Board) nodo.getState().getInformation();
		
		int distancia = 0;		
		
		for (int i=0; i<tableroFinal.getTiles().length*tableroFinal.getTiles()[0].length; i++) {
			distancia += distanciaManhattan(tableroFinal.getTile(i), tableroActual.getTile(i));
		}
		
		return distancia * -1;
	}
	
	private int distanciaManhattan(Tile ficha1, Tile ficha2) {
		return Math.abs(ficha1.x - ficha2.x) + Math.abs(ficha1.y - ficha2.y);
	}
}