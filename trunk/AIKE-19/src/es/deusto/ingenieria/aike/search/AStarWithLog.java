package es.deusto.ingenieria.aike.search;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.aike.search.log.SearchLog;
import es.deusto.ingenieria.ingenieria.search.Node;

/**
 * Class defining the A* method.
 */
public class AStarWithLog extends AStar {

	/**
	 * Constructor method.
	 * @param function evaluation function to be used by A*.
	 */
	public AStarWithLog(EvaluationFunction function){
		super(function);
	}
	

	public Node search(Problem problem, State initialState) {
		//A list to keep the nodes generated during the search process.
		List<Node> frontier = new ArrayList<Node>();
		//List of states generated during the search process. This is used to check for repeated states.
		List<State> generatedStates = new ArrayList<State>();
		//List of states expended during the search process. This is used to check for repeated states.
		List<State> expandedStates = new ArrayList<State>();
		//First node in the list of generated nodes.
		Node firstNode = null;
		//successor nodes list.
		List<Node> successorNodes = null;
		//Flag that signals whether a solution has been found.
		boolean solutionFound = false;

		//Defines and initializes the search log.
		SearchLog searchLog = this.createLog();
		
		//Initialize the generated nodes list with a node containing the problem's initial state.
		frontier.add(new Node(initialState));

		//Loop until the problem is solved or the generated nodes list is empty
		while (!solutionFound && !frontier.isEmpty()) {			
			//write the content of the generated nodes list in the search log.
			this.writeLog(searchLog, frontier);			
			//remove the first node from the generated nodes list.
			firstNode = frontier.remove(0);
			
			//If the first node contains a problem's final state
			if (problem.isFinalState(firstNode.getState())) {
				//change the flag to signal that the problem is solved
				solutionFound = true;
			//If the first node doesn't contain a problem's final state				
			} else {
				//Expand the first node.
				successorNodes = this.expand(firstNode, problem, generatedStates, expandedStates);
				//If new successor nodes resulted from the expansion
				if (successorNodes != null && !successorNodes.isEmpty()) {
					//Add the successor nodes to the generated nodes list.
					frontier.addAll(successorNodes);
					//Sort the generated nodes list according to the evaluation function value
					// of the nodes. This comparison criteria is defined within the compareTo()
					// method of Node.
					Collections.sort(frontier);
				}
			}
		}
		
		// closes the search log.
		this.closeLog(searchLog);
		
		// If the problem is solved
		if (solutionFound) {
			//Return the first node as it contains the problem's final state
			return firstNode;
		//If the problem is not solved
		} else {
			//return null
			return null;
		}
	}
}