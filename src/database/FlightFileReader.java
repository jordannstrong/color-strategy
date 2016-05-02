import java.util.*;
import java.io.File;
import java.awt.Color;

public class FlightFileReader
{
    // instance variables - replace the example below with your own
    LinkedList<Parameter> parameterList;
    LinkedList<Flight> flightList;
    boolean allFlights;
    public FlightFileReader(String filename, LinkedList<Parameter> _parameterList, boolean _allFlights)
    {
        flightList = new LinkedList<Flight>();
        File file = new File(filename);
        allFlights = _allFlights;
        if(file.exists())
        {
            parameterList = _parameterList;
            ListIterator<Parameter> iterator = parameterList.listIterator();
            try
            {
                Scanner s = new Scanner(file).useDelimiter(",");
                int counter = 0;
                String[] columnNames = new String[35];
                
                //Move scanner past all Column names, store them in an array
                while( s.hasNext() && counter < 35 )
                {
                    String temp = s.next().trim();
                    columnNames[counter] = temp;
                    counter++;
                }
                
                
                //Find out what parameters we have
                int[] paramKeys = new int[5];
                String[] paramValues = new String[5];
                String[] paramEvals = new String[5];
                Color[] paramColors = new Color[5];
                int paramCounter = 0;
                while(iterator.hasNext())
                {
                    Parameter temp = iterator.next();
                    paramValues[paramCounter] = temp.getParameterValue();
                    paramEvals[paramCounter] = temp.getEval();
                    paramColors[paramCounter] = temp.getParameterColor();
                    for(int i = 0; i < 35; i++)
                    {
                        if(columnNames[i].equals(temp.getParameterName()))
                        {
                            paramKeys[paramCounter] = i;
                        }                        
                    }
                    paramCounter++;
                }
                
                
                //Scan the remaining flights, see if they match, assign color and coordinates
                while(s.hasNext())
                {
                    Flight flight = new Flight();
                    for(int i = 0; i < 35; i++)
                    {
                        String val = s.next().trim();
                        String eval = "";
                        String paramVal = "";
                        int currentKey = 10;
                        boolean match = true;
                        
                        if(i == 9)
                        {
                            Double d = Double.parseDouble(val);
                            flight.setMaxX(d);
                        }
                        if(i == 10)
                        {
                            Double d = Double.parseDouble(val);
                            flight.setMinX(d);
                        }
                        if(i == 11)
                        {
                            Double d = Double.parseDouble(val);
                            flight.setMaxY(d);
                        }
                        if(i == 12)
                        {
                            Double d = Double.parseDouble(val);
                            flight.setMinY(d);
                        }                        
                        if(i == paramKeys[0])
                        {
                            currentKey = 0;
                            eval = paramValues[0];
                            paramVal = paramValues[0];
                        }
                        else if(i == paramKeys[1])
                        {
                            currentKey = 1;
                            eval = paramValues[1];
                            paramVal = paramValues[1];
                        }
                        else if(i == paramKeys[2])
                        {
                            currentKey = 2;
                            eval = paramValues[2];
                            paramVal = paramValues[2];
                        }
                        else if(i == paramKeys[3])
                        {
                            currentKey = 3;
                            eval = paramValues[3];
                            paramVal = paramValues[3];
                        }
                        else if(i == paramKeys[4])
                        {
                            currentKey = 4;
                            eval = paramValues[4];
                            paramVal = paramValues[4];
                        }
                        else
                        {
                            match = false;
                        }
                        
                        //This flight could be a potential match, must compare
                        if(match)
                        {
                            try{                                
                                if(eval.equals("="))
                                {
                                    if(val.equals(paramVal))
                                        flight.setPathColor(paramColors[currentKey]);
                                }
                                else
                                {
                                    double val1 = Double.parseDouble(val);
                                    double val2 = Double.parseDouble(paramVal);
                                    if(eval.equals(">"))
                                    {
                                        if(val1 > val2)
                                        {
                                            flight.setPathColor(paramColors[currentKey]);
                                        }
                                    }
                                    else if(eval.equals("<"))
                                    {
                                        if(val1 < val2)
                                        {
                                            flight.setPathColor(paramColors[currentKey]);
                                        }
                                    }
                                    else if(eval.equals(">="))
                                    {
                                        if(val1 >= val2)
                                        {
                                            flight.setPathColor(paramColors[currentKey]);
                                        }
                                    }
                                    else if(eval.equals("<="))
                                    {
                                        if(val1 <= val2)
                                        {
                                            flight.setPathColor(paramColors[currentKey]);
                                        }
                                    }
                                }
                            }
                            catch(Exception e)
                            {
                            }
                        }
                  }
                  flightList.add(flight);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }   
        }
    }    
    public LinkedList<Flight> getFlightList()
    {
        return flightList;
    }
}