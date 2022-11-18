package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// initializes the ticketing system
public class Main {
    public static void main(String[] args) {
        try {
            new Gui();
            new TicketingSystem();
        } catch (FileNotFoundException e) {
            System.out.println("Cant run application- no file found.");

        }
    }
}
