import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.LinkedList;
import java.util.ListIterator;

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
    private LinkedList<JComboBox> evalBoxes;
    private LinkedList<JComboBox> valueBoxes;
    
    //Must be initialized with the 
    protected ParameterChooser(LinkedList<Column> _colummList)
    {
        nameBoxes = new LinkedList<JComboBox>();
        evalBoxes = new LinkedList<JComboBox>();
        valueBoxes = new LinkedList<JComboBox>();

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
        String[] names = new String[columnList.size() + 1];
        names[0] = "";
        //LinkedList<String[]> values = new LinkedList<String[]>();
        int count = 1;
        while(iterator.hasNext())
        {
            Column temp =  iterator.next();
            names[count] = temp.getName();
            /*
            //ListIterator<String> itty = temp.getValues().listIterator();
            //String[] vals = new String[5];
            int arrayCount = 0;
            while(itty.hasNext())
            {
                vals[arrayCount] = itty.next();
                arrayCount++;
            }
            */
            count++;
        }
        
        
        //create list of evaluators
        String[] evals = new String[5];
        evals[0] = "=";
        evals[1] = ">";
        evals[2] = "<";
        evals[3] = ">=";
        evals[4] = "<=";

        //Now that we have the column names ready, create list of name boxes
        for(int i = 0; i < 5; i++)
        {
            JComboBox nameBox = new JComboBox(names);
            JComboBox evalBox = new JComboBox(evals);
            JComboBox valueBox = new JComboBox();
            int yPos = 15 + i * 25;
            nameBox.setBounds(10, yPos, 150, 30);
            evalBox.setBounds(165, yPos, 20, 30);
            valueBox.setBounds(190, yPos, 150, 30);
            panel.add(nameBox);
            panel.add(evalBox);
            panel.add(valueBox);
            
            nameBoxes.add(nameBox);
            evalBoxes.add(evalBox);
            valueBoxes.add(valueBox);
            
            nameBox.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent actionEvent) {
                    valueBox.removeAllItems();
                    ListIterator<Column> itty = columnList.listIterator();
                    while(itty.hasNext())
                    {
                        Column temp = itty.next();
                        if(temp.getName().equals(String.valueOf(nameBox.getSelectedItem())))
                            {
                                for(String str : temp.getValues())
                                    valueBox.addItem(str);
                            }
                     }
                }
            });            
        }

        
        JButton button = new JButton("Accept");
        button.setLayout(null);
        button.setBounds(10, 200 + 10, 100, 30);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent) {
                getParameterList();
                System.out.println(parameterList);
                
            }
        });
        
        JButton button2 = new JButton("Close");
        button2.setLayout(null);
        button2.setBounds(120, 200 + 10, 100, 30);
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(1);
            }
        });
        
        //Now that all combo boxes are added, display the frame
        frame.add(button);
        frame.add(button2);
        frame.add(panel);
        frame.setLayout(null);
        frame.setSize(500, 300);
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

        while(nameItty.hasNext() && valueItty.hasNext())
        {
            JComboBox nameBox = (JComboBox) nameItty.next();
            JComboBox valueBox = (JComboBox) valueItty.next();

            String name = String.valueOf(nameBox.getSelectedItem());
            String value = String.valueOf(valueBox.getSelectedItem());

            Parameter param = new Parameter();
            param.setParameterName(name);
            param.setParameterValue(value);

            parameterList.add(param);
        }

        return parameterList;
    }
}
