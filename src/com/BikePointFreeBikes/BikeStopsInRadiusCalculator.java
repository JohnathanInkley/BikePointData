package com.BikePointFreeBikes;

import java.util.HashMap;

public class BikeStopsInRadiusCalculator {

    private HashMap<String, BikeStopEntry> currentBikeStopMap;
    private UserLocation currentLocation;
    private BikeAvailabilityMapProvider mapProvider;

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

    public void setCurrentLocation(String location) {
        currentLocation = AddressStringToUserLocationConverter.getUserLocationFromString(location);
    }
}
