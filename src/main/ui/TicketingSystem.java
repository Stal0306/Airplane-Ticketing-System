package ui;

import model.AccountList;
import model.Airplane;
import model.AirplaneList;
import model.UserAccount;

import java.util.ArrayList;
import java.util.Scanner;

// represents the User Interface of the ticketing system;
// user an create an account, view flights and buy them with extra luggage
// and choice of seat(economy, business, first)
public class TicketingSystem {
    private UserAccount user;
    private AccountList accountList;
    private AirplaneList airlineList;
    private ArrayList<Airplane> specdest;

    // EFFECTS: initializes the UI and creates new lists of airplanes and accounts to be used.
    public TicketingSystem() {
        specdest = new ArrayList<Airplane>();
        airlineList = new AirplaneList();
        accountList = new AccountList();
        runUI();
    }

    // EFFECTS - runs the UI
    public void runUI() {
        airlineList.airlineOptions();
        makeAccount();
        bookingOptions();
        buyTicket();
    }

    // MODIFIES - this
    // EFFECTS - creates an account with a name, id number and balance, and adds user to lists
    public void makeAccount() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your full name");
        String name = s.nextLine();
        System.out.println("Enter your 8-digit UBC ID number. This ID number will be unique to your account");
        int idnum = s.nextInt();
        System.out.println("Welcome to Air UBC, " + name + "!");
        System.out.println("Please input the amount you would like to add to this account for your future"
                + " flight purchases:");
        int balance = s.nextInt();
        user = new UserAccount(name, idnum, balance, 0);
        accountList.addAccount(user);
    }

    // MODIFIES: this
    // EFFECTS: displays the destination options for the user to choose from
    public void bookingOptions() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please type in your final destination:");
        System.out.println("We offer flights to:\nToronto\nEdmonton\nMontreal\nHalifax\nCalgary\nVictoria");
        String dest = s.nextLine();
        this.specdest = airlineList.checkDestination(dest, airlineList.getAirplanelist());
        checkListEmpty(dest, specdest);
    }

    // MODIFIES: this
    // EFFECTS: buys the ticket for the user
    public void buyTicket() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please type in the flight name of your preference or type 'back' to go back.");
        String opt = s.nextLine();
        if (opt.equals("back")) {
            bookingOptions();
            buyTicket();
        } else {
            int temp = 0;
            for (Airplane p : specdest) {
                System.out.println(specdest);
                if (opt.equals(p.getFlightName())) {
                    temp = 1;
                    confirmSpecAndPay(p);
                }
            }
            if (temp == 0) {
                System.out.println("Sorry, the flight name you have entered is not applicable. Please try again.");
                buyTicket();
            }
        }
    }

    public void checkListEmpty(String dest, ArrayList<Airplane> specdest) {
        if (specdest.size() != 0) {
            displayFlights(dest, specdest);
        } else {
            System.out.println("Sorry, we do not offer flights to " + dest + ":");
            bookingOptions();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays each flights information
    public void displayFlights(String dest, ArrayList<Airplane> planes) {
        System.out.println("Here are our available flights to " + dest + ":");
        for (Airplane p : planes) {
            System.out.println("Flight name: " + p.getFlightName());
            System.out.println("Destination: " + p.getDestination());
            System.out.println("Time: " + p.getTime());
            System.out.println("First Class Price: $" + p.getFirst());
            System.out.println("Business Class Price: $" + p.getBusiness());
            System.out.println("Economy Class Price: $" + p.getEconomy());
            System.out.println("Baggage costs(per bag): $" + p.getBagCost());
            System.out.println("     ");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to customize their flight seat and baggage options
    public void confirmSpecAndPay(Airplane p) {
        Scanner s = new Scanner(System.in);
        System.out.println("What class do you prefer to fly on; please type one of the following- "
                    + "first, business, economy:");
        String seat = s.nextLine();
        classCost(seat, p);
        System.out.println("Your total is: " + user.getCost());
        System.out.println("Would you like to add extra baggage? If yes, enter number of bags, else enter 0.");
        int bag = s.nextInt();
        user.baggageCost(bag, p);
        System.out.println("Your total is: " + user.getCost());
        System.out.println("Would your like to confirm this flight? Answer yes or no.");
        String dummy = s.nextLine();
        String last = s.nextLine();
        finalOption(last, p);
    }

    // REQUIRES: seat must be first, business or economy
    // MODIFIES: this
    // EFFECTS: adds plane seat's cost to user's costs based on seat choice
    public int classCost(String seat, Airplane plane) {
        if (seat.equals("first")) {
            user.addCost(plane.getFirst());
        } else if (seat.equals("business")) {
            user.addCost(plane.getBusiness());
        } else if (seat.equals("economy")) {
            user.addCost(plane.getEconomy());
        } else {
            System.out.println("Sorry, your input is incorrect. Please type in your preference.");
            confirmSpecAndPay(plane);
        }
        return user.getCost();
    }

    // MODIFIES: this
    // EFFECTS: makes payments if funds are sufficient; else asks user to add money
    private void finalOption(String last, Airplane p) {
        Scanner s = new Scanner(System.in);
        if (last.equals("yes")) {
            if (user.checkPayment()) {
                user.makePayment();
                System.out.println("Your booking for flight " + p.getFlightName() + " to " + p.getDestination()
                        + " has been confirmed!");
                System.out.println("Your remaining balance is " + user.getBalance());
                System.out.println("Thank you for choosing Air UBC");
            } else {
                System.out.println("Sorry, you don't have sufficient funds. :(");
                System.out.println("Your balance is " + user.getBalance());
                System.out.println("Please enter the amount you would like to add to your account.");
                int extrabal = s.nextInt();
                user.addBalance(extrabal);
                finalOption(last, p);
            }
        } else {
            user.setCost(0);
            bookingOptions();
            buyTicket();
        }
    }
}
