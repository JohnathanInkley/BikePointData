package com.BikePointFreeBikes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

class BikeStopEntryTest {

    @Test
    public void sameObjectsShouldBeEqual() {
        BikeStopEntry bikeData = new BikeStopEntry(5, 2, 1);
        BikeStopEntry equalBikeData = new BikeStopEntry(5, 2, 1);
        BikeStopEntry notEqualBikeData = new BikeStopEntry(1, 1, 1);
        assertTrue(bikeData.equals(equalBikeData));
        assertFalse(bikeData.equals(notEqualBikeData));
    }

    @Test
    public void numberOfBikesShouldBeNonNegative() {
        try {
            BikeStopEntry bikeData = new BikeStopEntry(-1, 1, 1);
            fail("Cannot have negative bikes");
        } catch (RuntimeException ex) {
        }
    }

    @Test
    public void distanceShouldBeCalculatedBetweenTwoDataUnits() {
        BikeStopEntry first = new BikeStopEntry(0, 1, 1);
        BikeStopEntry second = new BikeStopEntry(0, 1, 2);
        assertEquals(69.132, first.distanceTo(second),0.1);
    }

    @Test
    public void shouldBeAbleToDisplayADataUnit() {
        BikeStopEntry data = new BikeStopEntry(1, 2, 3);
        assertEquals("1 free bike(s) at latitude 2.0 and longitude 3.0", data.toString());
    }
}