package com.BikePointFreeBikes;

import java.util.HashMap;

public class BikeStopsInRadiusCalculator {

    private String GOOGLE_API_KEY = "AIzaSyDuzsl1bwFRRwvwUSNkkm5sAs8rb05AyEI";

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

    private String getGoogleGeocodeSyntax(String location) {
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append("https://maps.googleapis.com/maps/api/geocode/json?address=");
        String[] locationWords = location.split("\\s+");
        for (String word : locationWords) {
            requestBuilder.append(word);
            requestBuilder.append("+");
        }
        requestBuilder.append("&key=");
        requestBuilder.append(GOOGLE_API_KEY);
        return requestBuilder.toString();
    }

    private UserLocation getUserLocationFromString(String location) {
        String googleRequest = getGoogleGeocodeSyntax(location);
        String googleResults = WebDataReader.readTextFromURL(googleRequest);
        String intermediateResult = googleResults.split("\"location\"")[1];
        String[] splitString = intermediateResult.split("\\s+");
        double latitude = Double.valueOf(splitString[5].replace(",",""));
        double longitude = Double.valueOf(splitString[8]);
        return new UserLocation(location, latitude, longitude);
    }

    public void setCurrentLocation(String location) {
        currentLocation = getUserLocationFromString(location);
    }
}
