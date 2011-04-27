package es.deusto.ingenieria.aike.consistency;

import java.util.ArrayList;
import java.util.List;


import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

public class NodeConsistency implements IConstraintPropagation 
{

	@Override
	public void makeConsistent(List<Variable<Integer>> myVariables) 
	{
		//For each variable in the list, we have to check if their constraints
		//are unary and change the values of the domain		
		
		for(int i = 0; i < myVariables.size(); i++)
		{
			Variable<Integer> varAux = myVariables.get(i);
			
			//We loop all the variables and we look if they are unary 
			for(int j = 0; j < varAux.getConstraints().size(); j++)
			{
				
				Constraint<Integer> myConstraint = varAux.getConstraints().get(j);
				
			
			
				//check if unary
				if(myConstraint.getVariables().size() == 1)
				{
					//It is unary
					//now we check what values does not fit it
					
					varAux.getConstraints().remove(myConstraint);
					j--;
					
					List<Integer> myDomain = varAux.getDomain();
					for(int k = 0; k < myDomain.size(); k++)
					{
						if(myConstraint.isSatisfied(varAux, myDomain.get(k)))
						{
							//We can assign this value, so we leave it
						}
						else
						{
							//We have to delete this value							
							myDomain.remove(k);
							k--; 							
						}
					}
					
					
					
				}
			}
		}
		
	}

}
