package es.deusto.ingenieria.aike.maze;

import java.awt.Point;

public class Tile extends Point 
{
	private static final long serialVersionUID = 1L;
	private String type = "U";//It will be O, X or F, U for unknown
	private boolean up_wall;
	private boolean down_wall;
	private boolean right_wall;
	private boolean left_wall;
	
	public Tile(String type, int row, int column)
	{
		super(column, row);
		this.type = type;
	}
	
	public Tile(String type, int row, int column, boolean up_wall, boolean down_wall, boolean right_wall, boolean left_wall)
	{
		super(column, row);
		this.type = type;
		this.up_wall = up_wall;
		this.left_wall = left_wall;
		this.down_wall = down_wall;
		this.right_wall = right_wall;
	}
	
	public Tile()
	{
	}
	
	public Tile(int row, int column)
	{
		super(column,row);
	}
	
	public void setRow(int row) {
		this.y = row;
	}
	
	public int getRow() {
		return this.y;
	}
	
	public void setColumn(int column) {
		this.x = column;
	}
	
	public int getColumn() {
		return this.x;
	}
	
	public Point getPosition() {
		return this;
	}
	
	
	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}
	
	

	public boolean isUp_wall() 
	{
		return up_wall;
	}

	public void setUp_wall(boolean up_wall) 
	{
		this.up_wall = up_wall;
	}

	public boolean isDown_wall() 
	{
		return down_wall;
	}

	public void setDown_wall(boolean down_wall) 
	{
		this.down_wall = down_wall;
	}

	public boolean isRight_wall() 
	{
		return right_wall;
	}

	public void setRight_wall(boolean right_wall) 
	{
		this.right_wall = right_wall;
	}

	public boolean isLeft_wall() 
	{
		return left_wall;
	}

	public void setLeft_wall(boolean left_wall) 
	{
		this.left_wall = left_wall;
	}

	public boolean equals(Object obj) 
	{
		//Two tiles are the same if their coordenates are the same and their types and walls the same (if it is f)
		
		boolean equals = false;
		if ( obj != null && obj instanceof Tile) 
		{				
			Tile otherTile = (Tile)obj; 
			
			equals = otherTile.x == this.x && otherTile.y == this.y &&
			       otherTile.type == this.type;
			
			if(equals == true) //We have to see if it is F to look the walls
			{
				if(otherTile.type .equals("F"))
				{
					//It is F so we see the walls
					equals = otherTile.isDown_wall() == this.isDown_wall() && otherTile.isLeft_wall() == this.isLeft_wall() && otherTile.isRight_wall() == this.isRight_wall() && otherTile.isUp_wall() == this.isUp_wall();
				}
			}
			else
			{
				return equals;
			}
		}
		
		return equals;
	
	}

	public String toString() 
	{
		return this.getRow() + "," + this.getColumn();
	}

	public Object clone() 
	{
		return new Tile(this.type, this.getRow(), this.getColumn(), this.isUp_wall(), this.isLeft_wall(), this.isDown_wall(), this.isRight_wall());
	}
}