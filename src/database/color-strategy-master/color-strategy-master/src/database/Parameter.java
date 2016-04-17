package color;
import java.awt.Color;

/*
* This class is a representation of a Parameter object that will be used to sort the matching Flights.
* It contains a Color object representing the color any objects matching that parameter will be set to,
* and two string fields that represent its value and name. 
*
* @author Spencer Bialt
* @date  4/02/2016
*/


public class Flight
{
	private String value;
	private String name;
	private Color color;

	//Constructor
	public Flight()
	{

	}
	
	//Getter for value field
	public getParameterValue()
	{
		return value;
	}

	//Setter for value field
	public setParameterValue(String _value)
	{
		value = _value;
	}

	//Getter for name field
	public getParameterName()
	{
		return name;
	}

	//Setter for name field
	public setParameterName(String _name)
	{
		name = _name;
	}

		//Getter for color field
	public getParameterColor()
	{
		return color;
	}

	//Setter for startPoint field
	public setParameterColor(Color color)
	{
		color = _color;
	}
}
