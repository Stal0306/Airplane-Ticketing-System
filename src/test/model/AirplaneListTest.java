package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AirplaneListTest {

    AirplaneList airtest;

    @BeforeEach
    public void setUp() {
        airtest = new AirplaneList();
        airtest.airlineOptions();
    }

    @Test
    public void testConstructor() {
        assertEquals(7,airtest.sizeAirplanelist());
    }

    @Test
    public void testGetAirplaneList() {
        assertEquals(7, airtest.getAirplanelist().size());
    }

    @Test
    public void testCheckAirplaneList() {
        Airplane air1 = new Airplane("stat200","Abu Dhabi","0000hrs",1500,5000,13250,500);
        airtest.addAirplane(air1);
        assertTrue(airtest.checkAirplanelist(air1));
        assertEquals(8,airtest.sizeAirplanelist());
        Airplane air2 = new Airplane("stat201","Dubai","2359hrs",1375,6300,17500,500);
        assertFalse(airtest.checkAirplanelist(air2));
    }

    @Test
    public void testSizeAirplaneList() {
        assertEquals(7, airtest.sizeAirplanelist());
        Airplane air1 = new Airplane("stat200","Abu Dhabi","0000hrs",1500,5000,13250,500);
        airtest.addAirplane(air1);
        assertEquals(8, airtest.sizeAirplanelist());
    }

    @Test
    public void testAddAirplane() {
        Airplane air = new Airplane("stat200","Abu Dhabi","0000hrs",1500,5000,13250,500);
        assertFalse(airtest.checkAirplanelist(air));
        assertEquals(7,airtest.sizeAirplanelist());
        airtest.addAirplane(air);
        assertTrue(airtest.checkAirplanelist(air));
        assertEquals(8,airtest.sizeAirplanelist());
    }

    @Test
    public void testAddMultipleAirplanes() {
        Airplane air1 = new Airplane("stat200","Abu Dhabi","0000hrs",1500,5000,13250,500);
        Airplane air2 = new Airplane("stat201","Dubai","2359hrs",1375,6300,17500,500);
        assertFalse(airtest.checkAirplanelist(air1));
        assertFalse(airtest.checkAirplanelist(air2));
        assertEquals(7,airtest.sizeAirplanelist());
        airtest.addAirplane(air1);
        assertTrue(airtest.checkAirplanelist(air1));
        assertFalse(airtest.checkAirplanelist(air2));
        assertEquals(8,airtest.sizeAirplanelist());
        airtest.addAirplane(air2);
        assertTrue(airtest.checkAirplanelist(air1));
        assertTrue(airtest.checkAirplanelist(air2));
        assertEquals(9,airtest.sizeAirplanelist());
    }

    @Test
    public void testAddOneAirplaneMultipleTimes() {
        Airplane air = new Airplane("stat200","Abu Dhabi","0000hrs",1500,5000,13250,500);
        assertFalse(airtest.checkAirplanelist(air));
        assertEquals(7,airtest.sizeAirplanelist());
        airtest.addAirplane(air);
        assertTrue(airtest.checkAirplanelist(air));
        assertEquals(8,airtest.sizeAirplanelist());
        airtest.addAirplane(air);
        assertTrue(airtest.checkAirplanelist(air));
        assertEquals(9,airtest.sizeAirplanelist());
    }

    @Test
    public void testCheckDestination() {
        ArrayList<Airplane> testdest = new ArrayList<>();
        Airplane air1 = new Airplane("stat200", "Abu Dhabi", "0000hrs", 1500, 5000, 13250, 500);
        Airplane air2 = new Airplane("stat201", "Dubai", "2359hrs", 1375, 6300, 17500, 500);
        Airplane air3 = new Airplane("stat302", "Abu Dhabi", "1000hrs", 1500, 4000, 12500, 475);
        testdest.add(air1);
        testdest.add(air2);
        testdest.add(air3);
        assertEquals(2, airtest.checkDestination("Abu Dhabi", testdest).size());
        assertTrue(airtest.checkDestination("Abu Dhabi", testdest).contains(air1));
        assertEquals(0, airtest.checkDestination("Toronto", testdest).size());
        assertFalse(airtest.checkDestination("Toronto", testdest).contains(air3));
    }
}
