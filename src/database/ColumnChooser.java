import java.awt.BorderLayout;
import java.awt.GridLayout;
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
public class ColumnChooser extends JFrame
{
    private LinkedList<Column> columnList;
    private JCheckBox allFlightsBox;
    //Must be initialized with titles and types of data table columns
    //Constructor will create a JFrame containing a Scroll Pane of 
    //checkboxes that allow the user to select their desired data
    protected ColumnChooser(LinkedList<Column> _columnList) 
    {
        //Prepare column list for iteration
        columnList = _columnList;
        ListIterator<Column> iterator = columnList.listIterator();
        //Set up frame with scrollbar
        JPanel panel  = new JPanel();
        panel.setSize(300, 400);
        panel.setLayout(new GridLayout(13, 6, 10, 0));
        setTitle("Select Data Columns");
        JScrollPane scrollPanel = new JScrollPane();
        scrollPanel.setLayout(null);
        scrollPanel.setBounds(0, 0, 300, 400);

        int currentYPos = 10;
        
        //Create action listener that will handle items being checked
       ActionListener checkBoxListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            AbstractButton abstractButton =
                    (AbstractButton) actionEvent.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            if(!selected)
            {
                //Unchecked, remove it from column list
                //Get column name from checkbox
                String checkBoxText = abstractButton.getText();
                String currentName = checkBoxText.trim();


                //Iterate through list, find the column and remove it
                ListIterator<Column> itty = (ListIterator<Column>) columnList.iterator();
                while(itty.hasNext())
                {
                    Column temp = itty.next();
                    if(temp.getName().equals(currentName))
                    {
                        temp.makeHidden();
                    }
                }
            }
            else
            {
                //Rechecked, add it back to column list
                 //Get column name and type from checkbox
                String checkBoxText = abstractButton.getText();
                String currentName = checkBoxText.trim();
                Column temp = new Column();
                temp.makeVisible();
            }
        }
        };
        
        //iterate through list, add checkbox for each column
        while(iterator.hasNext())
        {
            //Create a new JCheckbox with the column title and type displayed
            Column temp = iterator.next();
            String checkBoxString = temp.getName();
            if(checkBoxString.contains("MIN") || checkBoxString.contains("MAX"))
            {
                iterator.remove();
            }
            else
            {
                JCheckBox checkBox = new JCheckBox(checkBoxString, true);
                checkBox.setBounds(10, currentYPos, 325, 30);
                currentYPos += 25;
                checkBox.addActionListener(checkBoxListener);
                //Add the check to the frame
                panel.add(checkBox);
            }
        }
        
        //Checkbox for including ALL flights or just the ones that match certain params
        allFlightsBox = new JCheckBox("Include all flights", true);
        allFlightsBox.setBounds(10, currentYPos, 325, 30);
        //Add the check to the frame
        panel.add(allFlightsBox);

        //Create "ok" button
        JButton button = new JButton("Accept");
        button.setLayout(null);
        button.setBounds(10, currentYPos + 10, 80, 30);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                ParameterChooser pc = new ParameterChooser(columnList);
            }
        });
        
        JButton button2 = new JButton("Close");
        button2.setLayout(null);
        button2.setBounds(120, currentYPos + 10, 80, 30);
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(1);
            }
        });
               
        panel.add(button);
        panel.add(button2);
        scrollPanel.add(panel);
        //Now that all checkboxes are added, display the frame
        
        getContentPane().add(scrollPanel, BorderLayout.CENTER);
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*Class will return the list of columns chosen by the user
    */
    public LinkedList<Column> getSelectedColumnList()
    {       
        if(allFlightsBox.isSelected())
        {   
            Column temp = new Column();
            temp.setName("ALLFLIGHTS");
            columnList.add(temp);
        }
        return columnList;
    }
}
