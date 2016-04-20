package flightData;

import java.util.LinkedList;

/**
 * Created by Ryan on 4/9/2016
 *
 * FlightBuilder class creates a list of Flight objects depending on
 * the parameters of the flights in question. These parameters are
 * provided by the required interface.
 */
public class FlightBuilder
{
	private LinkedList<String> queryList;
	private LinkedList<Flight> flightList;

	/**
	 * Main method if we need one. Right now it just prints out "Hello" like it
	 * does in the DistanceCalculator
	 */
	public static void main(String[] args)
	{
		System.out.println("Hello.");
	}// end Main

	/**
	 * The default constructor for FlightBuilder. Assumes that there was no
	 * information about the FlightBuilder provided and sets all field values
	 * to null.
	 */
	public FlightBuilder()
	{
		this.queryList = null;
		this.flightList = null;
	}// end FlightBuilder

	/**
	 * The regular constructor for FlightBuilder. Assumes that a queryList was
	 * provided and sets the queryList field.
	 */
	public FlightBuilder(LinkedList<String> queryList)
	{
		this.queryList = queryList;
		this.flightList = null;
	}// end FlightBuilder

	/**
	 * Accessor method to return the a list of all Flights matching all the
	 * information that is in question.
	 *
	 * @return A LinkedList of Flight objects
	 */
	public LinkedList<Flight> getFlightList()
	{
		// Some processing is needed here to fill flightList with Flight objects
		// containing information that we want. IE: All Flights with startPoint
		// of PHL (Philly airport).

		//flightList.add(new Flight
				(String flightID, String startPoint, String endPoint, Color color))

		return this.flightList;
	}// end getFlightList
}// end FlightBuilder