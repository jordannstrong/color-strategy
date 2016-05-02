package database;

import java.util.*;
import java.io.File;

public class ColumnFileReader
{
    // instance variables - replace the example below with your own
    LinkedList<Column> columnList;
    public ColumnFileReader(String filename)
    {
        File file = new File(filename);
         if(file.exists())
        {
            try
            {
                Scanner s = new Scanner(file).useDelimiter(",");
                int counter = 0;
                columnList = new LinkedList<Column>();
                
                while( s.hasNext() && counter < 35 )
                {
                    String temp = s.next().trim();
                    Column column = new Column();
                    column.setName(temp);
                    columnList.add(column);
                    counter++;
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }   
        }
    }
    
    public LinkedList<Column> getColumnList()
    {
        return columnList;
    }
}