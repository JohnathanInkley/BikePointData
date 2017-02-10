package com.BikePointFreeBikes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BikeStopsInRadiusCalculatorTest {

    private static BikeAvailabilityMapProvider newProvider;

    @BeforeAll
    public static void setUpProvider() {
        newProvider = new BikeAvailabilityMapProvider();
        newProvider.start();
    }

    @Test
    public void calculatorShouldBeAbleToGenerateAnInitialLocationMap() {
        BikeStopsInRadiusCalculator newCalculator = new BikeStopsInRadiusCalculator();
        HashMap<String, BikeStopEntry> expectedMap = newProvider.getLatestBikeAvailabilityMap();
        newCalculator.openConnectionToMapProvider(newProvider);
        HashMap<String, BikeStopEntry> actualMap = newCalculator.getCurrentBikeStopMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void calculatorShouldTakeALocationFromAUser() {
        UserLocation location = new UserLocation("myLocation", 51.529163, -0.10997);
        BikeStopsInRadiusCalculator newCalculator = new BikeStopsInRadiusCalculator();
        newCalculator.openConnectionToMapProvider(newProvider);
        newCalculator.setCurrentLocation(location);
        UserLocation calculatorLocation = newCalculator.getCurrentLocation();
        assertEquals(location, calculatorLocation);
    }

    @Test
    public void calculatorShouldHavePredicateToSayWhetherDistanceLessThanSpecified() {
        UserLocation centre = new UserLocation("Clerkenwell", 51.529163, -0.10997);

        BikeStopsInRadiusCalculator calculator = new BikeStopsInRadiusCalculator();
        calculator.openConnectionToMapProvider(newProvider);
        calculator.setCurrentLocation(centre);

        HashMap<String, BikeStopEntry> calculatedCloseBikeStops = calculator.getBikeStopEntriesWithinDistance(0.15);
        HashSet<String> bikeStopsCloseFromMap = new HashSet<>();
        bikeStopsCloseFromMap.add("River Street , Clerkenwell");
        bikeStopsCloseFromMap.add("Hardwick Street, Clerkenwell");

        assertEquals(bikeStopsCloseFromMap, calculatedCloseBikeStops.keySet());
    }
}
