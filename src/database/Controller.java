package database;
import java.util.*;

/*This class will serve as a controller for the other classes.
*@author Spencer Bialt
*@date 04/02/2016
*/
public class Controller
{
	private LoadDatabase loadDB();
	private LoadDatabaseUI loadDBUI();

	private Controller()
	{
		loadDB = new LoadDatabase();
		loadDBUI = new LoadDatabaseUI(loadDB.getColumnList());

	}
}