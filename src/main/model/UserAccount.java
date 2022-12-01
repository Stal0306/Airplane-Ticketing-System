package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


import java.util.ArrayList;

// Represents a user's account with a name, an ID number and account balance
public class UserAccount implements Writable {

    private String fullname;
    private int idnumber;
    private int balance;
    private int cost;
    private ArrayList<Airplane> booked;

    // EFFECTS: creates an account with user's full name, balance, cost of purchase, and an id number;
    //         ID number is an 8-digit number;
    public UserAccount(String name, int id, int balance, int cost) {
        this.fullname = name;
        this.idnumber = id;
        this.balance = balance;
        this.cost = cost;
        this.booked = new ArrayList<>();
    }

    // EFFECTS: returns full name of user;
    public String getFullName() {
        return fullname;
    }

    // REQUIRES: ID number must be an 8-digit number
    // EFFECTS: returns ID number of user;
    public int getIdNumber() {
        EventLog.getInstance().logEvent(new Event(("Account Info printed!")));
        return idnumber;
    }

    // EFFECTS: returns account balance of user;
    public int getBalance() {
        return balance;
    }

    // EFFECTS: returns the cost of the flight of the user;
    public int getCost() {
        return cost;
    }

    // EFFECTS: returns the list of booked flights for the user;
    public ArrayList<Airplane> getBooked() {
        return booked;
    }

    // EFFECTS: sets the cost of the flight;
    public void setCost(int i) {
        this.cost = i;
    }

    // REQUIRES: amount > 0;
    // MODIFIES: this;
    // EFFECTS: adds amount to balance in user's account;
    public void addBalance(int amount) {
        balance += amount;
    }

    // REQUIRES: amount > 0;
    // MODIFIES: this;
    // EFFECTS: adds amount to cost in user's account;
    public void addCost(int amount) {
        cost += amount;
    }

    // REQUIRES: amount > 0;
    // MODIFIES: this
    // EFFECTS: reduces amount from account balance; account must have enough balance
    //          for payment to go through; else return FALSE;
    public boolean makePayment() {
        if (balance >= cost) {
            balance -= cost;
            setCost(0);
            EventLog.getInstance().logEvent(new Event(("New flight booked to account!")));
            return true;
        }
        return false;
    }

    // EFFECTS: checks if balance is sufficient for payment
    public boolean checkPayment() {
        return (balance >= cost);
    }

    // MODIFIES: this
    // EFFECTS: adds baggage cost to user's costs
    public int baggageCost(int baggage, Airplane plane) {
        this.cost += (baggage * plane.getBagCost());
        return cost;
    }

    // MODIFIES: this
    // EFFECTS: adds booked airplane to booked
    public void addBookedAirplane(Airplane p) {
        booked.add(p);

    }

    // EFFECTS: returns this as a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", fullname);
        json.put("idnumber", idnumber);
        json.put("balance", balance);
        json.put("cost", cost);
        json.put("booked", planestoJson());
        EventLog.getInstance().logEvent(new Event(("Account was saved.")));
        return json;
    }

    // EFFECTS: returns booked planes of a user as a JSON Array
    private JSONArray planestoJson() {
        JSONArray jsonArray = new JSONArray();
        for (Airplane p: this.booked) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
