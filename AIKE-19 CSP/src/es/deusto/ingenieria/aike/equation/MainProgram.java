package es.deusto.ingenieria.aike.equation;

import java.util.List;

import es.deusto.ingenieria.aike.consistency.NodeConsistency;
import es.deusto.ingenieria.aike.csp.algorithm.BackTracking;
import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class MainProgram {

	public static void main(String[] args) {
		try 
		{					
			InformationXMLReader entornoSAXParser = new XMLReader("data/equationMinSec-1.xml");
			@SuppressWarnings("unchecked")
			List<Integer> initialParameters = (List<Integer>) entornoSAXParser.getInformation();
			
			int multiplier = initialParameters.get(0);
			int constant = initialParameters.get(1);
			int maxMinutes = initialParameters.get(2);
			
			System.out.println("multiplier: " + multiplier);
			System.out.println("constant: " + constant);
			System.out.println("maxMinutes: " + maxMinutes);
			System.out.println();
			
			EquationProblem problem = new EquationProblem(multiplier, constant, maxMinutes);
			NodeConsistency consistencyMaker = new NodeConsistency();
			consistencyMaker.makeConsistent(problem.getVariables());
			
			
			
			BackTracking<Integer> backTracking = new BackTracking<Integer>();			
			problem.solve(backTracking);			
		} 
		
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}