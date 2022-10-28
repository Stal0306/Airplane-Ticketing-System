package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
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
        assertEquals(8,airtest.sizeAirplanelist());
    }

    @Test
    public void testCheckDestination() {
        ArrayList<Airplane> testdest = new ArrayList<>();
        Airplane air1 = new Airplane("stat200","Abu Dhabi","0000hrs",1500,5000,13250,500);
        Airplane air2 = new Airplane("stat201","Dubai","2359hrs",1375,6300,17500,500);
        Airplane air3 = new Airplane("stat220","Sharjah","1200hrs",1200,4000,11250,400);
        testdest.add(air1);
        testdest.add(air2);
        testdest.add(air3);
    }
}
