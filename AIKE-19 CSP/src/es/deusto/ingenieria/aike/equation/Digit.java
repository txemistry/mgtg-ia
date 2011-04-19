package es.deusto.ingenieria.aike.equation;

import java.util.List;

import es.deusto.ingenieria.aike.csp.formulation.Variable;

//We have subclassed Variable binding its parameter to Integer
//This means that the values the variables of the Equation Problem will take are Integers
public class Digit extends Variable<Integer> 
{
	
	public Digit(int id, List<Integer> domainValues) 
	{
		//Each digit has a differet character, so it will be used as a name
		super(String.valueOf(id), domainValues);
	}
	
	/**
	 * Returns a string describing the digit variable.
	 * The string specifies: "digit Name = assigned value/?"
	 * 
	 * @return String, describing the variable.
	 */	
	public String toString() 
	{
		String result = "Digit " + this.getName() + " = ";

		if (this.getValue() != null) 
		{
			result += "value " + this.getValue();
		} 
		else 
		{
			result += "?";
		}

		return result;
	}
}