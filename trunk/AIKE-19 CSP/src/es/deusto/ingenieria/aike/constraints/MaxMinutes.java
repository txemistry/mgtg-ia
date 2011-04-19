package es.deusto.ingenieria.aike.constraints;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;
import es.deusto.ingenieria.aike.equation.EquationProblem;

public class MaxMinutes extends Constraint<Integer> 
{
	public MaxMinutes(List<Variable<Integer>> variables, String name)
	{
		super(name, variables);
	}

	@Override
	public boolean isSatisfied(Variable<Integer> variable, Integer value) 
	{
		boolean satisfied = false;
		
		if(value <= EquationProblem.maxMinutes/10)
		{
			satisfied = true;
		}
		 return satisfied;
	}
}
