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
 * Class defining the Best First Search method.
 */
public class AStar extends HeuristicSearchMethod 
{

	/**
	 * Constructor method.
	 * @param function evaluation function to be used by Best First Search.
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
		List<State> expandedStates = new ArrayList<State>(); //PASO 3?????
		
		
		//First node in the list of generated nodes.
		Node firstNode = null;
		//successor nodes list.
		List<Node> successorNodes = null;
		//Flag that signals whether a solution has been found.
		boolean solutionFound = false;	

		
		//Initialize the generated nodes list with a node containing the problem's initial state.
		frontier.add(new Node(initialState)); //ESTE ES EL PASO 1 Y EL PASO 2 JUNTOS
		

		
		//Loop until the problem is solved or the generated nodes list is empty
		while (!solutionFound && !frontier.isEmpty()) //PASO 4
		{			
			//remove the first node from the generated nodes list.
			firstNode = frontier.remove(0);//PASO 4.1 	
			//If the first node contains a problem's final state
			if (problem.isFinalState(firstNode.getState())) //PASO 4.2
			{
				//change the flag to signal that the problem is solved
				solutionFound = true;
			//If the first node doesn't contain a problem's final state				
			} 
			else //PASO 4.3
			{
				//metemos el nodo actual en la lista de expanded
				expandedStates.add(firstNode.getState()); //PASO 4.3.1???
				
				
				//AL METODO EXPAND LE LLEGARIAN LISTAS DE NODOS EN VEZ DE LISTAS DE ESTADOS,
				//SIN EMBARGO, COMO ESTO SUPONDRIA CAMBIAR LAS CABECERAS DE LAS PLANTILLAS
				//HEMOS DECIDIDO MANTENERLAS COMO ESTAN, Y CONSTRUIR LAS LISTAS DE NODOS 
				//AL COMIENZO DEL METODO EXPAND
				
				
				
				//Expand the first node.
				successorNodes = this.expand(firstNode, problem, generatedStates, expandedStates);
				//If new successor nodes resulted from the expansion
				if (successorNodes != null && !successorNodes.isEmpty()) 
				{
					
					//Add the successor nodes to the generated nodes list.
					frontier.addAll(successorNodes); //PASO 4.3.2
					
					//Sort the generated nodes list according to the evaluation function value
					//of the nodes. This comparison criteria is defined within the compareTo()
					//method of Node.
					Collections.sort(frontier); //PASO 4.3.3 como ordenamos segun el criterio f(n)??
				}
			}
		}
		
		// If the problem is solved //PASO 5
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
		
		//INICIALIZAMOS ESAS LISTAS
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
							System.out.println("estoy en el primer if");
							successorNode.setOperator(operator.getName());
							successorNode.setParent(node);
							double heuristic = this.getEvaluationFunction().calculateH(successorNode);
							double pathCost = this.getEvaluationFunction().calculateG(successorNode.getParent()) - operator.getCost();
							fNew = heuristic + pathCost; //son calculadas en negativo
							
							//le seteamos sus valores de G y H y su depth
							successorNode.setDepth(node.getDepth() + 1);
							successorNode.setH(heuristic);
							successorNode.setG(pathCost);
							successorNodes.add(successorNode);
							generatedStates.add(successorState);
							
						}
						
						if(frontier.contains(successorNode))
						{
							//no deberia coger el nodo que he enconrado en  frontier porque es ese el que se va a modificar?
							
							
							System.out.println("estoy en el segundo if");
							Node oldNode = frontier.get(frontier.indexOf(successorNode));
							//ahora deberia calcular la fNew de oldNode
							
							
							double fPrevious = 0;
							double heuristic = this.getEvaluationFunction().calculateH(oldNode);
							double pathCost = this.getEvaluationFunction().calculateG(oldNode);
							fPrevious = heuristic + pathCost; //este es la f del nodo que ya estaba en frontier
							
							heuristic = this.getEvaluationFunction().calculateH(successorNode);
							pathCost =  this.getEvaluationFunction().calculateG(node) - operator.getCost();
							fNew = heuristic + pathCost;
							
							if(fPrevious < fNew) //significa que es mejor el pathcost del nuevo padre
							{
								oldNode.setParent(node);
								//la heuristica no cambiaria, pero el path cost si poruqe ha cambiado su padre
								successorNode.setG(pathCost);
							}
							else
							{
								successorNode.setOperator(operator.getName());
								successorNode.setParent(node);
								 heuristic = this.getEvaluationFunction().calculateH(successorNode);
								 pathCost = this.getEvaluationFunction().calculateG(successorNode.getParent()) - operator.getCost();
								
								
								//le seteamos sus valores de G y H y su depth
								successorNode.setDepth(node.getDepth() + 1);
								successorNode.setH(heuristic);
								successorNode.setG(pathCost);
							}
							
						}
						
						if(expandedNodes.contains(successorNode))
						{
							System.out.println("estoy en el tercer if");
							Node oldNode = expandedNodes.get(expandedNodes.indexOf(successorNode));
							//ahora deberia calcular la fNew de oldNode
							
							
							double fPrevious = 0;
							double heuristic = this.getEvaluationFunction().calculateH(oldNode);
							double pathCost = this.getEvaluationFunction().calculateG(oldNode);
							fPrevious = heuristic + pathCost; //este es la f del nodo que ya estaba en frontier
							
							heuristic = this.getEvaluationFunction().calculateH(successorNode);
							pathCost =  this.getEvaluationFunction().calculateG(node) - operator.getCost();
							fNew = heuristic + pathCost;
							
							if(fPrevious < fNew) //significa que es mejor el pathcost del nuevo padre
							{
								oldNode.setParent(node);
								//la heuristica no cambiaria, pero el path cost si poruqe ha cambiado su padre
								successorNode.setG(pathCost);
								this.updateChildren(successorNode, frontier, expandedNodes);
							}
							else
							{
								successorNode.setOperator(operator.getName());
								successorNode.setParent(node);
								 heuristic = this.getEvaluationFunction().calculateH(successorNode);
								 pathCost = this.getEvaluationFunction().calculateG(successorNode.getParent()) - operator.getCost();
								
								
								//le seteamos sus valores de G y H y su depth
								successorNode.setDepth(node.getDepth() + 1);
								successorNode.setH(heuristic);
								successorNode.setG(pathCost);
							}
							
						}
						
						System.out.println("        Heuristica: " + successorNode.getH() );
						System.out.println("        Path Cost: " +  successorNode.getG() );
						
						
						
						/*//NUEVO CODIGO??????
						
						//If the new node hadn't been generated before nor expanded
						//if (!generatedStates.contains(successorNode) && !expandedStates.contains(successorNode)) 
						//{
							//Set values for the various node's attributes
							successorNode.setOperator(operator.getName());
							successorNode.setParent(node);
							successorNode.setDepth(node.getDepth() + 1);
							//evaluation function = heuristic function
							successorNode.setH(this.getEvaluationFunction().calculateH(successorNode));
							
							//Para saber su G es decir pathCost, sera el pathCost de su padre mas el valor asociado al operador en curso
							
							successorNode.setG(this.getEvaluationFunction().calculateG(successorNode.getParent()) - operator.getCost());
							System.out.println("el caluculo del nodo: " + ((Board) successorNode.getState().getInformation()).toString() + " es: ");
							System.out.println("        Heuristica: " + successorNode.getH() );
							System.out.println("        Path Cost: " +  successorNode.getG() );
					
														
							//Add the new node to the list of successor nodes.
							successorNodes.add(successorNode);
							//Insert current successor State to the list of generated states
							generatedStates.add(successorState);
						//}
*/					}
					
					
				}
			}
		}
		return successorNodes;
	}
	
	private void updateChildren(Node successorNode, List<Node> frontier, List<Node> expandedNodes)
	{
		System.out.println("ESTOY EN LA RECURSIVA");
		for(int i = 0; i<frontier.size(); i++)
		{
			Node auxNode = frontier.get(i);
			if(auxNode.getParent().equals(successorNode))
			{
				//SOY SU PADRE
				auxNode.setG(this.getEvaluationFunction().calculateH(auxNode));
				auxNode.setG(this.getEvaluationFunction().calculateG(successorNode) - 1);
			}
		}
		
		for(int i = 0; i < expandedNodes.size(); i++)
		{
			Node auxNode = expandedNodes.get(i);
			if(auxNode.getParent().equals(successorNode))
			{
				//SOY SU PADRE
				auxNode.setG(this.getEvaluationFunction().calculateH(auxNode));
				auxNode.setG(this.getEvaluationFunction().calculateG(successorNode) - 1);
				this.updateChildren(auxNode, frontier, expandedNodes);
			}
		}
	}
}