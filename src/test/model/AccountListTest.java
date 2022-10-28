package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountListTest {

    AccountList l;

    @BeforeEach
    public void setUp() {
        l = new AccountList();
    }

    @Test
    public void testConstructor() {
        assertEquals(0,l.sizeAccountList());
    }

}
