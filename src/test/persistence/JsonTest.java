package persistence;

import model.Airplane;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAirplane(String flightname,String destination,String time,int economy,int business,int first, int bagcost, Airplane plane) {
        assertEquals(flightname, plane.getFlightName());
        assertEquals(destination, plane.getDestination());
        assertEquals(time, plane.getTime());
        assertEquals(economy, plane.getEconomy());
        assertEquals(business, plane.getBusiness());
        assertEquals(first, plane.getFirst());
        assertEquals(bagcost, plane.getBagCost());

    }
}
