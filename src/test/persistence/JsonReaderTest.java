package persistence;

import model.Airplane;
import model.UserAccount;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderDoesNotExist() {
        JsonReader reader = new JsonReader("./data/doesNotExist.json");
        try {
            UserAccount ua = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/TestJsonReaderNoPlane.json");
        try {
            UserAccount ua = reader.read();
            assertEquals("Stallon Pinto", ua.getFullName());
            assertEquals(88077979, ua.getIdNumber());
            assertEquals(1000, ua.getBalance());
            assertEquals(0, ua.getCost());
            assertEquals(0, ua.getBooked().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderUserWithPlanes() {
        JsonReader reader = new JsonReader("./data/TestJsonReader.json");
        try {
            UserAccount ua = reader.read();
            assertEquals("Stallon Pinto", ua.getFullName());
            assertEquals(88077979,ua.getIdNumber());
            assertEquals(8000, ua.getBalance());
            assertEquals(0, ua.getCost());
            assertEquals(2, ua.getBooked().size());
            ArrayList<Airplane> planes = ua.getBooked();
            checkAirplane("atsc113","Victoria", "1100hrs", 115,375, 1000, 50, planes.get(0));
            checkAirplane("atsc113","Victoria", "1100hrs", 115,375, 1000, 50, planes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}