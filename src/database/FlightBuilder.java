package database;
import java.util.*;
import java.awt.Color;
import java.sql.*;

/*
* This class will build a list of Flight objects, which contains the 
* start and end points for a Flight, as well as its color if determined
* by the user.
*
* @author Spencer Bialt
* @date  4/15/2016
*/
public class FlightBuilder
{
	private LinkedList<Flight> flightList;
	private LinkedList<Query> queryList;
	private String url;
	private Connection conn;
	private Statement stmt;

	/*
	* Constructor for FlightBuilder. Will create a list of Flight objects
	* that have a start point, end point, and color 
	*/
	private FlightBuilder(LinkedList<Query> _queryList) throws SQLException
	{
		//Prepare the list of queries for iteration
		queryList = _queryList;
		ListIterator<Query> iterator = queryList.listIterator();

		//Prepare the connection
		url = "jdbc:oracle:thin:@//localhost:1521/cablocal";
		conn = DriverManager.getConnection(url, "uret01", "rowan");

		/*
		* Iterator through query list, build Flight objects and add them to
		* flightList
		*/
		while(iterator.hasNext())
		{	
			//First need to make the query to the SQL database
			Query temp = iterator.next();
			String query = temp.getQuery();
			ResultSet flightData = stmt.executeQuery(query);

			/*
			* Now need to build the Flight object from the resulting
			* information
			* */

			if(flightData.next())
			{
				Flight flight = new Flight();
				flight.setStartPoint(flightData.getString("DEPARTLOC"));
				flight.setEndPoint(flightData.getString("ENDLOC"));
			}

			flightObject.setColor(color);
			flightList.add(flightObject);
		}
	}

	//Now that the query has been built, it is available for return
	public LinkedList<Flight> getFlightList()
	{
		return flightList;
	}
}
