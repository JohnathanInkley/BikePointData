package com.BikePointFreeBikes;

public class BikeStopEntry {
    private final int numberOfFreeBikes;
    private final double latitude;
    private final double longitude;
    private static final int RADIUS_OF_WORLD = 3961;

    public BikeStopEntry(int numberOfFreeBikes, double latitude, double longitude) {
        validateInputs(numberOfFreeBikes);
        this.numberOfFreeBikes = numberOfFreeBikes;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private static void validateInputs(int numberOfFreeBikes) {
        if (numberOfFreeBikes < 0) {
            throw new RuntimeException("Negative number of bikes: " + numberOfFreeBikes);
        }
    }

    public static BikeStopEntry getDefaultEmptyEntry() {
        return new BikeStopEntry(0,0,0);
    }

    public boolean equals(Object second) {
        BikeStopEntry secondData = (BikeStopEntry) second;
        return secondData.numberOfFreeBikes == numberOfFreeBikes
                && secondData.latitude == latitude
                && secondData.longitude == longitude;
    }

    public double distanceTo(BikeStopEntry second) {
        double deltaLat = Math.toRadians(latitude - second.latitude);
        double deltaLong = Math.toRadians(longitude - second.longitude);
        double mathsThing = Math.sin(deltaLat/2)*Math.sin(deltaLat/2) + Math.cos(Math.toRadians(latitude))
                            *Math.cos(Math.toRadians(second.latitude))
                            *Math.sin(deltaLong/2)*Math.sin(deltaLong/2);
        double c = 2*Math.atan2(Math.sqrt(mathsThing),Math.sqrt(1 - mathsThing));
        return RADIUS_OF_WORLD*c;
    }

    public String toString() {
        return numberOfFreeBikes + " free bike(s) at latitude " + latitude + " and longitude " + longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


}
