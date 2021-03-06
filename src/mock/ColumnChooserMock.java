package mock;

import database.Column;

import javax.swing.*;
import java.util.LinkedList;
import java.util.ListIterator;

/* This class will serve as the User Interface for 
* loading the database and selecting the columns
* the user wishes to visualize.
*@author Spencer Bialt
*@date 04/10/2016 
*/
public class ColumnChooserMock
{
	private LinkedList<Column> columnList;
	//Must be initialized with titles and types of data table columns
	//Constructor will create a JFrame containing a Scroll Pane of 
	//checkboxes that allow the user to select their desired data
	protected ColumnChooserMock(LinkedList<Column> _columnList)
	{
		//Prepare column list for iteration
		columnList = _columnList;
		ListIterator<Column> iterator = columnList.listIterator();

		//Set up frame with scrollbar
		JFrame frame = new JFrame("Select Database Columns");
		JScrollPane scrollPane = new JScrollPane(frame);
		frame.getContentPane().add(scrollPane);
		//iterate through list, add checkbox for each column
		while(iterator.hasNext())
		{
			//Create a new JCheckbox with the column title and type displayed
			Column temp = iterator.next();
			String checkBoxString = temp.getName() +
					" (" + temp.getType() + ")";
			JCheckBox checkBox = new JCheckBox(checkBoxString, true);

			//Add the check to the frame
			frame.getContentPane().add(checkBox);
		}

		//Now that all checkboxes are added, display the frame
		frame.pack();
		frame.setVisible(true);
	}

	/*Class will return the list of columns chosen by the user
	*/
	public LinkedList<Column> getSelectedColumnList()
	{
		//Must add logic to determine what is currently checked off 
		return columnList;
	}
}
