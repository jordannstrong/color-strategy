package database;
import java.util.LinkedList;

/* This class will serve as the User Interface for 
* selecting which flights the user wishes to visualize
* via matching Parameters.
*@author Spencer Bialt
*@date 04/10/2016 
*/
public class ParameterChooser
{
	private LinkedList<Parameter> parameterList;
	private LinkedList<Column> columnList;
	private LinkedList<JComboBox> nameBoxes;
	private LinkedList<JComboBox> valueBoxes;
	
	//Must be initialized with the 
	protected ParameterChooser(LinkedList<Column> _colummList)
	{
		nameBoxes = new LinkedList<JComboBox>;
		valueBoxes = new LinkedList<JComboBox>;

		//Prepare column list for iteration
		columnList = _colummList;
		ListIterator<Column> iterator = columnList.listIterator();

		//Set up frame and panel
		JFrame frame = new JFrame("Select Data Colors");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 400, 300);


		/*
		iterate through list, create a string [] of the columns for the
		* JComboBoxes
		* */
		String[] names = new String[columnList.size()];
		String[] values = new String[columnList.size()];
		int count = 0;
		while(iterator.hasNext())
		{
			Column temp = iterator.next();
			names[count] = temp.getName();
			values[count] = temp.getValue();
			count++;
		}

		//Now that we have the column names ready, create list of name boxes
		for(int i = 0; i < 5; i++)
		{
			JComboBox nameBox = new JComboBox(names);
			JComboBox valueBox = new JComboBox(values);
			int yPos = 10 + i * 25;
			nameBox.setBounds(10, yPos, 150, 30);
			valueBox.setBound(165, yPos, 150, 30);
			panel.add(nameBox);
			panel.add(valueBox);
		}

		//Now that all combo boxes are added, display the frame
		frame.add(panel);
		frame.setLayout(null);
		frame.setSize(400, 300);
     	frame.setLocationRelativeTo(null);
     	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/*Class will return the list of parameters chosen by the user
	*/
	public LinkedList<Parameter> getParameterList()
	{		
		ListIterator nameItty = nameBoxes.listIterator();
		ListIterator valueItty = valueBoxes.listIterator();

		while(nameItty.hasNext() && valueBoxes.hasNext())
		{
			JComboBox nameBox = nameItty.next();
			JComboBox valueBox = valueItty.next();

			String name = String.valueof(nameBox.getSelectedItem());
			String value = String.valueof(valueBox.getSelectedItem());

			Parameter param = new Parameter();
			param.setName(name);
			param.setValue(value);

			parameterList.add(param);
		}

		return parameterList;
	}
}
