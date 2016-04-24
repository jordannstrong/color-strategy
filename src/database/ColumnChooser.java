package database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;

/* This class will serve as the User Interface for 
* loading the database and selecting the columns
* the user wishes to visualize.
*@author Spencer Bialt
*@date 04/10/2016 
*/
public class ColumnChooser
{
	private LinkedList<Column> columnList;
	//Must be initialized with titles and types of data table columns
	//Constructor will create a JFrame containing a Scroll Pane of 
	//checkboxes that allow the user to select their desired data
	protected ColumnChooser(LinkedList<Column> _columnList)
	{
		//Prepare column list for iteration
		columnList = _columnList;
		ListIterator<Column> iterator = columnList.listIterator();

		//Set up frame with scrollbar
		JFrame frame = new JFrame("Select Database Columns");
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setLayout(null);
		scrollPanel.setBounds(0, 0, 300, 300);

		int currentYPos = 10;


		//Create action listener that will handle items being checked
		    ActionListener actionListener = new ActionListener() {
      		public void actionPerformed(ActionEvent actionEvent) {
        	AbstractButton abstractButton =
					(AbstractButton) actionEvent.getSource();
        	boolean selected = abstractButton.getModel().isSelected();
        	if(!selected)
        	{
        		//Unchecked, remove it from column list
        		//Get column name from checkbox
        		String checkBoxText = abstractButton.getText();
        		String[] split = checkBoxText.split("()");


        		//Iterate through list, find the column and remove it
        		ListIterator<Column> itty = (ListIterator<Column>) columnList.iterator();
        		while(iterator.hasNext())
        		{
        			Column temp = iterator.next();
        			if(temp.getName().equals(split[0]))
        				iterator.remove();
        		}
        	}
        	else
        	{
        		//Rechecked, add it back to column list
        		 //Get column name and type from checkbox
        		String checkBoxText = abstractButton.getText();
        		String[] split = checkBoxText.split("()");
        		Column temp = new Column();
        		temp.setName(split[0]);
        		temp.setValue(split[1].substring(0, length-1));
        	}
      	}
    	};

		//iterate through list, add checkbox for each column
		while(iterator.hasNext())
		{
			//Create a new JCheckbox with the column title and type displayed
			Column temp = iterator.next();
			String checkBoxString = temp.getName() + "(" + temp.getType() +
					")";
			JCheckBox checkBox = new JCheckBox(checkBoxString, true);
			checkBox.setBounds(10, currentYPos, 300, 30);
			currentYPos += 25;

			//Add the check to the frame
			frame.getContentPane().add(checkBox);
		}

		//Now that all checkboxes are added, display the frame
		frame.add(scrollPanel);
		frame.setLayout(null);
		frame.setSize(300, 300);
     	frame.setLocationRelativeTo(null);
     	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/*Class will return the list of columns chosen by the user
	*/
	public LinkedList<Column> getSelectedColumnList()
	{		
		return columnList;
	}
}
