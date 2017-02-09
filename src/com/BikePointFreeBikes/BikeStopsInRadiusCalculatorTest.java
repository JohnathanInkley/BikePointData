package com.BikePointFreeBikes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

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
}
