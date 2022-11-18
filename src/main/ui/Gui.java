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
    private JPanel pickFlight;
    private JTextArea displayText;
    private JTextField flightName;


    public Gui() {
        initializeObjects();
        airlineList.airlineOptions();
        setFramesAndPanels();
        setLabels();
        setButtons();
        makeMainFrame();
    }

    public void initializeObjects() {
        specdest = new ArrayList<Airplane>();
        airlineList = new AirplaneList();
        accountList = new AccountList();
        jsonWriter = new JsonWriter(SAVE_DEST);
        jsonReader = new JsonReader(SAVE_DEST);
    }

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

    public void loadAccount() {
        try {
            user = jsonReader.read();
            consoleText.setText("Welcome back, " + user.getFullName() + "!");
        } catch (IOException e) {
            consoleText.setText("Unable to load account from " + SAVE_DEST);
        }
    }

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
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        String spec =  String.valueOf(dest.getSelectedItem());
        specdest = airlineList.checkDestination(spec, airlineList.getAirplanelist());
        createBookingFrame(spec, specdest);

    }

    // EFFECTS: if flights to destination are available, displays flights; else go back to booking options
    public void createBookingFrame(String dest, ArrayList<Airplane> specdest) {
        bookingFrame = new JFrame();
        flightDisplay = new JPanel();
        pickFlight = new JPanel();
        displayText = new JTextArea();
        flightName = new JTextField("<Input the flight name of your preference here>");

        bookingFrame.setTitle("Flight Booking");
        bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookingFrame.setSize(500, 500);
        bookingFrame.setLayout(null);
        bookingFrame.setVisible(true);

        flightDisplay.setBounds(0, 0, 500, 200);
        flightDisplay.setLayout(new BorderLayout());
        displayText.setFont(new Font("Century", Font.PLAIN, 20));
        displayText.setLayout(null);
        displayText.setEditable(false);

        pickFlight.setBounds(0, 200, 500, 200);
        pickFlight.setLayout(new BorderLayout());
        flightName.setBounds(0,0,100,20);

        setBookingFrame();
    }

    public void setBookingFrame() {
        flightDisplay.add(displayText);
        pickFlight.add(flightName);
        bookingFrame.add(flightDisplay);
        bookingFrame.add(pickFlight);
    }

    public void accountInfoPrint() {
        consoleText.setText("");
        consoleText.append("Account Name: " + user.getFullName());
        consoleText.append("\nUBC ID: " + user.getIdNumber());
        consoleText.append("\nAccount Name: " + user.getBalance());
    }

    public void previousFlights() {
        consoleText.setText("");
        for (Airplane p: user.getBooked()) {
            consoleText.append("Flight name: " + p.getFlightName());
            consoleText.append("Destination: " + p.getDestination());
            consoleText.append("Time: " + p.getTime());
            consoleText.append("     ");
        }
    }
}
