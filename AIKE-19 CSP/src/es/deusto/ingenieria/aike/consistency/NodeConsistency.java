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
		//por cada varibale en la lista, se tiene que comprobar cuales de sus restricciones
		//son unarias y actualizar los valores del dominio de la variable en curso
		
		
		for(int i = 0; i < myVariables.size(); i++)
		{
			Variable<Integer> varAux = myVariables.get(i);
			
			//nos recorremos todas las variables y miramos a ver si estan en restricciones unarias
			for(int j = 0; j < varAux.getConstraints().size(); j++)
			{
				
				Constraint<Integer> myConstraint = varAux.getConstraints().get(j);
				
			
			
				//comprobamos si es unaria
				if(myConstraint.getVariables().size() == 1)
				{
					//significa que es unaria
					//ahora miramos que valores no cumplen sus restricciones
					
					varAux.getConstraints().remove(myConstraint);
					j--;
					
					List<Integer> myDomain = varAux.getDomain();
					for(int k = 0; k < myDomain.size(); k++)
					{
						if(myConstraint.isSatisfied(varAux, myDomain.get(k)))
						{
							//significa que el valor en curso se le puede asignar asi que se deja
						}
						else
						{
							//isSatisfied me ha devuelto false por lo qeu deberiamos eliminar del
							//dominio de la variable el valor domainValue
							//myVariables.get(i).getDomain().remove(k);
							
							myDomain.remove(k);
							k--; //esto es para que no se salte un valor, al borrar el anterior
							
						}
					}
					
					
					
				}
			}
		}
		
	}

}
