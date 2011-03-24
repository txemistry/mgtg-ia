package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class MainProgram {
	
	public static void main(String[] args) 
	{
		try 
		{
			MazeProblem problem = new MazeProblem();
			InformationXMLReader entornoSAXParser = new BoardXMLReader("data/parkinglotmaze1.xml"); 					
			State initialState = new State((Board)entornoSAXParser.getInformation());
			System.out.println(initialState.toString());
			
			
			 State state2;
             state2 = problem.getOperators().get(0).apply(initialState);
             System.out.println(((Board)state2.getInformation()).toString());
			
			
			
			//System.out.println(second.toString());
			//problem.addInitialState(initialState);		
					
		} catch (Exception ex) 
		{
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}