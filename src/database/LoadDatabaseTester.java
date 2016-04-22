import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

 
/**
 * Test class for LoadDatbase submodule,
 * will output column results to a .txt file.
 * @author (Spencer Bialt) 
 * @version ()
 */
public class LoadDatabaseTester
{
    public static void main(String[] args)
    {
        LoadDatabase loadDB = new LoadDatabase();
        LinkedList<Column> columnList = loadDB.getColumnList();
        printResults(columnList);
    }
    
    public void printResults(LinkedList<Column> columnList)
    {
        //First create an output string to write to file
        String outputString = "NAME              TYPE \n";
        //Iterate through data columns and add them to file
        ListIterator<Column> iterator = columnList.listIterator();
        while(iterator.hasNext())
        {
            Column temp = iterator.next();
            outputString = temp.getName() + "    " + temp.getType() + "\n";
        }
        
            //Change to desired location of output file
            String destination = "";
            File file = new File(destination);
        
        	FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.close();
    }
}
