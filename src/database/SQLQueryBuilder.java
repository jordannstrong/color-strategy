package database;
import java.util.*;
import java.awt.Color;

/*
* This class will build a list of SQLQuery objects, which stores
* a SQL query for a user selected parameter and its associated color.
*
* @author Spencer Bialt
* @date  4/15/2016
*/
public class SQLQueryBuilder
{
	private LinkedList<Parameter> parameterList;
	private LinkedList<Query> queryList;
	private String tableName;
	
	/*
	* Constructor for SQLQueryBuilder. Will create a list of SQLQuery objects
	* based on the parameters selected by the user. 
	*/
	private SQLQueryBuilder(LinkedList<Parameter> _parameterList)
	{
		//Prepare the list of perameters for iteration
		parameterList = _parameterList;
		ListIterator<Parameter> iterator = parameterList.listIterator();

		//tableName provided by Chu Yao
		tableName = "ac_listrw15150805";

		//Iterator through parameter list, build query objects and add them to queryList
		while(iterator.hasNext())
		{	
			//First get all the information from the parameter
			Parameter temp = iterator.next();
			String name = temp.getParameterName();
			String value = temp.getParameterValue();
			Color color = temp.getParameterColor();
			
			//Now build the query object
			Query queryObject = new Query();
			String query = "select * from " + tableName + " where " + name + " = '" + value + "'";
			queryObject.setQuery(query);
			queryObject.setColor(color);

			queryList.add(queryObject);
		}
	}

	//Now that the query has been built, it is available for return
	public LinkedList<Query> getQueryList()
	{
		return queryList;
	}
}
