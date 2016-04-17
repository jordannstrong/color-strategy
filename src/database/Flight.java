package color;
import java.awt.Color;

/*
* This class is a representation of a Flight object that will be written to a KML file.
* It contains a Color object representing the color it will be set to, along with a string
* representation of its starting and ending coordinates.
*
* @author Spencer Bialt
* @date  4/02/2016
*/


public class Flight
{
	private String startPoint;
	private String endPoint;
	private Color color;

	//Constructor
	public Flight()
	{

	}
	
	//Getter for startPoint field
	public getStartPoint()
	{
		return startPoint;
	}

	//Setter for startPoint field
	public setStartPoint(String _startPoint)
	{
		startPoint = _startPoint;
	}

	//Getter for endPoint field
	public getEndPoint()
	{
		return endPoint;
	}

	//Setter for endPoint field
	public setEndPoint(String _endPoint)
	{
		endPoint = _endPoint;
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
