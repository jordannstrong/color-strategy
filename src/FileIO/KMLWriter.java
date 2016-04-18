package FileIO;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kevin on 4/16/2016.
 *
 * Class for writing KML files to visualize flight paths. KML files end in '.kml' and can be opened by
 * Google Earth.
 */
public class KMLWriter
{
	private File kmlFile;
	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;

	/**
	 * Main driver for testing.
	 */
	public static void main(String[] args)
	{
		KMLWriter k = new KMLWriter();
		k.toFile("TestFile.kml");
	}

	public KMLWriter()
	{
		try
		{
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();

			Attr nameSpace = doc.createAttributeNS("http://www.opengis.net/kml/2.2", "kml");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void toFile(String fileName)
	{
		kmlFile = new File(fileName);

		try
		{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(kmlFile);

			transformer.transform(source, result);

			System.out.println(kmlFile.getCanonicalPath());
		}
		catch (TransformerException te)
		{
			te.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
