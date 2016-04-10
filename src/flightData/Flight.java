package flightData;

/**
 * Created by Kevin on 3/29/2016;   Modified by Ryan on 4/9/2016
 *
 * Flight class is a container for all the information about a single flight.
 * This information contains the ID of the Flight, the starting and ending
 * airports, the color of the flight path, as well as a list of all the
 * datapoints along the flight's path.
 */
public class Flight {
    private String flightID;
    private String startPoint;
    private String endPoint;
    //private Color pathColor;


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
        this.startPoint = null;
        this.endPoint = null;
        //this.pathColor = null;
    }//end Flight

    /**
     * The regular constructor for Flight. Assumes that all information except for
     * the color of the Flight was provided, and sets appropriate fields.
     */
    public Flight(String flightID, String startPoint, String endPoint){
        this.flightID = flightID;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        //this.pathColor = null;
    }// end Flight

    /**
     * The regular constructor for Flight. Assumes that all information needed
     * was provided and the appropriate fields are set to their variables.
     */
    public Flight(String flightID, String startPoint, String endPoint, Color pathColor){
        this.flightID = flightID;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.pathColor = pathColor;
    }// end Flight

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
    public void setStartPoint(String startPoint){
        this.startPoint = startPoint;
    }// end setStartPoint

    /**
     * Mutator method to set the endPoint field of the Flight
     *
     * @param endPoint A String containing the ending airport's name
     */
    public void setEndPoint(String endPoint){
        this.endPoint = endPoint;
    }// end setEndPoint

    /**
     * Mutator method to set the pathColor field of the Flight
     *
     * @param pathColor A Color object containing the color value of the path
     */
    public void setColor(Color pathColor) {
        this.pathColor = pathColor;
    }// end setColor

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
    public String getStartPoint(){
        return this.startPoint;
    }// end getStartPoint

    /**
     * Accessor method to get the endPoint field of the Flight
     *
     * @return A String of the endPoint
     */
    public String getEndPoint(){
        return this.endPoint;
    }// end getEndPoint

    /**
     * Accessor method to get the pathColor field of the Flight
     *
     * @return A Color object containing the color of the path
     */
    public Color getColor(){
        return this.pathColor;
    }// end getColor

}// end Flight
