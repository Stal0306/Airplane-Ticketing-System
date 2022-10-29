package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Airplane;
import model.UserAccount;
import org.json.*;

// Represents a reader that reads the user account from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserAccount read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private UserAccount parseUserAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int idnum = jsonObject.getInt("idnumber");
        int balance = jsonObject.getInt("balance");
        int cost = jsonObject.getInt("cost");
        UserAccount ua = new UserAccount(name, idnum, balance, cost);
        addPlanes(ua, jsonObject);
        return ua;
    }

    // MODIFIES: ua
    // EFFECTS: parses airplanes from JSON object and adds them to user account
    private void addPlanes(UserAccount ua, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("booked");
        for (Object json : jsonArray) {
            JSONObject nextPlane = (JSONObject) json;
            addPlane(ua, nextPlane);
        }
    }

    // MODIFIES: ua
    // EFFECTS: parses plane from JSON object and adds it to user account
    private void addPlane(UserAccount ua, JSONObject jsonObject) {
        String flightname = jsonObject.getString("flightname");
        String destination = jsonObject.getString("destination");
        String time = jsonObject.getString("time");
        int economy = jsonObject.getInt("economy");
        int business = jsonObject.getInt("business");
        int first = jsonObject.getInt("first");
        int bagcost = jsonObject.getInt("bagcost");
        Airplane plane = new Airplane(flightname,destination,time,economy,business,first,bagcost);
        ua.addBookedAirplane(plane);
    }
}
