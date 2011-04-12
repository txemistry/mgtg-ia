package es.deusto.ingenieria.aike.search;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.Problem;
import es.deusto.ingenieria.aike.formulation.State;
import es.deusto.ingenieria.aike.search.heuristic.EvaluationFunction;
import es.deusto.ingenieria.aike.search.heuristic.HeuristicSearchMethod;
import es.deusto.ingenieria.ingenieria.search.Node;

/**
 * Class defining the A*.
 */
public class AStar extends HeuristicSearchMethod 
{

	/**
	 * Constructor method.
	 * @param function evaluation function to be used by A*.
	 */
	public AStar(EvaluationFunction function)
	{
		super(function);
	}
	
	/**
	 * Carries out a search process from the initial state
	 * to the final state of the given problem.
	 * This method is defined according to the second version of the basic search algorithm
	 * which checks for repeated states (refer to the last algorithm studied in chapter 3).
	 * 
	 * @param problem
	 *            Problem to be solved by a search method.
	 * @param initialState
	 *            Problem's initial state. 
	 * @return 
	 * @return Node
	 *         <ul>
	 *         <li>If a solution is found, Node contains the problem's final state</li>
	 *         <li>If the problem can't be solved, Node contains null.</li>
	 *         </ul>
	 */
	public Node search(Problem problem, State initialState) 
	{
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

		
		//Initialize the generated nodes list with a node containing the problem's initial state.
		frontier.add(new Node(initialState));
		

		
		//Loop until the problem is solved or the generated nodes list is empty
		while (!solutionFound && !frontier.isEmpty()) 
		{			
			//remove the first node from the generated nodes list.
			firstNode = frontier.remove(0);	
			//If the first node contains a problem's final state
			if (problem.isFinalState(firstNode.getState())) //PASO 4.2
			{
				//change the flag to signal that the problem is solved
				solutionFound = true;
			//If the first node doesn't contain a problem's final state				
			} 
			else 
			{
				//the actual node in the expanded list
				expandedStates.add(firstNode.getState()); 
				
				
				//THE EXPAND METHOD MUST WORK WITH NODE LISTS AND NOT WITH STATE LISTS
				//THIS MEANS TO CHANGE THE HEADERS OF THE TEMPLATE SO WE HAVE DECIDED
				//TO KEEP THEM AND CONSTRUCT THE NODE LISTS AT THE BEGGINING OH THE EXPAND METHOD
				
				
				
				//Expand the first node.
				successorNodes = this.expand(firstNode, problem, generatedStates, expandedStates);
				//If new successor nodes resulted from the expansion
				if (successorNodes != null && !successorNodes.isEmpty()) 
				{
					
					//Add the successor nodes to the generated nodes list.
					frontier.addAll(successorNodes);
					
					//Sort the generated nodes list according to the evaluation function value
					//of the nodes. This comparison criteria is defined within the compareTo()
					//method of Node.
					Collections.sort(frontier);
				}
			}
		}
		
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

	/**
	 * Expands the node's state thereby generating a list of successor nodes.
	 * Expansion takes place by invoking the problem's operators apply() method on the
	 * node's state.
	 * 
	 * @param node
	 *            node whose state is to be expanded.
	 * @param problem
	 *            problem to solve
	 * @param generatedStates
	 *            List<State> states generated along the search process.
	 * @param expandedStates
	 *            List<State> states expanded along the search process.
	 * @return List<Node> containing the successor nodes.
	 */
	
	//generatedStates es la frontier
	
	
	protected List<Node> expand(Node node, Problem problem, List<State> generatedStates, List<State> expandedStates) {
		List<Node> successorNodes = new ArrayList<Node>();
		Node successorNode = null;
		State currentState = null;
		State successorState = null;
		List<Node> frontier = new ArrayList<Node>();
		List<Node> expandedNodes = new ArrayList<Node>();
		
		//iNITIALIZE THE LISTS
		for(State auxState:generatedStates)
		{
			frontier.add(new Node(auxState));
		}
		
		for(State auxState:expandedStates)
		{
			expandedNodes.add(new Node(auxState));
		}
		
		
		
		//If the current node and problem aren't null
		if (node != null && problem != null) 
		{
			//Make the current state the state kept in the node.
			currentState = node.getState();
			//Remove current state from the list of generated states.
			generatedStates.remove(currentState);
			//Insert current state to the list of generated states.
			expandedStates.add(currentState);			
			//If current state is not null
			if (currentState != null) 
			{
				//process the list of problem operators
				for (Operator operator : problem.getOperators()) 
				{
					//Apply the operator to the current state
					successorState = operator.apply(currentState);
					//If the operator was applicable, a new successor state was generated
					if (successorState != null) 
					{
						//make a new node to keep the new successor state
						successorNode = new Node(successorState);
						
						
						
						double fNew = 0;
						if(!frontier.contains(successorNode) && !expandedNodes.contains(successorNode))
						{
							System.out.println("I am in the first if");
							successorNode.setOperator(operator.getName());
							successorNode.setParent(node);
							double heuristic = this.getEvaluationFunction().calculateH(successorNode);
							double pathCost = this.getEvaluationFunction().calculateG(successorNode.getParent()) - operator.getCost();
							fNew = heuristic + pathCost; //We calculate them in negative
							
							//We set the G, the H and the depth
							successorNode.setDepth(node.getDepth() + 1);
							successorNode.setH(heuristic);
							successorNode.setG(pathCost);
							successorNodes.add(successorNode);
							generatedStates.add(successorState);
							
						}
						
						if(frontier.contains(successorNode))
						{
							
							System.out.println("I am in the second if");
							Node oldNode = frontier.get(frontier.indexOf(successorNode));
							//Now should calculate	the fnew of oldNode						
							
							double fPrevious = 0;
							double heuristic = this.getEvaluationFunction().calculateH(oldNode);
							double pathCost = this.getEvaluationFunction().calculateG(oldNode);
							fPrevious = heuristic + pathCost; //The f of the node that is in the frontier
							
							heuristic = this.getEvaluationFunction().calculateH(successorNode);
							pathCost =  this.getEvaluationFunction().calculateG(node) - operator.getCost();
							fNew = heuristic + pathCost;
							
							if(fPrevious < fNew) //It means that the F of the new parent is better
							{
								oldNode.setParent(node);
								//The heuristic is the same but the G no, because we have changed his parent
								successorNode.setG(pathCost);
							}
							else
							{
								successorNode.setOperator(operator.getName());
								successorNode.setParent(node);
								 heuristic = this.getEvaluationFunction().calculateH(successorNode);
								 pathCost = this.getEvaluationFunction().calculateG(successorNode.getParent()) - operator.getCost();
								
								
								//We set the F, G anf depth
								successorNode.setDepth(node.getDepth() + 1);
								successorNode.setH(heuristic);
								successorNode.setG(pathCost);
							}
							
						}
						
						if(expandedNodes.contains(successorNode))
						{
							System.out.println("I am in the third if");
							Node oldNode = expandedNodes.get(expandedNodes.indexOf(successorNode));
							//Now should calculate	the fnew of oldNode
							
							
							double fPrevious = 0;
							double heuristic = this.getEvaluationFunction().calculateH(oldNode);
							double pathCost = this.getEvaluationFunction().calculateG(oldNode);
							fPrevious = heuristic + pathCost; //The f of the node that is in the frontier
							
							heuristic = this.getEvaluationFunction().calculateH(successorNode);
							pathCost =  this.getEvaluationFunction().calculateG(node) - operator.getCost();
							fNew = heuristic + pathCost;
							
							if(fPrevious < fNew) //It means that the F of the new parent is better
							{
								oldNode.setParent(node);
								//The heuristic is the same but the G no, because we have changed his parent
								successorNode.setG(pathCost);
								this.updateChildren(successorNode, frontier, expandedNodes);
							}
							else
							{
								successorNode.setOperator(operator.getName());
								successorNode.setParent(node);
								 heuristic = this.getEvaluationFunction().calculateH(successorNode);
								 pathCost = this.getEvaluationFunction().calculateG(successorNode.getParent()) - operator.getCost();
								
								
								//We set the G, the H and the depth
								successorNode.setDepth(node.getDepth() + 1);
								successorNode.setH(heuristic);
								successorNode.setG(pathCost);
							}
							
						}
						
						System.out.println("        Heuristic: " + successorNode.getH() );
						System.out.println("        Path Cost: " +  successorNode.getG() );
						
						
						
					}	
				}
			}
		}
		return successorNodes;
	}
	
	private void updateChildren(Node successorNode, List<Node> frontier, List<Node> expandedNodes)
	{
		System.out.println("I am in the recursive one");
		for(int i = 0; i<frontier.size(); i++)
		{
			Node auxNode = frontier.get(i);
			if(auxNode.getParent().equals(successorNode))
			{
				//I am his father
				auxNode.setG(this.getEvaluationFunction().calculateH(auxNode));
				auxNode.setG(this.getEvaluationFunction().calculateG(successorNode) - 1);
			}
		}
		
		for(int i = 0; i < expandedNodes.size(); i++)
		{
			Node auxNode = expandedNodes.get(i);
			if(auxNode.getParent().equals(successorNode))
			{
				//I am his father
				auxNode.setG(this.getEvaluationFunction().calculateH(auxNode));
				auxNode.setG(this.getEvaluationFunction().calculateG(successorNode) - 1);
				this.updateChildren(auxNode, frontier, expandedNodes); //The recursive method for if our kids have kids and so on
			}
		}
	}
}