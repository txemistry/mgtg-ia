package es.deusto.ingenieria.aike.constraints;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class MaxSeconds extends Constraint<Integer> 
{

	public MaxSeconds(List<Variable<Integer>> variables, String name) 
	{
		super(name, variables);
	}

	@Override
	public boolean isSatisfied(Variable<Integer> variable, Integer value) 
	{
		
		boolean satisfied = false;
		
		if(value <= 5)
		{
			satisfied = true;
		}
		 return satisfied;
	}

		
}
