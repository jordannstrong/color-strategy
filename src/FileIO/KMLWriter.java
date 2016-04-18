package FileIO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import flightData.Flight;
import org.w3c.dom.*;

import java.io.File;
import java.util.*;

import java.awt.Color;

/**
 * Created by Kevin on 4/16/2016.
 *
 * Class for writing KML files to visualize flight paths. KML filenames end in '.kml' and can be opened by
 * Google Earth.
 */
public class KMLWriter {

    private Document doc;
    private Element documentElement;

    private Flight[] flights;

    /**
     * Main driver for testing.
     */
    public static void main(String[] args) {
        KMLWriter k = new KMLWriter(getTestFlights());
        k.toFile("TestFile.kml");
    }
    public KMLWriter(Flight[] flights) {
        this.flights = flights;

        try {
            // set up header nodes
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            Element kmlElement = doc.createElement("kml");
            kmlElement.setAttribute("xmlns", "http://www.opengis.net/kml/2.2");
            doc.appendChild(kmlElement);

            documentElement = doc.createElement("Document");
            kmlElement.appendChild(documentElement);

            Element openElement = doc.createElement("open");
            openElement.setTextContent("1");
            documentElement.appendChild(openElement);

            for (Flight f : flights) {
                addFlightPathElement(f);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addStyleElements() {
        String styleID;
        for (Flight flight : flights) {
            styleID = "" + flight.getPathColor().getRGB();

        }
    }

    //TODO: Remove, for testing only
    private void addTestStyleElements() {
        addStyleElement("redStyle", "ff0000ff", "4");
        addStyleElement("blueStyle", "ffff0000", "4");
        addStyleElement("greenStyle", "ff00ff00", "4");
    }

    //TODO: Remove, for testing only
    private static Flight[] getTestFlights() {
        List<double[]> testCoords = new ArrayList<double[]>();
        double[] first = new double[]{
                -74.2, 40.0, 3230
        };
        double[] second = new double[]{
                -78.2, 39.3, 10121
        };
        double[] third = new double[]{
                -82.9, 38.4, 10656
        };
        testCoords.add(first);
        testCoords.add(second);
        testCoords.add(third);

        List<double[]> testCoords2 = new ArrayList<double[]>();
        double[] first2 = new double[] {
                -73.75, 40.5, 1000,
        };
        double[] second2 = new double[] {
                -75.3,  39.0, 8000
        };
        double[] third2 = new double[] {
                -79.6, 37.5, 10000
        };
        testCoords2.add(first2);
        testCoords2.add(second2);
        testCoords2.add(third2);

        List<double[]> testCoords3 = new ArrayList<double[]>();
        double[] first3 = new double[] {
                -74.75, 40.2, 7000,
        };
        double[] weird = new double[] {
                -76.8, 35.6, 11000
        };
        double[] second3 = new double[] {
                -81.3,  33.0, 11231
        };
        double[] third3 = new double[] {
                -82.6, 30.5, 11323
        };
        testCoords3.add(first3);
        testCoords3.add(weird);
        testCoords3.add(second3);
        testCoords3.add(third3);

        Flight[] sampleFlights = new Flight[]{
                new Flight("", testCoords, Color.RED),
                new Flight("", testCoords2, Color.BLUE),
                new Flight("", testCoords3, Color.PINK)
        };

        return sampleFlights;
    }

    private void addStyleElement(String styleID, String colorValue, String width) {
        Element styleElement = doc.createElement("Style");
        styleElement.setAttribute("id", styleID);
        documentElement.appendChild(styleElement);

        Element lineStyleElement = doc.createElement("LineStyle");
        styleElement.appendChild(lineStyleElement);

        Element colorElement = doc.createElement("color");
        colorElement.setTextContent(colorValue);
        lineStyleElement.appendChild(colorElement);

        Element widthElement = doc.createElement("width");
        widthElement.setTextContent(width);
        lineStyleElement.appendChild(widthElement);

    }

    private void addFlightPathElement(Flight flight) {
        Element placemarkElement = doc.createElement("Placemark");
        documentElement.appendChild(placemarkElement);

        //chcek if the color style element has already been created, and if not create it
        String styleID = "" + flight.getPathColor().getRGB();
        if (doc.getElementById(styleID) == null) {
            addStyleElement(styleID, flight.getKMLColor(), "4");
        }

        Element styleUrlElement = doc.createElement("styleUrl");
        styleUrlElement.setTextContent(styleID);
        placemarkElement.appendChild(styleUrlElement);

        Element lineStringElement = doc.createElement("LineString");
        placemarkElement.appendChild(lineStringElement);

        Element altModeElement = doc.createElement("altitudeMode");
        altModeElement.setTextContent("absolute");
        lineStringElement.appendChild(altModeElement);

        Element coordinatesElement = doc.createElement("coordinates");
        String coordinatesText = "";
        List<double[]> coordinateList = flight.getCoordinateList();
        for (double[] coord : coordinateList) {
            coordinatesText += "" + coord[0] + "," + coord[1] + "," + coord[2] + "\n";
        }
        coordinatesElement.setTextContent(coordinatesText);
        lineStringElement.appendChild(coordinatesElement);
    }

    public void toFile(String fileName) {
        File kmlFile = new File(fileName);

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(kmlFile);

            transformer.transform(source, result);
        }
        catch (TransformerException te) {
            te.printStackTrace();
        }
    }
}
