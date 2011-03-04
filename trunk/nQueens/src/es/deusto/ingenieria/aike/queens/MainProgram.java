package es.deusto.ingenieria.aike.queens;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.blind.DepthFSwithLog;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {
	
	public static void main(String[] args) {
		try {
			QueensProblem.num = 4;
			QueensProblem problem = new QueensProblem();
			
			System.out.println("se han creado " + problem.getOperators().size() + " operadores");
			
			State initialState = new State(new Board(4));
			problem.addInitialState(initialState);	
			
		/*	State state2;
			State state3;
			state2 = problem.getOperators().get(0).apply(initialState);
			System.out.println(((Board)state2.getInformation()).toString());
			state3 = problem.getOperators().get(1).apply(state2);
			System.out.println("aqui no llego");
			System.out.println(((Board)state3.getInformation()).toString());*/

			
			
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