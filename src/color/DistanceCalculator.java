package color;

import flightData.Flight;

public class DistanceCalculator {
    public static void main(String[] args) {
        System.out.println("Hello.");
    }

    private double distance(Flight f1, Flight f2) {
        double dLat = Math.toRadians(f2.getLatitude()-f1.getLatitude());
        double dLon = Math.toRadians(f2.getLongitude()-f1.getLongitude());
        double distance = (dLon * dLon) + (dLat *dLat);
        return Math.sqrt(distance);
    }


}