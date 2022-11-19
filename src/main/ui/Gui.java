package ui;

import model.AccountList;
import model.Airplane;
import model.AirplaneList;
import model.UserAccount;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Creates a GUI Instance with all required fields and methods using JFrame
public class Gui extends JFrame implements ActionListener {
    private UserAccount user;
    private AccountList accountList;
    private AirplaneList airlineList;
    private ArrayList<Airplane> specdest;
    private static final String SAVE_DEST = "./data/user.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame frame;
    private JLabel label;
    private ImageIcon plane;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel consolePanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTextArea consoleText;
    private JFrame bookingFrame;
    private JPanel flightDisplay;
    private JTextArea displayText;


    // EFFECTS: initializes the GUI
    public Gui() {
        initializeObjects();
        airlineList.airlineOptions();
        setFramesAndPanels();
        setLabels();
        setButtons();
        makeMainFrame();
    }

    // EFFECTS: initializes some fields
    public void initializeObjects() {
        specdest = new ArrayList<Airplane>();
        airlineList = new AirplaneList();
        accountList = new AccountList();
        jsonWriter = new JsonWriter(SAVE_DEST);
        jsonReader = new JsonReader(SAVE_DEST);
    }

    // EFFECTS: creates frame and panels
    public void setFramesAndPanels() {
        frame = new JFrame();
        mainPanel = new JPanel();
        buttonPanel = new JPanel();
        consolePanel = new JPanel();

        frame.setTitle("UBC Air Ticketing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.setVisible(true);

        mainPanel.setBackground(new Color(252, 198, 23));
        mainPanel.setBounds(0, 0, 800, 200);
        mainPanel.setLayout(new BorderLayout());

        buttonPanel.setBounds(0, 200, 800, 300);
        buttonPanel.setLayout(new GridLayout(3, 2));

        consolePanel.setBounds(0, 500, 800, 255);
        consolePanel.setLayout(new BorderLayout());

        consoleText = new JTextArea();
        consoleText.setFont(new Font("Century", Font.PLAIN, 20));
        consoleText.setLayout(null);
        consoleText.setEditable(false);

    }

    // EFFECTS: creates a label for the main frame
    public void setLabels() {
        label = new JLabel();
        plane = new ImageIcon("C:\\Users\\Stallon Pinto\\IdeaProjects\\project_s7f6k\\src\\main\\Images\\airplane.png");

        label.setText("Welcome to Air UBC");
        label.setIcon(plane);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setForeground(new Color(20, 21, 180));
        label.setFont(new Font("Century", Font.PLAIN, 40));
        label.setIconTextGap(20);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 0, 100, 50);
    }

    // EFFECTS: sets the buttons of the main frame
    public void setButtons() {
        button1 = new JButton("Create New Account");
        button1.setFont(new Font("Century", Font.PLAIN, 20));
        button1.addActionListener(this);

        button2 = new JButton("Load Previous Account");
        button2.setFont(new Font("Century", Font.PLAIN, 20));
        button2.addActionListener(this);

        button3 = new JButton("Save Account Information");
        button3.setFont(new Font("Century", Font.PLAIN, 20));
        button3.addActionListener(this);

        button4 = new JButton("Book a flight");
        button4.setFont(new Font("Century", Font.PLAIN, 20));
        button4.addActionListener(this);

        button5 = new JButton("View Account Details");
        button5.setFont(new Font("Century", Font.PLAIN, 20));
        button5.addActionListener(this);

        button6 = new JButton("Load Booked Flights");
        button6.setFont(new Font("Century", Font.PLAIN, 20));
        button6.addActionListener(this);
    }

    // EFFECTS: puts together the main frame with all its components
    public void makeMainFrame() {
        mainPanel.add(label);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);

        consolePanel.add(consoleText);
        frame.add(mainPanel);
        frame.add(buttonPanel);
        frame.add(consolePanel);
    }

    // EFFECTS: performs actions based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            addAccount();
        }
        if (e.getSource() == button2) {
            loadAccount();
        }
        if (e.getSource() == button3) {
            saveAccount();
        }
        if (e.getSource() == button4) {
            makeBooking();
        }
        if (e.getSource() == button5) {
            accountInfoPrint();
        }
        if (e.getSource() == button6) {
            previousFlights();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new user and adds it to a list of users
    public void addAccount() {
        JTextField username = new JTextField();
        JTextField ubcid = new JTextField();
        JTextField balance = new JTextField();

        Object[] message = {"Full Name:", username, "UBC ID:", ubcid, "Balance:", balance};
        JOptionPane.showConfirmDialog(null, message, "Create Account", JOptionPane.OK_CANCEL_OPTION);
        user = new UserAccount(username.getText(),
                Integer.parseInt(ubcid.getText()),
                Integer.parseInt(balance.getText()), 0);
        consoleText.setText("Welcome to Air UBC, " + user.getFullName());
        accountList.addAccount(user);
    }

    // EFFECTS: loads previously saved user account details using the Json framework
    public void loadAccount() {
        try {
            user = jsonReader.read();
            consoleText.setText("Welcome back, " + user.getFullName() + "!");
        } catch (IOException e) {
            consoleText.setText("Unable to load account from " + SAVE_DEST);
        }
    }

    // EFFECTS: saves user account information using the Json framework
    public void saveAccount() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            consoleText.setText("Your account has been saved to " + SAVE_DEST);
        } catch (FileNotFoundException e) {
            consoleText.setText("Unable to save account to " + SAVE_DEST);
        }
    }

    // EFFECTS: checks which flights fly to required destination
    public void makeBooking() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Toronto");
        model.addElement("Edmonton");
        model.addElement("Halifax");
        model.addElement("Calgary");
        model.addElement("Montreal");
        model.addElement("Victoria");
        JComboBox dest = new JComboBox(model);

        JOptionPane.showConfirmDialog(null, dest, "Choose your final destination",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String spec = String.valueOf(dest.getSelectedItem());
        specdest = airlineList.checkDestination(spec, airlineList.getAirplanelist());
        createBookingFrame();
        setBookingFrame(spec, specdest);
    }

    // EFFECTS: if flights to destination are available, displays flights; else go back to booking options
    public void createBookingFrame() {
        bookingFrame = new JFrame();
        flightDisplay = new JPanel();
        displayText = new JTextArea();

        bookingFrame.setTitle("Flight Booking");
        bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookingFrame.setSize(500, 400);
        bookingFrame.setLayout(null);
        bookingFrame.setVisible(true);

        flightDisplay.setBounds(0, 0, 500, 400);
        flightDisplay.setLayout(new BorderLayout());
        displayText.setFont(new Font("Century", Font.PLAIN, 12));
        displayText.setLayout(new GridLayout(1, 3));
        displayText.setEditable(false);
    }

    // EFFECTS: creates booking frame
    public void setBookingFrame(String dest, ArrayList<Airplane> specdest) {
        displayText.append("Here are our available flights to " + dest);
        displayText.append("\n ");
        for (Airplane p : specdest) {
            displayText.append("\nFlight Name: " + p.getFlightName());
            displayText.append("\nDestination: " + p.getDestination());
            displayText.append("\nTime: " + p.getTime());
            displayText.append("\nEconomy Cost: " + p.getEconomy());
            displayText.append("\nBusiness Cost: " + p.getBusiness());
            displayText.append("\nFirst Cost: " + p.getFirst());
            displayText.append("\nBaggage Cost: " + p.getBagCost());
            displayText.append("\n ");
        }

        flightDisplay.add(displayText);
        bookingFrame.add(flightDisplay);

        chooseFlightPopUp();
    }

    // EFFECTS: creates pop up to confirm flight name
    public void chooseFlightPopUp() {
        JTextField flight = new JTextField();

        Object[] message = {"Enter Flight Name of your Preference:", flight};
        JOptionPane.showConfirmDialog(null, message, "Pick Flight", JOptionPane.OK_CANCEL_OPTION);
        String chosen = flight.getText();
        int temp = 0;
        for (Airplane p : specdest) {
            if (chosen.equals(p.getFlightName())) {
                temp = 1;
                chooseClassAndBaggagePopUp(p);
            }
        }
        if (temp == 0) {
            displayText.append("\n ");
            displayText.append("\nPlease enter a valid Flight Name");
            chooseFlightPopUp();
        }
    }

    // EFFECTS: confirms class and number of bags
    public void chooseClassAndBaggagePopUp(Airplane p) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Economy");
        model.addElement("Business");
        model.addElement("First");
        JComboBox seat = new JComboBox(model);
        JTextField bag = new JTextField();

        Object[] message = {"Class :", seat, "Number of bags:", bag};
        JOptionPane.showConfirmDialog(null, message, "Choose your class and baggage preference :",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String spec = String.valueOf(seat.getSelectedItem());
        int bags = Integer.parseInt(bag.getText());
        makeCost(spec, bags, p);
        displayText.setFont(new Font("Century", Font.PLAIN, 20));
        displayText.setText("");
        displayText.append("Your total cost is: " + user.getCost());
        confirmFlightAndPay(p);
    }

    // REQUIRES: seat must be first, business or economy
    // MODIFIES: this
    // EFFECTS: adds plane seat's cost to user's costs based on seat choice
    public void makeCost(String seat, int b, Airplane plane) {
        if (seat.equals("First")) {
            user.addCost(plane.getFirst());
        } else if (seat.equals("Business")) {
            user.addCost(plane.getBusiness());
        } else if (seat.equals("Economy")) {
            user.addCost(plane.getEconomy());
        }
        user.addCost(b * plane.getBagCost());
    }

    // EFFECTS: confirms tickets if "yes", else goes o main frame
    public void confirmFlightAndPay(Airplane p) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("yes");
        model.addElement("no");
        JComboBox pay = new JComboBox(model);

        JOptionPane.showConfirmDialog(null, pay, "Would you like to confirm your flight?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String dec = String.valueOf(pay.getSelectedItem());
        if (dec.equals("yes")) {
            if (user.checkPayment()) {
                endPayment(p);
            } else {
                addMoney(p);
            }
        } else {
            user.setCost(0);
            bookingFrame.dispose();
        }
    }

    // EFFECTS: makes payment and deducts balance from account
    private void endPayment(Airplane p) {
        user.makePayment();
        bookingFrame.dispose();
        consoleText.setText("");
        consoleText.append("Your booking for flight " + p.getFlightName() + " to " + p.getDestination()
                + " has been confirmed!");
        consoleText.append("\n");
        System.out.println("\nYour remaining balance is " + user.getBalance());
        user.addBookedAirplane(p);
        consoleText.append("\n");
        consoleText.append("\nThank you for Choosing Air UBC!");

    }

    // EFFECTS: adds balance to your account if it is insufficient to make payment
    public void addMoney(Airplane p) {
        displayText.append("Sorry, you don't have sufficient funds. :(");
        displayText.append("\n");
        displayText.append("\nYour balance is " + user.getBalance());
        displayText.append("\nYour cost is " + user.getCost());

        JTextField balance = new JTextField("Add balance");

        Object[] message = {" Your current balance is " + user.getBalance(), balance};
        JOptionPane.showConfirmDialog(null, message, "Add balance", JOptionPane.OK_CANCEL_OPTION);
        user.addBalance(Integer.parseInt(balance.getText()));
        confirmFlightAndPay(p);
    }

    // EFFECTS: prints user's information
    public void accountInfoPrint() {
        consoleText.setText("");
        consoleText.append("Account Name: " + user.getFullName());
        consoleText.append("\nUBC ID: " + user.getIdNumber());
        consoleText.append("\nAccount Name: " + user.getBalance());
    }

    // EFFECTS: prints previously booked flight of users
    public void previousFlights() {
        consoleText.setText("");
        for (Airplane p : user.getBooked()) {
            consoleText.append("Flight name: " + p.getFlightName());
            consoleText.append("\nDestination: " + p.getDestination());
            consoleText.append("\nTime: " + p.getTime());
            consoleText.append("\n");
        }
    }
}
