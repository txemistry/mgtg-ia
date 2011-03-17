package es.deusto.ingenieria.aike.maze;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.puzzle.CarMovement.Destination;
import es.deusto.ingenieria.ingenieria.search.Node;
import es.deusto.ingenieria.ingenieria.search.SearchMethod;

public class PuzzleProblem extends Problem {
	
	public PuzzleProblem(){
		super();
		this.crearEstadosFinales();
		this.createOperators();
	}

	private void crearEstadosFinales() {
		this.addFinalState(new State(new Board(0,1,2,3,4,5,6,7,8)));
	}

	public void createOperators() {
		Operator operator = new CarMovement(Destination.UP);
		this.addOperator(operator);
		operator = new CarMovement(Destination.DOWN);
		this.addOperator(operator);
		operator = new CarMovement(Destination.RIGHT);
		this.addOperator(operator);
		operator = new CarMovement(Destination.LEFT);
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