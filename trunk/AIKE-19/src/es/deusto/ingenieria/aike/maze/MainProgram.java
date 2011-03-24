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
			 State state3;
			 
             state2 = problem.getOperators().get(0).apply(initialState);
             if(state2 == null)
            	 System.out.println("no es posible hacer ese movimiento");
             else
            	 System.out.println(((Board)state2.getInformation()).toString());
             
             state3 = problem.getOperators().get(3).apply(state2);
             if(state3 == null)
            	 System.out.println("no es posible hacer ese movimiento");
             else
            	 System.out.println(((Board)state3.getInformation()).toString());
					
					
		} catch (Exception ex) 
		{
			System.err.println("% [MainProgram] Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}