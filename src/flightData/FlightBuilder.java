package flightData;

import FileIO.KMLWriter;
import color.ColorAssigner;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	private Flight[] flightList;

	/**
	 * Main method if we need one. Right now it just prints out "Hello" like it
	 * does in the DistanceCalculator
	 */
	public static void main(String[] args) {
		Flight[] fs = getTestFlights();
		colorByDest(fs);
		KMLWriter kw = new KMLWriter(fs);
		kw.toFile("TestFile.kml");
	}

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
	 * TODO: Remove, for testing only
	 * Dummy flights I came up with just to test the KMLWriter and ColorAssigner.
	 */
	public static Flight[] getTestFlights() {
		final double JFK_LON = -73.778;
		final double JFK_LAT = 40.641;
		final double JFK_ALT = 10;
		final double PHL_LON = -75.242;
		final double PHL_LAT = 39.874;
		final double IAD_LON = -77.457;
		final double IAD_LAT = 38.953;
		final double LAX_LON = -118.409;
		final double LAX_LAT = 33.942;
		final double DFW_LON = -97.040;
		final double DFW_LAT = 32.900;
		final double GEG_LON = -117.535;
		final double GEG_LAT = 47.622;
		final double SLC_LON = -111.979;
		final double SLC_LAT = 40.789;
		final double[] PHL = new double[] {PHL_LON, PHL_LAT, 36000};
		final double[] JFK = new double[] {JFK_LON, JFK_LAT, 36000};
		final double[] DFW = new double[] {DFW_LON, DFW_LAT, 36000};
		final double[] IAD = new double[] {IAD_LON, IAD_LAT, 36000};
		final double[] LAX = new double[] {LAX_LON, LAX_LAT, 36000};
		final double[] GEG = new double[] {GEG_LON, GEG_LAT, 36000};
		final double[] SLC = new double[] {SLC_LON, SLC_LAT, 36000};

		double[][] PHLtoIAD = new double[][] {PHL, IAD};
		double[][] PHLtoLAX = new double[][] {PHL, LAX};
		double[][] PHLtoJFK = new double[][] {PHL, JFK};
		double[][] PHLtoDFW = new double[][] {PHL, DFW};
		double[][] JFKtoDFW = new double[][] {JFK, DFW};
		double[][] JFKtoIAD = new double[][] {JFK, IAD};
		double[][] JFKtoLAX = new double[][] {JFK, LAX};
		double[][] SLCtoGEG = new double[][] {SLC, GEG};

		return new Flight[] {
				new Flight("", "PHL", "IAD", PHLtoIAD, null),
				new Flight("", "PHL", "LAX", PHLtoLAX, null),
				new Flight("", "PHL", "JFK", PHLtoJFK, null),
				new Flight("", "PHL", "DFW", PHLtoDFW, null),
				new Flight("", "JFK", "DFW", JFKtoDFW, null),
				new Flight("", "JFK", "IAD", JFKtoIAD, null),
				new Flight("", "JFK", "LAX", JFKtoLAX, null),
				new Flight("", "SLC", "GEG", SLCtoGEG, null)
		};
	}

	/**
	 * Accessor method to return the a list of all Flights matching all the
	 * information that is in question.
	 *
	 * @return A LinkedList of Flight objects
	 */
	public Flight[] getFlightList()
	{
		Flight[] flights = getTestFlights(); // TODO: Replace with actual user input
		return null;
	}

	private static void colorByOrigin(Flight[] flights) {
		List<String> origins = new ArrayList<>();
		for (Flight f : flights) {
			if (origins.contains(f.getOrigin())) {
				f.setPathColor(new Color(ColorAssigner.getColor(origins.indexOf(f.getOrigin()))));
			} else {
				f.setPathColor(new Color(ColorAssigner.getColor(origins.size())));
				origins.add(f.getOrigin());
			}
		}
	}

	private static void colorByDest(Flight[] flights) {
		List<String> dests = new ArrayList<>();
		for (Flight f : flights) {
			if (dests.contains(f.getOrigin())) {
				f.setPathColor(new Color(ColorAssigner.getColor(dests.indexOf(f.getDestination()))));
			} else {
				f.setPathColor(new Color(ColorAssigner.getColor(dests.size())));
				dests.add(f.getDestination());
			}
		}
	}
}