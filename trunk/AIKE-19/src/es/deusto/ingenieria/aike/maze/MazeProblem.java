package es.deusto.ingenieria.aike.maze;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.maze.CarMovement.Destination;
import es.deusto.ingenieria.ingenieria.search.Node;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class MazeProblem extends Problem 
{
	
	public MazeProblem()
	{
		super();
		//this.createOperators();
	}
	


	public void createOperators() 
	{
		Operator operator = new CarMovement(Destination.UP);
		operator.setCost(1);
		this.addOperator(operator);
		System.out.println("Se ha creado el operador UP");
		
		operator = new CarMovement(Destination.DOWN);
		operator.setCost(1);
		this.addOperator(operator);
		System.out.println("Se ha creado el operador DOWN");
		
		operator = new CarMovement(Destination.RIGHT);
		operator.setCost(1);
		this.addOperator(operator);
		System.out.println("Se ha creado el operador RIGHT");
		
		operator = new CarMovement(Destination.LEFT);
		operator.setCost(1);
		this.addOperator(operator);
		System.out.println("Se ha creado el operador LEFT");
	}
	
	public void solve(SearchMethod searchMethod) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
		System.out.println("\n* Begin '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		System.out.println("* End '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		
		if (finalNode != null) {
			System.out.println("* Solution found!!");
			
			//double pathCost = ((Board)finalNode.getState().getInformation()).getPathCost();
			double pathCost = finalNode.getG() * -1;
			
			System.out.println("The total pathCost of the problem assuming that each movement is associated with a value of 1: " + pathCost);
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);
		} else {
			System.out.println("* Solution not found :-(");
		}
	}
	
	public boolean isFinalState(State state)
	{
		//cuando un estado es final?
		//cuando la tile en la que esta el coche es de tipo F
		System.out.println();
		System.out.println();
		boolean end = false;
		
		Board board = (Board)state.getInformation();
		Tile currentTile= board.getCar();
		
		System.out.println("comprobado estado final en:" + ((Board) state.getInformation()).toString());
		if(currentTile.getType().equals("F"))
		{
			end = true;
		}
			
			
		 return end;
	}
}