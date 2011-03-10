package es.deusto.ingenieria.aike.queens;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.blind.DepthFSwithLog;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {
	
	public static void main(String[] args) {
		try {
			QueensProblem.num = 4;
			QueensProblem problem = new QueensProblem();
			
			System.out.println(problem.getOperators().size() + " operators have been created");
			
			State initialState = new State(new Board(4));
			problem.addInitialState(initialState);	
			
			
			SearchMethod search = DepthFSwithLog.getInstance();
			problem.solve(search);			
		} catch (Exception ex) {
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}