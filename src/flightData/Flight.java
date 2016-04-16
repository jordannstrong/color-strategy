package flightData;

import java.awt.Color;
import java.util.List;

/**
 * Created by Kevin on 3/29/2016;   Modified by Ryan on 4/9/2016
 *
 * Flight class is a container for all the information about a single flight.
 * This information contains the ID of the Flight,
 * the color of the flight path, as well as a list of all the
 * datapoints along the flight's path.
 */
public class Flight {
    private String flightID;
    private List<double[]> coordinateList; // where each coordinate triple is formatted: lon,lat,alt
    private Color pathColor;


    /**
     * Main method if we need one. Right now it just prints out "Hello" like it
     * does in the DistanceCalculator
     */
    public static void main(String[] args) {
        System.out.println("Hello.");
    }// end Main


    /**
     * The default constructor for Flight. Assumes that there was no information
     * about the Flight provided and sets all field values to null.
     */
    public Flight(){
        this.flightID = null;
        //this.pathColor = null;
    }//end Flight

    /**
     * The regular constructor for Flight. Assumes that all information except for
     * the color of the Flight was provided, and sets appropriate fields.
     */
    public Flight(String flightID, String startPoint, String endPoint, double[] startCoordinate, double[] endCoordinate){
        this.flightID = flightID;
        if (startCoordinate.length != 2 || endCoordinate.length != 2) {
            throw new IllegalArgumentException();
        }
        //this.pathColor = null;
    }// end Flight

    /**
     * The regular constructor for Flight. Assumes that all information needed
     * was provided and the appropriate fields are set to their variables.
     */
   /* public Flight(String flightID, String startPoint, String endPoint, Color pathColor){
        this.flightID = flightID;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pathColor = pathColor;
    }// end Flight*/

    /**
     * Mutator method to set the FlightID field of the Flight
     *
     * @param flightID A String containing the flight ID value
     */
    public void setFlightID(String flightID){
        this.flightID = flightID;
    }// end setFlightID

    /**
     * Mutator method to set the startPoint field of the Flight
     *
     * @param startPoint A String containing the initial airport's name
     */

    /**
     * Mutator method to set the pathColor field of the Flight
     *
     * @param pathColor A Color object containing the color value of the path
     */
    /*public void setColor(Color pathColor) {
        this.pathColor = pathColor;
    }// end setColor*/

    /**
     * Accessor method to get the FlightID field of the Flight
     *
     * @return A String of the FlightID
     */
    public String getFlightID(){
        return this.flightID;
    }// end getFlightID

    /**
     * Accessor method to get the startPoint field of the Flight
     *
     * @return A String of the startPoint
     */

    /**
     * Accessor method to get the pathColor field of the Flight
     *
     * @return A Color object containing the color of the path
     */
    /*public Color getColor(){
        return this.pathColor;
    }// end getColor*/

}// end Flight
