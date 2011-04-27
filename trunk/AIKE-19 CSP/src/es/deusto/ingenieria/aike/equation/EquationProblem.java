package es.deusto.ingenieria.aike.equation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.aike.constraints.Distinct;
import es.deusto.ingenieria.aike.constraints.FinalConstraint;
import es.deusto.ingenieria.aike.constraints.MaxMinutes;
import es.deusto.ingenieria.aike.constraints.MaxSeconds;
import es.deusto.ingenieria.aike.csp.algorithm.CSPAlgorithm;
import es.deusto.ingenieria.aike.csp.formulation.CSPproblem;
import es.deusto.ingenieria.aike.csp.formulation.Variable;

//We have subclassed CSPproblem, binding its parameter Type to Integer
//This means that the values the variables of the Equation Problem will take are Integers

public class EquationProblem extends CSPproblem<Integer> 
{
		
	public static int multiplier;
	public static int constant;
	public static int maxMinutes;
	
	public EquationProblem(int multiplier, int constant, int maxMinutes)
	{
		EquationProblem.multiplier = multiplier;
		EquationProblem.constant = constant;
		EquationProblem.maxMinutes = maxMinutes;
		
		this.createDigits();
		this.createConstraints();
	}
	
	private void createDigits() 
	{		
		for(int i = 1; i < 8; i++)
		{
			this.addVariable(new Digit(i, this.createDomain()));

		}

	}
	
	private void createConstraints()
	{
		 
		//We create a list with the varibles that are associated with the constraint
		for(int i = 0; i < 7; i++)
		{
			List<Variable<Integer>> constVariables = new ArrayList<Variable<Integer>>();
			constVariables.add(this.getVariables().get(i));
			Distinct distinct = new Distinct(constVariables, "" + (i+1) + "Distinct");
			//We add to the i variable this constraint
			this.getVariables().get(i).addConstraint(distinct);
		}
		
		//MAXMINUTES
		List<Variable<Integer>> constVariables = new ArrayList<Variable<Integer>>();
		constVariables.add(this.getVariables().get(0));
		MaxMinutes max = new MaxMinutes(constVariables, "AMinutes");
		this.getVariables().get(0).addConstraint(max);
		constVariables.clear();
		constVariables.add(this.getVariables().get(4));
		max = new MaxMinutes(constVariables, "EMinutes");
		this.getVariables().get(4).addConstraint(max);
		
		//MAXSECONDS
		constVariables.clear();
		constVariables.add(this.getVariables().get(2));
		MaxSeconds maxSec = new MaxSeconds(constVariables, "CSeconds");
		this.getVariables().get(2).addConstraint(maxSec);
		constVariables.clear();
		constVariables.add(this.getVariables().get(6));
		maxSec = new MaxSeconds(constVariables, "GSeconds");
		this.getVariables().get(6).addConstraint(maxSec);
		
		//Final constraint
		FinalConstraint test = new FinalConstraint(this.getVariables(), "finalConstraint");
		for (Variable<Integer> aux : this.getVariables()) 
		{
			aux.addConstraint(test);			
		}
		
		
		
		
		
		
	}
	
	private List<Integer> createDomain() 
	{
		List<Integer> digitValues = new ArrayList<Integer>();		
		
		for (int i=1; i<10; i++) 
		{
			digitValues.add(i);
		}

		return digitValues;
	}
	
	public String toString() 
	{
		String result = "";
		
		System.out.println("A B : C D * " + EquationProblem.multiplier + " = E F : G " + EquationProblem.constant);
		
		for (int i = 0; i < 7; i++)
		{
			System.out.print(this.getVariables().get(i).getValue() + " ");
			if((i == 1)||(i==5))
				System.out.print(": ");
			if(i == 3)
				System.out.print("* " + EquationProblem.multiplier + " = ");
		}
		System.out.print(EquationProblem.constant);
		
		
		
		return result;
	}
	
	public void solve(CSPAlgorithm<Integer> algorithm) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
		Date beginDate = GregorianCalendar.getInstance().getTime();
		System.out.println("\n* Begin '" + algorithm.getClass().getName() + "' (" + formatter.format(beginDate) + ")");		
		boolean solutionFound = algorithm.solve(this);
		Date endDate = GregorianCalendar.getInstance().getTime();		
		System.out.println("* End   '" + algorithm.getClass().getName() + "' (" + formatter.format(endDate) + ")");
		
		long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
		long seconds = miliseconds / 1000;
		miliseconds %= 1000;		
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		
		String time = "\n* Search lasts: ";
		time += (hours > 0) ? hours + " h " : " ";
		time += (minutes > 0) ? minutes + " m " : " ";
		time += (seconds > 0) ? seconds + "s " : " ";
		time += (miliseconds > 0) ? miliseconds + "ms " : " ";
		
		System.out.println(time);
		
		if (solutionFound) {
			System.out.println("\n* Solution found!!");			
			System.out.println("\n" + this);
		} else {
			System.out.println("\n* Solution not found :-(");
		}
	}
	
}