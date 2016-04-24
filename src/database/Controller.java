package database;
import java.sql.SQLException;

/*This class will serve as a controller for the other classes.
*@author Spencer Bialt
*@date 04/02/2016
*/
public class Controller
{
	private LoadDatabase loadDB;
	private ColumnChooser columnChooser;
	private ParameterChooser parameterChooser;
	private SQLQueryBuilder sqlQueryBuilder;


	private Controller() throws SQLException {
		loadDB = new LoadDatabase();
		columnChooser = new ColumnChooser(loadDB.getColumnList());
		parameterChooser =
				new ParameterChooser(columnChooser.getSelectedColumnList());
		sqlQueryBuilder =
				new SQLQueryBuilder(parameterChooser.getParameterList());
	}
}