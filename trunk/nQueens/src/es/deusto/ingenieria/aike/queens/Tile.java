package es.deusto.ingenieria.aike.queens;

import java.awt.Point;

public class Tile extends Point 
{

	private static final long serialVersionUID = 1L;
	private boolean reina;
	//private int x;  ESTOS DOS ATRIBUTOS LOS HEREDA DE LA CLASE Point
	//private int y;
	
	
	
	public Tile(int row, int column, boolean reina)
	{
		super(column, row);
		this.reina = reina;
	}
	
	public void setRow(int row) 
	{
		this.y = row;
	}
	
	public int getRow() 
	{
		return this.y;
	}
	
	public void setColumn(int column) 
	{
		this.x = column;
	}
	
	public int getColumn() 
	{
		return this.x;
	}
	
	public boolean isReina() 
	{
		return reina;
	}

	public void setReina(boolean reina) 
	{
		this.reina = reina;
	}

	public Point getPosition() //me devuelve una "Tile" que es un Point que tiene las propiedades x e y
	{
		return this;
	}

	
	public boolean equals(Object obj) 
	{
		//Cuando son dos Tile iguales? cuando coinciden sus coordenadas y si poseen reina o no
		if ( obj != null && obj instanceof Tile) 
		{				
			Tile otherTile = (Tile)obj; 
			
			return otherTile.x == this.x && otherTile.y == this.y &&
			       otherTile.isReina() == this.reina;
		} 
		else 
		{
			return false;
		}
	}

	public String toString() 
	{
		return "" + this.reina;
	}

	public Object clone() 
	{
		return new Tile(this.getRow(), this.getColumn(), this.reina);
	}
}