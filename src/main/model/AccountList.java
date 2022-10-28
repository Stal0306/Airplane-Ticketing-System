package model;

import java.util.ArrayList;
import java.util.List;

// Represents the list of accounts that have been created in the system
public class AccountList {
    private List<UserAccount> listOfAccounts;

    // EFFECTS: creates a new arraylist of UserAccounts
    public AccountList() {
        listOfAccounts = new ArrayList<>();
    }

    // EFFECTS: returns the size of the account list
    public int sizeAccountList() {
        return listOfAccounts.size();
    }

    // EFFECTS: returns the list of accounts in the system;
    public List<UserAccount> getListOfAccounts() {
        return listOfAccounts;
    }

    // REQUIRES: ID number must be an 8-digit number;
    // EFFECTS: Adds a given account to the list of accounts;
    public void addAccount(UserAccount account) {
        listOfAccounts.add(account);
    }

}