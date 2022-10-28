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

    @Test
    public void testSizeAccountList() {
        assertEquals(0, l.sizeAccountList());
        UserAccount u = new UserAccount("Stallon", 88077979, 0, 0);
        l.addAccount(u);
        assertEquals(1,l.sizeAccountList());
    }

    @Test
    public void testAddAccount() {
        UserAccount u = new UserAccount("Stallon", 88077979, 0, 0);
        l.addAccount(u);
        assertEquals(1,l.sizeAccountList());
        l.addAccount(u);
        assertEquals(1,l.sizeAccountList());
    }
}
