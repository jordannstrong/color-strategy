import java.util.*; 

/*
* This class is a representation of a single column of an
*  OracleDB data table. It contains two String fields,
* the name of the column and the data type of the column. 
* @date  4/02/2016
*/
public class Column
{
    private String name;
    private String type;
    private LinkedList<String> values;
    private boolean hidden;
    
    //Constructor
    public Column()
    {
        values = new LinkedList<String>();
        hidden = false;
    }
    
    //Getter for name field
    public String getName()
    {
        return name;
    }
    
    public void makeHidden()
    {
        hidden = true;
    }
    
    public void makeVisible()
    {
        hidden = false;
    }
    
    public boolean isHidden()
    {
        return hidden;
    }
    

    //Setter for name field
    public void setName(String _name)
    {
        name = _name;
    }

    //Getter for type field
    public String getType()
    {
        return type;
    }

    //Setter for type field
    public void setType(String _type)
    {
        type = _type;
    }

    //Getter for value field
    public LinkedList<String> getValues()
    {
        return values;
    }

    //Setter for v  alue field
    public void setValues(LinkedList<String> _values)
    {
        values = _values;
    }
    
    public void addValue(String _value)
    {
        values.add(_value);
    }
}
