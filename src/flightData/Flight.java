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
public class Flight
{
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
	 *
	 * @param flightID
	 * @param coordinateList List of coordinate triples where each triple is of
	 *                       the form lon,lat,alt
	 * @param pathColor
	 */
	public Flight(String flightID, List<double[]> coordinateList,
				  Color pathColor){
		this.flightID = flightID;
		this.coordinateList = coordinateList;
		this.pathColor = pathColor;

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

    public Color getPathColor() {
        return pathColor;
    }

    public void setPathColor(Color color) {
        pathColor = color;
    }

    public String getKMLColor() {
        String sRGB = Integer.toHexString(pathColor.getRGB());

		return sRGB.substring(0, 2) + sRGB.substring(6, 8) +
				sRGB.substring(4, 6) + sRGB.substring(2, 4);
	}

    public List<double[]> getCoordinateList() {
        return coordinateList;
    }

	public double[] getStartCoordinate() {
		return coordinateList.get(0);
	}

	public double[] getEndCoordinate() {
		return coordinateList.get(coordinateList.size()-1);
	}

	public double getSlope() {
		double x1 = getStartCoordinate()[0];
		double y1 = getStartCoordinate()[1];
		double x2 = getEndCoordinate()[0];
		double y2 = getEndCoordinate()[1];

		return y2-y1 / x2-x1;
	}

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
