package es.deusto.ingenieria.aike.queens;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.blind.BreadthFSwithLog;
import es.deusto.ingenieria.aike.search.blind.DepthFSwithLog;
import es.deusto.ingenieria.aike.search.heuristic.BestFS;
import es.deusto.ingenieria.aike.search.heuristic.BestFSwithLog;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {
	
	public static void main(String[] args) {
		try {
			QueensProblem.num = 8;
			QueensProblem problem = new QueensProblem();
			
			System.out.println("se han creado " + problem.getOperators().size() + " operadores");
			
			State initialState = new State(new Board(8));
			problem.addInitialState(initialState);	
			
			
			//SearchMethod search = new BestFS(new ManhattanDistance());
			//problem.solve(search);
			//search = new BestFSwithLog(new ManhattanDistance());
			//problem.solve(search);
			SearchMethod search = DepthFSwithLog.getInstance();
			problem.solve(search);			
		} catch (Exception ex) {
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}