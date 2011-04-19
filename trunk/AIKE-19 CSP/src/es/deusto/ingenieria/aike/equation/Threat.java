package es.deusto.ingenieria.aike.equation;

import java.awt.Point;
import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Constraint;
import es.deusto.ingenieria.aike.csp.formulation.Variable;


//We have subclassed Constraint binding its parameter to Integer
//This means that the values the variables of the Queens Problem will take are Integers
public class Threat extends Constraint<Integer> {

	public Threat(List<Variable<Integer>> variables, String name){
		super(name, variables);
	}

	public boolean isSatisfied(Variable<Integer> variable, Integer value) {
		//we associate a Point with the current queen variable (passed in the variable parameter)
		//this point is made out of the column and row values of the queen variable
		//and will be used to check whether other queens are on the same diagonal as the current queen
		Point currentQueen = new Point(Integer.valueOf(variable.getName()), value);
		Point auxQueen = null;
		
		//For each of the queen variables involved in the constraint
		for (Variable<Integer> varAux : this.getVariables()) {
			//If the variable is a different queen from the current one and has a value assigned
			if (!varAux.equals(variable) && varAux.hasValue()) {
				//associate a Point with such queen variable 
				//the point is made out of the column and row values of the queen
				auxQueen = new Point(Integer.valueOf(varAux.getName()), varAux.getValue());
				//If this queen has the same row value (i.e. y value) as the current queen,
				//then the constraint is NOT satisfied
				//If the Point associated to this queen is on the  same diagonal as 
				//the Point associated to the current queen,
				//then the constraint is NOT satisfied either
				if (currentQueen.y == auxQueen.y ||
				    Math.abs(currentQueen.x - auxQueen.x) == Math.abs(currentQueen.y - auxQueen.y)) {
					return false;
				}
			}
		}
		
		return true;
	}
}