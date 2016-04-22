import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
 
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
        String url;
        Connection conn;
        Statement stmt;
        url = "jdbc:oracle:thin:@//localhost:1521/cablocal";
        conn = DriverManager.getConnection(url, "uret01", "rowan");
        tableName = "ac_listrw15150805";
        stmt = conn.createStatement();
        String query = "select * from " + tableName;
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData metadata = rs.getMetaData();
        int columnCount = metadata.getColumnCount();    
        String outputString = "";
        for (int i = 1; i <= columnCount; i++)
         {
            outputString += metadata.getColumnName(i) + ", ";      
        }
        outputString += "\n";
        while (rs.next()) 
        {
        String row = "";
            for (int i = 1; i <= columnCount; i++) {
                row += rs.getString(i) + ", ";  
        outputString += row + "\n";        
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
