package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountTest {

    UserAccount ua;

    @BeforeEach
    public void setUp() {
        ArrayList<Airplane> booked = new ArrayList<>();
        ua = new UserAccount("Stallon Pinto", 88077979, 1000,0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Stallon Pinto", ua.getFullName());
        assertEquals(88077979, ua.getIdNumber());
        assertEquals(1000, ua.getBalance());
        assertEquals(0, ua.getCost());
    }

    @Test
    public void testAddBalance() {
        ua.addBalance(500);
        assertEquals(1500, ua.getBalance());
    }

    @Test
    public void testAddBalanceMultipleTimes() {
        ua.addBalance(700);
        assertEquals(1700, ua.getBalance());
        ua.addBalance(200);
        assertEquals(1900, ua.getBalance());
    }

    @Test
    public void testAddCost() {
        ua.addCost(500);
        assertEquals(500, ua.getCost());
    }

    @Test
    public void testAddCostMultipleTimes() {
        ua.addCost(700);
        assertEquals(700, ua.getCost());
        ua.addCost(200);
        assertEquals(900, ua.getCost());
    }

    @Test
    public void testMakePayment() {
        ua.addCost(800);
        assertTrue(ua.makePayment());
        assertEquals(200, ua.getBalance());
        assertEquals(0, ua.getCost());
    }

    @Test
    public void testMakePaymentMultipleTimes() {
        ua.addCost(800);
        assertTrue(ua.makePayment());
        assertEquals(200, ua.getBalance());
        assertEquals(0, ua.getCost());
        ua.addCost(500);
        assertFalse(ua.makePayment());
        assertEquals(200, ua.getBalance());
        assertEquals(500, ua.getCost());
        ua.addBalance(400);
        assertTrue(ua.makePayment());
        assertEquals(100, ua.getBalance());
        assertEquals(0, ua.getCost());
    }

    @Test
    public void testMakePaymentExactAmount() {
        ua.addCost(1000);
        assertTrue(ua.makePayment());
        assertEquals(0, ua.getBalance());
        assertEquals(0, ua.getCost());
    }

    @Test
    public void testCheckSuccessfulPayment() {
        ua.addCost(950);
        assertTrue(ua.checkPayment());
    }

    @Test
    public void testCheckFailedPayment() {
        ua.addCost(1950);
        assertFalse(ua.checkPayment());
    }

    @Test
    public void testCheckPaymentExactAmount() {
        ua.addCost(1000);
        assertTrue(ua.checkPayment());
    }

    @Test
    public void testCheckMultiplePayment() {
        ua.addCost(500);
        assertTrue(ua.checkPayment());
        ua.addCost(250);
        assertTrue(ua.checkPayment());
        ua.addCost(1000);
        assertFalse(ua.checkPayment());
    }

    @Test
    public void testBaggageCost() {
        Airplane air = new Airplane("stat200","Calgary","1100hrs",600,1400, 4000,200);
        assertEquals(200, ua.baggageCost(1,air));
        ua.setCost(0);
        assertEquals(600, ua.baggageCost(3,air));
        ua.setCost(0);
        assertEquals(0, ua.baggageCost(0,air));
    }

    @Test
    public void testAddBookedAirplane() {
        Airplane air = new Airplane("stat200","Calgary","1100hrs",600,1400, 4000,200);
        ua.addBookedAirplane(air);
        assertEquals(1, ua.getBooked().size());
        assertTrue(ua.getBooked().contains(air));
    }

    @Test
    public void testAddMultipleBookedAirplanes() {
        Airplane air1 = new Airplane("stat200","Calgary","1100hrs",600,1400, 4000,200);
        Airplane air2 = new Airplane("stat201","Edmonton","1100hrs",600,1400, 4000,200);
        ua.addBookedAirplane(air1);
        assertEquals(1, ua.getBooked().size());
        assertTrue(ua.getBooked().contains(air1));
        assertFalse(ua.getBooked().contains(air2));
        ua.addBookedAirplane(air2);
        assertEquals(2,ua.getBooked().size());
        assertTrue(ua.getBooked().contains(air1));
        assertTrue(ua.getBooked().contains(air2));
    }

    public void testAddSameBookedAirplanesMultipleTimes() {
        Airplane air1 = new Airplane("stat200","Calgary","1100hrs",600,1400, 4000,200);
        ua.addBookedAirplane(air1);
        assertEquals(1, ua.getBooked().size());
        assertTrue(ua.getBooked().contains(air1));
        ua.addBookedAirplane(air1);
        assertEquals(2,ua.getBooked().size());
        assertTrue(ua.getBooked().contains(air1));
    }


}
