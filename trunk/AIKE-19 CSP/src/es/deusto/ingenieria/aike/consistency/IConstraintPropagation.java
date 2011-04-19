package es.deusto.ingenieria.aike.consistency;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Variable;

public interface IConstraintPropagation 
{
	void makeConsistent(List<Variable<Integer>> myVariables);
}
