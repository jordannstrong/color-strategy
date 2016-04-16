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
import java.util.List;

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
        KMLWriter k = new KMLWriter(null);
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

            addTestStyleElements();
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
            //addStyleElement(styleID, ); TODO
        }

        Element styleUrlElement = doc.createElement("styleUrl");
        styleUrlElement.setTextContent("" + flight.getPathColor().getRGB());
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
