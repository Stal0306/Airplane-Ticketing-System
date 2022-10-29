package persistence;

import model.Airplane;
import model.UserAccount;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            UserAccount ua = new UserAccount("Stallon Pinto", 88077979, 1000,0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterUserNoPlane() {
        try {
            UserAccount ua = new UserAccount("Abdulla Sayyed", 55555555, 2000,0);
            JsonWriter writer = new JsonWriter("./data/TestJsonWriterNoPlane.json");
            writer.open();
            writer.write(ua);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestJsonWriterNoPlane.json");
            ua = reader.read();
            assertEquals("Abdulla Sayyed", ua.getFullName());
            assertEquals(55555555, ua.getIdNumber());
            assertEquals(2000, ua.getBalance());
            assertEquals(0, ua.getCost());
            assertEquals(0, ua.getBooked().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterUserAccount() {
        try {
            UserAccount ua = new UserAccount("Abu Fazl", 92929292, 2000, 0);
            ua.addBookedAirplane(new Airplane("scie113","Mangalore","200hrs",1350,5200,12000,700));
            ua.addBookedAirplane(new Airplane("math110","Winnipeg","2200hrs",350,2150,7000,200));
            JsonWriter writer = new JsonWriter("./data/TestJsonWriter.json");
            writer.open();
            writer.write(ua);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestJsonWriter.json");
            ua = reader.read();
            assertEquals("Abu Fazl", ua.getFullName());
            assertEquals(92929292,ua.getIdNumber());
            assertEquals(2000, ua.getBalance());
            assertEquals(0, ua.getCost());
            assertEquals(2, ua.getBooked().size());
            ArrayList<Airplane> planes = ua.getBooked();
            checkAirplane("scie113","Mangalore", "200hrs", 1350,5200, 12000, 700, planes.get(0));
            checkAirplane("math110","Winnipeg", "2200hrs", 350,2150, 7000, 200, planes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
