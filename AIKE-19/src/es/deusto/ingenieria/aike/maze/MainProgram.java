package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.AStarWithLog;
import es.deusto.ingenieria.aike.search.heuristic.BestFSwithLog;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MainProgram {
	
	public static void main(String[] args) 
	{
		try 
		{
			MazeProblem problem = new MazeProblem();
			InformationXMLReader entornoSAXParser = new BoardXMLReader("data/parkinglotmaze4.xml"); 					
			State initialState = new State((Board)entornoSAXParser.getInformation());
			System.out.println(initialState.toString());
			
			System.out.println(problem.getOperators().size() + " operators have been created");
			problem.addInitialState(initialState);	
			
		
			//Con A*
			SearchMethod search = new AStarWithLog(new ManhattanDistance());
			problem.solve(search);
			
			//Con heuristica
			//SearchMethod search = new BestFSwithLog(new ManhattanDistance());
			//problem.solve(search);
			
			
			//Sin heuristica
			//SearchMethod search = DepthFSwithLog.getInstance();
			//problem.solve(search);
			
			
		
					
		} catch (Exception ex) 
		{
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}