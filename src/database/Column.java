package color;

/*
* This class is a representation of a single column of an
*  OracleDB data table. It contains two String fields,
* the name of the column and the data type of the column. 
* @date  4/02/2016
*/
public class Column
{
	private String name;
	private String type;

	//Constructor
	public Column()
	{

	}
	
	//Getter for name field
	public getName()
	{
		return name;
	}

	//Setter for name field
	public setName(String _name)
	{
		name = _name;
	}

	//Getter for type field
	public getType()
	{
		return type;
	}

	//Setter for type field
	public setType(String _type)
	{
		return type;
	}
}
