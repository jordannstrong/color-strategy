package database;

import java.util.*;
import java.sql.*;
/**
 * Launch a demo of ColumnChooser --> ParameterChooser
 * 
 * @author (Spencer Bialt) 
 * @version (1.0)
 */
public class main
{
    /**
     * Constructor for objects of class main
     */
    public static void main(String[] args)
    {
        
        //initialise instance variables
        if(args.length > 0)
        {
           ColumnFileReader cfr = new ColumnFileReader(args[0]);
           ValueFileReader vfr = new ValueFileReader(args[1], cfr.getColumnList());
           LinkedList<Column> columnList = vfr.getColumnList();
           ColumnChooser cc = new ColumnChooser(columnList);
           ListIterator<Column> iterator = columnList.listIterator();
           
           
        }
    }
}
