package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Represents the list of airplanes owned by Air UBC
public class AirplaneList {

    private ArrayList<Airplane> listOfAirplanes;

    // EFFECTS: constructs a new list of airplanes;
    public AirplaneList() {
        listOfAirplanes = new ArrayList<Airplane>();
    }

    // EFFECTS: returns the list of airplanes
    public ArrayList<Airplane> getAirplanelist() {
        return listOfAirplanes;
    }

    // EFFECTS: checks if airplane belongs to list
    public boolean checkAirplanelist(Airplane air) {
        return listOfAirplanes.contains(air);
    }

    // EFFECTS: returns size of the list
    public int sizeAirplanelist() {
        return listOfAirplanes.size();
    }

    // MODIFIES: this
    // EFFECTS: adds given airplane to the list
    public void addAirplane(Airplane airplane) {
        listOfAirplanes.add(airplane);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds airplanes to the list of airplanes;
    public ArrayList<Airplane> airlineOptions() {
        Airplane cpsc210 = new Airplane("cpsc210","Halifax","1700hrs",
                250, 700,2350, 100);
        Airplane math200 = new Airplane("math200","Toronto", "800hrs",
                275, 800, 4000, 125);
        Airplane math221 = new Airplane("math221","Toronto", "1900hrs",
                350, 1000, 4800, 125);
        Airplane biol111 = new Airplane("biol111","Montreal", "1300hrs",
                275, 750, 3760, 125);
        Airplane wrds150 = new Airplane("wrds150","Edmonton", "300hrs",
                150, 500, 2000, 100);
        Airplane fren101 = new Airplane("fren101","Calgary", "2200hrs",
                180, 700, 2500, 100);
        Airplane atsc113 = new Airplane("atsc113","Victoria", "1100hrs",
                115, 375, 1000, 50);

        listOfAirplanes.add(cpsc210);
        listOfAirplanes.add(math200);
        listOfAirplanes.add(math221);
        listOfAirplanes.add(biol111);
        listOfAirplanes.add(wrds150);
        listOfAirplanes.add(fren101);
        listOfAirplanes.add(atsc113);

        return listOfAirplanes;
    }

    // EFFECTS: checks if flight to destination are available; add if it does
    public ArrayList<Airplane> checkDestination(String dest, ArrayList<Airplane> listOfAirplanes) {
        ArrayList<Airplane> samedest = new ArrayList<>();
        for (Airplane p: listOfAirplanes) {
            if (p.getDestination().equals(dest)) {
                samedest.add(p);
            }
        }
        return samedest;
    }
}
