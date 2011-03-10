package es.deusto.ingenieria.aike.queens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.ingenieria.search.Node;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class QueensProblem extends Problem 
{	

	public static int num;

	public QueensProblem() 
	{
		super();
		//this.createOperators();
	}


	public void createOperators() 
	{

		
		
		for(int i = 0; i < QueensProblem.num; i++)
		{
		
				Operator operator = new PlaceQueenOperator(i);
				this.addOperator(operator);
			
		}
		
	}
	
	public void solve(SearchMethod searchMethod) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
		System.out.println("\n* Begin '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		System.out.println("* End '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		
		if (finalNode != null) {
			System.out.println("* Solution found!!");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);
			
			System.out.println("the final solution is: ");
			
			ArrayList <Tile> placedQ = ((Board)finalNode.getState().getInformation()).getPlacedQueens();
			
			for(Tile queen:placedQ)
			{
				System.out.println("Queen placed in Tile: " + queen.getRow() + "," + queen.getColumn());
			}
			
		} else {
			System.out.println("* Solution not found :-(");
		}
	}
	
	public boolean isFinalState(State state)
	{

		
		//When is a state final?
		//when it has placed all the queens 
		
		
		
		
		Board board = (Board)state.getInformation();
		ArrayList<Tile>placedQ = board.getPlacedQueens();
		
		if(placedQ.size() == QueensProblem.num )
			return true;
		else return false;
		
	}


}