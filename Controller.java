import java.util.*;
package color;

/*This class will serve as a controller for the other classes.
*@author Spencer Bialt
*@date 04/02/2016
*/
private class Controller
{
	private LoadDatabase loadDB();
	private LoadDatabaseUI loadDBUI();

	private Controller()
	{
		loadDB = new LoadDatabase();
		loadDBUI = new LoadDatabaseUI(loadDB.getColumnList());

	}
}