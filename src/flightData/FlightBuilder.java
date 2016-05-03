package flightData;
import database.Flight;

import FileIO.KMLWriter;
import color.ColorAssigner;
import database.Parameter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ryan on 4/9/2016
 *
 * FlightBuilder class creates a list of Flight objects depending on
 * the parameters of the flights in question. These parameters are
 * provided by the required interface.
 *
 * @author Kevin DeMoura
 */
public class FlightBuilder
{
	private LinkedList<String> queryList;
	private Flight[] flightList;
	private CSVParser csvParser;
    private List<Parameter> pList;
	/**
	 * Driver method for testing.
	 */
	public static void main(String[] args) {
        /*try {
            ColorAssigner ca = new ColorAssigner();
            FlightBuilder fb = new FlightBuilder(new File("res\\test_routes_350.csv"), 2, 4);
            Flight[] fs = fb.getFlightList();
            ca.colorByOrigin(fs);
            KMLWriter kw = new KMLWriter(fs);
            kw.toFile("TestFile.kml");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
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
	 *
	 * @param csvFile csv file
     * @param originIndex Index of the origin airport code (3 or 4 char) as a String in the csv table
     * @param destIndex Index of the destination airport code (3 or 4 char) as a String in the csv table
     */
	public FlightBuilder(File csvFile, int originIndex, int destIndex) throws IOException{
            flightList = new Flight[1000];
        BufferedReader fr = new BufferedReader(new FileReader(csvFile));
        int i = 0;
        fr.readLine();
		do {
            String line = fr.readLine();
            if (line == null) {
                continue;
            }
            String orig = readCol(line, originIndex).trim();
            String dest = readCol(line, destIndex).trim();
			double[] origLONLAT = getCoordinatesOfAirport(orig);
			double[] destLONLAT = getCoordinatesOfAirport(dest);
            if (origLONLAT == null || destLONLAT == null) {
                System.err.println("Problem with record " + i);
                continue;
            }
            double[] originCoords = {origLONLAT[0], origLONLAT[1], 11000};
            double[] destCoords = {destLONLAT[0], destLONLAT[1], 11000};
			flightList[i] = (new Flight(
					"",
					orig,
					dest,
                    new double[][] {originCoords, destCoords}, null
        ));
            i++;
		} while (fr.readLine() != null);
        flightList = cleanArray(flightList);
		System.out.println("" + flightList.length);
	}

    public FlightBuilder(File csvFile, List<Parameter> pList, int originIndex, int destIndex) throws IOException{
        flightList = new Flight[1000];
        this.pList = pList;
        BufferedReader fr = new BufferedReader(new FileReader(csvFile));
        int i = 0;
        fr.readLine();
        do {
            String line = fr.readLine();
            if (line == null) {
                continue;
            }
            String orig = readCol(line, originIndex).trim();
            String dest = readCol(line, destIndex).trim();
            double[] origLONLAT = getCoordinatesOfAirport(orig);
            double[] destLONLAT = getCoordinatesOfAirport(dest);
            if (origLONLAT == null || destLONLAT == null) {
                System.err.println("Problem with record " + i);
                continue;
            }
            double[] originCoords = {origLONLAT[0], origLONLAT[1], 11000};
            double[] destCoords = {destLONLAT[0], destLONLAT[1], 11000};
            Color color = null;
            for (Parameter p : pList) {
                switch(p.getParameterName()) {
                    case "ORIGIN_FIX":
                        if (orig.equals(p.getParameterValue())) color = p.getParameterColor();
                        break;
                    case "DEST_FIX":
                        if (dest.equals(p.getParameterValue())) color = p.getParameterColor();
                        break;
                }
            }
            flightList[i] = (new Flight(
                    "",
                    orig,
                    dest,
                    new double[][] {originCoords, destCoords}, color
            ));
            i++;
        } while (fr.readLine() != null);
        flightList = cleanArray(flightList);
        System.out.println("" + flightList.length);
    }

    private String readCol(String line, int col) throws IOException{
        String[] array = line.split(",");
        return array[col] == null ? "" : array[col];
    }

    private Flight[] cleanArray(Flight[] array) {
        int numOfNull = 0;
        for (Flight f : array) {
            if (f == null) numOfNull++;
        }
        Flight[] cArray = new Flight[array.length-numOfNull];
        int i = 0;
        for (Flight f : array) {
            if (f != null) cArray[i] = f;
            i++;
        }
        return cArray;
    }

    /**
     *
     * @param orig
     * @return the coordinates in an array in the form of {lon, lat}.
     */
    public static double[] getCoordinatesOfAirport(String orig) {
        try {
            CSVParser parser = CSVParser.parse(
                    new File("res//airport_coordinate_list.csv"),
                    Charset.defaultCharset(),
                    CSVFormat.EXCEL);
            for (CSVRecord c : parser) {
				double lat = Double.valueOf(c.get(6));
				double lon = Double.valueOf(c.get(7));
				int i = 4;
				if (c.get(i).equals("")) i = 5;
                if (c.get(i).equals(orig) || c.get(i).equals(orig.toLowerCase())) {

                    return new double[] {lon, lat};
                }
            }

        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

    /**
	 * for testing only
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
		return flightList;
	}

    public List<Parameter> getpList() {
        return pList;
    }
}