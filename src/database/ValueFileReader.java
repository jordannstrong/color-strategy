import java.util.*;
import java.io.File;

public class ValueFileReader
{
    private LinkedList<Column> columnList;
    public ValueFileReader(String filename, LinkedList<Column> _columnList)
    {
        File file = new File(filename);
        columnList = _columnList;
        if(file.exists())
        {
            try
            {
                Scanner s = new Scanner(file).useDelimiter(",");
                                
                //load in 10 sample records
                for(int i = 0; i < 10; i++)
                {
                    int currentValCount = 0;
                    ListIterator<Column> iterator = columnList.listIterator();
                    //Add a record's value to the column list
                    while(iterator.hasNext())
                    {  
                        Column temp = iterator.next();
                        if(s.hasNext())
                        {
                            String val = s.next().trim();
                            currentValCount++;
                            temp.addValue(val);
                        }
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }   
        }
    }
    
    public LinkedList<Column> getColumnList()
    {
        return columnList;
    }
}