package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTest {

    Airplane p;

    @BeforeEach
    public void setUp() {
        p = new Airplane("stat200","Abu Dhabi","0000hrs",1500,5000,13250,500);
    }

    @Test
    public void testConstructor() {
        assertEquals("stat200", p.getFlightName());
        assertEquals("Abu Dhabi", p.getDestination());
        assertEquals("0000hrs", p.getTime());
        assertEquals(1500, p.getEconomy());
        assertEquals(5000, p.getBusiness());
        assertEquals(13250, p.getFirst());
        assertEquals(500, p.getBagCost());
    }
}


