package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an airplane with destination, time, and different costs.
public class Airplane implements Writable {

    private String flightname;
    private String destination;
    private String time;
    private int economy;
    private int business;    private int first;
    private int bagcost;

    // EFFECTS - creates an airplane object containing its name, destination, time, and different costs.
    //           flight cost for
    public Airplane(String flightname, String destination, String time, int economy,
                    int business, int first, int bagcost) {
        this.flightname = flightname;
        this.destination = destination;
        this.time = time;
        this.economy = economy;
        this.business = business;
        this.first = first;
        this.bagcost = bagcost;
    }

    // EFFECTS - returns flight name of given plane;
    public String getFlightName() {
        return flightname;
    }

    // EFFECTS - returns destination of given plane;
    public String getDestination() {
        return destination;
    }

    // EFFECTS - returns time of given plane;
    public String getTime() {
        EventLog.getInstance().logEvent(new Event(("A booked flight is printed!")));
        return time;

    }

    // EFFECTS - returns price of economy seat of given plane;
    public int getEconomy() {
        return economy;
    }

    // EFFECTS - returns price of business seat of given plane;
    public int getBusiness() {
        return business;
    }

    // EFFECTS - returns price of first class seat of given plane;
    public int getFirst() {
        return first;
    }

    // EFFECTS - returns baggage cost of given plane;
    public int getBagCost() {
        return bagcost;
    }

    // EFFECTS: returns this as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("flightname", flightname);
        json.put("destination", destination);
        json.put("time", time);
        json.put("economy", economy);
        json.put("business", business);
        json.put("first", first);
        json.put("bagcost", bagcost);
        return json;
    }
}
