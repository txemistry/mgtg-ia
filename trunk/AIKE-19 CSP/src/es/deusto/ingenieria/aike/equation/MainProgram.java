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
			List<Integer> initialParameters = (List<Integer>) entornoSAXParser.getInformation();
			
			int multiplier = initialParameters.get(0);
			int constant = initialParameters.get(1);
			int maxMinutes = initialParameters.get(2);
			
			System.out.println("multiplier: " + multiplier);
			System.out.println("constatnt: " + constant);
			System.out.println("maxMinutes: " + maxMinutes);
			System.out.println();
			
			EquationProblem problem = new EquationProblem(multiplier, constant, maxMinutes);
			NodeConsistency consistencyMaker = new NodeConsistency();
			consistencyMaker.makeConsistent(problem.getVariables());
			
			
			
			for(int i = 0; i < problem.getVariables().size(); i++)
			{
				List<Integer> dominio = problem.getVariables().get(i).getDomain();
				
				System.out.println("Este deberia ser el dominio de la variable " + i);
				System.out.println("Las restricciones de esta variable despues de haberlas borrado supuestamente es:  " + problem.getVariables().get(i).getConstraints().size());
				for(int j = 0; j < dominio.size(); j++)
				{
					System.out.print(dominio.get(j)+ "   ");
				}
				System.out.println();
				System.out.println();
			}
			
			System.out.println("\nAHORA YA PODRIA LLAMAR AL METODOD DE BACKTRACKING");
			
			
			//BackTracking<Integer> backTracking = new BackTracking<Integer>();			
			//problem.solve(backTracking);			
		} 
		
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}