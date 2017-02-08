package com.BikePointFreeBikes;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BikeAvailabilityMapProviderTest {
    @Test
    public void objectShouldReturnAMapOFLocationsAndFreeBikes() {
        HashMap<String, BikeStopEntry> testMap = new HashMap<>(1);
        testMap.put("bikeStop", new BikeStopEntry(1,2,3));
        BikeAvailabilityMapProvider provider = new BikeAvailabilityMapProvider();
        provider.startDummy();
        HashMap<String, BikeStopEntry> providedMap = provider.getLatestBikeAvailabilityMapDummy();
        assertEquals(testMap, providedMap);
    }

    @Test
    public void objectShouldReturnAMapOFLocationsAndFreeBikesNonDummy() {
        BikeStopEntry clerkenwellTestEntry = new BikeStopEntry(0, 51.529163, -0.10997);
        BikeAvailabilityMapProvider provider = new BikeAvailabilityMapProvider();
        provider.start();
        HashMap<String, BikeStopEntry> providedMap = provider.getLatestBikeAvailabilityMap();
        BikeStopEntry clerkenwellRealEntry = providedMap.get("River Street , Clerkenwell");
        assertEquals(clerkenwellTestEntry.getLatitude(), clerkenwellRealEntry.getLatitude());
        assertEquals(clerkenwellTestEntry.getLongitude(), clerkenwellRealEntry.getLongitude());
    }

}
