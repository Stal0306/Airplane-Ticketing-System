package model;

import ui.TicketingSystem;

// Represents a user's account with a name, an ID number and account balance
public class UserAccount {
    private int idnumber;
    private String fullname;
    private int balance;
    private int cost;

    // EFFECTS: creates an account with user's full name, balance, cost of purchase, and an id number;
    //         ID number is an 8-digit number;
    public UserAccount(String name, int id, int balance, int cost) {
        this.fullname = name;
        this.idnumber = id;
        this.balance = balance;
        this.cost = cost;
    }

    // EFFECTS: returns full name of user;
    public String getFullName() {
        return fullname;
    }

    // REQUIRES: ID number must be an 8-digit number
    // EFFECTS: returns ID number of user;
    public int getIdNumber() {
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
}
