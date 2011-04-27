package es.deusto.ingenieria.aike.constraints;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;
import es.deusto.ingenieria.aike.equation.EquationProblem;

public class FinalConstraint extends Constraint<Integer> 
{

	public FinalConstraint(List<Variable<Integer>> variables, String name) 
	{
		super(name, variables);
	}

	@Override
	public boolean isSatisfied(Variable<Integer> variable, Integer value) 
	{
		int cont = 0;
		for(Variable<Integer> varAux:this.getVariables())
		{
			if(varAux.hasValue())
				cont ++;
		}
		
		if (cont == 6)
		{
			//All the variables are assigned except G, so we do the final check
			//We put them in auxiliar variables to do it simple

			
			int A = this.getVariables().get(0).getValue();
			int B = this.getVariables().get(1).getValue();
			int C = this.getVariables().get(2).getValue();
			int D = this.getVariables().get(3).getValue();
			int E = this.getVariables().get(4).getValue();
			int F = this.getVariables().get(5).getValue();
			int G = value;
			
			if((((C*10+D)*EquationProblem.multiplier) - ((((C*10+D)*EquationProblem.multiplier)/60)*60)) == (G*10)+EquationProblem.constant)
			{
				if(A == ((EquationProblem.maxMinutes)/10))
				{
					if(B > (EquationProblem.maxMinutes - (EquationProblem.maxMinutes/10)*10))
					{
						return false;
					}
				}
				
				if(E == ((EquationProblem.maxMinutes)/10))
				{
					if(F > (EquationProblem.maxMinutes - (EquationProblem.maxMinutes/10)*10))
					{
						return false;
					}
				}
				
				if((((A*10+B)*EquationProblem.multiplier) + ((((C*10+D)*EquationProblem.multiplier)/60))) == (E*10)+F)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}

	}

}
