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

    // REQUIRES: ID number must be an 8-digit number;
    // EFFECTS: Adds a given account to the list of accounts;
    public void addAccount(UserAccount account) {
        listOfAccounts.add(account);
        EventLog.getInstance().logEvent(new Event(("New Account created and added to List of Accounts")));
    }
}