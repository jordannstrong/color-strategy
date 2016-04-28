package database;
import java.awt.Color;

/*
* This class is a representation of a SQL Query, that also stores the
* associated color for Flight objects matching the Query.
*
* @author Spencer Bialt
* @date  4/10/2016
*/

public class Query
{
	private String query;
	private Color color;

	//Constructor
	public Query()
	{
		query = "";
	}
	

	//Getter for query field
	public String getQuery()
	{
		return query;
	}

	//Setter for query field
	public void setQuery(String _query)
	{
		query = _query;
	}

		//Getter for color field
	public Color getColor()
	{
		return color;
	}

	//Setter for startPoint field
	public void setColor(Color _color)
	{
		color = _color;
	}
}