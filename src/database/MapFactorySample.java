package database;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Properties;
import gov.faa.cpat.Util.CpatStatics;
import gov.faa.cpat.Util.Map.*;
import gov.faa.cpat.Util.PositionPointData;
/**
 * Utility for converting from stereo to geodetic (lon, lat, alt)
 * @author cyao
 */
public class MapFactorySample {

    /**
     * @param args the command line arguments
     */
    public void start() {
        Properties props = new Properties();
        props.setProperty("isEllipsoidal", "true");
        props.setProperty("ellipsoidalType", "WGS1984");
        props.setProperty("projection", "STEREO");
        props.setProperty("tanX", "334");
        props.setProperty("tanY", "400");
        props.setProperty("tanLat", "36.85");
        props.setProperty("tanLon", "-75.60");
        props.setProperty("radius", "3439.90");
        try {
            MapProjection proj = MapFactory.createProjection(props);
            double x =82.75;
            double y =524.394;
            PositionPointData latlon = proj.toGeodetic(new PositionPointData(x, y, CpatStatics.STEREO));
            double lat = latlon.getCoord1();
            double lon = latlon.getCoord2();
            System.out.println("STEREO: X="+ x + " and " + " y=" + y );
            System.out.println("GEODETIC: LAT="+ lat + " and " + " LON=" + lon );
//            System.out.println(latlon.getCoord1() + ", " + latlon.getCoord2());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        MapFactorySample map = new MapFactorySample();
        map.start();
    }

}