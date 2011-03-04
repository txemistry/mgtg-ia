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
		//mi operador recibe una Tile en la que posicionar una reina
		//Si aun no he creado mi tablero, como se las "destination" (Tiles) que les tengo qeu pasar para crearme los operadores????
		//PREGUNTAR!!!!!!!!   
		//MODIFICACION: en vez de pasarle a isApplicable una tile, le paso un numero de columna  
		//me hago tantos operadores como casillas tiene el tablero???
		
		System.out.println("estoy en createOperadores y num vale: " + QueensProblem.num);
		
		for(int i = 0; i < QueensProblem.num; i++)
		{
			for(int j = 0; j < QueensProblem.num; j++)
			{
				Operator operator = new PlaceQueenOperator(new Tile(i, j, false));
				this.addOperator(operator);
				System.out.println("acabo de crear el operador para la casilla " + i + "," + j);
			}
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
			
			System.out.println("la solucion final es:");
			
			ArrayList <Tile> placedQ = ((Board)finalNode.getState().getInformation()).getPlacedQueens();
			
			for(Tile queen:placedQ)
			{
				System.out.println("Reina colocada en la casilla: " + queen.getRow() + "," + queen.getColumn());
			}
			
		} else {
			System.out.println("* Solution not found :-(");
		}
	}
	
	public boolean isFinalState(State state)
	{
		//Cuando un estado es final??? 
		//Cuando tiene colocadas todas las reinas opsibles
		//y ninguna de ellas se ataca con otras
		//iremos comprobando una por una de las que estan en la lista de placedQueens
		
		
		
		
		Board board = (Board)state.getInformation();
		ArrayList<Tile>placedQ = board.getPlacedQueens();
		/*boolean conflicto = false;
		
	
		
		for(Tile queen:placedQ)
		{
			//comprobamos que en su misma fila no halla otra reina
			int i = 0;
			boolean enc = false;
			while(i < placedQ.size() && !enc)
			{

				if(placedQ.get(i).getRow() == queen.getRow())
				{
					//la poscion que me han pasado entra en conflicto con otra reina
					//devolverŽ false
					enc = true;
				}
				i++;
			}
			
			if(enc == true)
			{
				//ha encontrado conflicto con otra fila
				conflicto = true;
			}
			else
			{
				//hay que comprobar que no existan conflictos con las diagonales
				//enc ya era false
				i = 0;
				while(i < placedQ.size() && !enc)
				{
					int absFila;
					int absColumna;
					
					absFila = Math.abs(queen.getRow() - placedQ.get(i).getRow());
					absColumna = Math.abs(queen.getColumn() - placedQ.get(i).getColumn());
					if(absFila == absColumna)
					{
						//Son diagonales y por lo tanto...
						enc = true;
					}
					//Si son distintas no son diagonales
					i++;
				}
				if(enc == true)
				{
					conflicto =  true;
				}
				else
				{
					//el destination que me han pasdo no esntra en conflicto con ninguna reina posicionada anteriormente
					conflicto = false;
				}
			}
		}*/
		
		if(placedQ.size() == QueensProblem.num )
			return true;
		else return false;
		
	}


}