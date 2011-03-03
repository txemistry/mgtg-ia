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
	
	public QueensProblem()
	{
		super();
		this.createOperators();
	}


	public void createOperators() 
	{
		//mi operador recibe una Tile en la que posicionar una reina
		//Si aun no he creado mi tablero, como se las "destination" (Tiles) que les tengo qeu pasar para crearme los operadores????
		//PREGUNTAR!!!!!!!
		Operator operator = new PlaceQueenOperator(Destination.UP);
		this.addOperator(operator);
		operator = new PlaceQueenOperator(Destination.DOWN);
		this.addOperator(operator);
		operator = new PlaceQueenOperator(Destination.RIGHT);
		this.addOperator(operator);
		operator = new PlaceQueenOperator(Destination.LEFT);
		this.addOperator(operator);
	}
	
	public void solve(SearchMethod searchMethod) {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
		System.out.println("\n* Begin '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));
		System.out.println("* End '" + searchMethod.getClass().getCanonicalName() + "' (" + formatter.format(GregorianCalendar.getInstance().getTime()) + ")");
		
		if (finalNode != null) {
			System.out.println("* Solution found!!");
			List<String> operators = new ArrayList<String>();
			searchMethod.solutionPath(finalNode, operators);
			searchMethod.createSolutionLog(operators);
		} else {
			System.out.println("* Solution not found :-(");
		}
	}
}