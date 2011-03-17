package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.blind.BreadthFSwithLog;
import es.deusto.ingenieria.aike.search.heuristic.BestFS;
import es.deusto.ingenieria.aike.search.heuristic.BestFSwithLog;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {
	
	public static void main(String[] args) {
		try {
			PuzzleProblem problem = new PuzzleProblem();
			State initialState = new State(new Board(7,1,2,3,4,5,6,8,0));
			problem.addInitialState(initialState);			
			SearchMethod search = new BestFS(new ManhattanDistance());
			problem.solve(search);
			search = new BestFSwithLog(new ManhattanDistance());
			problem.solve(search);
			search = BreadthFSwithLog.getInstance();
			problem.solve(search);			
		} catch (Exception ex) {
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}