package color;
import java.awt.Color;

/*
* This class is a representation of a SQL Query, that also stores the associated color
* for Flight objects matching the Query. 
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
		color = new Color();
	}
	

	//Getter for query field
	public getQuery()
	{
		return query;
	}

	//Setter for query field
	public setQuery(String _query)
	{
		query = _query;
	}

		//Getter for color field
	public getColor()
	{
		return color;
	}

	//Setter for startPoint field
	public setColor(Color color)
	{
		color = _color;
	}
}