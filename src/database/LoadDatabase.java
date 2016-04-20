package database;
import java.sql.*;
import java.util.*;

/*
* This class query an OracleDB for it's column titles.
* The column titles will then be passed to the user who
* can select the data they wish to analyze.
* @author Spencer Bialt
* @date  3/31/2016
*/
public class LoadDatabase
{
	private String tableName;
	private String url;
	private Connection conn;
	private Statement stmt;
	private LinkedList<Column> columnList;
	/*
	* Constructor for LoadDatabase. Will create a SQL connection,
	* query for the Column titles, and return them as a list.
	*/
	private LoadDatabase() throws SQLException
	{
		columnList = new LinkedList<>();
		//Connection URL, ID and tableName provided by Chu Yao
		url = "jdbc:oracle:thin:@//localhost:1521/cablocal";
		conn = DriverManager.getConnection(url, "uret01", "rowan");
		tableName = "ac_listrw15150805";
		stmt = conn.createStatement();

		/*
		* This query will return both the column names and data types from the
		* table
		*/
		String query =
				"select * from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='"
						+ tableName + "'";
		ResultSet columnSet = stmt.executeQuery(query);
		
		//Use a while loop to iterate through the results set
		while(columnSet.next())
		{
			Column temp = new Column();
			/*
			* These statements will retrieve Column Name and DataType from the
			* results seet
			*/
			temp.setName(columnSet.getString("COLUMN_NAME"));
			temp.setType(columnSet.getString("DATA_TYPE"));
			columnList.add(temp);
		}

		//Finished storing columns, close database connection
		stmt.close();
		conn.close();
	}

	//@return the list of columns populated by the LoadDatabase constructor
	public LinkedList getColumnList()
	{
		return columnList;
	}
}


