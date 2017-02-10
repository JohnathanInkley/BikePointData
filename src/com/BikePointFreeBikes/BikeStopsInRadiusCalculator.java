package com.BikePointFreeBikes;

import java.util.HashMap;

public class BikeStopsInRadiusCalculator {

    private BikeAvailabilityMapProvider mapProvider;
    private HashMap<String, BikeStopEntry> currentBikeStopMap;
    private UserLocation currentLocation;

    public BikeStopsInRadiusCalculator() {
    }

    public void openConnectionToMapProvider(BikeAvailabilityMapProvider newProvider) {
        mapProvider = newProvider;
        updateBikeStopMap();
    }

    public void updateBikeStopMap() {
        currentBikeStopMap = mapProvider.getLatestBikeAvailabilityMap();
    }

    public HashMap<String, BikeStopEntry> getCurrentBikeStopMap() {
        return currentBikeStopMap;
    }


    public void setCurrentLocation(UserLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setCurrentLocation(double latitude, double longitude) {
        currentLocation = new UserLocation("", latitude, longitude);
    }

    public UserLocation getCurrentLocation() {
        return currentLocation;
    }

    public HashMap<String,BikeStopEntry> getBikeStopEntriesWithinDistance(double radius) {
        HashMap<String, BikeStopEntry> bikeStopsWithinDistance = new HashMap<>();
        currentBikeStopMap.forEach((stopName, stopEntry) -> {
            if (currentLocation.distanceTo(stopEntry.getLocation()) <= radius) {
                bikeStopsWithinDistance.put(stopName, stopEntry);
            }
        });
        return bikeStopsWithinDistance;
    }
}
