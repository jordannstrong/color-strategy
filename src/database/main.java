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
        String arg;
        //initialise instance variables
        if(args.length == 0) arg = "res/ac_list200.csv"; // if run with no args, use this as demo
        else arg = args[0];

        ColumnFileReader cfr = new ColumnFileReader("res/ac_listrw15150805.csv");
        ValueFileReader vfr = new ValueFileReader("res/sample_values2.csv", cfr.getColumnList());
        LinkedList<Column> columnList = vfr.getColumnList();
        ColumnChooser cc = new ColumnChooser(columnList, arg);
        ListIterator<Column> iterator = columnList.listIterator();
    }
}
