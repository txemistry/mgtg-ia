package es.deusto.ingenieria.aike.constraints;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;
import es.deusto.ingenieria.aike.equation.EquationProblem;

public class Distinct extends Constraint<Integer>
{

	
	public Distinct(List<Variable<Integer>> variables, String name)
	{
		super(name, variables);
	}

	@Override
	public boolean isSatisfied(Variable<Integer> variable, Integer value) 
	{
		boolean satisfied = false;
		
		if(value != EquationProblem.multiplier && value != EquationProblem.constant)
		{
			satisfied = true;
		}
		 return satisfied;
	}

}
